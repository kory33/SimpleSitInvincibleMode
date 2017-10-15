package com.github.kory33.simplesitinvinciblemode

import net.apcat.simplesit.SimpleSitPlayer
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.plugin.java.JavaPlugin

class SimpleSitInvincibleMode : JavaPlugin(), Listener {
    override fun onEnable() {
        super.onEnable()
        server.pluginManager.registerEvents(this, this)
    }

    override fun onDisable() {
        super.onDisable()
        HandlerList.unregisterAll(this as JavaPlugin)
    }

    @EventHandler
    fun onPlayerDamage(event : EntityDamageEvent) {
        val damagedPlayer = event.entity as? Player ?: return

        val simpleSitPlayer = SimpleSitPlayer(damagedPlayer)

        if (simpleSitPlayer.isLaying or simpleSitPlayer.isSitting) {
            event.isCancelled = true
        }
    }
}