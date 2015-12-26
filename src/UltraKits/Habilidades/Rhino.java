package UltraKits.Habilidades;

import UltraKits.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

@SuppressWarnings("unused")
public class Rhino
  implements Listener
{
  public static HashMap<String, BukkitTask> tasks = new HashMap();
  
  @EventHandler
  public void carregar(PlayerToggleSneakEvent e)
  {
    final Player p = e.getPlayer();
    if (Main.rhino.contains(p.getName())) {
      if (p.isSneaking())
      {
        if (tasks.containsKey(p.getName()))
        {
          BukkitTask task = (BukkitTask)tasks.get(p.getName());
          task.cancel();
          tasks.remove(p.getName());
        }
        if (p.getLevel() >= 1)
        {
          p.setLevel(0);
          p.setExp(0.0F);
          List<Entity> nearby = p.getNearbyEntities(10.0D, 10.0D, 10.0D);
          for (Entity en : nearby) {
            if ((en instanceof LivingEntity))
            {
              en.setVelocity(en.getVelocity().multiply(-2));
              en.setVelocity(new Vector(en.getVelocity().getX(), 1.0D, en.getVelocity().getZ()));
              ((LivingEntity)en).damage(6.0D, p);
            }
          }
          new BukkitRunnable()
          {
            int distance = 0;
            
            public void run()
            {
              if (this.distance < 5)
              {
                p.setVelocity(p.getLocation().getDirection().multiply(2.0D));
                this.distance += 1;
              }
              else
              {
                cancel();
              }
            }
          }.runTaskTimer(Main.plugin, 0L, 5L);
          
          new BukkitRunnable()
          {
            int n = 0;
            
            public void run()
            {
              if (this.n < 4)
              {
                p.playSound(p.getLocation(), Sound.HORSE_GALLOP, 50.0F, 1.0F);
                this.n += 1;
              }
              else
              {
                cancel();
              }
            }
          }.runTaskTimer(Main.plugin, 0L, 5L);
          return;
        }
        p.setLevel(0);
        p.setExp(0.0F);
      }
      else
      {
        p.setExp(0.0F);
        BukkitTask id = new BukkitRunnable()
        {
          public void run()
          {
            if (p.getLevel() < 1)
            {
              p.giveExp(2);
              p.playSound(p.getLocation(), Sound.HORSE_SOFT, 1.0F, 1.0F);
              p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20, 0));
            }
            else
            {
              p.playSound(p.getLocation(), Sound.HORSE_IDLE, 1.0F, 1.0F);
              p.setLevel(1);
              p.giveExp(-1);
              cancel();
            }
          }
        }.runTaskTimer(Main.plugin, 0L, 20L);
        tasks.put(p.getName(), id);
      }
    }
  }
}
