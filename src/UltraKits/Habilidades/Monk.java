package UltraKits.Habilidades;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import UltraKits.Main;
import UltraKits.u1v1.Commands;

public class Monk
  implements Listener
{
  public static HashMap<String, Long> cooldown = new HashMap();
  
  @EventHandler
  public void trocar(PlayerInteractEntityEvent e)
  {
    Player p = e.getPlayer();
    if ((e.getRightClicked() instanceof Player))
    {
      Player r = (Player)e.getRightClicked();
      if ((p.getItemInHand().getType() == Material.BLAZE_ROD) && 
        (Main.monk.contains(p.getName())) && (!Commands.em.contains(p.getName()))) {
        if ((!cooldown.containsKey(p.getName())) || (((Long)cooldown.get(p.getName())).longValue() <= System.currentTimeMillis()))
        {
          if (Main.areaPvP(p))
          {
            if (Main.areaPvP(r))
            {
              int random = new Random().nextInt(r.getInventory().getSize() - 10 + 1 + 10);
              ItemStack selected = r.getInventory().getItem(random);
              ItemStack held = r.getItemInHand();
              r.setItemInHand(selected);
              r.getInventory().setItem(random, held);
              cooldown.put(p.getName(), Long.valueOf(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(15L)));
            }
            else
            {
              p.sendMessage(ChatColor.RED + "Este player precisa estar em uma area com PVP.");
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
}
