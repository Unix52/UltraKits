package UltraKits;

import UltraKits.u1v1.Commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class ServerScoreboard
  implements Listener
{
  public static HashMap<String, Scoreboard> scoreboards = new HashMap();
  public static HashMap<String, Score> deaths = new HashMap();
  public static HashMap<String, Score> kills = new HashMap();
  public static HashMap<String, Score> killstreak = new HashMap();
  public static HashMap<String, Score> kdr = new HashMap();
  public static HashMap<String, Integer> ks = new HashMap();
  public static boolean enableSB = Main.plugin.getConfig().getBoolean("EnableScoreboard");
  
  public static void criarScoreboard(Player p)
  {
    if (enableSB) {
      if (!scoreboards.containsKey(p.getName()))
      {
        if (Main.m.getConfigurationSection(p.getName()) == null)
        {
          Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
          Objective obj = sb.registerNewObjective(p.getName(), "dummy");
          obj.setDisplaySlot(DisplaySlot.SIDEBAR);
          obj.setDisplayName(Main.plugin.getConfig().getString("Scoreboard.title").replaceAll("&", "§").replaceAll("PLAYER", p.getName()));
          Score d = obj.getScore(Bukkit.getOfflinePlayer(Main.plugin.getConfig().getString("Scoreboard.deaths").replaceAll("&", "§")));
          Score k = obj.getScore(Bukkit.getOfflinePlayer(Main.plugin.getConfig().getString("Scoreboard.kills").replaceAll("&", "§")));
          Score kd = obj.getScore(Bukkit.getOfflinePlayer(Main.plugin.getConfig().getString("Scoreboard.kdr").replaceAll("&", "§")));
          Score ks = obj.getScore(Bukkit.getOfflinePlayer(Main.plugin.getConfig().getString("Scoreboard.killstreak").replaceAll("&", "§")));
          
          d.setScore(0);
          k.setScore(0);
          kd.setScore(0);
          ks.setScore(0);
          
          scoreboards.put(p.getName(), sb);
          deaths.put(p.getName(), d);
          kills.put(p.getName(), k);
          kdr.put(p.getName(), kd);
          killstreak.put(p.getName(), ks);
          
          Main.m.set(p.getName() + ".deaths", Integer.valueOf(0));
          Main.m.set(p.getName() + ".kills", Integer.valueOf(0));
          Main.m.set(p.getName() + ".killstreak", Integer.valueOf(0));
          try
          {
            Main.m.save(Main.n);
          }
          catch (IOException e)
          {
            e.printStackTrace();
          }
          p.setScoreboard((Scoreboard)scoreboards.get(p.getName()));
        }
        else
        {
          Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
          Objective obj = sb.registerNewObjective(p.getName(), "dummy");
          obj.setDisplaySlot(DisplaySlot.SIDEBAR);
          obj.setDisplayName(Main.plugin.getConfig().getString("Scoreboard.title").replaceAll("&", "§").replaceAll("PLAYER", p.getName()));
          Score d = obj.getScore(Bukkit.getOfflinePlayer(Main.plugin.getConfig().getString("Scoreboard.deaths").replaceAll("&", "§")));
          Score k = obj.getScore(Bukkit.getOfflinePlayer(Main.plugin.getConfig().getString("Scoreboard.kills").replaceAll("&", "§")));
          Score kd = obj.getScore(Bukkit.getOfflinePlayer(Main.plugin.getConfig().getString("Scoreboard.kdr").replaceAll("&", "§")));
          Score ks = obj.getScore(Bukkit.getOfflinePlayer(Main.plugin.getConfig().getString("Scoreboard.killstreak").replaceAll("&", "§")));
          
          d.setScore(Main.m.getInt(p.getName() + ".deaths"));
          k.setScore(Main.m.getInt(p.getName() + ".kills"));
          if ((k.getScore() > 0) && (d.getScore() > 0)) {
            kd.setScore(k.getScore() / d.getScore());
          } else if ((k.getScore() > 0) && (d.getScore() <= 0)) {
            kd.setScore(k.getScore());
          } else if ((k.getScore() == 0) && (d.getScore() == 0)) {
            kd.setScore(0);
          } else if ((k.getScore() == 0) && (d.getScore() > 0)) {
            k.setScore(0);
          }
          ks.setScore(Main.m.getInt(p.getName() + ".killstreak"));
          
          scoreboards.put(p.getName(), sb);
          deaths.put(p.getName(), d);
          kills.put(p.getName(), k);
          kdr.put(p.getName(), kd);
          killstreak.put(p.getName(), ks);
          
          p.setScoreboard((Scoreboard)scoreboards.get(p.getName()));
        }
      }
      else {
        p.setScoreboard((Scoreboard)scoreboards.get(p.getName()));
      }
    }
  }
  
  @EventHandler
  public void main(PlayerDeathEvent e)
  {
    if ((e.getEntity().getKiller() instanceof Player))
    {
      Player p = e.getEntity();
      Player killer = e.getEntity().getKiller();
      if ((enableSB) && 
        (!Commands.em.contains(p.getName())) && (!Commands.em.contains(killer.getName())))
      {
        Score d = (Score)deaths.get(p.getName());
        Score k = (Score)kills.get(killer.getName());
        d.setScore(d.getScore() + 1);
        k.setScore(k.getScore() + 1);
        deaths.put(p.getName(), d);
        kills.put(killer.getName(), k);
        if (ks.containsKey(p.getName())) {
          ks.remove(p.getName());
        }
        if (ks.containsKey(killer.getName()))
        {
          if (((Integer)ks.get(killer.getName())).intValue() + 1 == 5)
          {
            Score ks = (Score)killstreak.get(killer.getName());
            ks.setScore(ks.getScore() + 1);
            killstreak.put(killer.getName(), ks);
            ((HashMap<String, Scoreboard>) ks).remove(killer.getName());
          }
          else
          {
            ks.put(killer.getName(), Integer.valueOf(((Integer)ks.get(killer.getName())).intValue() + 1));
          }
        }
        else {
          ks.put(killer.getName(), Integer.valueOf(1));
        }
      }
      if ((!Commands.em.contains(p.getName())) && 
        (kills.containsKey(p.getName())) && (deaths.containsKey(p.getName()))) {
        if ((((Score)kills.get(p.getName())).getScore() > 0) && (((Score)deaths.get(p.getName())).getScore() > 0))
        {
          Score kd = (Score)kdr.get(p.getName());
          kd.setScore(((Score)kills.get(p.getName())).getScore() / ((Score)deaths.get(p.getName())).getScore());
          kdr.put(p.getName(), kd);
        }
        else if ((((Score)kills.get(p.getName())).getScore() > 0) && (((Score)deaths.get(p.getName())).getScore() == 0))
        {
          Score kd = (Score)kdr.get(p.getName());
          kd.setScore(((Score)kills.get(p.getName())).getScore());
          kdr.put(p.getName(), kd);
        }
        else if ((((Score)kills.get(p.getName())).getScore() == 0) && (((Score)deaths.get(p.getName())).getScore() > 0))
        {
          Score kd = (Score)kdr.get(p.getName());
          kd.setScore(0);
          kdr.put(p.getName(), kd);
        }
        else if ((((Score)kills.get(p.getName())).getScore() == 0) && (((Score)deaths.get(p.getName())).getScore() == 0))
        {
          Score kd = (Score)kdr.get(p.getName());
          kd.setScore(0);
          kdr.put(p.getName(), kd);
        }
      }
      if ((!Commands.em.contains(p.getName())) && 
        (kills.containsKey(killer.getName())) && (deaths.containsKey(killer.getName()))) {
        if ((((Score)kills.get(killer.getName())).getScore() > 0) && (((Score)deaths.get(killer.getName())).getScore() > 0))
        {
          Score kd = (Score)kdr.get(killer.getName());
          kd.setScore(((Score)kills.get(killer.getName())).getScore() / ((Score)deaths.get(killer.getName())).getScore());
          kdr.put(killer.getName(), kd);
        }
        else if ((((Score)kills.get(killer.getName())).getScore() > 0) && (((Score)deaths.get(killer.getName())).getScore() == 0))
        {
          Score kd = (Score)kdr.get(killer.getName());
          kd.setScore(((Score)kills.get(killer.getName())).getScore());
          kdr.put(killer.getName(), kd);
        }
        else if ((((Score)kills.get(killer.getName())).getScore() == 0) && (((Score)deaths.get(killer.getName())).getScore() > 0))
        {
          Score kd = (Score)kdr.get(killer.getName());
          kd.setScore(0);
          kdr.put(killer.getName(), kd);
        }
        else if ((((Score)kills.get(killer.getName())).getScore() == 0) && (((Score)deaths.get(killer.getName())).getScore() == 0))
        {
          Score kd = (Score)kdr.get(killer.getName());
          kd.setScore(0);
          kdr.put(killer.getName(), kd);
        }
      }
    }
  }
  
  public static void resetScoreboard(Player p)
  {
    if (kills.containsKey(p.getName()))
    {
      Score k = (Score)kills.get(p.getName());
      k.setScore(0);
      kills.put(p.getName(), k);
    }
    if (deaths.containsKey(p.getName()))
    {
      Score d = (Score)deaths.get(p.getName());
      d.setScore(0);
      deaths.put(p.getName(), d);
    }
    if (kdr.containsKey(p.getName()))
    {
      Score kd = (Score)kdr.get(p.getName());
      kd.setScore(0);
      kdr.put(p.getName(), kd);
    }
    if (killstreak.containsKey(p.getName()))
    {
      Score ks = (Score)killstreak.get(p.getName());
      ks.setScore(0);
      killstreak.put(p.getName(), ks);
    }
  }
}
