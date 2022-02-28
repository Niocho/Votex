package com.niocho.www.votex.listener

import com.niocho.www.votex.Launch
import com.niocho.www.votex.Priority
import com.niocho.www.votex.Votex
import com.niocho.www.votex.listener.impl.PlayerMoveListener
import org.bukkit.event.player.PlayerMoveEvent

@Launch(Priority.COMMON)
class ListenerModule : com.niocho.www.votex.Module{
    override fun onEnable() {
        Votex.plugin.let {
            it.logger.info("enabling listener module")
            it.server.pluginManager.registerEvents(PlayerMoveListener(), it)
            it.logger.info("listener module finished enable")
        }
    }

    override fun onDisable() {
        Votex.plugin.let {
            it.logger.info("disabling listener module")
            PlayerMoveEvent.getHandlerList().unregister(it)
            it.logger.info("disabled listener module")
        }
    }
}