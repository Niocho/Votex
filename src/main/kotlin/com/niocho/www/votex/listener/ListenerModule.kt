package com.niocho.www.votex.listener

import com.niocho.www.votex.Votex
import com.niocho.www.votex.listener.impl.PlayerMoveListener
import org.bukkit.event.player.PlayerMoveEvent

object ListenerModule : com.niocho.www.votex.Module{
    override fun onEnable() {
        Votex.plugin?.let {
            it.logger.info("enabling listener module")
            it.server.pluginManager.registerEvents(PlayerMoveListener(), it)
            it.logger.info("listener module finished enable")
        } ?: println("Votex: critical problem: cannot find plugin")
    }

    override fun onDisable() {
        Votex.plugin?.let {
            PlayerMoveEvent.getHandlerList().unregister(it)
        }
    }
}