package com.niocho.www.votex.listener.impl

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent

class PlayerMoveListener: Listener{
    @EventHandler
    fun onEvent(event: PlayerMoveEvent) {
        var player = event.player

    }
}