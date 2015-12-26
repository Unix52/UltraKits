package UltraKits.Habilidades;

import UltraKits.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class Alien
  implements Listener
{
  public static HashMap<String, Long> cooldown = new HashMap();
  public static HashMap<String, ArrayList<String>> players = new HashMap();
  public static ArrayList<String> levitados = new ArrayList();
  
  @EventHandler
  public void levitar(PlayerInteractEvent e)
  {
    final Player p = e.getPlayer();
    if ((e.getAction().name().contains("RIGHT")) && 
      (p.getItemInHand().getType() == Material.ENDER_PORTAL_FRAME) && 
      (Main.alien.contains(p.getName())))
    {
      e.setCancelled(true);
      p.updateInventory();
      if ((!cooldown.containsKey(p.getName())) || (((Long)cooldown.get(p.getName())).longValue() <= System.currentTimeMillis()))
      {
        if (Main.areaPvP(p))
        {
          List<Entity> nearby = p.getNearbyEntities(10.0D, 10.0D, 10.0D);
          for (Entity en : nearby) {
            if ((en instanceof Player))
            {
              Player s = (Player)en;
              if (s != p) {
                if (Main.areaPvP(s))
                {
                  if ((!Main.inGladiator(p)) && 
                    (!Main.inGladiator(s)))
                  {
                    levitados.add(s.getName());
                    Location l = new Location(s.getWorld(), s.getLocation().getX(), s.getLocation().getY() + 4.0D, s.getLocation().getZ());
                    s.setAllowFlight(true);
                    s.setFlying(true);
                    s.teleport(l);
                    ArrayList<String> all = new ArrayList();
                    all.add(s.getName());
                    players.put(p.getName(), all);
                    s.sendMessage(ChatColor.AQUA + "O Alien " + ChatColor.DARK_PURPLE + p.getName() + ChatColor.AQUA + " levitou você para estudos.");
                    s.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 1200, 0));
                    s.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 0));
                    new BukkitRunnable()
                    {
                      int n = 0;
                      
                      public void run()
                      {
                        if (this.n < 5)
                        {
                          if (Alien.players.containsKey(p.getName()))
                          {
                            ArrayList<String> a = (ArrayList)Alien.players.get(p.getName());
                            for (String b : a)
                            {
                              Player s = Bukkit.getPlayer(b);
                              if (s != null) {
                                s.playSound(s.getLocation(), Sound.VILLAGER_HAGGLE, 1.0F, 1.0F);
                              }
                            }
                          }
                          this.n += 1;
                        }
                        else
                        {
                          if (Alien.players.containsKey(p.getName()))
                          {
                            ArrayList<String> a = (ArrayList)Alien.players.get(p.getName());
                            for (String b : a)
                            {
                              if (Alien.levitados.contains(b)) {
                                Alien.levitados.remove(b);
                              }
                              Player s = Bukkit.getPlayer(b);
                              if (s != null)
                              {
                                s.setFlying(false);
                                s.setAllowFlight(false);
                                s.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 0));
                                s.sendMessage(ChatColor.YELLOW + "O estudo do Alien " + ChatColor.DARK_PURPLE + p.getName() + ChatColor.YELLOW + " acabou.");
                              }
                            }
                            Alien.players.remove(p.getName());
                          }
                          cancel();
                        }
                      }
                    }.runTaskTimer(Main.plugin, 0L, 40L);
                  }
                  cooldown.put(p.getName(), Long.valueOf(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(60L)));
                  p.playSound(p.getLocation(), Sound.ENDERDRAGON_WINGS, 1.0F, 1.0F);
                  p.sendMessage(ChatColor.BLUE + "Voce usou suas habilidades de " + ChatColor.DARK_PURPLE + "Alien");
                }
                else
                {
                  p.sendMessage(ChatColor.RED + "Voce nao pode usar esta habilidade no Gladiator.");
                }
              }
            }
          }
        }
        else
        {
          p.sendMessage(ChatColor.RED + "Voce pode usar esta habilidade apenas em areas com PVP.");
        }
        return;
      }
      p.sendMessage(ChatColor.RED + "Faltam " + TimeUnit.MILLISECONDS.toSeconds(((Long)cooldown.get(p.getName())).longValue() - System.currentTimeMillis()) + " segundos para poder usar novamente.");
    }
  }
  
  @EventHandler
  public void mover(PlayerMoveEvent e)
  {
    Player p = e.getPlayer();
    if (((e.getFrom().getBlockX() != e.getTo().getBlockX()) || (e.getFrom().getBlockZ() != e.getTo().getBlockZ()) || (e.getTo().getBlockY() != e.getFrom().getBlockY())) && 
      (levitados.contains(p.getName())))
    {
      p.teleport(e.getFrom());
      p.sendMessage(ChatColor.RED + "Voce nao pode se mover enquanto estiver sobre dominio do Alien.");
      return;
    }
  }
  
  @EventHandler
  public void morrer(PlayerDeathEvent e)
  {
    Player p = e.getEntity();
    if (levitados.contains(p.getName()))
    {
      p.setFlying(false);
      p.setAllowFlight(false);
      levitados.remove(p.getName());
      for (Map.Entry en : players.entrySet())
      {
        ArrayList<String> a = (ArrayList)en.getValue();
        if (a.contains(p.getName())) {
          a.remove(p.getName());
        }
        players.put((String)en.getKey(), a);
      }
    }
  }
  
  @EventHandler
  public void sair(PlayerQuitEvent e)
  {
    Player p = e.getPlayer();
    if (levitados.contains(p.getName()))
    {
      p.setFlying(false);
      p.setAllowFlight(false);
      levitados.remove(p.getName());
      for (Map.Entry en : players.entrySet())
      {
        ArrayList<String> a = (ArrayList)en.getValue();
        if (a.contains(p.getName())) {
          a.remove(p.getName());
        }
        players.put((String)en.getKey(), a);
      }
    }
  }
}
