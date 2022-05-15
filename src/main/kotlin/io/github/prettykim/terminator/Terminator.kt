package io.github.prettykim.terminator

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.Arrow
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.plugin.java.JavaPlugin

class Terminator : JavaPlugin(), Listener {
    override fun onEnable() {
        server.pluginManager.registerEvents(this, this)
    }

    @EventHandler
    fun shootArrows(event: PlayerInteractEvent) {
        if (
            ((event.action == Action.RIGHT_CLICK_AIR) || (event.action == Action.RIGHT_CLICK_BLOCK))
            && (event.item?.type == Material.BOW)
        ) {
            val player = event.player
            val playerLocation = player.eyeLocation.clone()

            val armorStand = player.world.spawn(playerLocation, ArmorStand::class.java)
            armorStand.isMarker = true
            armorStand.isVisible = false

            val defaultYaw = armorStand.eyeLocation.yaw
            val location = Location(
                player.world,
                playerLocation.x,
                playerLocation.y,
                playerLocation.z,
                defaultYaw,
                playerLocation.pitch
            )

            for (i in -1..1) {
                location.yaw = defaultYaw + (10.0f * i)

                armorStand.teleport(location)
                armorStand.launchProjectile(Arrow::class.java)
            }

            armorStand.remove()
        }
    }
}
