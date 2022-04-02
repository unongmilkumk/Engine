package io.github.itkova.api

import com.mojang.authlib.GameProfile
import net.minecraft.network.protocol.game.PacketPlayOutEntityHeadRotation
import net.minecraft.network.protocol.game.PacketPlayOutNamedEntitySpawn
import net.minecraft.network.protocol.game.PacketPlayOutPlayerInfo
import net.minecraft.server.level.EntityPlayer
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.v1_18_R1.CraftServer
import org.bukkit.craftbukkit.v1_18_R1.CraftWorld
import org.bukkit.craftbukkit.v1_18_R1.entity.CraftPlayer
import org.bukkit.entity.Player
import java.util.*

class NPC {
    companion object {
        fun createNPC(player: Player, name: String, skin: String? = null) {

            val server = (player.server as CraftServer).server
            val world = (player.world as CraftWorld).handle
            val profile = GameProfile(UUID.randomUUID(), name)

            val npc = EntityPlayer(server, world, profile)

            val lc = player.location
            npc.a(lc.x, lc.y, lc.z, lc.yaw, lc.pitch)

            sendPacket(npc)

        }

        private fun sendPacket(npc: EntityPlayer) {
            for (p in Bukkit.getOnlinePlayers()) {
                val connection = (p as CraftPlayer).handle.b

                connection.a(PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.a, npc))
                connection.a(PacketPlayOutNamedEntitySpawn(npc))
                connection.a(PacketPlayOutEntityHeadRotation(npc, (npc.x * 256 / 360).toInt().toByte()))
            }
        }
    }
}