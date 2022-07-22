package io.github.itkova.command

import io.github.itkova.Main
import io.github.itkova.wand.FireWand
import io.github.itkova.wand.WaterGun
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import java.util.*

class EngineMainCommand(main: Main) : CommandExecutor, TabCompleter {
    private val m = main
    @Suppress("DEPRECATION")
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val p = sender as Player
        if (args.isNotEmpty() && p.isOp) {
            when (args[0]) {
                "item" -> {
                    when (args[1]) {
                        "fire_wand" -> {
                            p.inventory.addItem(FireWand(m = m).item)
                        }
                        "water_gun" -> {
                            p.inventory.addItem(WaterGun(m = m).item)
                        }
                    }
                }
            }
        }
        return true
    }
    @Suppress("DEPRECATED_IDENTITY_EQUALS")
    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): MutableList<String>? {
        if (alias.equals("eg", true)) {
            if (args.size === 1) {
                var returns1: MutableList<String> = arrayListOf("item")
                var returns2: MutableList<String> = ArrayList()
                for (returns in returns1) {
                    if (returns.lowercase(Locale.getDefault()).startsWith(args[0].lowercase(Locale.getDefault()))) {
                        returns2.add(returns)
                    }
                }
                return returns2
            } else if (args.size === 2 && args[0] == "item") {
                var returns3: MutableList<String> = arrayListOf("fire_wand","water_gun")
                val returns4: MutableList<String> = ArrayList()
                for (returns in returns3) {
                    if (returns.lowercase(Locale.getDefault()).startsWith(args[1].lowercase(Locale.getDefault()))) {
                        returns4.add(returns)
                    }
                }
                return returns4
            } else {
                return mutableListOf("")
            }
        } else return null
    }
}