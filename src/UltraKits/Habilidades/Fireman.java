package UltraKits.Habilidades;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;

import UltraKits.Main;

public class Fireman
  implements Listener
{
  public Plugin plugin;
  
  public Fireman(Main plugin)
  {
    this.plugin = plugin;
  }
  
  @EventHandler
  public void damage(EntityDamageEvent e)
  {
    if ((e.getEntity() instanceof Player))
    {
      Player p = (Player)e.getEntity();
      if ((Main.fireman.contains(p.getName())) && (
        (e.getCause() == EntityDamageEvent.DamageCause.LAVA) || 
        (e.getCause() == EntityDamageEvent.DamageCause.FIRE) || 
        (e.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK))) {
        e.setCancelled(true);
      }
    }
  }
  
  @EventHandler
  public void Fogo(EntityDamageByEntityEvent e)
  {
    if ((e.getDamager() instanceof Player))
    {
      Player p = (Player)e.getDamager();
      if ((Main.fireman.contains(p.getName())) && 
        (p.getInventory().getItemInHand() != null) && 
        (p.getInventory().getItemInHand().getType() == Material.STONE_SWORD) && 
        ((e.getEntity() instanceof LivingEntity)))
      {
        LivingEntity en = (LivingEntity)e.getEntity();
        if (en.isDead()) {
          return;
        }
        en.setFireTicks(100);
      }
    }
  }
}
