package UltraKits.Habilidades;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import UltraKits.Main;

public class SpiderMan
  implements Listener
{
  public static HashMap<String, Long> cooldown = new HashMap();
  public static HashMap<String, Snowball> teias = new HashMap();
  
  @EventHandler
  public void lancar(PlayerInteractEvent e)
  {
    Player p = e.getPlayer();
    if (((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) && 
      (Main.spiderman.contains(p.getName())) && 
      (p.getItemInHand().getType() == Material.STRING))
    {
      e.setCancelled(true);
      p.updateInventory();
      if ((!cooldown.containsKey(p.getName())) || (((Long)cooldown.get(p.getName())).longValue() <= System.currentTimeMillis()))
      {
        if (Main.areaPvP(p))
        {
          Snowball teia = (Snowball)p.launchProjectile(Snowball.class);
          teias.put(p.getName(), teia);
          cooldown.put(p.getName(), Long.valueOf(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(10L)));
          p.sendMessage(ChatColor.DARK_PURPLE + "Teia lançada!");
          p.playSound(p.getLocation(), Sound.IRONGOLEM_THROW, 1.0F, 1.0F);
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
  public void teia(ProjectileHitEvent e)
  {
    if (((e.getEntity() instanceof Snowball)) && 
      ((e.getEntity().getShooter() instanceof Player)))
    {
      Snowball teia = (Snowball)e.getEntity();
      Player p = (Player)e.getEntity().getShooter();
      if ((teias.containsKey(p.getName())) && 
        (teia == teias.get(p.getName())))
      {
        teias.remove(p.getName());
        final Block b = teia.getLocation().getBlock();
        b.setType(Material.WEB);
        new BukkitRunnable()
        {
          public void run()
          {
            if (b.getType() == Material.WEB) {
              b.setType(Material.AIR);
            }
          }
        }.runTaskLater(Main.plugin, 100L);
        return;
      }
    }
  }
}
