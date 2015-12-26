package UltraKits.Habilidades;

import UltraKits.Main;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

public class Endermage
  implements Listener
{
  public Main plugin;
  
  public Endermage(Main plugin)
  {
    this.plugin = plugin;
  }
  
  @EventHandler
  public void onPlace(PlayerInteractEvent event)
  {
    ItemStack item = event.getItem();
    Player p2 = event.getPlayer();
    if (((event.getAction() == Action.RIGHT_CLICK_BLOCK) || (event.getAction() == Action.LEFT_CLICK_BLOCK)) && (item != null) && (Main.endermage.contains(p2.getName())) && (event.getMaterial() == Material.PORTAL))
    {
      event.setCancelled(true);
      final Block b = event.getClickedBlock();
      if ((Main.endermage.contains(p2.getName())) && (b.getTypeId() == 120)) {
        return;
      }
      if (Main.areaPvP(p2))
      {
        item.setAmount(0);
        if (item.getAmount() == 0) {
          event.getPlayer().setItemInHand(new ItemStack(0));
        }
        final Location portal = b.getLocation().clone().add(0.5D, 0.5D, 0.5D);
        final Material material = b.getType();
        final byte dataValue = b.getData();
        portal.getBlock().setTypeId(120);
        final Player mager = event.getPlayer();
        for (int i = 0; i <= 5; i++)
        {
          final int no = i;
          Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
          {
            public void run()
            {
              for (Player p : Bukkit.getOnlinePlayers()) {
                if ((b.getTypeId() == 120) && 
                  (!Main.endermage.contains(p.getName())) && (p != mager.getPlayer()) && (Endermage.this.isEnderable(portal, p.getLocation())))
                {
                  Location teleport = portal.clone().add(0.0D, 0.5D, 0.0D);
                  if (p.getLocation().distance(portal) > 3.0D)
                  {
                    mager.getPlayer().sendMessage("�a�lJogador Puxado!");
                    mager.getPlayer().sendMessage("�c�lVoce tem 5 segundos de invencibilidade, se prepare, ou fuja!");
                    
                    mager.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 2));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 2));
                    
                    p.sendMessage("�cVoce Foi Puxado!");
                    p.sendMessage("�cVoce tem�c�l 5 segundos de invencibilidade, se prepare, ou fuja!");
                    

                    p.setNoDamageTicks(100);
                    
                    mager.getPlayer().setNoDamageTicks(100);
                    mager.getPlayer().teleport(teleport);
                    p.teleport(teleport);
                    ItemStack endermage = new ItemStack(Material.PORTAL, 1);
                    ItemMeta name = endermage.getItemMeta();
                    name.setDisplayName(ChatColor.RED + "Endermage");
                    endermage.setItemMeta(name);
                    mager.getInventory().addItem(new ItemStack[] { endermage });
                  }
                  portal.getBlock().setTypeIdAndData(material.getId(), dataValue, true);
                }
              }
              if (no == 5)
              {
                ItemStack endermage = new ItemStack(Material.PORTAL, 1);
                ItemMeta name = endermage.getItemMeta();
                name.setDisplayName(ChatColor.RED + "Endermage");
                endermage.setItemMeta(name);
                mager.getInventory().addItem(new ItemStack[] { endermage });
                portal.getBlock().setTypeIdAndData(material.getId(), dataValue, true);
              }
            }
          }, i * 20);
        }
        return;
      }
      p2.sendMessage(ChatColor.RED + "Voce pode usar esta habilidade apenas em areas com PVP.");
      return;
    }
  }
  
  private boolean isEnderable(Location portal, Location player)
  {
    return (Math.abs(portal.getX() - player.getX()) < 2.0D) && (Math.abs(portal.getZ() - player.getZ()) < 2.0D) && (
      Math.abs(portal.getY() - player.getY()) > 1.0D);
  }
}
