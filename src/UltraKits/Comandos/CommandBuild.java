package UltraKits.Comandos;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import UltraKits.Main;

public final class CommandBuild
  implements Listener, CommandExecutor
{
  public static final String prefix = "§4[UltraKits] ";
  String servername = Main.plugin.getConfig().getString("ServerName");
  private static boolean build = false;
  
  public final boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    Player p = (Player)sender;
    if ((cmd.getName().equalsIgnoreCase("build")) && 
      (p.hasPermission("uk.build")))
    {
      if (args.length == 0)
      {
        sender.sendMessage("§b[" + this.servername + "]" + " Construir esta:  " + (build ? ChatColor.GREEN + "ativado" : new StringBuilder().append(ChatColor.RED).append("desativado").toString()));
        sender.sendMessage(ChatColor.RED + "Sintaxe correta:" + ChatColor.RED + "/build [on|off]");
        return true;
      }
      if (args[0].equalsIgnoreCase("on"))
      {
        build = true;
        sender.sendMessage("§b[" + this.servername + "]" + " Construir esta:  " + (build ? ChatColor.GREEN + "ativado" : new StringBuilder().append(ChatColor.RED).append("desativado").toString()));
      }
      else if (args[0].equalsIgnoreCase("off"))
      {
        build = false;
        sender.sendMessage("§b[" + this.servername + "]" + " Construir esta:  " + (build ? ChatColor.RED + "desativado" : new StringBuilder().append(ChatColor.RED).append("desativado").toString()));
      }
      else
      {
        sender.sendMessage(ChatColor.RED + "Sintaxe correta:" + ChatColor.RED + " /build [on|off]");
      }
      return true;
    }
    return true;
  }
  
  @EventHandler(priority=EventPriority.MONITOR)
  public final void onBlockPlace(BlockPlaceEvent event)
  {
    if (!build) {
      event.setCancelled(true);
    }
  }
  
  @EventHandler(priority=EventPriority.MONITOR)
  public final void onBlockBreak(BlockBreakEvent event)
  {
    if (!build) {
      event.setCancelled(true);
    }
  }
}
