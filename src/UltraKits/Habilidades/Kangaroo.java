package UltraKits.Habilidades;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import UltraKits.Main;

public class Kangaroo
  implements Listener
{
  @SuppressWarnings({ "unchecked", "rawtypes" })
ArrayList<Player> kangaroo = new ArrayList();
  public Main plugin;
  
  public Kangaroo(Main plugin)
  {
    this.plugin = plugin;
  }
  
  public Kangaroo() {}
  
  @EventHandler
  public void onInteract(PlayerInteractEvent event)
  {
    Player p = event.getPlayer();
    if (p.getItemInHand().getType() == Material.FIREWORK)
    {
      if ((event.getAction() == Action.LEFT_CLICK_AIR) || 
        (event.getAction() == Action.LEFT_CLICK_BLOCK) || 
        (event.getAction() == Action.RIGHT_CLICK_BLOCK) || 
        (event.getAction() == Action.RIGHT_CLICK_AIR)) {
        event.setCancelled(true);
      }
      if (!this.kangaroo.contains(p)) {
        if (Main.areaPvP(p))
        {
          if (!p.isSneaking())
          {
            p.setFallDistance(-5.0F);
            Vector vector = p.getEyeLocation().getDirection();
            vector.multiply(0.6F);
            vector.setY(1.2F);
            p.setVelocity(vector);
          }
          else
          {
            p.setFallDistance(-5.0F);
            Vector vector = p.getEyeLocation().getDirection();
            vector.multiply(1.2F);
            vector.setY(0.8D);
            p.setVelocity(vector);
          }
          this.kangaroo.add(p);
        }
        else
        {
          p.sendMessage(ChatColor.RED + "Voce pode usar esta habilidade apenas em areas com PVP.");
        }
      }
    }
  }
  
  @EventHandler
  public void onMove(PlayerMoveEvent event)
  {
    Player p = event.getPlayer();
    if (this.kangaroo.contains(p))
    {
      Block b = p.getLocation().getBlock();
      if ((b.getType() != Material.AIR) || 
        (b.getRelative(BlockFace.DOWN).getType() != Material.AIR))
      {
        this.kangaroo.remove(p);
        
        return;
      }
    }
  }
  
  @EventHandler
  public void onDamage(EntityDamageEvent event)
  {
    Entity e = event.getEntity();
    if ((e instanceof Player))
    {
      Player player = (Player)e;
      if (((event.getEntity() instanceof Player)) && 
        (event.getCause() == EntityDamageEvent.DamageCause.FALL) && 
        (player.getInventory().contains(Material.FIREWORK)) && 
        (event.getDamage() >= 7.0D)) {
        event.setDamage(7.0D);
      }
    }
  }
}
