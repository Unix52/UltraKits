package UltraKits.Comandos;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import UltraKits.Main;
import UltraKits.SettingsManager;
import UltraKits.u1v1.Commands;

public class Comandos
  implements CommandExecutor
{
  public Main plugin;
  
  public Comandos(Main plugin)
  {
    this.plugin = plugin;
  }
  
  SettingsManager settings = SettingsManager.getInstance();
  
  @SuppressWarnings("deprecation")
public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
  {
    String cm = cmd.getName();
    if ((cmd.getName().equalsIgnoreCase("tpall")) && 
      (sender.hasPermission("uk.tpall")))
    {
      Player s = (Player)sender;
      for (Player player : Bukkit.getOnlinePlayers()) {
        player.teleport(s.getLocation());
      }
      Bukkit.getServer().broadcastMessage(Main.plugin.getConfig().getString("MensagemTpAll").replaceAll("&", "§"));
      return true;
    }
    final Player p = Bukkit.getPlayer(sender.getName());
    if ((cm.equalsIgnoreCase("setspawn")) && 
      (args.length == 0) && 
      (p.hasPermission("uk.setspawn")))
    {
      this.settings.getData().set("spawn.world", p.getLocation().getWorld().getName());
      this.settings.getData().set("spawn.x", Double.valueOf(p.getLocation().getX()));
      this.settings.getData().set("spawn.y", Double.valueOf(p.getLocation().getY()));
      this.settings.getData().set("spawn.z", Double.valueOf(p.getLocation().getZ()));
      this.settings.getData().set("spawn.pitch", Float.valueOf(p.getLocation().getPitch()));
      this.settings.getData().set("spawn.yaw", Float.valueOf(p.getLocation().getYaw()));
      this.settings.saveData();
      p.sendMessage("§aVoce selecionou o local do spawn!");
    }
    if (cmd.getName().equalsIgnoreCase("kick"))
    {
      if (p.hasPermission("uk.kick"))
      {
        if (args.length == 0)
        {
          p.sendMessage(ChatColor.DARK_RED + "Uso Correto: /kick <jogador> <motivo>");
          return true;
        }
        if (args.length == 1)
        {
          Player t = Bukkit.getPlayerExact(args[0]);
          if (t == null)
          {
            p.sendMessage(ChatColor.RED + "Jogador " + args[0] + " nao encontrado!");
            return true;
          }
          p.sendMessage(ChatColor.GREEN + "Voce kickou " + ChatColor.GREEN + t.getName());
          Bukkit.getServer().broadcastMessage(ChatColor.RED + t.getName() + ChatColor.GRAY + " foi kickado do servidor por " + ChatColor.RED + p.getName() + ChatColor.GRAY + "!");
          t.kickPlayer(ChatColor.RED + "Voce foi kickado do servidor!");
          return true;
        }
        if (args.length >= 2)
        {
          Player t = Bukkit.getPlayerExact(args[0]);
          if (t == null)
          {
            p.sendMessage(ChatColor.RED + "Jogador " + args[0] + ChatColor.RED + " nao encontrado");
            return true;
          }
          StringBuilder str = new StringBuilder();
          for (int i = 1; i < args.length; i++) {
            str.append(args[i]).append(" ");
          }
          String msg = str.toString();
          p.sendMessage(ChatColor.GRAY + "Voce kickou " + ChatColor.RED + t.getName() + ChatColor.GRAY + " por " + ChatColor.RED + msg);
          Bukkit.getServer().broadcastMessage(ChatColor.RED + t.getName() + ChatColor.GRAY + " foi kickado do servidor por " + ChatColor.RED + msg + ChatColor.GRAY + " pelo " + ChatColor.RED + p.getName() + ChatColor.GRAY + "!");
          t.kickPlayer(ChatColor.GRAY + "Voce foi kickado do servidor por " + ChatColor.RED + msg);
          return true;
        }
        return true;
      }
      p.sendMessage(ChatColor.DARK_RED + "Voce nao tem permissao!");
      return true;
    }
    if (commandLabel.equalsIgnoreCase("mute")) {
      if (sender.hasPermission("uk.mute"))
      {
        if (args.length == 0)
        {
          p.sendMessage(ChatColor.RED + "Sintaxe correta: /mute <jogador>");
        }
        else if (args.length == 1)
        {
          Player player = Bukkit.getPlayer(args[0]);
          if (player == null)
          {
            p.sendMessage(ChatColor.RED + "Voce nao pode silenciar um jogador offline");
            return true;
          }
          p.sendMessage(ChatColor.GREEN + "Jogador silenciado!");
          player.sendMessage(ChatColor.RED + "Voce foi silenciado, portanto nao podera falar!");
          Main.mute.add(player.getName());
        }
      }
      else {
        p.sendMessage(ChatColor.DARK_RED + "Voce nao tem permissao!");
      }
    }
    if (commandLabel.equalsIgnoreCase("morrer"))
    {
      p.setHealth(0.0D);
      p.sendMessage(ChatColor.RED + "Voce Se Matou!");
    }
    if (commandLabel.equalsIgnoreCase("desmute")) {
      if (sender.hasPermission("uk.desmute"))
      {
        if (args.length == 0)
        {
          p.sendMessage(ChatColor.DARK_GREEN + "Sintaxe correta: /desmute <jogador>");
        }
        else if (args.length == 1)
        {
          Player player = Bukkit.getPlayer(args[0]);
          if (player == null)
          {
            p.sendMessage(ChatColor.RED + "Nao e possivel deixar que um jogador offline volte a falar!");
            return true;
          }
          p.sendMessage(ChatColor.GREEN + "O jogador ja pode falar novamente");
          Main.mute.remove(player.getName());
        }
      }
      else {
        p.sendMessage(ChatColor.DARK_RED + "Voce Nao Tem Permissao!");
      }
    }
    if (cm.equalsIgnoreCase("spawn"))
    {
      p.sendMessage(ChatColor.GREEN + "Aguarde 5 segundos para se teleportar!");
      Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
      {
        public void run()
        {
          try
          {
            if (!Commands.em.contains(p.getName()))
            {
              World w = Bukkit.getServer().getWorld(Comandos.this.settings.getData().getString("spawn.world"));
              double x = Comandos.this.settings.getData().getDouble("spawn.x");
              double y = Comandos.this.settings.getData().getDouble("spawn.y");
              double z = Comandos.this.settings.getData().getDouble("spawn.z");
              Location loc1 = new Location(w, x, y, z);
              loc1.setPitch((float)Comandos.this.settings.getData().getDouble("spawn.pitch"));
              loc1.setYaw((float)Comandos.this.settings.getData().getDouble("spawn.yaw"));
              p.teleport(loc1);
              Main.resetKit(p);
              Main.restaurarItens(p);
            }
            else
            {
              p.sendMessage(ChatColor.RED + "Voce nao pode digitar este comando no 1v1. Digite /1v1 para sair.");
            }
          }
          catch (IllegalArgumentException e)
          {
            p.sendMessage(ChatColor.RED + "Spawn nao definido ainda. Peça a um Admin para seta-lo.");
          }
        }
      }, 100L);
    }
    if (cmd.getName().equalsIgnoreCase("head")) {
      if (p.hasPermission("uk.head"))
      {
        if (args.length == 0)
        {
          p.sendMessage(ChatColor.RED + "Sintaxe correta : /head <player>");
          return true;
        }
        if (args.length == 1)
        {
          ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.PLAYER.ordinal());
          SkullMeta meta = (SkullMeta)skull.getItemMeta();
          meta.setOwner(args[0]);
          meta.setDisplayName(ChatColor.GOLD + "Cabeca de " + args[0]);
          skull.setItemMeta(meta);
          
          p.getInventory().addItem(new ItemStack[] { skull });
          p.sendMessage(ChatColor.GREEN + "Voce recebeu a cabeca de " + args[0]);
          return true;
        }
      }
      else
      {
        p.sendMessage(ChatColor.DARK_RED + "Voce nao tem permissao");
        return true;
      }
    }
    if ((commandLabel.equalsIgnoreCase("fly")) && 
      (sender.hasPermission("uk.fly"))) {
      if (p.getAllowFlight())
      {
        p.setFlying(false);
        p.setAllowFlight(false);
        p.sendMessage(ChatColor.RED + "Modo de voo desativado");
      }
      else
      {
        p.setAllowFlight(true);
        p.setFlySpeed(0.3F);
        p.sendMessage(ChatColor.GREEN + "Modo de voo ativado");
      }
    }
    if (cm.equalsIgnoreCase("gm"))
    {
      if (!p.hasPermission("uk.gm"))
      {
        p.sendMessage(ChatColor.RED + "Voce nao tem permissao.");
        return true;
      }
      if (args.length == 0)
      {
        if (p.getGameMode() == GameMode.SURVIVAL) {
          p.setGameMode(GameMode.CREATIVE);
        } else if (p.getGameMode() == GameMode.CREATIVE) {
          p.setGameMode(GameMode.SURVIVAL);
        }
        p.sendMessage(ChatColor.GOLD + "GameMode alterado para: " + ChatColor.RED + p.getGameMode().name());
        return true;
      }
      if (args.length == 1)
      {
        if (args[0].equalsIgnoreCase("0")) {
          p.setGameMode(GameMode.SURVIVAL);
        } else if (args[0].equalsIgnoreCase("1")) {
          p.setGameMode(GameMode.CREATIVE);
        }
        p.sendMessage(ChatColor.GOLD + "GameMode alterado para: " + ChatColor.RED + p.getGameMode().name());
        return true;
      }
    }
    return true;
  }
}
