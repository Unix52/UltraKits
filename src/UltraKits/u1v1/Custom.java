package UltraKits.u1v1;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import UltraKits.Main;

public class Custom
  implements Listener
{
  static Plugin pl = Main.plugin;
  public static Inventory inv = Bukkit.createInventory(null, 27, Main.plugin.getConfig().getString("Custom.invTitle").replaceAll("&", "§"));
  static ItemStack cancel = new ItemStack(Material.WOOL);
  static ItemStack done = new ItemStack(Material.WOOL);
  static ItemStack armDima = new ItemStack(Material.DIAMOND_CHESTPLATE);
  static ItemStack armFerro = new ItemStack(Material.IRON_CHESTPLATE);
  static ItemStack armOuro = new ItemStack(Material.GOLD_CHESTPLATE);
  static ItemStack armChain = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
  static ItemStack armCouro = new ItemStack(Material.LEATHER_CHESTPLATE);
  static ItemStack swdDima = new ItemStack(Material.DIAMOND_SWORD);
  static ItemStack swdFerro = new ItemStack(Material.IRON_SWORD);
  static ItemStack swdOuro = new ItemStack(Material.GOLD_SWORD);
  static ItemStack swdPedra = new ItemStack(Material.STONE_SWORD);
  static ItemStack swdMadeira = new ItemStack(Material.WOOD_SWORD);
  static ItemStack resoup = new ItemStack(Material.MUSHROOM_SOUP);
  static ItemStack nosoup = new ItemStack(Material.BOWL);
  @SuppressWarnings({ "unchecked", "rawtypes" })
static ArrayList<ItemStack> customItem = new ArrayList();
  @SuppressWarnings({ "unchecked", "rawtypes" })
static HashMap<String, String> armadura = new HashMap();
  @SuppressWarnings({ "unchecked", "rawtypes" })
static HashMap<String, String> espada = new HashMap();
  @SuppressWarnings({ "unchecked", "rawtypes" })
static HashMap<String, Boolean> sopas = new HashMap();
  
  @SuppressWarnings({ "unchecked", "rawtypes" })
public static void loadCustomItems()
  {
    cancel.setDurability((short)14);
    done.setDurability((short)5);
    ItemMeta im1 = cancel.getItemMeta();
    im1.setDisplayName(ChatColor.RED + "Cancelar");
    cancel.setItemMeta(im1);
    ItemMeta im2 = done.getItemMeta();
    im2.setDisplayName(ChatColor.GREEN + "Pronto");
    done.setItemMeta(im2);
    
    ItemMeta meta1 = armDima.getItemMeta();
    meta1.setDisplayName(ChatColor.GOLD + "Armaduras");
    ArrayList<String> lore1 = new ArrayList();
    lore1.add(ChatColor.GREEN + "➨ Diamante");
    lore1.add(ChatColor.RED + "- Ferro");
    lore1.add(ChatColor.RED + "- Ouro");
    lore1.add(ChatColor.RED + "- Chain");
    lore1.add(ChatColor.RED + "- Couro");
    meta1.setLore(lore1);
    armDima.setItemMeta(meta1);
    
    ItemMeta meta2 = armFerro.getItemMeta();
    meta2.setDisplayName(ChatColor.GOLD + "Armadura");
    ArrayList<String> lore2 = new ArrayList();
    lore2.add(ChatColor.RED + "- Diamante");
    lore2.add(ChatColor.GREEN + "➨ Ferro");
    lore2.add(ChatColor.RED + "- Ouro");
    lore2.add(ChatColor.RED + "- Chain");
    lore2.add(ChatColor.RED + "- Couro");
    meta2.setLore(lore2);
    armFerro.setItemMeta(meta2);
    
    ItemMeta meta3 = armOuro.getItemMeta();
    meta3.setDisplayName(ChatColor.GOLD + "Armadura");
    ArrayList<String> lore3 = new ArrayList();
    lore3.add(ChatColor.RED + "- Diamante");
    lore3.add(ChatColor.RED + "- Ferro");
    lore3.add(ChatColor.GREEN + "➨ Ouro");
    lore3.add(ChatColor.RED + "- Chain");
    lore3.add(ChatColor.RED + "- Couro");
    meta3.setLore(lore3);
    armOuro.setItemMeta(meta3);
    
    ItemMeta meta4 = armChain.getItemMeta();
    meta4.setDisplayName(ChatColor.GOLD + "Armadura");
    ArrayList<String> lore4 = new ArrayList();
    lore4.add(ChatColor.RED + "- Diamante");
    lore4.add(ChatColor.RED + "- Ferro");
    lore4.add(ChatColor.RED + "- Ouro");
    lore4.add(ChatColor.GREEN + "➨ Chain");
    lore4.add(ChatColor.RED + "- Couro");
    meta4.setLore(lore4);
    armChain.setItemMeta(meta4);
    
    ItemMeta meta5 = armCouro.getItemMeta();
    meta5.setDisplayName(ChatColor.GOLD + "Armadura");
    ArrayList<String> lore5 = new ArrayList();
    lore5.add(ChatColor.RED + "- Diamante");
    lore5.add(ChatColor.RED + "- Ferro");
    lore5.add(ChatColor.RED + "- Ouro");
    lore5.add(ChatColor.RED + "- Chain");
    lore5.add(ChatColor.GREEN + "➨ Couro");
    meta5.setLore(lore5);
    armCouro.setItemMeta(meta5);
    
    ItemMeta meta6 = swdDima.getItemMeta();
    meta6.setDisplayName(ChatColor.GOLD + "Espada");
    ArrayList<String> lore6 = new ArrayList();
    lore6.add(ChatColor.GREEN + "➨ Diamante");
    lore6.add(ChatColor.RED + "- Ferro");
    lore6.add(ChatColor.RED + "- Ouro");
    lore6.add(ChatColor.RED + "- Pedra");
    lore6.add(ChatColor.RED + "- Madeira");
    meta6.setLore(lore6);
    swdDima.setItemMeta(meta6);
    
    ItemMeta meta7 = swdFerro.getItemMeta();
    meta7.setDisplayName(ChatColor.GOLD + "Espada");
    ArrayList<String> lore7 = new ArrayList();
    lore7.add(ChatColor.RED + "- Diamante");
    lore7.add(ChatColor.GREEN + "➨ Ferro");
    lore7.add(ChatColor.RED + "- Ouro");
    lore7.add(ChatColor.RED + "- Pedra");
    lore7.add(ChatColor.RED + "- Madeira");
    meta7.setLore(lore7);
    swdFerro.setItemMeta(meta7);
    
    ItemMeta meta8 = swdOuro.getItemMeta();
    meta7.setDisplayName(ChatColor.GOLD + "Espada");
    ArrayList<String> lore8 = new ArrayList();
    lore8.add(ChatColor.RED + "- Diamante");
    lore8.add(ChatColor.RED + "- Ferro");
    lore8.add(ChatColor.GREEN + "➨ Ouro");
    lore8.add(ChatColor.RED + "- Pedra");
    lore8.add(ChatColor.RED + "- Madeira");
    meta8.setLore(lore8);
    swdOuro.setItemMeta(meta8);
    
    ItemMeta meta9 = swdPedra.getItemMeta();
    meta9.setDisplayName(ChatColor.GOLD + "Espada");
    ArrayList<String> lore9 = new ArrayList();
    lore9.add(ChatColor.RED + "- Diamante");
    lore9.add(ChatColor.RED + "- Ferro");
    lore9.add(ChatColor.RED + "- Ouro");
    lore9.add(ChatColor.GREEN + "➨ Pedra");
    lore9.add(ChatColor.RED + "- Madeira");
    meta9.setLore(lore9);
    swdPedra.setItemMeta(meta9);
    
    ItemMeta meta10 = swdMadeira.getItemMeta();
    meta10.setDisplayName(ChatColor.GOLD + "Espada");
    ArrayList<String> lore10 = new ArrayList();
    lore10.add(ChatColor.RED + "- Diamante");
    lore10.add(ChatColor.RED + "- Ferro");
    lore10.add(ChatColor.RED + "- Ouro");
    lore10.add(ChatColor.RED + "- Pedra");
    lore10.add(ChatColor.GREEN + "➨ Madeira");
    meta10.setLore(lore10);
    swdMadeira.setItemMeta(meta10);
    
    ItemMeta im11 = resoup.getItemMeta();
    im11.setDisplayName(ChatColor.GOLD + "Sopas");
    resoup.setItemMeta(im11);
    
    ItemMeta im12 = nosoup.getItemMeta();
    im12.setDisplayName(ChatColor.GOLD + "Sem Sopas");
    nosoup.setItemMeta(im12);
  }
  
  @EventHandler
  public void criarCustom(InventoryClickEvent e)
  {
    Player p = (Player)e.getWhoClicked();
    if (e.getCurrentItem() == null) {
      return;
    }
    if (e.getInventory().getName().equals(inv.getName()))
    {
      if (e.getCurrentItem().equals(Desafiar.ic))
      {
        e.setCancelled(true);
        return;
      }
      if (e.getCurrentItem().equals(cancel))
      {
        e.setCancelled(true);
        p.closeInventory();
      }
      if (e.getCurrentItem().equals(done)) {
        if (Desafiar.desafiando.containsKey(p.getName()))
        {
          if (inv.contains(swdDima)) {
            espada.put(p.getName(), "Diamante");
          } else if (inv.contains(swdFerro)) {
            espada.put(p.getName(), "Ferro");
          } else if (inv.contains(swdOuro)) {
            espada.put(p.getName(), "Ouro");
          } else if (inv.contains(swdPedra)) {
            espada.put(p.getName(), "Pedra");
          } else if (inv.contains(swdMadeira)) {
            espada.put(p.getName(), "Madeira");
          }
          if (inv.contains(armDima)) {
            armadura.put(p.getName(), "Diamante");
          } else if (inv.contains(armFerro)) {
            armadura.put(p.getName(), "Ferro");
          } else if (inv.contains(armOuro)) {
            armadura.put(p.getName(), "Ouro");
          } else if (inv.contains(armChain)) {
            armadura.put(p.getName(), "Chain");
          } else if (inv.contains(armCouro)) {
            armadura.put(p.getName(), "Couro");
          }
          if (inv.contains(resoup)) {
            sopas.put(p.getName(), Boolean.valueOf(true));
          } else if (inv.contains(nosoup)) {
            sopas.put(p.getName(), Boolean.valueOf(false));
          }
          Desafiar.customizado.put(p.getName(), (String)armadura.get(p.getName()) + ";" + (String)espada.get(p.getName()) + ";" + sopas.get(p.getName()));
          
          String pname = (String)Desafiar.desafiando.get(p.getName());
          Player req = Bukkit.getPlayer(pname);
          if (req != null)
          {
            Desafiar.requests.put(p.getName(), req.getName());
            for (String b : pl.getConfig().getStringList("CustomDesafiou")) {
              if (((Boolean)sopas.get(p.getName())).booleanValue()) {
                p.sendMessage(b.replaceAll("@armadura", (String)armadura.get(p.getName())).replaceAll("@espada", (String)espada.get(p.getName())).replaceAll("&", "§").replaceAll("@desafiado", req.getName()).replaceAll("@sopa", "Sim"));
              } else {
                p.sendMessage(b.replaceAll("@armadura", (String)armadura.get(p.getName())).replaceAll("@espada", (String)espada.get(p.getName())).replaceAll("&", "§").replaceAll("@desafiado", req.getName()).replaceAll("@sopa", "Nao"));
              }
            }
            for (String s : pl.getConfig().getStringList("CustomConvite")) {
              if (((Boolean)sopas.get(p.getName())).booleanValue()) {
                req.sendMessage(s.replaceAll("@armadura", (String)armadura.get(p.getName())).replaceAll("@espada", (String)espada.get(p.getName())).replaceAll("&", "§").replaceAll("@desafiante", p.getName()).replaceAll("@sopa", "Sim"));
              } else {
                req.sendMessage(s.replaceAll("@armadura", (String)armadura.get(p.getName())).replaceAll("@espada", (String)espada.get(p.getName())).replaceAll("&", "§").replaceAll("@desafiante", p.getName()).replaceAll("@sopa", "Nao"));
              }
            }
          }
          e.setCancelled(true);
          p.closeInventory();
        }
        else
        {
          e.setCancelled(true);
          p.closeInventory();
          p.sendMessage(Main.plugin.getConfig().getString("SemDesafiante").replaceAll("&", "§"));
        }
      }
      if (e.getCurrentItem().equals(armDima))
      {
        e.setCancelled(true);
        inv.setItem(13, armFerro);
      }
      else if (e.getCurrentItem().equals(armFerro))
      {
        e.setCancelled(true);
        inv.setItem(13, armOuro);
      }
      else if (e.getCurrentItem().equals(armOuro))
      {
        e.setCancelled(true);
        inv.setItem(13, armChain);
      }
      else if (e.getCurrentItem().equals(armChain))
      {
        e.setCancelled(true);
        inv.setItem(13, armCouro);
      }
      else if (e.getCurrentItem().equals(armCouro))
      {
        e.setCancelled(true);
        inv.setItem(13, armDima);
      }
      if (e.getCurrentItem().equals(swdDima))
      {
        e.setCancelled(true);
        inv.setItem(11, swdFerro);
      }
      else if (e.getCurrentItem().equals(swdFerro))
      {
        e.setCancelled(true);
        inv.setItem(11, swdOuro);
      }
      else if (e.getCurrentItem().equals(swdOuro))
      {
        e.setCancelled(true);
        inv.setItem(11, swdPedra);
      }
      else if (e.getCurrentItem().equals(swdPedra))
      {
        e.setCancelled(true);
        inv.setItem(11, swdMadeira);
      }
      else if (e.getCurrentItem().equals(swdMadeira))
      {
        e.setCancelled(true);
        inv.setItem(11, swdDima);
      }
      if (e.getCurrentItem().equals(resoup))
      {
        e.setCancelled(true);
        inv.setItem(15, nosoup);
      }
      else if (e.getCurrentItem().equals(nosoup))
      {
        e.setCancelled(true);
        inv.setItem(15, resoup);
      }
    }
  }
}
