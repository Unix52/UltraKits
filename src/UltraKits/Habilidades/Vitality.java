package UltraKits.Habilidades;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import UltraKits.Main;

public class Vitality
  implements Listener
{
  @EventHandler
  public void onKill(PlayerDeathEvent e)
  {
    if ((e.getEntity().getKiller() instanceof Player))
    {
      Player k = e.getEntity().getKiller();
      if (Main.vitality.contains(k.getName()))
      {
        k.getInventory().remove(Material.BOWL);
        try
        {
          for (int i = 0; i < 36; i++) {
            k.getInventory().setItem(k.getInventory().firstEmpty(), new ItemStack(Material.MUSHROOM_SOUP));
          }
        }
        catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException) {}
        k.sendMessage(ChatColor.GREEN + "Seu inventario está cheio de sopas!");
        k.playSound(k.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
        return;
      }
    }
  }
}
