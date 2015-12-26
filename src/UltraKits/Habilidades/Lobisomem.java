package UltraKits.Habilidades;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import UltraKits.Main;

public class Lobisomem
  implements Listener
{
  public static HashMap<String, Long> cooldown = new HashMap();
  
  @EventHandler
  public void interact(PlayerInteractEvent e)
  {
    Player p = e.getPlayer();
    if (((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) && 
      (Main.lobisomem.contains(p.getName())) && 
      (p.getItemInHand().getType() == Material.MONSTER_EGG)) {
      if ((!cooldown.containsKey(p.getName())) || (((Long)cooldown.get(p.getName())).longValue() <= System.currentTimeMillis()))
      {
        e.setCancelled(true);
        p.updateInventory();
        if (Main.areaPvP(p))
        {
          Wolf w = (Wolf)p.getWorld().spawnEntity(new Location(p.getWorld(), p.getLocation().getX() + 2.0D, p.getLocation().getY(), p.getLocation().getZ()), EntityType.WOLF);
          Wolf w2 = (Wolf)p.getWorld().spawnEntity(new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ() + 2.0D), EntityType.WOLF);
          Wolf w3 = (Wolf)p.getWorld().spawnEntity(new Location(p.getWorld(), p.getLocation().getX() + 2.0D, p.getLocation().getY(), p.getLocation().getZ() + 2.0D), EntityType.WOLF);
          w.setAngry(true);
          w.setOwner(p);
          w2.setAngry(true);
          w2.setOwner(p);
          w3.setAngry(true);
          w3.setOwner(p);
          List<Entity> nearby = p.getNearbyEntities(5.0D, 5.0D, 5.0D);
          for (Entity en : nearby) {
            if ((en instanceof Player))
            {
              Player s = (Player)en;
              w.setTarget(s);
              w2.setTarget(s);
              w3.setTarget(s);
            }
          }
          p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 500, 0));
          p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 500, 0));
          p.playSound(p.getLocation(), Sound.WOLF_HOWL, 1.0F, 1.0F);
          cooldown.put(p.getName(), Long.valueOf(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(40L)));
          return;
        }
        p.sendMessage(ChatColor.RED + "Voce pode usar esta habilidade apenas em area com PVP.");
      }
      else
      {
        p.sendMessage(ChatColor.RED + "Faltam " + TimeUnit.MILLISECONDS.toSeconds(((Long)cooldown.get(p.getName())).longValue() - System.currentTimeMillis()) + " segundos para poder usar novamente.");
      }
    }
  }
}
