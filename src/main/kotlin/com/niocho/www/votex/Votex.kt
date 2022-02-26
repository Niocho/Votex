package com.niocho.www.votex

import com.niocho.www.votex.listener.ListenerModule
import org.bukkit.ChatColor
import org.bukkit.plugin.java.JavaPlugin

interface Module {
    fun onEnable()
    fun onDisable()
}

class Votex: JavaPlugin() {
    companion object {
        var plugin: Votex? = null
    }

    init {
        plugin = this
    }

    override fun onEnable() {
        server.consoleSender.sendMessage(ChatColor.DARK_GREEN.toString() + "Votex sound management plugin is initiating")
        saveDefaultConfig()
        server.consoleSender.sendMessage(ChatColor.GREEN.toString() + "Votex sound management plugin is initiating")

        ListenerModule.onEnable()
    }

    override fun onDisable() {
        ListenerModule.onDisable()
    }
}