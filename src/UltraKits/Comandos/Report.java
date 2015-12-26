package UltraKits.Comandos;

import me.confuser.barapi.BarAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import UltraKits.Main;

public class Report
  implements CommandExecutor, Listener
{
  public String[] aliases = { "report" };
  public String description = "Reportar";
  
  @SuppressWarnings("deprecation")
public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
  {
    Player p = (Player)sender;
    if (args.length < 2)
    {
      p.sendMessage(ChatColor.RED + "Sintaxe correta: /report <jogador> <motivo>");
    }
    else if (args.length >= 2)
    {
      Player target = Bukkit.getPlayer(args[0]);
      if (target == null)
      {
        p.sendMessage(ChatColor.RED + "Esse jogador não existe!");
      }
      else
      {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
          sb.append(args[i]).append(" ");
        }
        String allArgs = sb.toString().trim();
        
        sender.sendMessage(ChatColor.GREEN + "Seu report foi enviado com sucesso!");
        for (Player staff : Bukkit.getServer().getOnlinePlayers()) {
          if (staff.hasPermission("uk.ver"))
          {
            staff.sendMessage("§4[" + Main.plugin.getConfig().getString("ServerName") + "] " + ChatColor.RED + ChatColor.ITALIC + target.getName() + ChatColor.GRAY + " foi reportado por " + ChatColor.RED + ChatColor.ITALIC + sender.getName() + ChatColor.GRAY + "!");
            staff.sendMessage("§4[" + Main.plugin.getConfig().getString("ServerName") + "] " + ChatColor.RED + "Motivo: " + ChatColor.GRAY + ChatColor.ITALIC + allArgs);
            BarAPI.setMessage(staff, ChatColor.DARK_RED + "Um jogador foi reportado!", 10);
            staff.playSound(staff.getLocation(), Sound.GHAST_SCREAM, 7.0F, 7.0F);
          }
        }
      }
    }
    return true;
  }
}
