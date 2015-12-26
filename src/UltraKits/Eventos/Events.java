package UltraKits.Eventos;

import UltraKits.Comandos.CombatLog;
import UltraKits.Habilidades.Ghost;
import UltraKits.Habilidades.Gladiator;
import UltraKits.Habilidades.Indio;
import UltraKits.Habilidades.Ninja;
import UltraKits.Main;
import UltraKits.ServerScoreboard;
import UltraKits.SettingsManager;
import UltraKits.u1v1.Commands;
import UltraKits.u1v1.Desafiar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import me.confuser.barapi.BarAPI;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.help.HelpMap;
import org.bukkit.help.HelpTopic;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.ScoreboardManager;

public class Events
  implements Listener
{
  public static ItemStack warps = new ItemStack(Material.BOOK);
  public static ItemStack loja = new ItemStack(Material.DIAMOND);
  public static ItemStack kits = new ItemStack(Material.CHEST);
  public static HashMap<String, String> bars = new HashMap();
  public static Economy econ = null;
  public static EconomyResponse r;
  public Plugin plugin;
  
  public Events(Main plugin)
  {
    this.plugin = plugin;
  }
  
  SettingsManager settings = SettingsManager.getInstance();
  
  @EventHandler(priority=EventPriority.HIGHEST)
  public void soup(PlayerInteractEvent e)
  {
    Player p = e.getPlayer();
    if (((e.getAction() == Action.RIGHT_CLICK_BLOCK) || (e.getAction() == Action.RIGHT_CLICK_AIR)) && 
      (p.getItemInHand().getType() == Material.MUSHROOM_SOUP) && 
      (p.getHealth() > 0.0D) && (p.getHealth() < 20.0D))
    {
      if (!Main.quickdropper.contains(p.getName()))
      {
        p.getItemInHand().setType(Material.BOWL);
      }
      else
      {
        p.getItemInHand().setType(Material.BOWL);
        p.getInventory().remove(new ItemStack(Material.BOWL, 1));
        p.getWorld().dropItemNaturally(p.getLocation(), new ItemStack(Material.BOWL));
      }
      int regen = Main.plugin.getConfig().getInt("SoupRegen");
      int feed = Main.plugin.getConfig().getInt("SoupFeed");
      if (p.getHealth() + regen <= 20.0D) {
        p.setHealth(p.getHealth() + regen);
      } else {
        p.setHealth(20.0D);
      }
      if (p.getFoodLevel() + feed <= 20) {
        p.setFoodLevel(p.getFoodLevel() + feed);
      } else {
        p.setFoodLevel(20);
      }
      if (Main.plugin.getConfig().getBoolean("SoupSound")) {
        p.playSound(p.getLocation(), Sound.DRINK, 1.0F, 1.0F);
      }
    }
  }
  
  @EventHandler(priority=EventPriority.MONITOR)
  public void onLoginBan(PlayerLoginEvent e)
  {
    Player p = e.getPlayer();
    if (e.getResult().equals(PlayerLoginEvent.Result.KICK_FULL)) {
      e.disallow(PlayerLoginEvent.Result.KICK_FULL, ChatColor.RED + "O Servidor está cheio.\n " + ChatColor.GREEN + Main.plugin.getConfig().getString("ServerSite"));
    } else if (e.getResult().equals(PlayerLoginEvent.Result.KICK_WHITELIST)) {
      e.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, ChatColor.RED + "Você nao está na " + ChatColor.WHITE + "WhiteList\n " + ChatColor.GREEN + Main.plugin.getConfig().getString("ServerSite"));
    } else if (e.getResult().equals(PlayerLoginEvent.Result.KICK_BANNED)) {
      e.disallow(PlayerLoginEvent.Result.KICK_BANNED, ChatColor.RED + "Você está BANIDO! \n" + ChatColor.GREEN + Main.plugin.getConfig().getString("ServerSite"));
    }
    if (p.getName().length() + 4 <= 16) {
      if ((p.hasPermission("uk.admin")) || (p.isOp()))
      {
        p.setDisplayName(ChatColor.DARK_RED + p.getName() + ChatColor.RESET);
        p.setPlayerListName(ChatColor.DARK_RED + p.getName() + ChatColor.RESET);
      }
      else if (p.hasPermission("tab.vip"))
      {
        p.setDisplayName(ChatColor.GOLD + p.getName() + ChatColor.RESET);
        p.setPlayerListName(ChatColor.GOLD + p.getName() + ChatColor.RESET);
      }
      else if (p.hasPermission("tab.membro"))
      {
        p.setDisplayName(ChatColor.GRAY + p.getName() + ChatColor.RESET);
        p.setPlayerListName(ChatColor.GRAY + p.getName() + ChatColor.RESET);
      }
    }
  }
  
  @EventHandler(priority=EventPriority.NORMAL)
  public void comandoInvalido(PlayerCommandPreprocessEvent event)
  {
    if (!event.isCancelled())
    {
      Player player = event.getPlayer();
      String cmd = event.getMessage().split(" ")[0];
      HelpTopic topic = Bukkit.getServer().getHelpMap().getHelpTopic(cmd);
      if (topic == null)
      {
        player.sendMessage("§cComando inexistente ou invalido!");
        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
        event.setCancelled(true);
      }
    }
  }
  
  @EventHandler(priority=EventPriority.HIGHEST)
  public void onDrop(PlayerDropItemEvent e)
  {
    Player p = e.getPlayer();
    if ((e.getItemDrop().getItemStack().getType() != Material.BOWL) && (e.getItemDrop().getItemStack().getType() != Material.MUSHROOM_SOUP))
    {
      e.setCancelled(true);
      p.updateInventory();
      p.sendMessage(ChatColor.RED + "Este item nao pode ser dropado!");
      return;
    }
  }
  
  @EventHandler
  public void chuva(WeatherChangeEvent event)
  {
    if (event.toWeatherState()) {
      event.setCancelled(true);
    }
  }
  
  @EventHandler
  public void onDamage(EntityDamageEvent e)
  {
    if (e.getCause() == EntityDamageEvent.DamageCause.VOID)
    {
      Player p = (Player)e.getEntity();
      p.setHealth(0.0D);
    }
  }
  
  @EventHandler
  public void onKitReset(PlayerDeathEvent e)
  {
    e.getDrops().clear();
    e.setDeathMessage(null);
    Player p = e.getEntity();
    Ninja.resetTarget(p);
    for (PotionEffect effect : p.getActivePotionEffects()) {
      p.removePotionEffect(effect.getType());
    }
  }
  
  @EventHandler
  public void onPlayerColor(SignChangeEvent e)
  {
    if (e.getPlayer().hasPermission("uk.admin"))
    {
      if (e.getLine(0).contains("&")) {
        e.setLine(0, e.getLine(0).replace("&", "§"));
      }
      if (e.getLine(1).contains("&")) {
        e.setLine(1, e.getLine(1).replace("&", "§"));
      }
      if (e.getLine(2).contains("&")) {
        e.setLine(2, e.getLine(2).replace("&", "§"));
      }
      if (e.getLine(3).contains("&")) {
        e.setLine(3, e.getLine(3).replace("&", "§"));
      }
    }
  }
  
  @EventHandler(priority=EventPriority.MONITOR)
  public void onPlayerLoseHunger(FoodLevelChangeEvent e)
  {
    e.setFoodLevel(20);
  }
  
  @EventHandler
  public void explodeEvent(EntityExplodeEvent event)
  {
    event.setCancelled(true);
  }
  
  @EventHandler
  public void onPlayerExit(PlayerQuitEvent e)
  {
    Player p = e.getPlayer();
    if (CombatLog.cl.contains(p.getName()))
    {
      p.setHealth(0.0D);
      Bukkit.broadcastMessage(ChatColor.RED + p.getDisplayName() + ChatColor.RED + " foi morto por CombatLog!");
    }
  }
  
  @EventHandler(priority=EventPriority.MONITOR)
  public void onPlayerJoin(PlayerJoinEvent e)
  {
    final Player p = e.getPlayer();
    p.getInventory().clear();
    p.setHealth(20.0D);
    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable()
    {
      public void run()
      {
        p.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
        ServerScoreboard.criarScoreboard(p);
      }
    }, 1L);
    Main.resetKit(p);
    Main.restaurarItens(p);
    Desafiar.showPlayer(p);
    p.getInventory().setArmorContents(
      new ItemStack[] { new ItemStack(Material.AIR), 
      new ItemStack(Material.AIR), 
      new ItemStack(Material.AIR), 
      new ItemStack(Material.AIR) });
    p.getInventory().setArmorContents(null);
    try
    {
      World w = Bukkit.getServer().getWorld(this.settings.getData().getString("spawn.world"));
      double x = this.settings.getData().getDouble("spawn.x");
      double y = this.settings.getData().getDouble("spawn.y");
      double z = this.settings.getData().getDouble("spawn.z");
      Location loc = new Location(w, x, y, z);
      loc.setPitch((float)this.settings.getData().getDouble("spawn.pitch"));
      loc.setYaw((float)this.settings.getData().getDouble("spawn.yaw"));
      p.teleport(loc);
    }
    catch (IllegalArgumentException ex)
    {
      p.sendMessage(ChatColor.RED + "Spawn nao definido ainda. Peça a um Staff para seta-lo.");
    }
  }
  
  @SuppressWarnings({ "rawtypes", "unused" })
@EventHandler(priority=EventPriority.HIGHEST)
  public void onPlayerLeft(PlayerQuitEvent e)
  {
    Player p = e.getPlayer();
    if (Ghost.task.containsKey(p.getName()))
    {
      BukkitTask task = (BukkitTask)Ghost.task.get(p.getName());
      task.cancel();
      Ghost.task.remove(p.getName());
    }
    if (Ghost.arm.containsKey(p.getName())) {
      Ghost.arm.remove(p.getName());
    }
    if (Ghost.inv.containsKey(p.getName())) {
      Ghost.inv.remove(p.getName());
    }
    if (Ghost.fantasmas.contains(p.getName())) {
      Ghost.fantasmas.remove(p.getName());
    }
    if (Indio.tiros.containsKey(p.getName())) {
      Indio.tiros.remove(p.getName());
    }
    for (Map.Entry en : bars.entrySet())
    {
      String s = (String)en.getKey();
      String r = (String)en.getValue();
      if (p.getName() == s) {
        Player localPlayer1 = Bukkit.getPlayer(r);
      }
    }
    e.setQuitMessage(null);
    Bukkit.getServer().broadcast(ChatColor.RED + "- " + ChatColor.RESET + p.getDisplayName() + ChatColor.RESET + ChatColor.GRAY + " saiu do servidor", "exit.vanish.receive");
  }
  
  @EventHandler(priority=EventPriority.HIGHEST)
  public void msg(PlayerJoinEvent e)
  {
    Player p = e.getPlayer();
    Main.desafio.remove(p.getName());
    Main.admin.remove(p.getName());
    
    e.setJoinMessage(ChatColor.GREEN + "+ " + ChatColor.RESET + p.getDisplayName() + ChatColor.RESET + ChatColor.GRAY + " entrou no servidor");
    p.getPlayer().setTotalExperience(0);
    BarAPI.setMessage(p, Main.plugin.getConfig().getString("WelcomeBar").replaceAll("&", "§").replaceAll("PLAYER", p.getName()), 5);
    for (PotionEffect effect : p.getActivePotionEffects()) {
      p.removePotionEffect(effect.getType());
    }
  }
  
  @EventHandler(priority=EventPriority.MONITOR)
  public void onPlayerRespawn(PlayerRespawnEvent e)
  {
    Player p = e.getPlayer();
    if (!Commands.em.contains(p.getName())) {
      Main.resetKit(p);
    }
  }
  
  @EventHandler
  public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e)
  {
    Player player = e.getPlayer();
    if (Gladiator.fighting.containsKey(player.getPlayer().getName()))
    {
      player.sendMessage(ChatColor.RED + "Comandos desativados na arena gladiator!");
      player.playSound(player.getLocation(), Sound.CAT_HIT, 4.0F, 4.0F);
      e.setCancelled(true);
    }
  }
  
  @EventHandler
  public void barKit(EntityDamageByEntityEvent e)
  {
    if (((e.getEntity() instanceof Player)) && ((e.getDamager() instanceof Player)))
    {
      Player p = (Player)e.getEntity();
      Player damager = (Player)e.getDamager();
      if (!Commands.em.contains(p.getName()))
      {
        BarAPI.setMessage(damager, p.getName() + " - " + getKit(p), 5);
        if (bars.containsKey(damager.getName()))
        {
          if (bars.get(damager.getName()) != p.getName()) {
            bars.put(damager.getName(), p.getName());
          }
        }
        else {
          bars.put(damager.getName(), p.getName());
        }
      }
      else
      {
        BarAPI.removeBar(p);
      }
    }
  }
  
  public String getKit(Player p)
  {
    if (Main.hg.contains(p.getName())) {
      return "PvP";
    }
    if (Main.arqueiro.contains(p.getName())) {
      return "Arqueiro";
    }
    if (Main.urgal.contains(p.getName())) {
      return "Urgal";
    }
    if (Main.whister.contains(p.getName())) {
      return "Shooter";
    }
    if (Main.pyro.contains(p.getName())) {
      return "Pyro";
    }
    if (Main.Trocador.contains(p.getName())) {
      return "Trocador";
    }
    if (Main.soldado.contains(p.getName())) {
      return "Soldado";
    }
    if (Main.viper.contains(p.getName())) {
      return "Viper";
    }
    if (Main.ninja.contains(p.getName())) {
      return "Ninja";
    }
    if (Main.anchor.contains(p.getName())) {
      return "Anchor";
    }
    if (Main.tank.contains(p.getName())) {
      return "Tank";
    }
    if (Main.switcher.contains(p.getName())) {
      return "Switcher";
    }
    if (Main.darkmage.contains(p.getName())) {
      return "Darkmage";
    }
    if (Main.Teleporter.contains(p.getName())) {
      return "Teleporter";
    }
    if (Main.thor.contains(p.getName())) {
      return "Thor";
    }
    if (Main.specialist.contains(p.getName())) {
      return "Specialist";
    }
    if (Main.launcher.contains(p.getName())) {
      return "Launcher";
    }
    if (Main.milkman.contains(p.getName())) {
      return "Milkman";
    }
    if (Main.skeleton.contains(p.getName())) {
      return "Skeleton";
    }
    if (Main.fisherman.contains(p.getName())) {
      return "Fisherman";
    }
    if (Main.phantom.contains(p.getName())) {
      return "Phantom";
    }
    if (Main.gladiator.contains(p.getName())) {
      return "Gladiator";
    }
    if (Main.flash.contains(p.getName())) {
      return "Flash";
    }
    if (Main.grappler.contains(p.getName())) {
      return "Grappler";
    }
    if (Main.endermage.contains(p.getName())) {
      return "Endermage";
    }
    if (Main.monk.contains(p.getName())) {
      return "Monk";
    }
    if (Main.camel.contains(p.getName())) {
      return "Camel";
    }
    if (Main.frosty.contains(p.getName())) {
      return "Frosty";
    }
    if (Main.wither.contains(p.getName())) {
      return "Wither";
    }
    if (Main.poseidon.contains(p.getName())) {
      return "Poseidon";
    }
    if (Main.stomper.contains(p.getName())) {
      return "Stomper";
    }
    if (Main.reaper.contains(p.getName())) {
      return "Reaper";
    }
    if (Main.pisca.contains(p.getName())) {
      return "Remix";
    }
    if (Main.turtle.contains(p.getName())) {
      return "Turtle";
    }
    if (Main.jumper.contains(p.getName())) {
      return "Jumper";
    }
    if (Main.snail.contains(p.getName())) {
      return "Snail";
    }
    if (Main.fireman.contains(p.getName())) {
      return "Fireman";
    }
    if (Main.kangaroo.contains(p.getName())) {
      return "Kangaroo";
    }
    if (Main.viking.contains(p.getName())) {
      return "Viking";
    }
    if (Main.madman.contains(p.getName())) {
      return "Madman";
    }
    if (Main.grandpa.contains(p.getName())) {
      return "Grandpa";
    }
    if (Main.ghost.contains(p.getName())) {
      return "Ghost";
    }
    if (Main.barbarian.contains(p.getName())) {
      return "Barbarian";
    }
    if (Main.spiderman.contains(p.getName())) {
      return "SpiderMan";
    }
    if (Main.berserker.contains(p.getName())) {
      return "Berserker";
    }
    if (Main.indio.contains(p.getName())) {
      return "Indio";
    }
    if (Main.ryu.contains(p.getName())) {
      return "Ryu";
    }
    if (Main.neji.contains(p.getName())) {
      return "Neji";
    }
    if (Main.granadier.contains(p.getName())) {
      return "Granadier";
    }
    if (Main.rhino.contains(p.getName())) {
      return "Rhino";
    }
    if (Main.alien.contains(p.getName())) {
      return "Alien";
    }
    if (Main.critical.contains(p.getName())) {
      return "Critical";
    }
    if (Main.hulk.contains(p.getName())) {
      return "Hulk";
    }
    if (Main.lobisomem.contains(p.getName())) {
      return "Lobisomem";
    }
    if (Main.phantom.contains(p.getName())) {
      return "Phantom";
    }
    if (Main.vitality.contains(p.getName())) {
      return "Vitality";
    }
    if (Main.quickdropper.contains(p.getName())) {
      return "QuickDropper";
    }
    return "Sem Kit";
  }
}
