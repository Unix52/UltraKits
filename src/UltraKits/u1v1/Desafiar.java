package UltraKits.u1v1;

import UltraKits.Main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class Desafiar
  implements Listener
{
  static Plugin pl = Main.plugin;
  static FileConfiguration kits = Main.kit;
  static FileConfiguration data = Main.data;
  static HashMap<String, String> desafiando = new HashMap();
  static HashMap<String, String> kit = new HashMap();
  static HashMap<String, String> customizado = new HashMap();
  static HashMap<String, String> lutando = new HashMap();
  static HashMap<String, String> requests = new HashMap();
  static HashMap<String, BukkitRunnable> cooldown = new HashMap();
  static HashMap<String, BukkitRunnable> tasks = new HashMap();
  static ItemStack item;
  static ItemStack ic;
  static Inventory gui = Bukkit.createInventory(null, Main.plugin.getConfig().getInt("1v1Slots"), Main.plugin.getConfig().getString("1v1Title").replaceAll("&", "§"));
  
  @EventHandler
  public void desafiarPlayer(PlayerInteractEntityEvent e)
  {
    final Player p = e.getPlayer();
    if ((p.getItemInHand().equals(item)) && 
      ((e.getRightClicked() instanceof Player)))
    {
      final Player req = (Player)e.getRightClicked();
      double x;
      if (requests.containsKey(req.getName()))
      {
        if (requests.get(req.getName()) == p.getName())
        {
          requests.remove(req.getName());
          p.setFoodLevel(20);
          p.setHealth(20.0D);
          req.setFoodLevel(20);
          req.setHealth(20.0D);
          Commands.limparInv(p);
          Commands.limparInv(req);
          hidePlayer(p, req);
          hidePlayer(req, p);
          if ((Main.data.getConfigurationSection("Pos1") != null) && (Main.data.getConfigurationSection("Pos2") != null))
          {
            World w = Bukkit.getWorld(Main.data.getString("Pos1.world"));
            x = Main.data.getDouble("Pos1.x");
            double y = Main.data.getDouble("Pos1.y");
            double z = Main.data.getDouble("Pos1.z");
            p.teleport(new Location(w, x, y, z, (float)Main.data.getLong("Pos1.yaw"), (float)Main.data.getLong("Pos1.pitch")));
            
            World w2 = Bukkit.getWorld(Main.data.getString("Pos2.world"));
            double x2 = Main.data.getDouble("Pos2.x");
            double y2 = Main.data.getDouble("Pos2.y");
            double z2 = Main.data.getDouble("Pos2.z");
            req.teleport(new Location(w2, x2, y2, z2, (float)Main.data.getLong("Pos2.yaw"), (float)Main.data.getLong("Pos2.pitch")));
            lutando.put(p.getName(), req.getName());
            lutando.put(req.getName(), p.getName());
            requests.remove(req.getName());
            if (!customizado.containsKey(req.getName()))
            {
              if (kit.containsKey(req.getName()))
              {
                String id = (String)kit.get(req.getName());
                p.getInventory().clear();
                req.getInventory().clear();
                if (Main.kit.getConfigurationSection(id) != null)
                {
                  p.getEquipment().setHelmet(new ItemStack(Material.getMaterial(Main.kit.getString(id + ".helmet.type").toUpperCase())));
                  req.getEquipment().setHelmet(new ItemStack(Material.getMaterial(Main.kit.getString(id + ".helmet.type").toUpperCase())));
                  if (Main.kit.getStringList(id + ".helmet.enchants") != null) {
                    for (String en : Main.kit.getStringList(id + ".helmet.enchants"))
                    {
                      String[] enchant = en.split(" ");
                      p.getEquipment().getHelmet().addUnsafeEnchantment(Enchantment.getByName(enchant[0].toUpperCase()), Integer.valueOf(enchant[1]).intValue());
                      req.getEquipment().getHelmet().addUnsafeEnchantment(Enchantment.getByName(enchant[0].toUpperCase()), Integer.valueOf(enchant[1]).intValue());
                    }
                  }
                  p.getEquipment().setChestplate(new ItemStack(Material.getMaterial(Main.kit.getString(id + ".chestplate.type").toUpperCase())));
                  req.getEquipment().setChestplate(new ItemStack(Material.getMaterial(Main.kit.getString(id + ".chestplate.type").toUpperCase())));
                  if (Main.kit.getStringList(id + ".chestplate.enchants") != null) {
                    for (String en : Main.kit.getStringList(id + ".chestplate.enchants"))
                    {
                      String[] enchant = en.split(" ");
                      p.getEquipment().getChestplate().addUnsafeEnchantment(Enchantment.getByName(enchant[0].toUpperCase()), Integer.valueOf(enchant[1]).intValue());
                      req.getEquipment().getChestplate().addUnsafeEnchantment(Enchantment.getByName(enchant[0].toUpperCase()), Integer.valueOf(enchant[1]).intValue());
                    }
                  }
                  p.getEquipment().setLeggings(new ItemStack(Material.getMaterial(Main.kit.getString(id + ".leggings.type").toUpperCase())));
                  req.getEquipment().setLeggings(new ItemStack(Material.getMaterial(Main.kit.getString(id + ".leggings.type").toUpperCase())));
                  if (Main.kit.getStringList(id + ".leggings.enchants") != null) {
                    for (String en : Main.kit.getStringList(id + ".leggings.enchants"))
                    {
                      String[] enchant = en.split(" ");
                      p.getEquipment().getLeggings().addUnsafeEnchantment(Enchantment.getByName(enchant[0].toUpperCase()), Integer.valueOf(enchant[1]).intValue());
                      req.getEquipment().getLeggings().addUnsafeEnchantment(Enchantment.getByName(enchant[0].toUpperCase()), Integer.valueOf(enchant[1]).intValue());
                    }
                  }
                  p.getEquipment().setBoots(new ItemStack(Material.getMaterial(Main.kit.getString(id + ".boots.type").toUpperCase())));
                  req.getEquipment().setBoots(new ItemStack(Material.getMaterial(Main.kit.getString(id + ".boots.type").toUpperCase())));
                  if (Main.kit.getStringList(id + ".boots.enchants") != null) {
                    for (String en : Main.kit.getStringList(id + ".boots.enchants"))
                    {
                      String[] enchant = en.split(" ");
                      p.getEquipment().getBoots().addUnsafeEnchantment(Enchantment.getByName(enchant[0].toUpperCase()), Integer.valueOf(enchant[1]).intValue());
                      req.getEquipment().getBoots().addUnsafeEnchantment(Enchantment.getByName(enchant[0].toUpperCase()), Integer.valueOf(enchant[1]).intValue());
                    }
                  }
                  p.getInventory().setItemInHand(new ItemStack(Material.getMaterial(Main.kit.getString(id + ".sword.type").toUpperCase())));
                  req.getInventory().setItemInHand(new ItemStack(Material.getMaterial(Main.kit.getString(id + ".sword.type").toUpperCase())));
                  if (Main.kit.getStringList(id + ".sword.enchants") != null) {
                    for (String en : Main.kit.getStringList(id + ".sword.enchants"))
                    {
                      String[] enchant = en.split(" ");
                      p.getInventory().getItemInHand().addUnsafeEnchantment(Enchantment.getByName(enchant[0].toUpperCase()), Integer.valueOf(enchant[1]).intValue());
                      req.getInventory().getItemInHand().addUnsafeEnchantment(Enchantment.getByName(enchant[0].toUpperCase()), Integer.valueOf(enchant[1]).intValue());
                    }
                  }
                  if (Main.kit.getStringList(id + ".potions") != null) {
                    for (String pt : Main.kit.getStringList(id + ".potions"))
                    {
                      String[] potion = pt.split(" ");
                      p.addPotionEffect(new PotionEffect(PotionEffectType.getByName(potion[0].toUpperCase()), Integer.valueOf(potion[2]).intValue() * 20, Integer.valueOf(potion[1]).intValue() - 1));
                      req.addPotionEffect(new PotionEffect(PotionEffectType.getByName(potion[0].toUpperCase()), Integer.valueOf(potion[2]).intValue() * 20, Integer.valueOf(potion[1]).intValue() - 1));
                    }
                  }
                  int sopas = Main.kit.getInt(id + ".sopas");
                  for (int i = 0; i < sopas; i++)
                  {
                    p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
                    req.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
                  }
                  p.updateInventory();
                  req.updateInventory();
                }
              }
            }
            else
            {
              String cs = (String)customizado.get(req.getName());
              String[] c = cs.split(";");
              if (c[0].equalsIgnoreCase("Diamante"))
              {
                p.getEquipment().setArmorContents(new ItemStack[] { new ItemStack(Material.DIAMOND_BOOTS), new ItemStack(Material.DIAMOND_LEGGINGS), new ItemStack(Material.DIAMOND_CHESTPLATE), new ItemStack(Material.DIAMOND_HELMET) });
                req.getEquipment().setArmorContents(new ItemStack[] { new ItemStack(Material.DIAMOND_BOOTS), new ItemStack(Material.DIAMOND_LEGGINGS), new ItemStack(Material.DIAMOND_CHESTPLATE), new ItemStack(Material.DIAMOND_HELMET) });
              }
              else if (c[0].equalsIgnoreCase("Ferro"))
              {
                p.getEquipment().setArmorContents(new ItemStack[] { new ItemStack(Material.IRON_BOOTS), new ItemStack(Material.IRON_LEGGINGS), new ItemStack(Material.IRON_CHESTPLATE), new ItemStack(Material.IRON_HELMET) });
                req.getEquipment().setArmorContents(new ItemStack[] { new ItemStack(Material.IRON_BOOTS), new ItemStack(Material.IRON_LEGGINGS), new ItemStack(Material.IRON_CHESTPLATE), new ItemStack(Material.IRON_HELMET) });
              }
              else if (c[0].equalsIgnoreCase("Ouro"))
              {
                p.getEquipment().setArmorContents(new ItemStack[] { new ItemStack(Material.GOLD_BOOTS), new ItemStack(Material.GOLD_LEGGINGS), new ItemStack(Material.GOLD_CHESTPLATE), new ItemStack(Material.GOLD_HELMET) });
                req.getEquipment().setArmorContents(new ItemStack[] { new ItemStack(Material.GOLD_BOOTS), new ItemStack(Material.GOLD_LEGGINGS), new ItemStack(Material.GOLD_CHESTPLATE), new ItemStack(Material.GOLD_HELMET) });
              }
              else if (c[0].equalsIgnoreCase("Chain"))
              {
                p.getEquipment().setArmorContents(new ItemStack[] { new ItemStack(Material.CHAINMAIL_BOOTS), new ItemStack(Material.CHAINMAIL_LEGGINGS), new ItemStack(Material.CHAINMAIL_CHESTPLATE), new ItemStack(Material.CHAINMAIL_HELMET) });
                req.getEquipment().setArmorContents(new ItemStack[] { new ItemStack(Material.CHAINMAIL_BOOTS), new ItemStack(Material.CHAINMAIL_LEGGINGS), new ItemStack(Material.CHAINMAIL_CHESTPLATE), new ItemStack(Material.CHAINMAIL_HELMET) });
              }
              else if (c[0].equalsIgnoreCase("Couro"))
              {
                p.getEquipment().setArmorContents(new ItemStack[] { new ItemStack(Material.LEATHER_BOOTS), new ItemStack(Material.LEATHER_LEGGINGS), new ItemStack(Material.LEATHER_CHESTPLATE), new ItemStack(Material.LEATHER_HELMET) });
                req.getEquipment().setArmorContents(new ItemStack[] { new ItemStack(Material.LEATHER_BOOTS), new ItemStack(Material.LEATHER_LEGGINGS), new ItemStack(Material.LEATHER_CHESTPLATE), new ItemStack(Material.LEATHER_HELMET) });
              }
              if (c[1].equalsIgnoreCase("Diamante"))
              {
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.DIAMOND_SWORD) });
                req.getInventory().addItem(new ItemStack[] { new ItemStack(Material.DIAMOND_SWORD) });
              }
              else if (c[1].equalsIgnoreCase("Ferro"))
              {
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.IRON_SWORD) });
                req.getInventory().addItem(new ItemStack[] { new ItemStack(Material.IRON_SWORD) });
              }
              else if (c[1].equalsIgnoreCase("Ouro"))
              {
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.GOLD_SWORD) });
                req.getInventory().addItem(new ItemStack[] { new ItemStack(Material.GOLD_SWORD) });
              }
              else if (c[1].equalsIgnoreCase("Pedra"))
              {
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.STONE_SWORD) });
                req.getInventory().addItem(new ItemStack[] { new ItemStack(Material.STONE_SWORD) });
              }
              else if (c[1].equalsIgnoreCase("Madeira"))
              {
                p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.WOOD_SWORD) });
                req.getInventory().addItem(new ItemStack[] { new ItemStack(Material.WOOD_SWORD) });
              }
              if (c[2].equalsIgnoreCase("true")) {
                for (int i = 0; i < 35; i++)
                {
                  p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
                  req.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP) });
                }
              }
            }
            Events.move.add(p.getName());
            Events.move.add(req.getName());
            
            new BukkitRunnable()
            {
              int tempo = Main.plugin.getConfig().getInt("Cooldown1v1") + 1;
              
              public void run()
              {
                if (this.tempo > 1)
                {
                  this.tempo -= 1;
                  Desafiar.cooldown.put(p.getName(), this);
                  Desafiar.cooldown.put(req.getName(), this);
                  p.sendMessage(Main.plugin.getConfig().getString("Iniciando").replaceAll("&", "§").replaceAll("@tempo", this.tempo));
                  req.sendMessage(Main.plugin.getConfig().getString("Iniciando").replaceAll("&", "§").replaceAll("@tempo", this.tempo));
                  p.playSound(p.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                  req.playSound(req.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                }
                else
                {
                  if (Events.move.contains(p.getName())) {
                    Events.move.remove(p.getName());
                  }
                  if (Events.move.contains(req.getName())) {
                    Events.move.remove(req.getName());
                  }
                  p.sendMessage(Main.plugin.getConfig().getString("Valendo").replaceAll("&", "§"));
                  p.playSound(p.getLocation(), Sound.NOTE_PIANO, 1.0F, 1.0F);
                  req.sendMessage(Main.plugin.getConfig().getString("Valendo").replaceAll("&", "§"));
                  req.playSound(req.getLocation(), Sound.NOTE_PIANO, 1.0F, 1.0F);
                  cancel();
                }
              }
            }.runTaskTimer(pl, 0L, 20L);
          }
        }
      }
      else
      {
        for (Map.Entry entry : ItemManager.items.entrySet()) {
          gui.setItem(((Integer)entry.getValue()).intValue(), (ItemStack)entry.getKey());
        }
        desafiando.put(p.getName(), req.getName());
        p.openInventory(gui);
      }
    }
  }
  
  @EventHandler
  public void selecionarKit(InventoryClickEvent e)
  {
    Player p = (Player)e.getWhoClicked();
    if (e.getCurrentItem() == null) {
      return;
    }
    if (e.getInventory().getName().equals(gui.getName()))
    {
      Player req;
      if (ItemManager.id.containsKey(e.getCurrentItem()))
      {
        if (desafiando.containsKey(p.getName()))
        {
          String id = (String)ItemManager.id.get(e.getCurrentItem());
          req = Bukkit.getPlayer((String)desafiando.get(p.getName()));
          if (req != null)
          {
            requests.put(p.getName(), req.getName());
            kit.put(p.getName(), id);
            for (String msg : Main.plugin.getConfig().getStringList("Desafiou")) {
              if (Main.kit.getString(id + ".aliase") != null) {
                p.sendMessage(msg.replaceAll("&", "§").replaceAll("@kit", Main.kit.getString(id + ".aliase")).replaceAll("@desafiado", req.getName()));
              } else {
                p.sendMessage(msg.replaceAll("&", "§").replaceAll("@kit", id).replaceAll("@desafiado", req.getName()));
              }
            }
            for (String cv : Main.plugin.getConfig().getStringList("Convite")) {
              if (Main.kit.getString(id + ".aliase") != null) {
                req.sendMessage(cv.replaceAll("&", "§").replaceAll("@kit", Main.kit.getString(id + ".aliase")).replaceAll("@desafiante", p.getName()));
              } else {
                req.sendMessage(cv.replaceAll("&", "§").replaceAll("@kit", id).replaceAll("@desafiante", p.getName()));
              }
            }
          }
          else
          {
            p.sendMessage(Main.plugin.getConfig().getString("NaoOnline").replaceAll("&", "§"));
          }
        }
        e.setCancelled(true);
        p.closeInventory();
      }
      else if (!Custom.customItem.isEmpty())
      {
        for (ItemStack i : Custom.customItem) {
          if (e.getCurrentItem().equals(i))
          {
            e.setCancelled(true);
            p.closeInventory();
            Custom.inv.setItem(0, Custom.cancel);
            Custom.inv.setItem(9, Custom.cancel);
            Custom.inv.setItem(18, Custom.cancel);
            Custom.inv.setItem(8, Custom.done);
            Custom.inv.setItem(17, Custom.done);
            Custom.inv.setItem(26, Custom.done);
            
            ic = new ItemStack(Material.getMaterial(Main.plugin.getConfig().getInt("Custom.ItemRedor.id")));
            ItemMeta metaIC = ic.getItemMeta();
            metaIC.setDisplayName(Main.plugin.getConfig().getString("Custom.ItemRedor.title").replaceAll("&", "§"));
            ic.setItemMeta(metaIC);
            Custom.inv.setItem(1, ic);
            Custom.inv.setItem(2, ic);
            Custom.inv.setItem(3, ic);
            Custom.inv.setItem(4, ic);
            Custom.inv.setItem(5, ic);
            Custom.inv.setItem(6, ic);
            Custom.inv.setItem(7, ic);
            Custom.inv.setItem(10, ic);
            Custom.inv.setItem(12, ic);
            Custom.inv.setItem(14, ic);
            Custom.inv.setItem(16, ic);
            Custom.inv.setItem(19, ic);
            Custom.inv.setItem(20, ic);
            Custom.inv.setItem(21, ic);
            Custom.inv.setItem(22, ic);
            Custom.inv.setItem(23, ic);
            Custom.inv.setItem(24, ic);
            Custom.inv.setItem(25, ic);
            
            Custom.inv.setItem(13, Custom.armFerro);
            Custom.inv.setItem(11, Custom.swdDima);
            Custom.inv.setItem(15, Custom.resoup);
            Custom.espada.put(p.getName(), "diamond");
            Custom.armadura.put(p.getName(), "iron");
            Custom.sopas.put(p.getName(), Boolean.valueOf(true));
            p.openInventory(Custom.inv);
          }
        }
      }
    }
  }
  
  public static void hidePlayer(Player p, final Player p2)
  {
    new BukkitRunnable()
    {
      public void run()
      {
        if (!Desafiar.tasks.containsKey(Desafiar.this.getName())) {
          Desafiar.tasks.put(Desafiar.this.getName(), this);
        }
        for (Player n : Bukkit.getOnlinePlayers()) {
          if (n != p2)
          {
            n.hidePlayer(Desafiar.this);
            Desafiar.this.hidePlayer(n);
          }
        }
      }
    }.runTaskTimer(pl, 0L, 20L);
  }
  
  public static void showPlayer(Player p)
  {
    for (Player s : )
    {
      s.showPlayer(p);
      p.showPlayer(s);
    }
  }
}
