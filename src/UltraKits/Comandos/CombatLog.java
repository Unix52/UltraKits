package UltraKits.Comandos;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import UltraKits.Main;
import UltraKits.u1v1.Commands;

public class CombatLog
  implements Listener
{
  @SuppressWarnings({ "unchecked", "rawtypes" })
public static ArrayList<String> cl = new ArrayList();
  
  @EventHandler
  public void entrarCombate(EntityDamageByEntityEvent e)
  {
    if (((e.getEntity() instanceof Player)) && ((e.getDamager() instanceof Player)))
    {
      final Player p = (Player)e.getEntity();
      final Player damager = (Player)e.getDamager();
      if ((Main.plugin.getConfig().getBoolean("CombatLog")) && 
        (!Commands.em.contains(p.getName())))
      {
        if (!cl.contains(p.getName()))
        {
          cl.add(p.getName());
          p.sendMessage(ChatColor.RED + "Voce entrou em combate com: " + ChatColor.DARK_RED + damager.getName() + ChatColor.RED + ". Nao podera fugir durante os proximos 10 segundos.");
          Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable()
          {
            public void run()
            {
              if (CombatLog.cl.contains(p.getName()))
              {
                CombatLog.cl.remove(p.getName());
                if (p != null) {
                  p.sendMessage(ChatColor.GREEN + "Voce nao esta mais em combate!");
                }
              }
            }
          }, 200L);
        }
        if (!cl.contains(damager.getName()))
        {
          cl.add(damager.getName());
          damager.sendMessage(ChatColor.RED + "Voce entrou em combate com: " + ChatColor.DARK_RED + p.getName() + ChatColor.RED + ". Nao podera fugir durante os proximos 10 segundos.");
          Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable()
          {
            public void run()
            {
              if (CombatLog.cl.contains(damager.getName()))
              {
                CombatLog.cl.remove(damager.getName());
                if (damager != null) {
                  damager.sendMessage(ChatColor.GREEN + "Voce nao esta mais em combate!");
                }
              }
            }
          }, 200L);
        }
      }
    }
  }
  
  @EventHandler
  public void antiFuga(PlayerCommandPreprocessEvent e)
  {
    if (cl.contains(e.getPlayer().getName()))
    {
      e.setCancelled(true);
      e.getPlayer().sendMessage(ChatColor.RED + "Voce está em combate. Aguarde para digitar comandos.");
    }
  }
  
  @EventHandler
  public void reset(PlayerDeathEvent e)
  {
    Player p = e.getEntity();
    if (cl.contains(p.getName())) {
      cl.remove(p.getName());
    }
  }
}
