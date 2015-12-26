package UltraKits.Inventarios;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import UltraKits.Main;
import UltraKits.Eventos.Events;

public class WarpsListener
  implements Listener
{
  public static Inventory inv = Bukkit.createInventory(null, Main.plugin.getConfig().getInt("WarpSlots"), "§b§lWarps");
  
  @EventHandler
  public void onPlayerCLickInventry(InventoryClickEvent e)
  {
    Player p = (Player)e.getWhoClicked();
    if (e.getCurrentItem() == null) {
      return;
    }
    if (e.getInventory().getName().equals(inv.getName()))
    {
      if (e.getCurrentItem() == null) {
        return;
      }
      e.setCancelled(true);
      String warp = null;
      if (e.getCurrentItem().getType() != Material.AIR)
      {
        p.closeInventory();
        warp = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName());
        try
        {
          p.teleport(new Location(Bukkit.getWorld(Main.warps.getString(warp + ".world")), Main.warps.getDouble(warp + ".x"), Main.warps.getDouble(warp + ".y"), Main.warps.getDouble(warp + ".z")));
          if (Main.warps.getBoolean(warp + ".limpar"))
          {
            p.getEquipment().setArmorContents(new ItemStack[] { new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR) });
            p.getInventory().clear();
          }
          if (Main.warps.getString(warp + ".comando") != "nenhum") {
            p.chat("/" + Main.warps.getString(new StringBuilder(String.valueOf(warp)).append(".comando").toString()));
          }
          p.sendMessage(ChatColor.BLUE + "Teleportado!");
          p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0F, 1.0F);
        }
        catch (NullPointerException ex)
        {
          p.sendMessage(ChatColor.RED + "Erro nesta Warp.");
        }
        catch (IllegalArgumentException ex2)
        {
          p.sendMessage(ChatColor.RED + "Erro nesta Warp.");
        }
      }
    }
  }
  
  @SuppressWarnings("deprecation")
@EventHandler
  public void openMenu(PlayerInteractEvent e)
  {
    Player p = e.getPlayer();
    if (((e.getAction() == Action.RIGHT_CLICK_BLOCK) || (e.getAction() == Action.RIGHT_CLICK_AIR)) && 
      (p.getItemInHand().equals(Events.warps)))
    {
      e.setCancelled(true);
      List<String> warps = Main.warps.getStringList("Warps");
      for (String s : warps)
      {
        ItemStack i = new ItemStack(Material.getMaterial(Main.warps.getInt(s + ".item")));
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(ChatColor.GREEN + s);
        i.setItemMeta(m);
        inv.setItem(Main.warps.getStringList("Warps").indexOf(s), i);
        p.openInventory(inv);
      }
    }
  }
}
