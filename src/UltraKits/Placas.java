package UltraKits;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;

public class Placas
  implements Listener
{
  String servername = Main.plugin.getConfig().getString("ServerName");
  
  @EventHandler
  public void criar(SignChangeEvent e)
  {
    Player p = e.getPlayer();
    if (e.getLine(0).equalsIgnoreCase("[" + this.servername + "]"))
    {
      if (!p.hasPermission("uk.placas"))
      {
        e.getBlock().breakNaturally();
        p.sendMessage(ChatColor.RED + "Voce nao tem permissao.");
        return;
      }
      if (e.getLine(1).equalsIgnoreCase("sopas"))
      {
        e.setLine(0, "§o§l[" + this.servername + "]");
        e.setLine(1, "§6§lSopas");
      }
      else if (e.getLine(1).equalsIgnoreCase("pocoes"))
      {
          e.setLine(0, "§o§l[" + this.servername + "]");
        e.setLine(1, "§d§lPocoes");
      }
      else if (e.getLine(1).equalsIgnoreCase("potes"))
      {
          e.setLine(0, "§o§l[" + this.servername + "]");
        e.setLine(1, "§4§lPotes");
      }
      else if (e.getLine(1).equalsIgnoreCase("cogumelos"))
      {
          e.setLine(0, "§o§l[" + this.servername + "]");
        e.setLine(1, "§b§lCogumelos");
      }
      else if (e.getLine(1).equalsIgnoreCase("reparar"))
      {
          e.setLine(0, "§o§l[" + this.servername + "]");
        e.setLine(1, "§a§lReparar");
      }
    }
  }
  
  @EventHandler
  public void clicar(PlayerInteractEvent e)
  {
    Player p = e.getPlayer();
    if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) && (
      (e.getClickedBlock().getType() == Material.SIGN_POST) || (e.getClickedBlock().getType() == Material.WALL_SIGN)))
    {
      Sign s = (Sign)e.getClickedBlock().getState();
      if (s.getLine(0).equalsIgnoreCase("§o§l[" + this.servername + "]")) {
        if (ChatColor.stripColor(s.getLine(1)).equalsIgnoreCase("Sopas"))
        {
          Inventory sop = Bukkit.createInventory(p, 54, ChatColor.BLUE + "Sopas Gratis");
          for (int i = 0; i < sop.getSize(); i++) {
            sop.setItem(i, new ItemStack(Material.MUSHROOM_SOUP));
          }
          p.openInventory(sop);
        }
        else if (ChatColor.stripColor(s.getLine(1)).equalsIgnoreCase("Pocoes"))
        {
          Inventory sop = Bukkit.createInventory(p, 36, ChatColor.BLUE + "Pocoes Gratis");
          for (int i = 0; i < sop.getSize(); i++)
          {
            ItemStack potion = new ItemStack(Material.POTION);
            potion.setDurability((short)16421);
            sop.setItem(i, potion);
          }
          p.openInventory(sop);
        }
        else if (ChatColor.stripColor(s.getLine(1)).equalsIgnoreCase("Potes"))
        {
          Inventory sop = Bukkit.createInventory(p, 36, ChatColor.BLUE + "Potes Gratis");
          for (int i = 0; i < sop.getSize(); i++)
          {
            ItemStack potion = new ItemStack(Material.BOWL, 64);
            sop.setItem(i, potion);
          }
          p.openInventory(sop);
        }
        else
        {
          int i;
          ItemStack potion;
          if (ChatColor.stripColor(s.getLine(1)).equalsIgnoreCase("Cogumelos"))
          {
            Inventory sop = Bukkit.createInventory(p, 36, ChatColor.BLUE + "Cogumelos Gratis");
            for (int i1 = 0; i1 < 18; i1++)
            {
              ItemStack potion1 = new ItemStack(Material.BROWN_MUSHROOM, 64);
              sop.setItem(i1, potion1);
            }
            for (i = 18; i < 36; i++)
            {
              potion = new ItemStack(Material.RED_MUSHROOM, 64);
              sop.setItem(i, potion);
            }
            p.openInventory(sop);
          }
          else if (ChatColor.stripColor(s.getLine(1)).equalsIgnoreCase("Reparar"))
          {
            for (ItemStack is : p.getInventory().getContents()) {
              try
              {
                is.setDurability((short)0);
              }
              catch (NullPointerException localNullPointerException) {}
            }
            for (ItemStack is : p.getEquipment().getArmorContents()) {
              try
              {
                is.setDurability((short)0);
              }
              catch (NullPointerException localNullPointerException1) {}
            }
            p.sendMessage(ChatColor.GREEN + "Seus itens foram reparados com sucesso.");
          }
        }
      }
    }
  }
}
