package UltraKits.Habilidades;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import UltraKits.Main;

public class Eyer
  implements Listener
{
  public static HashMap<String, Long> cooldown = new HashMap();
  public static HashMap<String, EnderPearl> tiros = new HashMap();
  
  @EventHandler
  public void disparar(PlayerInteractEvent e)
  {
    Player p = e.getPlayer();
    if (((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) && 
      (Main.Teleporter.contains(p.getName())) && 
      (p.getItemInHand().getType() == Material.ENDER_PEARL))
    {
      e.setCancelled(true);
      p.updateInventory();
      if (Main.areaPvP(p))
      {
        if ((!cooldown.containsKey(p.getName())) || (((Long)cooldown.get(p.getName())).longValue() <= System.currentTimeMillis()))
        {
          if (!Gladiator.fighting.containsKey(p.getName()))
          {
            EnderPearl tiro = (EnderPearl)p.launchProjectile(EnderPearl.class);
            tiros.put(p.getName(), tiro);
            cooldown.put(p.getName(), Long.valueOf(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(15L)));
            p.sendMessage(ChatColor.GOLD + "Disparado!");
            p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1.0F, 1.0F);
          }
          else
          {
            p.sendMessage(ChatColor.RED + "Voce nao pode teleportar durante o Gladiator!");
          }
        }
        else {
          p.sendMessage(ChatColor.RED + "Faltam " + TimeUnit.MILLISECONDS.toSeconds(((Long)cooldown.get(p.getName())).longValue() - System.currentTimeMillis()) + " segundos para poder usar novamente.");
        }
      }
      else {
        p.sendMessage(ChatColor.RED + "Voce pode usar esta habilidade apenas em areas com PVP.");
      }
    }
  }
  
  @EventHandler
  public void teleportar(ProjectileHitEvent e)
  {
    if (((e.getEntity() instanceof EnderPearl)) && 
      ((e.getEntity().getShooter() instanceof Player)))
    {
      EnderPearl tiro = (EnderPearl)e.getEntity();
      Player p = (Player)e.getEntity().getShooter();
      if ((tiros.containsKey(p.getName())) && 
        (tiros.get(p.getName()) == tiro))
      {
        p.teleport(tiro.getLocation());
        tiros.remove(p.getName());
        p.sendMessage(ChatColor.BLUE + "Teleportado!");
        p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0F, 1.0F);
      }
    }
  }
}
