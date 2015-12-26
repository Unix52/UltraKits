package UltraKits.Comandos;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import UltraKits.Main;

public class KitReset
  implements CommandExecutor
{
  public static Main plugin;
  
  public KitReset(Main main)
  {
    plugin = main;
  }
  
  public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args)
  {
    Player p = (Player)sender;
    if (cmd.equalsIgnoreCase("kitreset"))
    {
      if (!p.hasPermission("uk.kitreset"))
      {
        p.sendMessage(ChatColor.RED + "Voce nao tem permissao!");
        return true;
      }
      p.sendMessage(Main.plugin.getConfig().getString("KitResetado").replaceAll("&", "§"));
      Main.resetKit(p);
      Main.restaurarItens(p);
    }
    return false;
  }
}
