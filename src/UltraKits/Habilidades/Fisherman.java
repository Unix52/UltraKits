package UltraKits.Habilidades;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

import UltraKits.Main;

public class Fisherman
  implements Listener
{
  @EventHandler
  public void onPlayerFish(PlayerFishEvent event)
  {
    Entity caught = event.getCaught();
    Block block = event.getHook().getLocation().getBlock();
    if ((caught != null) && (caught != block) && 
      (Main.fisherman.contains(event.getPlayer().getName()))) {
      caught.teleport(event.getPlayer().getLocation());
    }
  }
}
