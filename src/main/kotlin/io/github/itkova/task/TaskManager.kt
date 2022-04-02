package io.github.itkova.task

import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitRunnable

class TaskManager(val task: (() -> Unit) -> Unit): BukkitRunnable() {
    override fun run() {

        task { cancel() }

    }

    fun runTaskTimer(plugin: Plugin?, delay: Long, period: Long){
        this.runTaskTimer(plugin!!, delay, period)
    }
    fun runTaskLater(plugin: Plugin?, delay: Long){
        this.runTaskLater(plugin, delay)
    }
}

fun runTaskTimer(plugin: Plugin?, delay: Long, period: Long, task: (() -> Unit) -> Unit){
    TaskManager{task{it()}}.runTaskTimer(plugin, delay, period)
}

fun runTaskLater(plugin: Plugin?, delay: Long, task: (() -> Unit) -> Unit){
    TaskManager{task{it()}}.runTaskLater(plugin, delay)
}
