package UltraKits.Habilidades;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import UltraKits.Main;

public class Anchor
  implements Listener
{
  public Plugin plugin;
  
  public Anchor(Main plugin)
  {
    this.plugin = plugin;
  }
  
  @EventHandler(priority=EventPriority.MONITOR)
  public void onPlayerHitAnchor(EntityDamageByEntityEvent e)
  {
    if (!(e.getEntity() instanceof Player)) {
      return;
    }
    if (!(e.getDamager() instanceof Player)) {
      return;
    }
    final Player p = (Player)e.getEntity();
    Player a = (Player)e.getDamager();
    if (Main.anchor.contains(p.getName()))
    {
      p.setVelocity(new Vector());
      p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 4.0F, 4.0F);
      Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
      {
        public void run()
        {
          p.setVelocity(new Vector());
        }
      }, 1L);
    }
    if (Main.anchor.contains(a.getName()))
    {
      a.playSound(a.getLocation(), Sound.ANVIL_BREAK, 4.0F, 4.0F);
      p.setVelocity(new Vector());
      Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
      {
        public void run()
        {
          p.setVelocity(new Vector());
        }
      }, 1L);
    }
  }
}
