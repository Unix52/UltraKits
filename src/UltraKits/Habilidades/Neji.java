package UltraKits.Habilidades;

import UltraKits.Main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Neji
  implements Listener
{
  public static HashMap<String, Long> cooldown = new HashMap();
  public static ArrayList<String> aplicando = new ArrayList();
  public static ItemStack chakra = new ItemStack(Material.EYE_OF_ENDER);
  
  @EventHandler
  public void interact(PlayerInteractEvent e)
  {
    Player p = e.getPlayer();
    if (((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) && 
      (Main.neji.contains(p.getName())) && 
      (p.getItemInHand().equals(chakra)))
    {
      e.setCancelled(true);
      if ((!cooldown.containsKey(p.getName())) || (((Long)cooldown.get(p.getName())).longValue() <= System.currentTimeMillis()))
      {
        if (Main.areaPvP(p))
        {
          apartaivos(p);
          cooldown.put(p.getName(), Long.valueOf(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(25L)));
          p.sendMessage(ChatColor.DARK_PURPLE + "HAKKESHOU KAITEN!");
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
  
  public static void apartaivos(Player p)
  {
    new BukkitRunnable()
    {
      int c = 0;
      
      public void run()
      {
        if (this.c < 10)
        {
          this.c += 1;
          Neji.bloco(Neji.this);
          Neji.knockback(Neji.this);
          if (!Neji.aplicando.contains(Neji.this.getName())) {
            Neji.aplicando.add(Neji.this.getName());
          }
        }
        else
        {
          Neji.aplicando.remove(Neji.this.getName());
          cancel();
        }
      }
    }.runTaskTimer(Main.plugin, 0L, 3L);
  }
  
  public static void bloco(Player p)
  {
    Location l = p.getLocation();
    Effect ef = Effect.STEP_SOUND;
    Material m = Material.BEACON;
    Block b1 = l.getBlock().getRelative(BlockFace.EAST).getRelative(BlockFace.EAST).getRelative(BlockFace.NORTH);
    Block b2 = l.getBlock().getRelative(BlockFace.EAST).getRelative(BlockFace.EAST).getRelative(BlockFace.SOUTH);
    Block b3 = l.getBlock().getRelative(BlockFace.WEST).getRelative(BlockFace.WEST).getRelative(BlockFace.NORTH);
    Block b4 = l.getBlock().getRelative(BlockFace.WEST).getRelative(BlockFace.WEST).getRelative(BlockFace.SOUTH);
    Block b5 = b1.getRelative(BlockFace.UP);
    Block b6 = b2.getRelative(BlockFace.UP);
    Block b7 = b3.getRelative(BlockFace.UP);
    Block b8 = b4.getRelative(BlockFace.UP);
    Block b9 = l.getBlock().getRelative(BlockFace.SOUTH).getRelative(BlockFace.SOUTH).getRelative(BlockFace.NORTH);
    Block b10 = l.getBlock().getRelative(BlockFace.SOUTH).getRelative(BlockFace.SOUTH).getRelative(BlockFace.SOUTH);
    Block b11 = l.getBlock().getRelative(BlockFace.NORTH).getRelative(BlockFace.NORTH).getRelative(BlockFace.NORTH);
    Block b12 = l.getBlock().getRelative(BlockFace.NORTH).getRelative(BlockFace.NORTH).getRelative(BlockFace.SOUTH);
    Block b13 = b9.getRelative(BlockFace.UP);
    Block b14 = b10.getRelative(BlockFace.UP);
    Block b15 = b11.getRelative(BlockFace.UP);
    Block b16 = b12.getRelative(BlockFace.UP);
    l.getWorld().playEffect(b1.getLocation(), ef, m, 10);
    l.getWorld().playEffect(b2.getLocation(), ef, m, 10);
    l.getWorld().playEffect(b3.getLocation(), ef, m, 10);
    l.getWorld().playEffect(b4.getLocation(), ef, m, 10);
    l.getWorld().playEffect(b5.getLocation(), ef, m, 10);
    l.getWorld().playEffect(b6.getLocation(), ef, m, 10);
    l.getWorld().playEffect(b7.getLocation(), ef, m, 10);
    l.getWorld().playEffect(b8.getLocation(), ef, m, 10);
    l.getWorld().playEffect(b9.getLocation(), ef, m, 10);
    l.getWorld().playEffect(b10.getLocation(), ef, m, 10);
    l.getWorld().playEffect(b11.getLocation(), ef, m, 10);
    l.getWorld().playEffect(b12.getLocation(), ef, m, 10);
    l.getWorld().playEffect(b13.getLocation(), ef, m, 10);
    l.getWorld().playEffect(b14.getLocation(), ef, m, 10);
    l.getWorld().playEffect(b15.getLocation(), ef, m, 10);
    l.getWorld().playEffect(b16.getLocation(), ef, m, 10);
  }
  
  public static void knockback(Player p)
  {
    Location loc = p.getLocation();
    List<Entity> nearby = loc.getWorld().getEntities();
    for (Entity e : nearby) {
      if ((e.getLocation().distance(loc) < 6.0D) && 
        ((e instanceof Player)))
      {
        Player d = (Player)e;
        if (d != p)
        {
          d.damage(4.0D, p);
          d.setVelocity(d.getLocation().getDirection().multiply(-2));
        }
      }
    }
  }
  
  @EventHandler
  public void damage(EntityDamageEvent e)
  {
    if ((e.getEntity() instanceof Player))
    {
      Player p = (Player)e.getEntity();
      if (aplicando.contains(p.getName())) {
        e.setCancelled(true);
      }
    }
  }
}
