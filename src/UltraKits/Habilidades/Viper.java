package UltraKits.Habilidades;

import java.util.Random;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import UltraKits.Main;

public class Viper
  implements Listener
{
  @EventHandler
  public void onEntityDamage(EntityDamageByEntityEvent e)
  {
    if (((e.getDamager() instanceof Player)) && ((e.getEntity() instanceof LivingEntity)))
    {
      LivingEntity entity = (LivingEntity)e.getEntity();
      Player p = (Player)e.getDamager();
      if (Main.viper.contains(p.getName()))
      {
        Random rand = new Random();
        int percent = rand.nextInt(100);
        if (percent <= 33)
        {
          entity.addPotionEffect(new PotionEffect(
            PotionEffectType.POISON, 60, 0));
          return;
        }
        return;
      }
      return;
    }
  }
}
