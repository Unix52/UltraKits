package UltraKits.Comandos;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.PlayerInventory;

import UltraKits.Main;

public class Admin
  implements CommandExecutor, Listener
{
  @SuppressWarnings({ "unchecked", "rawtypes" })
public static ArrayList<String> vanish = new ArrayList();
  
  @SuppressWarnings("deprecation")
public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args)
  {
    if (!(sender instanceof Player))
    {
      sender.sendMessage("[UltraKits] Comando apenas para players in-game.");
      return true;
    }
    Player p = (Player)sender;
    if (!p.hasPermission("uk.admin"))
    {
      p.sendMessage(ChatColor.RED + "Voce nao tem permissao.");
      return true;
    }
    if (cmd.equalsIgnoreCase("v"))
    {
      if (vanish.contains(p.getName()))
      {
        vanish.remove(p.getName());
        for (Player o : Bukkit.getOnlinePlayers()) {
          o.showPlayer(p);
        }
        p.sendMessage(ChatColor.GREEN + "Todos podem te ver agora.");
      }
      else
      {
        vanish.add(p.getName());
        for (Player o : Bukkit.getOnlinePlayers()) {
          o.hidePlayer(p);
        }
        p.sendMessage(ChatColor.GOLD + "Ninguem consegue te ver agora.");
      }
    }
    else if (cmd.equalsIgnoreCase("admin"))
    {
      if (Main.admin.contains(p.getName()))
      {
        Main.admin.remove(p.getName());
        Bukkit.broadcastMessage(ChatColor.GREEN + "+ " + ChatColor.RESET + p.getDisplayName() + ChatColor.RESET + ChatColor.GRAY + " entrou no servidor");
        for (Player s1 : Bukkit.getOnlinePlayers()) {
          s1.showPlayer(p);
        }
        p.sendMessage(ChatColor.LIGHT_PURPLE + "Voce saiu do modo admin.");
        p.setGameMode(GameMode.SURVIVAL);
      }
      else
      {
        Main.admin.add(p.getName());
        for (Player s1 : Bukkit.getOnlinePlayers()) {
          s1.hidePlayer(p);
        }
        Bukkit.broadcastMessage(ChatColor.RED + "- " + ChatColor.RESET + p.getDisplayName() + ChatColor.RESET + ChatColor.GRAY + "  saiu do servidor");
        p.sendMessage(ChatColor.LIGHT_PURPLE + "Voce entrou no modo admin.");
        p.setGameMode(GameMode.CREATIVE);
      }
    }
    else if (cmd.equalsIgnoreCase("invsee"))
    {
      if (args.length == 0)
      {
        p.sendMessage(ChatColor.GOLD + "/invsee " + ChatColor.RED + "<player>");
        return true;
      }
      if (args.length == 1)
      {
        Player target = Bukkit.getPlayer(args[0]);
        if (target != null) {
          verInv(p, target);
        } else {
          p.sendMessage(ChatColor.RED + "Este player nao está online.");
        }
      }
    }
    else if (cmd.equalsIgnoreCase("ac"))
    {
      if (sender.hasPermission("uk.ac"))
      {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
          sb.append(args[i] + " ");
        }
        String allArgs = sb.toString().trim();
        for (Player staff : Bukkit.getServer().getOnlinePlayers()) {
          if (staff.hasPermission("uk.admin")) {
            staff.sendMessage("§4[ADMIN-CHAT] " + p.getName() + " §cMSG: §f" + allArgs);
          }
        }
      }
      return true;
    }
    return false;
  }
  
  @EventHandler
  public void adminListener(PlayerInteractEntityEvent e)
  {
    if ((e.getRightClicked() instanceof Player))
    {
      Player p = e.getPlayer();
      Player target = (Player)e.getRightClicked();
      if ((Main.admin.contains(p.getName())) && (p.hasPermission("uk.admin"))) {
        verInv(p, target);
      }
    }
  }
  
  public static void verInv(Player p, Player de)
  {
    PlayerInventory inv = de.getInventory();
    p.openInventory(inv);
  }
}
