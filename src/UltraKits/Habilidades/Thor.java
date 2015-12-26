package UltraKits.Habilidades;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import UltraKits.Main;

public class Thor
  implements Listener
{
  public Main plugin;
  
  public Thor(Main plugin)
  {
    this.plugin = plugin;
  }
  
  @EventHandler
  public void onPlayerThor(PlayerInteractEvent e)
  {
    final Player p = e.getPlayer();
    if (e.getAction() != Action.RIGHT_CLICK_BLOCK) {
      return;
    }
    if ((p.getItemInHand().getType() == Material.STONE_AXE) && 
      (Main.thor.contains(p.getName())))
    {
      if (Main.cooldown.contains(p.getName()))
      {
        e.setCancelled(true);
        p.sendMessage(ChatColor.RED + "Seus poderes so poderao ser usados daqui 5 segundos!");
        return;
      }
      e.setCancelled(true);
      Block b = e.getClickedBlock();
      if (Main.areaPvP(p))
      {
        World w = p.getWorld();
        w.spawnEntity(w.getHighestBlockAt(b.getLocation()).getLocation(), EntityType.LIGHTNING);
        Main.cooldown.add(p.getName());
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
        {
          public void run()
          {
            Main.cooldown.remove(p.getName());
          }
        }, 100L);
        return;
      }
      p.sendMessage(ChatColor.RED + "Voce pode usar esta habilidade apenas em areas com PVP.");
    }
  }
}
