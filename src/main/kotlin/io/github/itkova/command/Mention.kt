package io.github.itkova.command

import net.kyori.adventure.text.Component
import net.kyori.adventure.title.Title
import org.bukkit.Bukkit
import org.bukkit.Sound
import org.bukkit.SoundCategory
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player


class Mention : CommandExecutor{
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender is Player) {
            if (args.isEmpty() || args.size == 1) {
                sender.sendMessage("§c사용법 : /mention [플레이어] [메시지]")
            } else {
                val r = Bukkit.getPlayer(args[0])
                val message = args[1]
                if (r!!.isOnline) {
                    r.showTitle(Title.title(Component.text(" "), Component.text("§a" + sender.name + " : " + message), Title.DEFAULT_TIMES))
                    r.playSound(r.location, Sound.BLOCK_ANVIL_DESTROY, SoundCategory.MASTER, 100f, 1f)
                } else {
                    sender.sendMessage("§c존재하지 않거나 온라인이 아닌 플레이어입니다.")
                }
            }
        }
        return false
    }
}