package UltraKits.Habilidades;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import UltraKits.Main;

public class Ninja
  implements Listener
{
  public static HashMap<String, Long> cooldown = new HashMap();
  public static HashMap<String, String> tp = new HashMap();
  
  @EventHandler
  public void marcar(EntityDamageByEntityEvent e)
  {
    if (((e.getEntity() instanceof Player)) && ((e.getDamager() instanceof Player)))
    {
      Player p = (Player)e.getEntity();
      Player d = (Player)e.getDamager();
      if (Main.ninja.contains(d.getName())) {
        tp.put(d.getName(), p.getName());
      }
    }
  }
  
  @EventHandler
  public void teleportar(PlayerToggleSneakEvent e)
  {
    Player p = e.getPlayer();
    if ((Main.ninja.contains(p.getName())) && 
      (p.isSneaking())) {
      if (tp.containsKey(p.getName()))
      {
        if ((!cooldown.containsKey(p.getName())) || (((Long)cooldown.get(p.getName())).longValue() <= System.currentTimeMillis()))
        {
          String r = (String)tp.get(p.getName());
          Player req = Bukkit.getPlayer(r);
          if (req != null)
          {
            if (!Main.inGladiator(p))
            {
              if (!Main.inGladiator(req))
              {
                if (Main.areaPvP(p))
                {
                  if (Main.areaPvP(req))
                  {
                    p.teleport(req);
                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0F, 1.0F);
                    cooldown.put(p.getName(), Long.valueOf(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(12L)));
                  }
                  else
                  {
                    p.sendMessage(ChatColor.RED + "Este player nao está em uma area com PVP.");
                  }
                }
                else {
                  p.sendMessage(ChatColor.RED + "Voce pode usar esta habilidade apenas em areas com PVP.");
                }
              }
              else {
                p.sendMessage(ChatColor.RED + "Este player está lutando no Gladiator.");
              }
            }
            else {
              p.sendMessage(ChatColor.RED + "Voce está lutando no Gladiator.");
            }
          }
          else
          {
            p.sendMessage(ChatColor.RED + "Este player nao está online.");
            tp.remove(p.getName());
          }
        }
        else
        {
          p.sendMessage(ChatColor.RED + "Faltam " + TimeUnit.MILLISECONDS.toSeconds(((Long)cooldown.get(p.getName())).longValue() - System.currentTimeMillis()) + " segundos para poder usar novamente.");
        }
      }
      else {
        p.sendMessage(ChatColor.RED + "Ninguém para se teleportar.");
      }
    }
  }
  
  public static void resetTarget(Player p)
  {
    if (!tp.isEmpty()) {
      for (Map.Entry e : tp.entrySet())
      {
        String s = (String)e.getValue();
        if (p.getName() == s) {
          tp.remove((String)e.getKey());
        }
      }
    }
  }
}
