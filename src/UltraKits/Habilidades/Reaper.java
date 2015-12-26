package UltraKits.Habilidades;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import UltraKits.Main;

public class Reaper
  implements Listener
{
  @EventHandler
  public void OnClick(EntityDamageByEntityEvent e)
  {
    if (((e.getEntity() instanceof Player)) && ((e.getDamager() instanceof Player)))
    {
      Player damager = (Player)e.getDamager();
      Player victim = (Player)e.getEntity();
      if (Main.reaper.contains(damager.getName()))
      {
        Main.reaper.add(damager.getName());
        if (damager.getInventory().getItemInHand().getType() == Material.WOOD_HOE)
        {
          victim.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, 3));
          return;
        }
        return;
      }
    }
  }
}
