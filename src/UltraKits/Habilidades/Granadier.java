package UltraKits.Habilidades;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import UltraKits.Main;

public class Granadier
  implements Listener
{
  @EventHandler
  public void lancar(PlayerInteractEvent e)
  {
    Player p = e.getPlayer();
    if (((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) && 
      (Main.granadier.contains(p.getName())) && 
      (p.getItemInHand().getType() == Material.SNOW_BALL))
    {
      e.setCancelled(true);
      p.updateInventory();
      p.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.SNOW_BALL, 1) });
      Snowball granada = (Snowball)p.launchProjectile(Snowball.class);
      granada.setMetadata("granadier", new FixedMetadataValue(Main.plugin, Boolean.valueOf(true)));
      p.playSound(p.getLocation(), Sound.FUSE, 1.0F, 1.0F);
      p.sendMessage(ChatColor.GOLD + "Granada jogada!");
      return;
    }
  }
  
  @EventHandler
  public void explosao(ProjectileHitEvent e)
  {
    if ((e.getEntity() instanceof Snowball))
    {
      Snowball b = (Snowball)e.getEntity();
      if (b.hasMetadata("granadier")) {
        b.getWorld().createExplosion(b.getLocation(), 3.0F);
      }
      return;
    }
  }
  
  @EventHandler
  public void dano(EntityDamageEvent e)
  {
    if ((e.getEntity() instanceof Player))
    {
      Player p = (Player)e.getEntity();
      if ((e.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION) && 
        (Main.granadier.contains(p.getName()))) {
        e.setCancelled(true);
      }
    }
  }
}
