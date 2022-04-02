package io.github.itkova.api

import io.github.itkova.Main
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import java.io.IOException
import java.io.InputStreamReader
import java.util.logging.Level

class DataManager {
    private var plugin: Main = TODO()
    private var dataconfig: FileConfiguration
    private var configfile: File

    fun dataManager(plugin: Main) {
        this.plugin = plugin
        saveDefaultConfig()
    }

    fun reloadConfig() {
        if (this.configfile == null) {
            this.configfile = File(this.plugin.dataFolder, "config.yml")
        }

        this.dataconfig = YamlConfiguration.loadConfiguration(this.configfile)

        var defaultStream = this.plugin.getResource("config.yml")
        if (defaultStream != null) {
            val defaultconfig: YamlConfiguration = YamlConfiguration.loadConfiguration(InputStreamReader(defaultStream))
            this.dataconfig.setDefaults(defaultconfig)
        }
    }

    fun getConfig(): FileConfiguration {
        if (this.dataconfig == null) reloadConfig()

        return this.dataconfig
    }

    fun saveConfig() {
        if (this.dataconfig == null || this.configfile == null) return

        try {
            this.getConfig().save(this.configfile)
        } catch (e: IOException) {
            this.plugin.logger.log(Level.SEVERE, "파일을 저장할 수 없습니다!", e)
        }
    }

    fun saveDefaultConfig() {
        if (this.configfile == null) this.configfile = File(this.plugin.dataFolder, "config.yml")

        if(!this.configfile.exists()) {
            this.plugin.saveResource("config.yml", false)
        }
    }
}