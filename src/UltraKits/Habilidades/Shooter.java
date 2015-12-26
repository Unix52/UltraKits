package UltraKits.Habilidades;

import UltraKits.Main;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

public class Shooter
  implements Listener
{
  private Main plugin;
  
  public Shooter(Main instance)
  {
    this.plugin = instance;
  }
  
  @EventHandler(priority=EventPriority.NORMAL)
  public void onPlayerInteract(PlayerInteractEvent e)
  {
    final Player p = e.getPlayer();
    if (Main.whister.contains(p.getName()))
    {
      if (e.getAction() != Action.RIGHT_CLICK_AIR) {
        return;
      }
      if (e.getItem().getType() != Material.NETHER_STAR) {
        return;
      }
      p.removePotionEffect(PotionEffectType.WITHER);
      Vector velo1 = p.getLocation().getDirection().normalize().multiply(5);
      velo1.add(new Vector(Math.random() * 0.0D + 0.0D, 0.0D, 0.0D));
      if (Main.reload.contains(p.getName()))
      {
        p.sendMessage(ChatColor.RED + "Recarregando!");
      }
      else if (Main.areaPvP(p))
      {
        p.playSound(p.getLocation(), Sound.EXPLODE, 1.0F, 1.0F);
        p.getEyeLocation().getWorld().playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 5);
        p.getLocation().getWorld().playEffect(p.getLocation(), Effect.GHAST_SHOOT, 1);
        p.getLocation().getWorld().playEffect(p.getLocation(), Effect.BLAZE_SHOOT, 1);
        WitherSkull skull = (WitherSkull)p.launchProjectile(WitherSkull.class);
        skull.setVelocity(velo1);
        skull.setMetadata("shooter", new FixedMetadataValue(Main.plugin, Boolean.valueOf(true)));
        Main.reload.add(p.getName());
        p.setExp(0.0F);
        p.setLevel(0);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
        {
          public void run()
          {
            if (Main.whister.contains(p.getName()))
            {
              p.giveExp(2);
              p.playSound(p.getLocation(), Sound.WITHER_IDLE, 1.0F, 1.0F);
            }
          }
        }, 20L);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
        {
          public void run()
          {
            if (Main.whister.contains(p.getName()))
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
            if (Main.whister.contains(p.getName()))
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
            if (Main.whister.contains(p.getName()))
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
            if (Main.whister.contains(p.getName()))
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
            if (Main.whister.contains(p.getName()))
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
            if (Main.whister.contains(p.getName()))
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
            if (Main.whister.contains(p.getName()))
            {
              p.setExp(1.0F);
              p.playSound(p.getLocation(), Sound.WITHER_HURT, 1.0F, 1.0F);
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
  
  @EventHandler
  public void onHit(ProjectileHitEvent e)
  {
    if ((e.getEntity() instanceof WitherSkull))
    {
      WitherSkull s = (WitherSkull)e.getEntity();
      if (s.hasMetadata("shooter"))
      {
        List<Entity> nearby = e.getEntity().getNearbyEntities(5.0D, 5.0D, 5.0D);
        for (Entity en : nearby) {
          if ((en instanceof Player))
          {
            Player p = (Player)en;
            if (!Main.whister.contains(p.getName()))
            {
              p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 400, 0));
              if ((s.getShooter() instanceof Player))
              {
                Player sh = (Player)s.getShooter();
                p.damage(4.0D, sh);
              }
            }
          }
        }
      }
    }
  }
}
