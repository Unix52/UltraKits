package UltraKits.Habilidades;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import UltraKits.Main;

public class Ryu
  implements Listener
{
  public static HashMap<String, Long> cooldown = new HashMap();
  
  @EventHandler
  public void hadouken(PlayerInteractEvent e)
  {
    Player p = e.getPlayer();
    if (((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) && 
      (Main.ryu.contains(p.getName())) && 
      (p.getItemInHand().getType() == Material.BEACON)) {
      if ((!cooldown.containsKey(p.getName())) || (((Long)cooldown.get(p.getName())).longValue() <= System.currentTimeMillis()))
      {
        if (Main.areaPvP(p))
        {
          Location location = p.getEyeLocation();
          BlockIterator blocksToAdd = new BlockIterator(location, 0.0D, 40);
          while (blocksToAdd.hasNext())
          {
            Location blockToAdd = blocksToAdd.next().getLocation();
            p.getWorld().playEffect(blockToAdd, Effect.STEP_SOUND, Material.BEACON, 20);
            p.playSound(blockToAdd, Sound.IRONGOLEM_THROW, 3.0F, 3.0F);
          }
          Snowball h = (Snowball)p.launchProjectile(Snowball.class);
          Vector velo1 = p.getLocation().getDirection().normalize().multiply(10);
          h.setVelocity(velo1);
          h.setMetadata("hadouken", new FixedMetadataValue(Main.plugin, Boolean.valueOf(true)));
          cooldown.put(p.getName(), Long.valueOf(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(15L)));
          p.sendMessage(ChatColor.AQUA + "HADOUKEN!");
        }
        else
        {
          p.sendMessage(ChatColor.RED + "Voce pode usar esta habilidade apenas em areas com PVP.");
        }
      }
      else {
        p.sendMessage(ChatColor.RED + "Faltam " + TimeUnit.MILLISECONDS.toSeconds(((Long)cooldown.get(p.getName())).longValue() - System.currentTimeMillis()) + " segundos para poder usar novamente.");
      }
    }
  }
  
  @EventHandler
  public void dano(EntityDamageByEntityEvent e)
  {
    if (((e.getEntity() instanceof Player)) && ((e.getDamager() instanceof Snowball)))
    {
      Snowball s = (Snowball)e.getDamager();
      if (s.hasMetadata("hadouken")) {
        e.setDamage(e.getDamage() + 8.0D);
      }
    }
  }
}
