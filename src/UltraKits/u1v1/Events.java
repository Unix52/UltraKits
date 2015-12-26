package UltraKits.u1v1;

import UltraKits.Main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import me.confuser.barapi.BarAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Events
  implements Listener
{
  static ArrayList<String> move = new ArrayList();
  static ArrayList<String> respawn = new ArrayList();
  
  @EventHandler
  public void antiMove(PlayerMoveEvent e)
  {
    Player p = e.getPlayer();
    if (((e.getFrom().getBlockX() != e.getTo().getBlockX()) || (e.getFrom().getBlockZ() != e.getTo().getBlockZ())) && 
      (move.contains(p.getName()))) {
      p.teleport(e.getFrom());
    }
  }
  
  @EventHandler
  public void onQuit(PlayerQuitEvent e)
  {
    Player p = e.getPlayer();
    if (Commands.arm.containsKey(p.getName()))
    {
      p.getEquipment().setArmorContents((ItemStack[])Commands.arm.get(p.getName()));
      Commands.arm.remove(p.getName());
    }
    if (Commands.inv.containsKey(p.getName()))
    {
      p.getInventory().setContents((ItemStack[])Commands.inv.get(p.getName()));
      Commands.inv.remove(p.getName());
    }
    if (Desafiar.cooldown.containsKey(p.getName()))
    {
      BukkitRunnable task = (BukkitRunnable)Desafiar.cooldown.get(p.getName());
      task.cancel();
      Desafiar.cooldown.remove(p.getName());
    }
    if (Desafiar.tasks.containsKey(p.getName()))
    {
      BukkitRunnable task = (BukkitRunnable)Desafiar.tasks.get(p.getName());
      task.cancel();
      Desafiar.tasks.remove(p.getName());
    }
    if (move.contains(p.getName())) {
      move.remove(p.getName());
    }
    if (Commands.em.contains(p.getName()))
    {
      Commands.em.remove(p.getName());
      if (Main.data.getConfigurationSection("Saida") != null) {
        p.teleport(new Location(Bukkit.getWorld(Main.data.getString("Saida.world")), Main.data.getDouble("Saida.x"), Main.data.getDouble("Saida.y"), Main.data.getDouble("Saida.z"), (float)Main.data.getDouble("Saida.yaw"), (float)Main.data.getDouble("Saida.pitch")));
      } else {
        p.teleport(p.getWorld().getSpawnLocation());
      }
    }
    if (Desafiar.lutando.containsKey(p.getName()))
    {
      Player req = Bukkit.getPlayer((String)Desafiar.lutando.get(p.getName()));
      if (req != null)
      {
        Desafiar.showPlayer(req);
        if (Desafiar.lutando.containsKey(req.getName())) {
          Desafiar.lutando.remove(req.getName());
        }
        if (move.contains(req.getName())) {
          move.remove(req.getName());
        }
        if (Desafiar.cooldown.containsKey(req.getName()))
        {
          BukkitRunnable task = (BukkitRunnable)Desafiar.cooldown.get(req.getName());
          task.cancel();
          Desafiar.cooldown.remove(req.getName());
        }
        if (Desafiar.tasks.containsKey(req.getName()))
        {
          BukkitRunnable task = (BukkitRunnable)Desafiar.tasks.get(req.getName());
          task.cancel();
          Desafiar.tasks.remove(req.getName());
        }
        for (String n : Commands.em)
        {
          Player i = Bukkit.getPlayer(n);
          if (i != null) {
            for (String s : Main.plugin.getConfig().getStringList("Vitoria")) {
              if (req.getInventory().contains(Material.MUSHROOM_SOUP)) {
                i.sendMessage(s.replaceAll("&", "§").replaceAll("@perdedor", p.getName()).replaceAll("@vencedor", req.getName()).replaceAll("@sopas", getAmount(req, Material.MUSHROOM_SOUP)).replaceAll("@coracoes", (int)req.getHealth() / 2));
              } else {
                i.sendMessage(s.replaceAll("&", "§").replaceAll("@perdedor", p.getName()).replaceAll("@vencedor", req.getName()).replaceAll("@sopas", "0").replaceAll("@coracoes", req.getHealth() / 2.0D));
              }
            }
          }
        }
        if (Main.data.getConfigurationSection("Spawn") != null)
        {
          Commands.limparInv(req);
          req.getInventory().addItem(new ItemStack[] { Desafiar.item });
          req.teleport(new Location(Bukkit.getWorld(Main.data.getString("Spawn.world")), Main.data.getDouble("Spawn.x"), Main.data.getDouble("Spawn.y"), Main.data.getDouble("Spawn.z"), (float)Main.data.getLong("Spawn.yaw"), (float)Main.data.getLong("Spawn.pitch")));
          req.setHealth(20.0D);
          BarAPI.removeBar(req);
        }
        else
        {
          req.teleport(req.getWorld().getSpawnLocation());
        }
      }
      Desafiar.lutando.remove(p.getName());
    }
  }
  
  @EventHandler
  public void acabar(PlayerDeathEvent e)
  {
    if ((e.getEntity().getKiller() instanceof Player))
    {
      Player p = e.getEntity();
      Player killer = e.getEntity().getKiller();
      if (Desafiar.tasks.containsKey(p.getName()))
      {
        BukkitRunnable run = (BukkitRunnable)Desafiar.tasks.get(p.getName());
        run.cancel();
        Desafiar.tasks.remove(p.getName());
      }
      if (Desafiar.tasks.containsKey(killer.getName()))
      {
        BukkitRunnable run = (BukkitRunnable)Desafiar.tasks.get(killer.getName());
        run.cancel();
        Desafiar.tasks.remove(killer.getName());
      }
      if (Desafiar.lutando.containsKey(p.getName()))
      {
        e.getDrops().clear();
        String name = (String)Desafiar.lutando.get(p.getName());
        if (name == killer.getName())
        {
          Desafiar.lutando.remove(p.getName());
          if (Desafiar.lutando.containsKey(killer.getName())) {
            Desafiar.lutando.remove(killer.getName());
          }
          Desafiar.showPlayer(p);
          Desafiar.showPlayer(killer);
          for (String n : Commands.em)
          {
            Player i = Bukkit.getPlayer(n);
            if (i != null) {
              for (String s : Main.plugin.getConfig().getStringList("Vitoria")) {
                if (killer.getInventory().contains(Material.MUSHROOM_SOUP)) {
                  i.sendMessage(s.replaceAll("&", "§").replaceAll("@perdedor", p.getName()).replaceAll("@vencedor", killer.getName()).replaceAll("@sopas", getAmount(killer, Material.MUSHROOM_SOUP)).replaceAll("@coracoes", (int)killer.getHealth() / 2));
                } else {
                  i.sendMessage(s.replaceAll("&", "§").replaceAll("@perdedor", p.getName()).replaceAll("@vencedor", killer.getName()).replaceAll("@sopas", "0").replaceAll("@coracoes", (int)killer.getHealth() / 2));
                }
              }
            }
          }
          if (Main.data.getConfigurationSection("Spawn") != null)
          {
            Commands.limparInv(killer);
            killer.getInventory().addItem(new ItemStack[] { Desafiar.item });
            killer.teleport(new Location(Bukkit.getWorld(Main.data.getString("Spawn.world")), Main.data.getDouble("Spawn.x"), Main.data.getDouble("Spawn.y"), Main.data.getDouble("Spawn.z"), (float)Main.data.getLong("Spawn.yaw"), (float)Main.data.getLong("Spawn.pitch")));
            killer.setHealth(20.0D);
            BarAPI.removeBar(killer);
          }
          else
          {
            killer.teleport(killer.getWorld().getSpawnLocation());
          }
        }
      }
    }
  }
  
  @EventHandler
  public void aoTentarSair(PlayerCommandPreprocessEvent e)
  {
    if ((Commands.em.contains(e.getPlayer().getName())) && (!e.getMessage().equalsIgnoreCase("/1v1")))
    {
      e.setCancelled(true);
      e.getPlayer().sendMessage(ChatColor.RED + "Apenas o comando /1v1 pode ser usado!");
      return;
    }
  }
  
  @EventHandler
  public void onRespawn(PlayerRespawnEvent e)
  {
    final Player p = e.getPlayer();
    if (Commands.em.contains(p.getName())) {
      if (Main.data.getConfigurationSection("Spawn") != null)
      {
        Commands.limparInv(p);
        p.getInventory().addItem(new ItemStack[] { Desafiar.item });
        new BukkitRunnable()
        {
          public void run()
          {
            p.teleport(new Location(Bukkit.getWorld(Main.data.getString("Spawn.world")), Main.data.getDouble("Spawn.x"), Main.data.getDouble("Spawn.y"), Main.data.getDouble("Spawn.z"), (float)Main.data.getLong("Spawn.yaw"), (float)Main.data.getLong("Spawn.pitch")));
            p.setHealth(20.0D);
            BarAPI.removeBar(p);
          }
        }.runTaskLater(Main.plugin, 10L);
      }
      else
      {
        new BukkitRunnable()
        {
          public void run()
          {
            p.teleport(p.getWorld().getSpawnLocation());
          }
        }.runTaskLater(Main.plugin, 10L);
      }
    }
  }
  
  @EventHandler
  public void noDropItem(PlayerDropItemEvent e)
  {
    if (e.getItemDrop().getItemStack().equals(Desafiar.item)) {
      e.setCancelled(true);
    }
  }
  
  public int getAmount(Player player, Material id)
  {
    int amout = 0;
    for (ItemStack item : player.getInventory().getContents()) {
      if ((item != null) && (item.getType() == id) && (item.getAmount() > 0)) {
        amout += item.getAmount();
      }
    }
    return amout;
  }
  
  @EventHandler
  public void onDropBowl(PlayerDropItemEvent e)
  {
    if ((Commands.em.contains(e.getPlayer().getName())) && (
      (e.getItemDrop().getItemStack().getType() == Material.BOWL) || (e.getItemDrop().getItemStack().getType() == Material.MUSHROOM_SOUP))) {
      e.getItemDrop().remove();
    }
  }
}
