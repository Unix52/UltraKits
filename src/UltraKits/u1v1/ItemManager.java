package UltraKits.u1v1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import UltraKits.Main;

public class ItemManager
{
  static FileConfiguration kits = Main.kit;
  static Plugin pl = Main.plugin;
  static HashMap<ItemStack, String> id = new HashMap();
  static HashMap<ItemStack, Integer> items = new HashMap();
  
  public static void loadItems()
  {
    if (pl.getConfig().getBoolean("EnableCustom"))
    {
      Material m = Material.getMaterial(pl.getConfig().getInt("Custom.id"));
      if (m != null)
      {
        ItemStack c = new ItemStack(m);
        ItemMeta i = c.getItemMeta();
        i.setDisplayName(pl.getConfig().getString("Custom.title").replaceAll("&", "§"));
        List<String> lore = pl.getConfig().getStringList("Custom.lore");
        if ((lore != null) && (!lore.isEmpty()))
        {
          ArrayList<String> loreA = new ArrayList();
          for (int a = 0; a < lore.size(); a++) {
            loreA.add(((String)lore.get(a)).replaceAll("&", "§"));
          }
          i.setLore(loreA);
        }
        c.setItemMeta(i);
        Custom.customItem.clear();
        Custom.customItem.add(c);
        items.put(c, Integer.valueOf(pl.getConfig().getInt("Custom.slot") - 1));
      }
      else
      {
        Bukkit.getConsoleSender().sendMessage("§b[Mega1V1] §cErro na configuracao do material do custom.");
      }
    }
    Material material = Material.getMaterial(pl.getConfig().getInt("Item.id"));
    ArrayList<String> loreA;
    if (material != null)
    {
      Desafiar.item = new ItemStack(material);
      ItemMeta im = Desafiar.item.getItemMeta();
      im.setDisplayName(pl.getConfig().getString("Item.title").replaceAll("&", "§"));
      List<String> lore = pl.getConfig().getStringList("Item.lore");
      if ((lore != null) && (!lore.isEmpty()))
      {
        loreA = new ArrayList();
        for (int i = 0; i < lore.size(); i++) {
          loreA.add(((String)lore.get(i)).replaceAll("&", "§"));
        }
        im.setLore(loreA);
        Desafiar.item.setItemMeta(im);
      }
    }
    else
    {
      Bukkit.getConsoleSender().sendMessage("§b[Mega1V1] §cErro na configuracao do item.");
    }
    List<String> kit = kits.getStringList("Kits");
    if ((kit != null) && (!kit.isEmpty())) {
      for (String k : kit)
      {
        if (kits.getConfigurationSection(k) == null)
        {
          Bukkit.getConsoleSender().sendMessage("§b[Mega1V1] §cO Kit §f'" + k + "' §cnao foi encontrado.");
          return;
        }
        Material mat = Material.getMaterial(kits.getInt(k + ".id"));
        if (mat != null)
        {
          ItemStack item = new ItemStack(mat);
          ItemMeta im = item.getItemMeta();
          if (kits.getString(k + ".title") != null) {
            im.setDisplayName(kits.getString(k + ".title").replaceAll("&", "§"));
          }
          if (kits.getStringList(k + ".lore") != null)
          {
            List<String> l = kits.getStringList(k + ".lore");
            ArrayList<String> lore = new ArrayList();
            for (int i = 0; i < l.size(); i++) {
              lore.add(((String)l.get(i)).replaceAll("&", "§"));
            }
            im.setLore(lore);
          }
          item.setItemMeta(im);
          items.put(item, Integer.valueOf(kits.getInt(k + ".slot") - 1));
          id.put(item, k);
        }
        else
        {
          Bukkit.getConsoleSender().sendMessage("§b[Mega1V1] §cErros de configuracao no Kit:§f '" + k + "'");
        }
      }
    }
  }
}
