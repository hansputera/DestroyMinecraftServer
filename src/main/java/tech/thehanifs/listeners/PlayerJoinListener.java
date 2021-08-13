package tech.thehanifs.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;
import tech.thehanifs.Main;

import java.util.logging.Logger;

public class PlayerJoinListener implements Listener {
    private final Main main;
    private static final Logger logger = Logger.getLogger("PlayerJoinEvent");
    public PlayerJoinListener(Main main) {
        this.main = main;
        main.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        logger.info("Player has joined: " + player.getDisplayName());
        player.sendMessage("Are you ready?");
        int delay = this.main.configuration.getInt("delay") * 20;
        Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin) this, () -> {
            for (int index = 0; index < index+1; index++) {
                TNTPrimed tnt = (TNTPrimed) player.getWorld().spawnEntity(
                        player.getLocation().add(0, 1, 0), EntityType.PRIMED_TNT
                );
            }
            PlayerInventory inventory = player.getInventory();

            // Inventory set
            ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET, 1);
            ItemStack legs = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
            ItemStack boot = new ItemStack(Material.DIAMOND_BOOTS, 1);
            ItemStack chestPlate = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);

            inventory.setHelmet(helmet);
            inventory.setChestplate(chestPlate);
            inventory.setLeggings(legs);
            inventory.setBoots(boot);
            player.setHealth(player.getHealth() + 20);
        }, delay, delay);
    }
}
