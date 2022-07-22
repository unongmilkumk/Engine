package io.github.itkova.wand

import io.github.itkova.Main
import io.github.itkova.api.eItemStack
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

class WaterGun(private val m: Main) : Listener {
    val item = eItemStack(Material.DIAMOND_HORSE_ARMOR, "${ChatColor.BLUE}물총", "${ChatColor.AQUA}놀랍게도 지팡이다.", " ", "${ChatColor.GREEN}[ 이벤트 지팡이 ]")
    @EventHandler
    fun sunDustInteractEvent(e: PlayerInteractEvent) {
        val p = e.player
        if (p.inventory.itemInMainHand == item && p.getCooldown(item.type) == 0) {
            if (e.action.isRightClick) {
                e.isCancelled = true
                WandAPI().wandItem(p, item.type,Material.BLUE_DYE,10, 2.0, main = m, "waterGun")
            }
        }
    }
}