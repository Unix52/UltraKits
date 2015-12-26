package UltraKits.Habilidades;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import UltraKits.Main;

public class Hulk
  implements Listener
{
  public static HashMap<String, Long> cooldown = new HashMap();
  
  @EventHandler
  public void pegar(PlayerInteractEntityEvent e)
  {
    Player p = e.getPlayer();
    if ((e.getRightClicked() instanceof Player))
    {
      Player r = (Player)e.getRightClicked();
      if (Main.hulk.contains(p.getName())) {
        if ((!cooldown.containsKey(p.getName())) || (((Long)cooldown.get(p.getName())).longValue() <= System.currentTimeMillis()))
        {
          if (Main.areaPvP(p))
          {
            if (Main.areaPvP(r))
            {
              if (p.getItemInHand().getType() == Material.SADDLE)
              {
                e.setCancelled(true);
                p.updateInventory();
                p.setPassenger(r);
                cooldown.put(p.getName(), Long.valueOf(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(15L)));
                p.sendMessage(ChatColor.GOLD + "Voce pegou o player: " + ChatColor.WHITE + r.getName());
                r.sendMessage(ChatColor.GOLD + "Voce foi pego pelo Hulk: " + ChatColor.WHITE + p.getName());
              }
            }
            else {
              p.sendMessage(ChatColor.RED + "Voce pode usar esta habilidade apenas em players que estejam em areas com PVP.");
            }
          }
          else {
            p.sendMessage(ChatColor.RED + "Voce pode usar esta habilidade apenas em areas com PVP.");
          }
        }
        else {
          p.sendMessage(ChatColor.RED + "Faltam " + TimeUnit.MILLISECONDS.toSeconds(((Long)cooldown.get(p.getName())).longValue() - System.currentTimeMillis()) + " segundos para poder usar novamente.");
        }
      }
    }
  }
  
  @EventHandler
  public static void playerInteract(PlayerInteractEvent event)
  {
    if (!event.getAction().equals(Action.LEFT_CLICK_AIR)) {
      return;
    }
    Player player = event.getPlayer();
    if ((player.getPassenger() == null) || (!(player.getPassenger() instanceof Player))) {
      return;
    }
    if ((!Main.areaPvP(player)) || (!Main.areaPvP((Player)player.getPassenger())))
    {
      player.sendMessage(ChatColor.RED + "Essa habilidade pode ser usada apenas em areas com PVP.");
      return;
    }
    Player pass = (Player)player.getPassenger();
    player.eject();
    pass.damage(0.0D, player);
    pass.setVelocity(player.getLocation().getDirection().multiply(2.5D));
    pass.setVelocity(new Vector(pass.getVelocity().getX(), 1.0D, pass.getVelocity().getZ()));
    pass.sendMessage(ChatColor.RED + "Você foi jogado por " + ChatColor.DARK_RED + player.getName());
  }
}
