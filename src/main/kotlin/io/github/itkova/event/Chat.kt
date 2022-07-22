package io.github.itkova.event

import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerChatEvent

class Chat : Listener {
    @EventHandler
    fun itemChat(e: PlayerChatEvent) {
        e.isCancelled = true
        if (e.message == "[item]") {
            for (p in Bukkit.getOnlinePlayers()) {
                p.sendMessage(e.player.inventory.itemInMainHand.displayName())
            }
        } else {
            for (p in Bukkit.getOnlinePlayers()) {
                p.sendMessage("${e.player.name} : ${e.message}")
            }
        }
    }
}