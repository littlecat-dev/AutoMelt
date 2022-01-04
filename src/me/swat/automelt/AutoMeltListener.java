package me.swat.automelt;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

import java.util.Iterator;

public class AutoMeltListener implements Listener {
    AutoMeltMain main;

    public AutoMeltListener(AutoMeltMain main) {
        this.main = main;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Block block = e.getBlock();
        Player player = e.getPlayer();
        String itemName = player.getInventory().getItemInMainHand().getItemMeta().getDisplayName();
        if (itemName.equals("AutoMelter")) {
            if (block.breakNaturally()) {
                ItemStack item = e.getBlock().getDrops().stream().findAny().orElse(null);
                if (item == null)
                    return;

                Iterator<Recipe> recipes = Bukkit.getServer().recipeIterator();

                while (recipes.hasNext()) {
                    Recipe rec = recipes.next();
                    if (rec instanceof FurnaceRecipe) {
                        FurnaceRecipe frec = (FurnaceRecipe) rec;
                        if (frec.getInput().getType().equals(item.getType())) {
                            int amount = item.getAmount();
                            item = frec.getResult();
                            item.setAmount(amount);

                            e.getBlock().getWorld().spawn(e.getBlock().getLocation(), ExperienceOrb.class)
                                    .setExperience(2);
                            ;
                            break;
                        }
                    }
                }
                e.setCancelled(true);
                e.getBlock().setType(Material.AIR);
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), item);
            }
        }
    }
}
