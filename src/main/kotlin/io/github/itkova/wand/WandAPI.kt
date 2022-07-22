package io.github.itkova.wand

import io.github.itkova.Main
import io.github.itkova.api.randompercent
import org.bukkit.*
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.util.EulerAngle

class WandAPI{
    @Suppress("DEPRECATION")
    fun wandItem(player: Player, toolMaterial: Material, material: Material, cooldown: Int, damage: Double, main: Main, magic: String) {
        if (player.getCooldown(toolMaterial) == 0) {
            player.setCooldown(toolMaterial, cooldown)

            val amo = player.world.spawnEntity(player.location.add(0.0, 0.5, 0.0), EntityType.ARMOR_STAND) as ArmorStand

            amo.setArms(true)
            amo.setGravity(false)
            amo.isVisible = false
            amo.isSmall = true
            amo.isMarker = true
            amo.setItemInHand(ItemStack(material))
            amo.rightArmPose = EulerAngle(Math.toRadians(45.0), Math.toRadians(0.0), Math.toRadians(0.0))

            val v = player.location.add(player.location.direction.multiply(10)).subtract(player.location).toVector()

            object : BukkitRunnable() {
                var d = 50
                var i = 0

                override fun run() {

                    amo.teleport(amo.location.add(v.normalize()))

                    if (amo.getTargetBlockExact(1) != null && !amo.getTargetBlockExact(1)!!.isPassable) {
                        if (!amo.isDead) {
                            amo.remove()
                        }
                        cancel()
                    }

                    for (e in amo.location.chunk.entities) {
                        if (!amo.isDead) {
                            if (amo.location.distanceSquared(e.location) <= 1.0) {
                                if (e != player && e != amo) {
                                    if (e is LivingEntity) {
                                        e.damage(damage, player)
                                        if (magic == "fire") {
                                            e.fireTicks = 200
                                        }
                                        if (magic == "waterGun") {
                                            if (randompercent(1)) {
                                                e.damage(200.0 - damage, player)
                                                e.sendMessage("${ChatColor.AQUA}1%의 확률로 당신은 200데미지를 맞았습니다.")
                                                player.sendMessage("${ChatColor.AQUA}1%의 확률로 당신은 200데미지를 주었습니다.")
                                            }
                                        }
                                        amo.remove()
                                        cancel()
                                    }
                                }
                            }
                        }
                    }

                    if (i > d) {
                        if (!amo.isDead) {
                            amo.remove()
                            cancel()
                        }
                    }

                    i++
                }
            }.runTaskTimer(main, 0L, 1L)
        }
    }
}