package UltraKits.Comandos;

import java.io.IOException;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import UltraKits.Main;

public class Warps
  implements CommandExecutor
{
  @SuppressWarnings("deprecation")
public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args)
  {
    if (!(sender instanceof Player))
    {
      sender.sendMessage("[UltraKits] Comando apenas para players in-game.");
      return true;
    }
    Player p = (Player)sender;
    if (!p.hasPermission("uk.warp"))
    {
      p.sendMessage(ChatColor.RED + "Voce nao tem permissao.");
      return true;
    }
    if (cmd.equalsIgnoreCase("setwarp"))
    {
      if (args.length == 0) {
        return true;
      }
      if (args.length == 1)
      {
        if (Main.warps.getConfigurationSection(args[0]) != null)
        {
          p.sendMessage(ChatColor.RED + "Esta warp ja existe. Delete-a primeiro.");
          return true;
        }
        Main.warps.set(args[0] + ".world", p.getWorld().getName());
        Main.warps.set(args[0] + ".x", Double.valueOf(p.getLocation().getX()));
        Main.warps.set(args[0] + ".y", Double.valueOf(p.getLocation().getY()));
        Main.warps.set(args[0] + ".z", Double.valueOf(p.getLocation().getZ()));
        Main.warps.set(args[0] + ".item", Integer.valueOf(1));
        Main.warps.set(args[0] + ".limpar", Boolean.valueOf(false));
        Main.warps.set(args[0] + ".comando", "nenhum");
        List<String> warps = Main.warps.getStringList("Warps");
        warps.add(args[0]);
        Main.warps.set("Warps", warps);
        try
        {
          Main.warps.save(Main.w);
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
        p.sendMessage(ChatColor.GREEN + "Warp " + ChatColor.WHITE + args[0] + ChatColor.GREEN + " setada com sucesso.");
      }
    }
    else if (cmd.equalsIgnoreCase("witem"))
    {
      if (args.length == 0)
      {
        p.sendMessage(ChatColor.GOLD + "/witem " + ChatColor.RED + "<warp> <id>");
        return true;
      }
      if (args.length == 1)
      {
        p.sendMessage(ChatColor.GOLD + "/witem " + ChatColor.RED + "<warp> <id>");
        return true;
      }
      if (args.length == 2) {
        try
        {
          int id = Integer.valueOf(args[1]).intValue();
          Material mat = Material.getMaterial(id);
          if (mat != null)
          {
            if (Main.warps.getConfigurationSection(args[0]) == null)
            {
              p.sendMessage(ChatColor.RED + "Esta warp ainda nao foi setada.");
              return true;
            }
            Main.warps.set(args[0] + ".item", Integer.valueOf(id));
            try
            {
              Main.warps.save(Main.w);
            }
            catch (IOException e)
            {
              e.printStackTrace();
            }
            p.sendMessage(ChatColor.GREEN + "Item da warp " + ChatColor.WHITE + args[0] + ChatColor.GREEN + " alterado para o ID: " + ChatColor.WHITE + id);
          }
          else
          {
            p.sendMessage(ChatColor.RED + "Material inexistente.");
          }
        }
        catch (NumberFormatException e)
        {
          p.sendMessage(ChatColor.RED + "Use apenas numeros em ID.");
        }
      }
    }
    else if (cmd.equalsIgnoreCase("delwarp"))
    {
      if (args.length == 0) {
        return true;
      }
      if (args.length == 1)
      {
        List<String> warps = Main.warps.getStringList("Warps");
        if (Main.warps.getConfigurationSection(args[0]) == null)
        {
          p.sendMessage(ChatColor.RED + "Esta warp nao existe.");
          return true;
        }
        Main.warps.set(args[0], null);
        if (warps.contains(args[0])) {
          warps.remove(args[0]);
        }
        Main.warps.set("Warps", warps);
        try
        {
          Main.warps.save(Main.w);
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
        Main.warps = YamlConfiguration.loadConfiguration(Main.w);
        p.sendMessage(ChatColor.GREEN + "Warp " + ChatColor.WHITE + args[0] + ChatColor.GREEN + " deletada com sucesso.");
      }
    }
    else if (cmd.equalsIgnoreCase("wclear"))
    {
      if (args.length == 0) {
        return true;
      }
      if (args.length == 1) {
        return true;
      }
      if (args.length == 2)
      {
        List<String> warps = Main.warps.getStringList("Warps");
        if (warps.contains(args[0]))
        {
          if (args[1].equalsIgnoreCase("true"))
          {
            Main.warps.set(args[0] + ".limpar", Boolean.valueOf(true));
            try
            {
              Main.warps.save(Main.w);
            }
            catch (IOException e)
            {
              e.printStackTrace();
            }
            p.sendMessage(ChatColor.GOLD + "'Limpar' da warp " + ChatColor.WHITE + args[0] + ChatColor.GOLD + " foi definido para " + ChatColor.GREEN + "true");
          }
          else if (args[1].equalsIgnoreCase("false"))
          {
            Main.warps.set(args[0] + ".limpar", Boolean.valueOf(false));
            try
            {
              Main.warps.save(Main.w);
            }
            catch (IOException e)
            {
              e.printStackTrace();
            }
            p.sendMessage(ChatColor.GOLD + "'Limpar' da warp " + ChatColor.WHITE + args[0] + ChatColor.GOLD + " foi definido para " + ChatColor.RED + "false");
          }
        }
        else {
          return true;
        }
      }
    }
    else if (cmd.equalsIgnoreCase("wcmd"))
    {
      if (args.length == 0) {
        return true;
      }
      if (args.length == 1) {
        return true;
      }
      if (args.length >= 2)
      {
        List<String> warps = Main.warps.getStringList("Warps");
        if (warps.contains(args[0]))
        {
          String comando = "";
          if (!args[1].equalsIgnoreCase("nenhum"))
          {
            for (int i = 1; i < args.length; i++) {
              comando = comando + " " + args[i];
            }
            Main.warps.set(args[0] + ".comando", comando.replaceFirst(" ", ""));
            try
            {
              Main.warps.save(Main.w);
            }
            catch (IOException e)
            {
              e.printStackTrace();
            }
            p.sendMessage(ChatColor.GREEN + "Comando da warp " + ChatColor.WHITE + args[0] + ChatColor.GREEN + " alterado para: " + ChatColor.WHITE + "/" + comando.replaceFirst(" ", ""));
          }
          else
          {
            Main.warps.set(args[0] + ".comando", "nenhum");
            try
            {
              Main.warps.save(Main.w);
            }
            catch (IOException e)
            {
              e.printStackTrace();
            }
            p.sendMessage(ChatColor.GREEN + "Nenhum comando para a warp " + ChatColor.WHITE + args[0]);
          }
        }
        else
        {
          p.sendMessage(ChatColor.RED + "Esta warp nao existe.");
        }
      }
    }
    return false;
  }
}
