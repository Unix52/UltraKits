package UltraKits.Eventos;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class JumpBlocks
  implements Listener
{
  public static ArrayList<String> jump = new ArrayList();
  
  @EventHandler
  public void onJump(PlayerMoveEvent e)
  {
    Player p = e.getPlayer();
    if ((e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SPONGE) && (e.getTo().getBlock().getRelative(BlockFace.DOWN).getRelative(BlockFace.DOWN).getType() == Material.BEACON))
    {
      jump.remove(p.getName());
      p.setVelocity(p.getVelocity().setY(3.0D));
      p.playSound(p.getLocation(), Sound.SLIME_WALK2, 1.0F, 1.0F);
      jump.add(p.getName());
    }
    else if ((e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.EMERALD_BLOCK) && (e.getTo().getBlock().getRelative(BlockFace.DOWN).getRelative(BlockFace.DOWN).getType() == Material.BEACON))
    {
      jump.remove(p.getName());
      p.setVelocity(p.getLocation().getDirection().multiply(6.0D));
      p.setVelocity(new Vector(p.getVelocity().getX(), 1.5D, p.getVelocity().getZ()));
      p.playSound(p.getLocation(), Sound.FIREWORK_LAUNCH, 40.0F, 1.0F);
      jump.add(p.getName());
    }
  }
  
  @EventHandler
  public void onFall(EntityDamageEvent e)
  {
    if ((e.getEntity() instanceof Player))
    {
      Player p = (Player)e.getEntity();
      if ((e.getCause().equals(EntityDamageEvent.DamageCause.FALL)) && 
        (jump.contains(p.getName())))
      {
        e.setCancelled(true);
        jump.remove(p.getName());
      }
    }
  }
}
