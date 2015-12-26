package UltraKits.u1v1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import me.confuser.barapi.BarAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import UltraKits.Main;
import UltraKits.ServerScoreboard;
import UltraKits.Habilidades.Ninja;

public class Commands
  implements CommandExecutor
{
  @SuppressWarnings({ "unchecked", "rawtypes" })
public static HashMap<String, ItemStack[]> inv = new HashMap();
  @SuppressWarnings({ "unchecked", "rawtypes" })
public static HashMap<String, ItemStack[]> arm = new HashMap();
  @SuppressWarnings({ "unchecked", "rawtypes" })
public static ArrayList<String> em = new ArrayList();
  public static FileConfiguration data = Main.data;
  public static Plugin pl = Main.plugin;
  
  public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args)
  {
    if (!(sender instanceof Player))
    {
      sender.sendMessage("[Mega1V1] Comando apenas para players in-game.");
      return true;
    }
    Player p = (Player)sender;
    if (cmd.equalsIgnoreCase("1v1")) {
      if (args.length == 0)
      {
        if (!p.hasPermission("1v1.jogar"))
        {
          sendConfigMessage(p, "SemPermissao");
          return true;
        }
        if (!em.contains(p.getName()))
        {
          if (Main.data.getConfigurationSection("Spawn") == null)
          {
            p.sendMessage(pl.getConfig().getString("ErroConfig").replaceAll("&", "§"));
            return true;
          }
          Location loc1 = new Location(Bukkit.getWorld(Main.data.getString("Spawn.world")), Main.data.getDouble("Spawn.x"), Main.data.getDouble("Spawn.y"), Main.data.getDouble("Spawn.z"), (float)Main.data.getLong("Spawn.yaw"), (float)Main.data.getLong("Spawn.pitch"));
          p.teleport(loc1);
          arm.put(p.getName(), p.getEquipment().getArmorContents());
          inv.put(p.getName(), p.getInventory().getContents());
          Main.resetKit(p);
          limparInv(p);
          p.getInventory().addItem(new ItemStack[] { Desafiar.item });
          if (Main.plugin.getConfig().getBoolean("EnableScoreboard2"))
          {
            p.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
            SB.criarScoreboard(p);
            p.setHealth(20.0D);
          }
          Ninja.resetTarget(p);
          sendConfigMessage(p, "Entrou");
          em.add(p.getName());
        }
        else
        {
          em.remove(p.getName());
          if (Main.plugin.getConfig().getBoolean("EnableScoreboard2")) {
            p.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
          }
          if (Main.plugin.getConfig().getBoolean("EnableScoreboard")) {
            ServerScoreboard.criarScoreboard(p);
          }
          if (Main.data.getConfigurationSection("Saida") != null)
          {
            Location loc1 = new Location(Bukkit.getWorld(Main.data.getString("Saida.world")), Main.data.getDouble("Saida.x"), Main.data.getDouble("Saida.y"), Main.data.getDouble("Saida.z"), (float)Main.data.getLong("Saida.yaw"), (float)Main.data.getLong("Saida.pitch"));
            p.teleport(loc1);
          }
          else
          {
            p.teleport(p.getWorld().getSpawnLocation());
          }
          limparInv(p);
          if (inv.containsKey(p.getName()))
          {
            p.getInventory().setContents((ItemStack[])inv.get(p.getName()));
            inv.remove(p.getName());
          }
          if (arm.containsKey(p.getName()))
          {
            p.getEquipment().setArmorContents((ItemStack[])arm.get(p.getName()));
            arm.remove(p.getName());
          }
          sendConfigMessage(p, "ItensRestaurados");
          sendConfigMessage(p, "Saiu");
          p.setHealth(20.0D);
          BarAPI.setMessage(p, ChatColor.RED + "Voce saiu do 1v1!", 5);
        }
      }
      else if (args.length == 1)
      {
        if ((args[0].equalsIgnoreCase("set")) && 
          (!p.hasPermission("1v1.set")))
        {
          p.sendMessage(Main.plugin.getConfig().getString("SemPermissao").replaceAll("&", "§"));
          return true;
        }
        if (args[0].equalsIgnoreCase("reload"))
        {
          if (!p.hasPermission("1v1.set"))
          {
            p.sendMessage(Main.plugin.getConfig().getString("SemPermissao").replaceAll("&", "§"));
            return true;
          }
          reloadPlugin();
          p.sendMessage(Main.plugin.getConfig().getString("ConfigReload").replaceAll("&", "§"));
        }
        if ((!args[0].equalsIgnoreCase("reload")) && (!args[0].equalsIgnoreCase("set")) && 
          (p.hasPermission("1v1.set"))) {
          for (String s : Main.plugin.getConfig().getStringList("UsoAdmin")) {
            p.sendMessage(s.replaceAll("&", "§"));
          }
        }
      }
      else if (args.length > 1)
      {
        if (!p.hasPermission("1v1.set"))
        {
          p.sendMessage(Main.plugin.getConfig().getString("SemPermissao").replaceAll("&", "§"));
          return true;
        }
        if (args[0].equalsIgnoreCase("set"))
        {
          if (args[1].equalsIgnoreCase("spawn"))
          {
            Main.data.set("Spawn.world", p.getWorld().getName());
            Main.data.set("Spawn.x", Double.valueOf(p.getLocation().getX()));
            Main.data.set("Spawn.y", Double.valueOf(p.getLocation().getY()));
            Main.data.set("Spawn.z", Double.valueOf(p.getLocation().getZ()));
            Main.data.set("Spawn.yaw", Float.valueOf(p.getLocation().getYaw()));
            Main.data.set("Spawn.pitch", Float.valueOf(p.getLocation().getPitch()));
            try
            {
              Main.data.save(Main.d);
            }
            catch (IOException e)
            {
              e.printStackTrace();
            }
            p.sendMessage(Main.plugin.getConfig().getString("SpawnSetado").replaceAll("&", "§"));
          }
          if (args[1].equalsIgnoreCase("pos1"))
          {
            Main.data.set("Pos1.world", p.getWorld().getName());
            Main.data.set("Pos1.x", Double.valueOf(p.getLocation().getX()));
            Main.data.set("Pos1.y", Double.valueOf(p.getLocation().getY()));
            Main.data.set("Pos1.z", Double.valueOf(p.getLocation().getZ()));
            Main.data.set("Pos1.yaw", Float.valueOf(p.getLocation().getYaw()));
            Main.data.set("Pos1.pitch", Float.valueOf(p.getLocation().getPitch()));
            try
            {
              Main.data.save(Main.d);
            }
            catch (IOException e)
            {
              e.printStackTrace();
            }
            p.sendMessage(Main.plugin.getConfig().getString("Pos1Setada").replaceAll("&", "§"));
          }
          if (args[1].equalsIgnoreCase("pos2"))
          {
            Main.data.set("Pos2.world", p.getWorld().getName());
            Main.data.set("Pos2.x", Double.valueOf(p.getLocation().getX()));
            Main.data.set("Pos2.y", Double.valueOf(p.getLocation().getY()));
            Main.data.set("Pos2.z", Double.valueOf(p.getLocation().getZ()));
            Main.data.set("Pos2.yaw", Float.valueOf(p.getLocation().getYaw()));
            Main.data.set("Pos2.pitch", Float.valueOf(p.getLocation().getPitch()));
            try
            {
              Main.data.save(Main.d);
            }
            catch (IOException e)
            {
              e.printStackTrace();
            }
            p.sendMessage(Main.plugin.getConfig().getString("Pos2Setada").replaceAll("&", "§"));
          }
          if (args[1].equalsIgnoreCase("saida"))
          {
            Main.data.set("Saida.world", p.getWorld().getName());
            Main.data.set("Saida.x", Double.valueOf(p.getLocation().getX()));
            Main.data.set("Saida.y", Double.valueOf(p.getLocation().getY()));
            Main.data.set("Saida.z", Double.valueOf(p.getLocation().getZ()));
            Main.data.set("Saida.yaw", Float.valueOf(p.getLocation().getYaw()));
            Main.data.set("Saida.pitch", Float.valueOf(p.getLocation().getPitch()));
            try
            {
              Main.data.save(Main.d);
            }
            catch (IOException e)
            {
              e.printStackTrace();
            }
            sendConfigMessage(p, "SaidaSetada");
          }
        }
      }
    }
    return false;
  }
  
  public void sendConfigMessage(Player p, String path)
  {
    p.sendMessage(Main.plugin.getConfig().getString(path).replaceAll("&", "§"));
  }
  
  public static void limparInv(Player p)
  {
    p.getInventory().clear();
    p.getEquipment().setArmorContents(new ItemStack[] { new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR) });
  }
  
  public void reloadPlugin()
  {
    try
    {
      Main.data.save(Main.d);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    Main.data = YamlConfiguration.loadConfiguration(Main.d);
    try
    {
      Main.kit.save(Main.k);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    Main.kit = YamlConfiguration.loadConfiguration(Main.k);
    try
    {
      Main.stats.save(Main.s);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    Main.stats = YamlConfiguration.loadConfiguration(Main.s);
    
    ItemManager.loadItems();
  }
}
