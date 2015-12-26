package UltraKits.Habilidades;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import UltraKits.Main;

public class Flash
  implements Listener
{
  public Plugin plugin;
  
  public Flash(Main plugin)
  {
    this.plugin = plugin;
  }
  
  @EventHandler(priority=EventPriority.NORMAL)
  public void oss5(BlockPlaceEvent e)
  {
    Player p = e.getPlayer();
    if (Main.flash.contains(p.getName())) {
      e.setCancelled(true);
    }
  }
  
  @EventHandler
  public void FlashA(PlayerInteractEvent e)
  {
    final Player p = e.getPlayer();
    if (!Main.flash.contains(p.getName())) {
      return;
    }
    if (!e.getAction().name().contains("RIGHT")) {
      return;
    }
    if (p.getItemInHand().getType() != Material.REDSTONE_TORCH_ON) {
      return;
    }
    if (Main.reload.contains(p.getName())) {
      return;
    }
    if (!Main.kits.contains(p))
    {
      if (!Gladiator.in1v1.contains(p))
      {
        Main.reload.add(p.getName());
        p.sendMessage(ChatColor.RED + "Voce so podera usar depois de 15 segundos!");
        p.getWorld().strikeLightningEffect(p.getLocation());
        p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0F, 1.0F);
        p.getEyeLocation().getWorld().playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 5);
        
        Block b = p.getTargetBlock(null, 100).getRelative(BlockFace.UP);
        Location loc = b.getLocation();
        loc.setPitch(p.getLocation().getPitch());
        loc.setYaw(p.getLocation().getYaw());
        p.teleport(loc);
        p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200, 0));
        p.getItemInHand().setType(Material.REDSTONE_TORCH_OFF);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
        {
          public void run()
          {
            p.sendMessage(ChatColor.GREEN + "Carregado!");
            Main.reload.remove(p.getName());
            for (ItemStack i : p.getInventory().getContents()) {
              if ((i != null) && (i.getType() == Material.REDSTONE_TORCH_OFF)) {
                i.setType(Material.REDSTONE_TORCH_ON);
              }
            }
          }
        }, 300L);
      }
      else
      {
        p.sendMessage(ChatColor.RED + "Voce nao pode usar esta habilidade no Gladiator.");
      }
    }
    else {
      p.sendMessage(ChatColor.RED + "Voce pode usar esta habilidade apenas em areas com PVP.");
    }
  }
}