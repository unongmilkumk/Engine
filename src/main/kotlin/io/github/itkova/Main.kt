package io.github.itkova

import org.bukkit.ChatColor
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {

    override fun onEnable() {
        server.consoleSender.sendMessage("${ChatColor.GREEN}플러그인이 활성화되었습니다.")
    }

    override fun onDisable() {
        server.consoleSender.sendMessage("${ChatColor.RED}플러그인이 비활성화되었습니다.")
    }

}