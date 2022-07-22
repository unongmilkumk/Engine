package io.github.itkova.wand

import io.github.itkova.Main
import io.github.itkova.api.eItemStack
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

class FireWand(private val m: Main) : Listener {
    val item = eItemStack(Material.STICK, "${ChatColor.RED}불 완드", "${ChatColor.DARK_RED}불을 사용하는 지팡이다", " ", "${ChatColor.GREEN}[ 1급 지팡이 ]")
    @EventHandler
    fun sunDustInteractEvent(e: PlayerInteractEvent) {
        val p = e.player
        if (p.inventory.itemInMainHand == item && p.getCooldown(item.type) == 0) {
            if (e.action.isRightClick) {
                e.isCancelled = true
                WandAPI().wandItem(p, item.type,Material.REDSTONE,50, 20.0, main = m, "fire")
            }
        }
    }
}