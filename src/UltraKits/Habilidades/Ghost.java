package UltraKits.Habilidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import UltraKits.Main;

public class Ghost
  implements Listener
{
  public static HashMap<String, Long> cooldown = new HashMap();
  public static HashMap<String, ItemStack[]> arm = new HashMap();
  public static HashMap<String, ItemStack[]> inv = new HashMap();
  public static HashMap<String, BukkitTask> task = new HashMap();
  public static ArrayList<String> fantasmas = new ArrayList();
  public static ItemStack peito = new ItemStack(Material.LEATHER_CHESTPLATE);
  
  @EventHandler
  public void transformar(PlayerInteractEvent e)
  {
    final Player p = e.getPlayer();
    if (((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) && 
      (p.getItemInHand().getType() == Material.POTION) && (p.getItemInHand().getDurability() == 8238) && 
      (Main.ghost.contains(p.getName())))
    {
      e.setCancelled(true);
      p.updateInventory();
      if ((!cooldown.containsKey(p.getName())) || (((Long)cooldown.get(p.getName())).longValue() <= System.currentTimeMillis()))
      {
        if (!Main.kits.contains(p))
        {
          arm.put(p.getName(), p.getEquipment().getArmorContents());
          inv.put(p.getName(), p.getInventory().getContents());
          p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 2147483647, 0));
          p.getInventory().clear();
          p.getEquipment().setArmorContents(new ItemStack[] { new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR) });
          ItemStack cabeca = new ItemStack(Material.SKULL_ITEM);
          cabeca.setDurability((short)1);
          peito = new ItemStack(Material.LEATHER_CHESTPLATE);
          LeatherArmorMeta meta = (LeatherArmorMeta)peito.getItemMeta();
          meta.setColor(Color.BLACK);
          peito.setItemMeta(meta);
          p.getEquipment().setHelmet(cabeca);
          p.getEquipment().setChestplate(peito);
          ItemStack foice = new ItemStack(Material.IRON_HOE);
          ItemMeta im = foice.getItemMeta();
          im.setDisplayName(ChatColor.RED + "Seifadora de Vidas");
          foice.setItemMeta(im);
          p.getInventory().addItem(new ItemStack[] { foice });
          p.updateInventory();
          fantasmas.add(p.getName());
          cooldown.put(p.getName(), Long.valueOf(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(80L)));
          p.sendMessage(ChatColor.GREEN + "Voce se transformou em um fantasma! " + ChatColor.DARK_PURPLE + "ASSOMBRE " + ChatColor.GREEN + "seus inimigos!");
          p.playSound(p.getLocation(), Sound.AMBIENCE_CAVE, 1.0F, 1.0F);
          BukkitTask id = new BukkitRunnable()
          {
            public void run()
            {
              p.getInventory().clear();
              if (Ghost.arm.containsKey(p.getName()))
              {
                p.getEquipment().setArmorContents((ItemStack[])Ghost.arm.get(p.getName()));
                Ghost.arm.remove(p.getName());
              }
              if (Ghost.inv.containsKey(p.getName()))
              {
                p.getInventory().setContents((ItemStack[])Ghost.inv.get(p.getName()));
                Ghost.inv.remove(p.getName());
              }
              Ghost.fantasmas.remove(p.getName());
              if (Ghost.task.containsKey(p.getName())) {
                Ghost.task.remove(p.getName());
              }
              p.removePotionEffect(PotionEffectType.INVISIBILITY);
              p.updateInventory();
              p.sendMessage(ChatColor.GOLD + "Voce voltou a forma humana!");
              p.playSound(p.getLocation(), Sound.ZOMBIE_UNFECT, 1.0F, 1.0F);
            }
          }.runTaskLater(Main.plugin, 400L);
          task.put(p.getName(), id);
          return;
        }
        p.sendMessage(ChatColor.RED + "Voce pode usar esta habilidade apenas em areas com PVP.");
      }
      else
      {
        p.sendMessage(ChatColor.RED + "Faltam " + TimeUnit.MILLISECONDS.toSeconds(((Long)cooldown.get(p.getName())).longValue() - System.currentTimeMillis()) + " segundos para poder usar novamente.");
        return;
      }
    }
  }
  
  @EventHandler
  public void dano(EntityDamageByEntityEvent e)
  {
    if (((e.getEntity() instanceof Player)) && ((e.getDamager() instanceof Player)))
    {
      Player p = (Player)e.getEntity();
      Player d = (Player)e.getDamager();
      if ((Main.ghost.contains(d.getName())) && 
        (d.getItemInHand().getType() == Material.IRON_HOE))
      {
        e.setDamage(e.getDamage() * 3.5D);
        d.sendMessage(ChatColor.AQUA + "SUGUE A ALMA DE " + ChatColor.DARK_RED + p.getName() + ChatColor.AQUA + " !");
        d.playSound(d.getLocation(), Sound.GHAST_SCREAM, 1.0F, 1.0F);
        return;
      }
    }
  }
  
  @EventHandler
  public void dano2(EntityDamageEvent e)
  {
    if ((e.getEntity() instanceof Player))
    {
      Player p = (Player)e.getEntity();
      if (fantasmas.contains(p.getName()))
      {
        e.setCancelled(true);
        return;
      }
    }
  }
  
  @EventHandler
  public void itens(PlayerPickupItemEvent e)
  {
    if (fantasmas.contains(e.getPlayer().getName())) {
      e.setCancelled(true);
    }
  }
}