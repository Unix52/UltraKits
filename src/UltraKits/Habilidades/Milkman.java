package UltraKits.Habilidades;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import UltraKits.Main;

public class Milkman
  implements Listener
{
  private transient HashMap<ItemStack, Integer> cooldown = new HashMap();
  public int maxUses = 5;
  public String milkbucketName = "Milkman's Bucket";
  public String[] potionEffects = { "REGENERATION 900 0", "FIRE_RESISTANCE 900 0", "SPEED 900 0" };
  
  @EventHandler
  public void onConsume(PlayerItemConsumeEvent event)
  {
    ItemStack item = event.getItem();
    Player p = event.getPlayer();
    if (Main.milkman.contains(p.getName()))
    {
      for (String string : this.potionEffects)
      {
        String[] effect = string.split(" ");
        PotionEffect potionEffect = new PotionEffect(PotionEffectType.getByName(effect[0].toUpperCase()), 
          Integer.parseInt(effect[1]), Integer.parseInt(effect[2]));
        p.addPotionEffect(potionEffect, true);
      }
      if (!this.cooldown.containsKey(item)) {
        this.cooldown.put(item, Integer.valueOf(0));
      }
      this.cooldown.put(item, Integer.valueOf(((Integer)this.cooldown.get(item)).intValue() + 1));
      if (((Integer)this.cooldown.get(item)).intValue() == this.maxUses)
      {
        this.cooldown.remove(item);
        event.setCancelled(true);
        p.setItemInHand(new ItemStack(Material.BUCKET, item.getAmount(), item.getDurability()));
      }
      else
      {
        event.setCancelled(true);
      }
    }
  }
}
