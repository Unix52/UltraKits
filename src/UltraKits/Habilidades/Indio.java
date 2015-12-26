package UltraKits.Habilidades;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import UltraKits.Main;

public class Indio
  implements Listener
{
  public static HashMap<String, Long> cooldown = new HashMap();
  public static HashMap<String, Arrow> tiros = new HashMap();
  
  @EventHandler
  public void disparar(PlayerInteractEvent e)
  {
    Player p = e.getPlayer();
    if (((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) && 
      (p.getItemInHand().getType() == Material.PUMPKIN_SEEDS) && 
      (Main.indio.contains(p.getName()))) {
      if ((!cooldown.containsKey(p.getName())) || (((Long)cooldown.get(p.getName())).longValue() <= System.currentTimeMillis()))
      {
        if (Main.areaPvP(p))
        {
          e.setCancelled(true);
          p.updateInventory();
          Arrow tiro = (Arrow)p.launchProjectile(Arrow.class);
          Vector vec = p.getLocation().getDirection();
          tiro.setVelocity(new Vector(
            vec.getX() * 1.0D * 3.5D, 
            vec.getY() * 1.0D * 4.0D, 
            vec.getZ() * 1.0D * 3.5D));
          tiros.put(p.getName(), tiro);
          cooldown.put(p.getName(), Long.valueOf(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(20L)));
          p.sendMessage(ChatColor.GOLD + "Dardo disparado!");
          p.playSound(p.getLocation(), Sound.GLASS, 1.0F, 1.0F);
          return;
        }
        p.sendMessage(ChatColor.RED + "Voce pode usar esta habilidade apenas em areas com PVP.");
      }
      else
      {
        p.sendMessage(ChatColor.RED + "Faltam " + TimeUnit.MILLISECONDS.toSeconds(((Long)cooldown.get(p.getName())).longValue() - System.currentTimeMillis()) + " segundos para poder usar novamente.");
      }
    }
  }
  
  @EventHandler
  public void aplicar(EntityDamageByEntityEvent e)
  {
    if (((e.getEntity() instanceof Player)) && ((e.getDamager() instanceof Arrow)))
    {
      Player p = (Player)e.getEntity();
      Arrow tiro = (Arrow)e.getDamager();
      if ((tiro.getShooter() instanceof Player))
      {
        Player shooter = (Player)tiro.getShooter();
        if ((tiros.containsKey(shooter.getName())) && 
          (tiro == tiros.get(shooter.getName())))
        {
          p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 300, 0));
          p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 300, 0));
          p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 300, 0));
          p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 300, 0));
          p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 300, 0));
          p.sendMessage(ChatColor.BLUE + "Voce foi atingido por um dardo do Pataxó " + ChatColor.DARK_PURPLE + shooter.getName());
          p.playSound(p.getLocation(), Sound.BURP, 1.0F, 1.0F);
          shooter.sendMessage(ChatColor.YELLOW + "O Sol conspira ao seu favor! " + ChatColor.DARK_PURPLE + p.getName() + ChatColor.YELLOW + " foi acertado pelo seu dardo!");
          shooter.playSound(shooter.getLocation(), Sound.CAT_PURR, 1.0F, 1.0F);
          tiros.remove(shooter.getName());
          return;
        }
      }
    }
  }
}
