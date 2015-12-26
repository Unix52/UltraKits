package UltraKits.Comandos;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitTask;

public class Chat
  implements CommandExecutor, Listener
{
  @SuppressWarnings({ "unchecked", "rawtypes" })
public static ArrayList<BukkitTask> task = new ArrayList();
  
  public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args)
  {
    if (!(sender instanceof Player))
    {
      sender.sendMessage("[UltraKits] Comando apenas para players in-game.");
      return true;
    }
    Player p = (Player)sender;
    if (cmd.equalsIgnoreCase("cc"))
    {
      if (!p.hasPermission("uk.cc"))
      {
        p.sendMessage(ChatColor.RED + "Voce nao tem permissao.");
        return true;
      }
      Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage(" ");Bukkit.broadcastMessage("§f§l                   Chat Limpo");
    }
    return false;
  }
  
  @EventHandler
  public void aoFalar(AsyncPlayerChatEvent e)
  {
    if ((e.getPlayer().hasPermission("uk.chatcolor")) || (e.getPlayer().hasPermission("uk.chatcolor"))) {
      e.setFormat(e.getPlayer().getDisplayName() + ChatColor.GRAY + ": " + ChatColor.RESET + e.getMessage().replaceAll("&", "§"));
    } else {
      e.setFormat(e.getPlayer().getDisplayName() + ChatColor.GRAY + ": " + ChatColor.RESET + e.getMessage());
    }
  }
}
