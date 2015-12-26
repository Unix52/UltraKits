package UltraKits.Habilidades;

import UltraKits.Main;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;

public class Pyro
  implements Listener
{
  private Main plugin;
  
  public Pyro(Main instance)
  {
    this.plugin = instance;
  }
  
  public int cooldown = 15;
  
  @EventHandler
  public void onPlayerInteract(PlayerInteractEvent e)
  {
    final Player p = e.getPlayer();
    if ((Main.pyro.contains(p.getName())) && 
      ((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.LEFT_CLICK_AIR)) && 
      (p.getItemInHand().getType() == Material.FIREWORK_CHARGE)) {
      if (Main.reload.contains(p.getName()))
      {
        p.sendMessage(ChatColor.RED + "Recarregando!");
      }
      else if (Main.areaPvP(p))
      {
        p.playSound(p.getLocation(), Sound.EXPLODE, 1.0F, 1.0F);
        p.getEyeLocation().getWorld().playEffect(p.getLocation(), Effect.SMOKE, 5);
        p.getLocation().getWorld().playEffect(p.getLocation(), Effect.GHAST_SHOOT, 1);
        p.getLocation().getWorld().playEffect(p.getLocation(), Effect.GHAST_SHRIEK, 1);
        
        Fireball f = (Fireball)e.getPlayer().launchProjectile(Fireball.class);
        f.setIsIncendiary(false);
        f.setYield(0.0F);
        Main.reload.add(p.getName());
        p.setExp(0.0F);
        p.setLevel(0);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
        {
          public void run()
          {
            if (Main.pyro.contains(p.getName()))
            {
              p.giveExp(2);
              p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
            }
          }
        }, 20L);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
        {
          public void run()
          {
            if (Main.pyro.contains(p.getName()))
            {
              p.giveExp(2);
              p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
            }
          }
        }, 40L);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
        {
          public void run()
          {
            if (Main.pyro.contains(p.getName()))
            {
              p.giveExp(2);
              p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
            }
          }
        }, 60L);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
        {
          public void run()
          {
            if (Main.pyro.contains(p.getName()))
            {
              p.giveExp(2);
              p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
            }
          }
        }, 80L);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
        {
          public void run()
          {
            if (Main.pyro.contains(p.getName()))
            {
              p.giveExp(2);
              p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
            }
          }
        }, 100L);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
        {
          public void run()
          {
            if (Main.pyro.contains(p.getName()))
            {
              p.giveExp(2);
              p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
            }
          }
        }, 120L);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
        {
          public void run()
          {
            if (Main.pyro.contains(p.getName()))
            {
              p.giveExp(2);
              p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
            }
          }
        }, 140L);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
        {
          public void run()
          {
            if (Main.pyro.contains(p.getName()))
            {
              p.setExp(1.0F);
              p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 1.0F, 1.0F);
              Main.reload.remove(p.getName());
            }
          }
        }, 160L);
      }
      else
      {
        p.sendMessage(ChatColor.RED + "Voce pode usar esta habilidade apenas em areas com PVP.");
      }
    }
  }
  
  @EventHandler(priority=EventPriority.NORMAL)
  public void onEntityDamage(EntityDamageByEntityEvent e)
  {
    if (((e.getEntity() instanceof Player)) && ((e.getDamager() instanceof Fireball)))
    {
      Player p = (Player)e.getEntity();
      Fireball f = (Fireball)e.getDamager();
      if ((f.getShooter() instanceof Player))
      {
        Player s = (Player)f.getShooter();
        if (Main.pyro.contains(s.getName())) {
          p.setFireTicks(100);
        }
      }
    }
  }
}
