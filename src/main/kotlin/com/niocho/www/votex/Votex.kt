package com.niocho.www.votex

import com.niocho.www.votex.listener.ListenerModule
import org.bukkit.ChatColor
import org.bukkit.plugin.java.JavaPlugin
import java.lang.annotation.Inherited
import java.util.*

@Target(AnnotationTarget.CLASS)
@MustBeDocumented
@Inherited
@Retention(AnnotationRetention.RUNTIME)
annotation class Launch(val value: Priority = Priority.COMMON)

enum class Priority(val value: Int) {
    LOWEST(0),
    LOW(1),
    COMMON(2),
    HIGH(3),
    HIGHEST(4),
    MONITOR(5)
}

interface Module {
    fun onEnable()
    fun onDisable()
}

class Votex: JavaPlugin() {
    companion object {
        lateinit var plugin: Votex
        private set
    }

    init {
        plugin = this
    }
    lateinit var listenerModule: ListenerModule
    private set

    override fun onEnable() {
        server.consoleSender.sendMessage("${ChatColor.DARK_GREEN}Votex sound management plugin is initiating")
        saveDefaultConfig()
        Votex::class.java.declaredFields.filter {
            Module::class.java.isAssignableFrom(it.type) && it.type.isAnnotationPresent(Launch::class.java)
        }.sortedBy {
            it.type.getAnnotation(Launch::class.java).value.value
        }.forEach {
            val clazz = it.type
            try {
                val pluginConstructor = clazz.getConstructor(Votex::class.java)
                val obj = pluginConstructor.newInstance(this) as Module
                it.set(this, obj)
                obj.onEnable()
            } catch (e: Exception) {
                val defaultConstructor = clazz.getConstructor()
                val obj = defaultConstructor.newInstance() as Module
                it.set(this, obj)
                obj.onEnable()
            }
        }
        server.consoleSender.sendMessage("${ChatColor.GREEN}Votex sound management plugin is initiated")
    }

    override fun onDisable() {
        server.consoleSender.sendMessage("${ChatColor.DARK_PURPLE}Votex sound management plugin is initiating")
        Votex::class.java.declaredFields.filter {
            Module::class.java.isAssignableFrom(it.type) && it.type.isAnnotationPresent(Launch::class.java)
        }.sortedBy {
            it.type.getAnnotation(Launch::class.java).value.value
        }.reversed().forEach {
            Optional.ofNullable(it.get(this)).ifPresent { value ->
                (value as Module).onDisable()
            }
        }
        server.consoleSender.sendMessage("${ChatColor.LIGHT_PURPLE}Votex sound management plugin is initiating")
    }
}