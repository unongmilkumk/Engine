package io.github.itkova

import io.github.itkova.command.EngineMainCommand
import io.github.itkova.command.Mention
import io.github.itkova.event.Chat
import io.github.itkova.wand.FireWand
import io.github.itkova.wand.WaterGun
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.plugin.java.JavaPlugin
import java.util.*
import kotlin.collections.HashMap
import kotlin.math.round

val mana = HashMap<UUID, Int>()

class Main : JavaPlugin() {

    override fun onEnable() {
        logger.info("${ChatColor.GREEN}플러그인이 활성화되었습니다.")
        server.pluginManager.registerEvents(Chat(), this@Main)
        server.pluginManager.registerEvents(FireWand(m = this@Main), this@Main)
        server.pluginManager.registerEvents(WaterGun(m = this@Main), this@Main)
        server.getPluginCommand("mention")!!.setExecutor(Mention())
        server.getPluginCommand("eg")!!.setExecutor(EngineMainCommand(this@Main))
        server.getPluginCommand("eg")!!.tabCompleter = EngineMainCommand(this@Main)

        for (i in Bukkit.getOnlinePlayers()) {
            if (mana[i.uniqueId] == null) {
                mana[i.uniqueId] = 100
            }
        }

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this@Main, {
            for (i in Bukkit.getOnlinePlayers()) {
                i.sendActionBar(Component.text("${ChatColor.RED}♡${round(i.health).toInt()} ${ChatColor.AQUA}✏${mana[i.uniqueId]}"))
            }
        }, 40, 0)
    }

    override fun onDisable() {
        logger.info("${ChatColor.RED}플러그인이 비활성화되었습니다.")
    }

}