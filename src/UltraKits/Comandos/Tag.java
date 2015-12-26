package UltraKits.Comandos;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tag
  implements CommandExecutor
{
  public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args)
  {
    if (!(sender instanceof Player))
    {
      sender.sendMessage("[UltraKits]");
      return true;
    }
    Player p = (Player)sender;
    if (cmd.equalsIgnoreCase("cor"))
    {
      if (!p.hasPermission("uk.cor")) {
        return true;
      }
      if (args.length == 0)
      {
        p.sendMessage(ChatColor.GOLD + "/cor " + ChatColor.RED + "<cor> " + ChatColor.WHITE + "ou " + ChatColor.GOLD + "/cor " + ChatColor.RED + "<player> <cor>");
        return true;
      }
      if (args.length == 1)
      {
        try
        {
          ChatColor cor = ChatColor.valueOf(args[0].toUpperCase());
          if (p.getName().length() + 4 <= 16)
          {
            p.setPlayerListName(cor + p.getName() + ChatColor.RESET);
            p.setDisplayName(cor + p.getName() + ChatColor.RESET);
            p.sendMessage(ChatColor.GREEN + "Alterada a cor do seu nome para: " + cor + cor.name());
          }
          else
          {
            p.sendMessage(ChatColor.RED + "Seu nome é grande demais para usar uma cor.");
          }
        }
        catch (IllegalArgumentException ex)
        {
          p.sendMessage(ChatColor.RED + "Cor inexistente ou incorretamente especificada.");
        }
      }
      else if (args.length == 2)
      {
        Player target = Bukkit.getPlayer(args[0]);
        if (target != null) {
          try
          {
            ChatColor cor = ChatColor.valueOf(args[1].toUpperCase());
            if (target.getName().length() + 4 <= 16)
            {
              target.setPlayerListName(cor + target.getName() + ChatColor.RESET);
              target.setDisplayName(cor + target.getName() + ChatColor.RESET);
              target.sendMessage(ChatColor.GREEN + "Alterada a cor do seu nome para: " + cor + cor.name());
              p.sendMessage(ChatColor.GREEN + "A cor do nome de " + ChatColor.WHITE + target.getName() + ChatColor.GREEN + " foi alterada para: " + cor + cor.name());
            }
            else
            {
              p.sendMessage(ChatColor.RED + "O nome do jogador requerido é muito grande para receber cores.");
            }
          }
          catch (IllegalArgumentException ex)
          {
            p.sendMessage(ChatColor.RED + "Cor inexistente ou incorretamente especificada.");
          }
        } else {
          p.sendMessage(ChatColor.RED + "Este player nao esta online.");
        }
      }
    }
    return false;
  }
}
