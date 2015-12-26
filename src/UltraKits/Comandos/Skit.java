package UltraKits.Comandos;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import UltraKits.Main;

public class Skit
  implements Listener, CommandExecutor
{
  @SuppressWarnings({ "unchecked", "rawtypes" })
public HashMap<String, ItemStack[]> kits = new HashMap();
  @SuppressWarnings({ "unchecked", "rawtypes" })
public HashMap<String, ItemStack[]> armor = new HashMap();
  
  public boolean isInt(String s)
  {
    try
    {
      Integer.parseInt(s);
      return true;
    }
    catch (NumberFormatException localNumberFormatException) {}
    return false;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (!(sender instanceof Player))
    {
      sender.sendMessage(ChatColor.RED + "Somente jogadores podem executar este comando!");
      return true;
    }
    Player p = (Player)sender;
    if ((cmd.getName().equalsIgnoreCase("skit")) && (
      (p.hasPermission("uk.skit")) || (p.isOp())))
    {
      if (args.length == 0)
      {
        p.sendMessage(ChatColor.AQUA + "Como criar um skit(kit para eventos):");
        p.sendMessage(ChatColor.GRAY + "1º - Monte seu inventario da maneira como quer que os jogadores recebam");
        p.sendMessage(ChatColor.GRAY + "2º - Digite /skit criar <nome>");
        p.sendMessage(ChatColor.RED + "");
        p.sendMessage(ChatColor.GREEN + "Como aplicar um skit:");
        p.sendMessage(ChatColor.GRAY + "1º - Digite /skit aplicar <nome> <raio>");
        p.sendMessage(ChatColor.RED + "OBS.:" + ChatColor.GRAY + " O kit precisa ter sido criado previamente!");
        return true;
      }
      if (args[0].equalsIgnoreCase("criar"))
      {
        if (args.length == 1)
        {
          p.sendMessage(ChatColor.AQUA + "Como criar um skit:");
          p.sendMessage(ChatColor.GRAY + "1º - Monte seu inventario da maneira como quer que os jogadores recebam");
          p.sendMessage(ChatColor.GRAY + "2º - Digite /skit criar <nome>");
          return true;
        }
        String name = args[1];
        this.kits.put(name, p.getInventory().getContents());
        this.armor.put(name, p.getInventory().getArmorContents());
        p.sendMessage(ChatColor.GREEN + "Kit " + args[1] + " criado com sucesso!");
        return true;
      }
      if (args[0].equalsIgnoreCase("aplicar"))
      {
        if (args.length <= 2)
        {
          p.sendMessage(ChatColor.GREEN + "Como aplicar um skit:");
          p.sendMessage(ChatColor.GRAY + "1º - Digite /skit aplicar <nome> <raio>");
          p.sendMessage(ChatColor.RED + "OBS.:" + ChatColor.GRAY + " O kit precisa ter sido criado previamente!");
          return true;
        }
        String name = args[1];
        if ((!this.kits.containsKey(name)) && (!this.armor.containsKey(name)))
        {
          p.sendMessage(ChatColor.RED + "Kit " + name + " nao encontrado!");
          return true;
        }
        if (isInt(args[2]))
        {
          int numero = Integer.parseInt(args[2]);
          for (Entity ent : p.getNearbyEntities(numero, numero, numero)) {
            if ((ent instanceof Player))
            {
              Player plr = (Player)ent;
              plr.getInventory().setArmorContents((ItemStack[])this.armor.get(name));
              plr.getInventory().setContents((ItemStack[])this.kits.get(name));
            }
          }
          Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "Kit " + name + " aplicado para jogadores em um raio de " + numero + " blocos");
          p.sendMessage(ChatColor.GREEN + "Kit " + name + " aplicado para jogadores em um raio de " + numero + " blocos");
          return true;
        }
        return true;
      }
    }
    if ((cmd.getName().equalsIgnoreCase("togglepvp")) && (
      (p.hasPermission("uk.togglepvp")) || (p.isOp())))
    {
      if (p.getWorld().getPVP())
      {
        p.getWorld().setPVP(false);
        Bukkit.getServer().broadcastMessage(Main.plugin.getConfig().getString("PvPLigado").replaceAll("&", "§"));
        return true;
      }
      p.getWorld().setPVP(true);
      Bukkit.getServer().broadcastMessage(Main.plugin.getConfig().getString("PvPDesligado").replaceAll("&", "§"));
      return true;
    }
    return true;
  }
}
