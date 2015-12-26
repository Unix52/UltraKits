package UltraKits;

import UltraKits.Comandos.Admin;
import UltraKits.Comandos.Chat;
import UltraKits.Comandos.Comandos;
import UltraKits.Comandos.CombatLog;
import UltraKits.Comandos.CommandBuild;
import UltraKits.Comandos.KitReset;
import UltraKits.Comandos.MuteP;
import UltraKits.Comandos.Report;
import UltraKits.Comandos.Skit;
import UltraKits.Comandos.Tag;
import UltraKits.Comandos.Tell;
import UltraKits.Comandos.Warps;
import UltraKits.Eventos.JumpBlocks;
import UltraKits.Habilidades.Alien;
import UltraKits.Habilidades.Anchor;
import UltraKits.Habilidades.Barbarian;
import UltraKits.Habilidades.Berserker;
import UltraKits.Habilidades.Camel;
import UltraKits.Habilidades.Critical;
import UltraKits.Habilidades.Darkmage;
import UltraKits.Habilidades.Endermage;
import UltraKits.Habilidades.Eyer;
import UltraKits.Habilidades.Fireman;
import UltraKits.Habilidades.Fisherman;
import UltraKits.Habilidades.Flash;
import UltraKits.Habilidades.Frosty;
import UltraKits.Habilidades.Ghost;
import UltraKits.Habilidades.Gladiator;
import UltraKits.Habilidades.Granadier;
import UltraKits.Habilidades.Hulk;
import UltraKits.Habilidades.Indio;
import UltraKits.Habilidades.Jumper;
import UltraKits.Habilidades.Kangaroo;
import UltraKits.Habilidades.Launcher;
import UltraKits.Habilidades.Lobisomem;
import UltraKits.Habilidades.Milkman;
import UltraKits.Habilidades.Monk;
import UltraKits.Habilidades.Neji;
import UltraKits.Habilidades.Ninja;
import UltraKits.Habilidades.Phantom;
import UltraKits.Habilidades.Poseidon;
import UltraKits.Habilidades.Pyro;
import UltraKits.Habilidades.Reaper;
import UltraKits.Habilidades.Rhino;
import UltraKits.Habilidades.Ryu;
import UltraKits.Habilidades.Shooter;
import UltraKits.Habilidades.Skeleton;
import UltraKits.Habilidades.Snail;
import UltraKits.Habilidades.Soldado;
import UltraKits.Habilidades.Specialist;
import UltraKits.Habilidades.SpiderMan;
import UltraKits.Habilidades.Stomper;
import UltraKits.Habilidades.Switcher;
import UltraKits.Habilidades.Thor;
import UltraKits.Habilidades.Trocador;
import UltraKits.Habilidades.Turtle;
import UltraKits.Habilidades.Viking;
import UltraKits.Habilidades.Viper;
import UltraKits.Habilidades.Vitality;
import UltraKits.Habilidades.Wither;
import UltraKits.Inventarios.MenuInv;
import UltraKits.Inventarios.MenuInvListener;
import UltraKits.Inventarios.WarpsListener;
import UltraKits.u1v1.Commands;
import UltraKits.u1v1.Custom;
import UltraKits.u1v1.Desafiar;
import UltraKits.u1v1.ItemManager;
import UltraKits.u1v1.SB;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.Score;

public class Main
  extends JavaPlugin
  implements Listener
{
  public static SettingsManager settings;
  public static Plugin plugin;
  public static File d;
  public static FileConfiguration data;
  public static File k;
  public static FileConfiguration kit;
  public static File s;
  public static FileConfiguration stats;
  public static File w;
  public static FileConfiguration warps;
  public static File n;
  public static FileConfiguration m;
  public static List<String> lista = new ArrayList();
  
  public void onEnable()
  {
    plugin = this;
    autentificacao();
    verificarLicensa();
    registerEvents(plugin, new Listener[] { this, new Pyro(this), new JumpBlocks(), new Milkman(), new Gladiator(this), new MuteP(), new CommandBuild(), new Jumper(this), new Switcher(), new Skit(), new Specialist(), new SettingsManager(), new Placas(), new Shooter(this), new Endermage(this), new WarpsListener(), new Launcher(this), new KitItems(), new UltraKits.Eventos.Events(this), new Anchor(this), new MenuInv(), new Fireman(this), new MenuInvListener(), new Thor(this), new Fisherman(), new Camel(), new Frosty(), new Ninja(), new Kangaroo(), new Monk(), new Trocador(this), new Tell(), new Report(), new Reaper(), new Eyer(), new Poseidon(this), new Flash(this), new Skeleton(this), new Turtle(this), new Snail(), new Stomper(), new Viper(), new Darkmage(), new Wither(), new Soldado(this), new Custom(), new Desafiar(), new UltraKits.u1v1.Events(), new Chat(), new Viking(), new CombatLog(), new Ghost(), new SpiderMan(), new Barbarian(), new Berserker(), new ServerScoreboard(), new Indio(), new Ryu(), new Neji(), new Lobisomem(), new Granadier(), new Rhino(), new Phantom(), new Alien(), new Hulk(), new Critical(), new SB(), new Vitality(), new Admin() });
    comandos();
    saveDefaultConfig();
    getConfig().options().copyDefaults(true);
    configFiles();
    settings.setup(this);
    ItemManager.loadItems();
    Custom.loadCustomItems();
  }
  
  public void onDisable()
  {
    devolverItems();
    salvarStats2();
    salvarStats();
    HandlerList.unregisterAll(plugin);
    plugin = null;
  }
  
  public static HashMap<CommandSender, CommandSender> replies = new HashMap();
  public static ArrayList<String> admin = new ArrayList();
  public static ArrayList<String> gladgladiator = new ArrayList();
  public static ArrayList<String> invis = new ArrayList();
  public static ArrayList<String> reload = new ArrayList();
  public static ArrayList<String> kits = new ArrayList();
  public static ArrayList<String> skeleton = new ArrayList();
  public static ArrayList<String> hg = new ArrayList();
  public static ArrayList<String> whister = new ArrayList();
  public static ArrayList<String> arqueiro = new ArrayList();
  public static ArrayList<String> stomper = new ArrayList();
  public static ArrayList<String> Trocador = new ArrayList();
  public static ArrayList<String> viper = new ArrayList();
  public static ArrayList<String> cooldown = new ArrayList();
  public static ArrayList<String> thor = new ArrayList();
  public static ArrayList<String> launcher = new ArrayList();
  public static ArrayList<String> block = new ArrayList();
  public static ArrayList<String> turtle = new ArrayList();
  public static ArrayList<String> kitfreeze = new ArrayList();
  public static ArrayList<String> construiroon = new ArrayList();
  public static ArrayList<String> construiroff = new ArrayList();
  public static ArrayList<String> flash = new ArrayList();
  public static ArrayList<String> mute = new ArrayList();
  public static ArrayList<String> anchor = new ArrayList();
  public static ArrayList<String> bomber = new ArrayList();
  public static ArrayList<String> bowman = new ArrayList();
  public static ArrayList<String> jumper = new ArrayList();
  public static ArrayList<String> enderhg = new ArrayList();
  public static ArrayList<String> urgal = new ArrayList();
  public static ArrayList<String> pyro = new ArrayList();
  public static ArrayList<String> monk = new ArrayList();
  public static ArrayList<String> Teleporter = new ArrayList();
  public static ArrayList<String> fisherman = new ArrayList();
  public static ArrayList<String> reaper = new ArrayList();
  public static ArrayList<String> vanished = new ArrayList();
  public static ArrayList<String> frosty = new ArrayList();
  public static ArrayList<String> pisca = new ArrayList();
  public static ArrayList<String> specialist = new ArrayList();
  public static ArrayList<String> switcher = new ArrayList();
  public static ArrayList<String> snail = new ArrayList();
  public static ArrayList<String> milkman = new ArrayList();
  public static ArrayList<String> flat = new ArrayList();
  public static ArrayList<String> kangaroo = new ArrayList();
  public static ArrayList<String> gladiator = new ArrayList();
  public static ArrayList<String> camel = new ArrayList();
  public static ArrayList<String> cooldown1 = new ArrayList();
  public static ArrayList<String> grappler = new ArrayList();
  public static ArrayList<String> poseidon = new ArrayList();
  public static ArrayList<String> fireman = new ArrayList();
  public static ArrayList<String> wither = new ArrayList();
  public static ArrayList<String> Checkcooldown = new ArrayList();
  public static ArrayList<String> tank = new ArrayList();
  public static ArrayList<String> endermage = new ArrayList();
  public static ArrayList<String> phantom = new ArrayList();
  public static ArrayList<String> desafio = new ArrayList();
  public static ArrayList<String> ninja = new ArrayList();
  public static ArrayList<String> darkmage = new ArrayList();
  public static ArrayList<String> soldado = new ArrayList();
  public static ArrayList<String> viking = new ArrayList();
  public static ArrayList<String> madman = new ArrayList();
  public static ArrayList<String> grandpa = new ArrayList();
  public static ArrayList<String> ghost = new ArrayList();
  public static ArrayList<String> barbarian = new ArrayList();
  public static ArrayList<String> spiderman = new ArrayList();
  public static ArrayList<String> berserker = new ArrayList();
  public static ArrayList<String> indio = new ArrayList();
  public static ArrayList<String> ryu = new ArrayList();
  public static ArrayList<String> neji = new ArrayList();
  public static ArrayList<String> lobisomem = new ArrayList();
  public static ArrayList<String> granadier = new ArrayList();
  public static ArrayList<String> rhino = new ArrayList();
  public static ArrayList<String> alien = new ArrayList();
  public static ArrayList<String> hulk = new ArrayList();
  public static ArrayList<String> critical = new ArrayList();
  public static ArrayList<String> vitality = new ArrayList();
  public static ArrayList<String> quickdropper = new ArrayList();
  
  @EventHandler
  public void playerDeath(PlayerDeathEvent e)
  {
    Player p = e.getEntity();
    resetKit(p);
  }
  
  @EventHandler
  public void respawnar(PlayerRespawnEvent e)
  {
    final Player p = e.getPlayer();
    resetKit(p);
    restaurarItens(p);
    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()
    {
      public void run()
      {
        try
        {
          World w = Bukkit.getServer().getWorld(Main.settings.getData().getString("spawn.world"));
          double x = Main.settings.getData().getDouble("spawn.x");
          double y = Main.settings.getData().getDouble("spawn.y");
          double z = Main.settings.getData().getDouble("spawn.z");
          Location loc = new Location(w, x, y, z);
          loc.setPitch((float)Main.settings.getData().getDouble("spawn.pitch"));
          loc.setYaw((float)Main.settings.getData().getDouble("spawn.yaw"));
          p.teleport(loc);
        }
        catch (IllegalArgumentException ex)
        {
          p.sendMessage(ChatColor.RED + "Spawn nao definido ainda. Peça a um Staff para seta-lo.");
        }
      }
    }, 1L);
  }
  
  @EventHandler
  public void antiPluginView(PlayerCommandPreprocessEvent e)
  {
    Player p = e.getPlayer();
    if ((e.getMessage().equalsIgnoreCase("/pl")) || (e.getMessage().equalsIgnoreCase("/plugins")) || (e.getMessage().equalsIgnoreCase("/plugin")) || (e.getMessage().equalsIgnoreCase("/help")) || (e.getMessage().equalsIgnoreCase("/?")))
    {
      e.setCancelled(true);
      p.sendMessage(ChatColor.AQUA + "Quer um Plugin Como Esse? Skype:" + ChatColor.GRAY + " dlogylnaelc");
      p.sendMessage(ChatColor.AQUA + "UltraKits -" + ChatColor.GRAY + " http://goo.gl/bmQtdA");
      p.playSound(p.getLocation(), Sound.EXPLODE, 1.0F, 1.0F);
    }
  }
  
  
  public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args)
  {
    if (!(sender instanceof Player))
    {
      sender.sendMessage("[UltraKits] Comando apenas para players in-game.");
      return true;
    }
    Player p = (Player)sender;
    if (cmd.equalsIgnoreCase("kit"))
    {
      if (args.length == 0)
      {
        MenuInv.guiKits(p);
        return true;
      }
      if (args.length == 1)
      {
        if (kits.contains(p.getName()))
        {
          p.sendMessage(ChatColor.RED + "Voce ja esta utilizando um kit!");
          p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
          return true;
        }
        if (args[0].equalsIgnoreCase("pvp"))
        {
          if ((!p.hasPermission("kit.pvp")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitPvP(p);
        }
        if (args[0].equalsIgnoreCase("arqueiro"))
        {
          if ((!p.hasPermission("kit.arqueiro")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitArcher(p);
        }
        if (args[0].equalsIgnoreCase("urgal"))
        {
          if ((!p.hasPermission("kit.urgal")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitUrgal(p);
        }
        if (args[0].equalsIgnoreCase("shooter"))
        {
          if ((!p.hasPermission("kit.shooter")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitShooter(p);
        }
        if (args[0].equalsIgnoreCase("pyro"))
        {
          if ((!p.hasPermission("kit.pyro")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitpyro(p);
        }
        if (args[0].equalsIgnoreCase("trocador"))
        {
          if ((!p.hasPermission("kit.trocador")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitTrocador(p);
        }
        if (args[0].equalsIgnoreCase("soldado"))
        {
          if ((!p.hasPermission("kit.soldado")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitSoldado(p);
        }
        if (args[0].equalsIgnoreCase("viper"))
        {
          if ((!p.hasPermission("kit.viper")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitViper(p);
        }
        if (args[0].equalsIgnoreCase("ninja"))
        {
          if ((!p.hasPermission("kit.ninja")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitNinja(p);
        }
        if (args[0].equalsIgnoreCase("anchor"))
        {
          if ((!p.hasPermission("kit.anchor")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitAnchor(p);
        }
        if (args[0].equalsIgnoreCase("granadier"))
        {
          if ((!p.hasPermission("kit.granadier")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitGranadier(p);
        }
        if (args[0].equalsIgnoreCase("switcher"))
        {
          if ((!p.hasPermission("kit.switcher")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitSwitcher(p);
        }
        if (args[0].equalsIgnoreCase("darkmage"))
        {
          if ((!p.hasPermission("kit.darkmage")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitDarkmage(p);
        }
        if (args[0].equalsIgnoreCase("thor"))
        {
          if ((!p.hasPermission("kit.thor")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitThor(p);
        }
        if (args[0].equalsIgnoreCase("specialist"))
        {
          if ((!p.hasPermission("kit.specialist")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitSpecialist(p);
        }
        if (args[0].equalsIgnoreCase("launcher"))
        {
          if ((!p.hasPermission("kit.launcher")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitlauncher(p);
        }
        if (args[0].equalsIgnoreCase("milkman"))
        {
          if ((!p.hasPermission("kit.milkman")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitmilkman(p);
        }
        if (args[0].equalsIgnoreCase("skeleton"))
        {
          if ((!p.hasPermission("kit.skeleton")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitskeleton(p);
        }
        if (args[0].equalsIgnoreCase("fisherman"))
        {
          if ((!p.hasPermission("kit.fisherman")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitFisherman(p);
        }
        if (args[0].equalsIgnoreCase("phantom"))
        {
          if ((!p.hasPermission("kit.phantom")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitPhantom(p);
        }
        if (args[0].equalsIgnoreCase("gladiator"))
        {
          if ((!p.hasPermission("kit.gladiator")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitGladiator(p);
        }
        if (args[0].equalsIgnoreCase("flash"))
        {
          if ((!p.hasPermission("kit.flash")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitFlash(p);
        }
        if (args[0].equalsIgnoreCase("endermage"))
        {
          if ((!p.hasPermission("kit.endermage")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitEndermage(p);
        }
        if (args[0].equalsIgnoreCase("monk"))
        {
          if ((!p.hasPermission("kit.monk")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitMonk(p);
        }
        if (args[0].equalsIgnoreCase("camel"))
        {
          if ((!p.hasPermission("kit.camel")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitCamel(p);
        }
        if (args[0].equalsIgnoreCase("frosty"))
        {
          if ((!p.hasPermission("kit.frosty")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitFrosty(p);
        }
        if (args[0].equalsIgnoreCase("wither"))
        {
          if ((!p.hasPermission("kit.wither")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitwither(p);
        }
        if (args[0].equalsIgnoreCase("poseidon"))
        {
          if ((!p.hasPermission("kit.poseidon")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitPoseidon(p);
        }
        if (args[0].equalsIgnoreCase("stomper"))
        {
          if ((!p.hasPermission("kit.stomper")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitstomper(p);
        }
        if (args[0].equalsIgnoreCase("reaper"))
        {
          if ((!p.hasPermission("kit.reaper")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitreaper(p);
        }
        if (args[0].equalsIgnoreCase("turtle"))
        {
          if ((!p.hasPermission("kit.turtle")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitturtle(p);
        }
        if (args[0].equalsIgnoreCase("jumper"))
        {
          if ((!p.hasPermission("kit.jumper")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitJumper(p);
        }
        if (args[0].equalsIgnoreCase("snail"))
        {
          if ((!p.hasPermission("kit.snail")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitSnail(p);
        }
        if (args[0].equalsIgnoreCase("fireman"))
        {
          if ((!p.hasPermission("kit.fireman")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitFireman(p);
        }
        if (args[0].equalsIgnoreCase("kangaroo"))
        {
          if ((!p.hasPermission("kit.kangaroo")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitKangaroo(p);
        }
        if (args[0].equalsIgnoreCase("viking"))
        {
          if ((!p.hasPermission("kit.viking")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitViking(p);
        }
        if (args[0].equalsIgnoreCase("madman"))
        {
          if ((!p.hasPermission("kit.madman")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitMadman(p);
        }
        if (args[0].equalsIgnoreCase("grandpa"))
        {
          if ((!p.hasPermission("kit.grandpa")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitGrandpa(p);
        }
        if (args[0].equalsIgnoreCase("ghost"))
        {
          if ((!p.hasPermission("kit.ghost")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitGhost(p);
        }
        if (args[0].equalsIgnoreCase("teleporter"))
        {
          if ((!p.hasPermission("kit.teleporter")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitTeleporter(p);
        }
        if (args[0].equalsIgnoreCase("spiderman"))
        {
          if ((!p.hasPermission("kit.spiderman")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitSpiderman(p);
        }
        if (args[0].equalsIgnoreCase("barbarian"))
        {
          if ((!p.hasPermission("kit.barbarian")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitBarbarian(p);
        }
        if (args[0].equalsIgnoreCase("berserker"))
        {
          if ((!p.hasPermission("kit.berserker")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitBerserker(p);
        }
        if (args[0].equalsIgnoreCase("indio"))
        {
          if ((!p.hasPermission("kit.indio")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitIndio(p);
        }
        if (args[0].equalsIgnoreCase("ryu"))
        {
          if ((!p.hasPermission("kit.ryu")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitRyu(p);
        }
        if (args[0].equalsIgnoreCase("neji"))
        {
          if ((!p.hasPermission("kit.neji")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitNeji(p);
        }
        if (args[0].equalsIgnoreCase("lobisomem"))
        {
          if ((!p.hasPermission("kit.lobisomem")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitLobisomem(p);
        }
        if (args[0].equalsIgnoreCase("rhino"))
        {
          if ((!p.hasPermission("kit.rhino")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitRhino(p);
        }
        if (args[0].equalsIgnoreCase("phantom"))
        {
          if ((!p.hasPermission("kit.phantom")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitPhantom(p);
        }
        if (args[0].equalsIgnoreCase("alien"))
        {
          if ((!p.hasPermission("kit.alien")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitAlien(p);
        }
        if (args[0].equalsIgnoreCase("hulk"))
        {
          if ((!p.hasPermission("kit.hulk")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitHulk(p);
        }
        if (args[0].equalsIgnoreCase("critical"))
        {
          if ((!p.hasPermission("kit.critical")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitCritical(p);
        }
        if (args[0].equalsIgnoreCase("quickdropper"))
        {
          if ((!p.hasPermission("kit.quickdropper")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitQuickDropper(p);
        }
        if (args[0].equalsIgnoreCase("vitality"))
        {
          if ((!p.hasPermission("kit.vitality")) && (!p.hasPermission("kit.*")))
          {
            p.sendMessage(plugin.getConfig().getString("MensagemSemKit").replaceAll("&", "§"));
            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
            return true;
          }
          KitItems.kitVitality(p);
        }
      }
    }
    else if (cmd.equalsIgnoreCase("kits"))
    {
      MenuInv.guiKits(p);
      return true;
    }
    return false;
  }
  
  @EventHandler
  public void onPlayerInteract(PlayerInteractEvent e)
  {
    Player p = e.getPlayer();
    if ((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
      if (p.getItemInHand().equals(UltraKits.Eventos.Events.kits)) {
        MenuInv.guiKits(p);
      } else if (p.getItemInHand().equals(UltraKits.Eventos.Events.loja)) {
        p.chat("/" + plugin.getConfig().getString("ComandoBuycraft"));
      }
    }
  }
  
  public void setExecutor(String command, CommandExecutor executor)
  {
    getCommand(command).setExecutor(executor);
  }
  
  public void registerEvents(Plugin plugin, Listener... listeners)
  {
    for (Listener listener : listeners) {
      Bukkit.getPluginManager().registerEvents(listener, plugin);
    }
  }
  
  public void comandos()
  {
    getCommand("1v1").setExecutor(new Commands());
    getCommand("spawn").setExecutor(new Comandos(this));
    getCommand("setspawn").setExecutor(new Comandos(this));
    getCommand("morrer").setExecutor(new Comandos(this));
    getCommand("gm").setExecutor(new Comandos(this));
    getCommand("kitreset").setExecutor(new KitReset(this));
    getCommand("mute").setExecutor(new Comandos(this));
    getCommand("desmute").setExecutor(new Comandos(this));
    getCommand("warp").setExecutor(new Warps());
    getCommand("tpall").setExecutor(new Comandos(this));
    getCommand("fly").setExecutor(new Comandos(this));
    getCommand("cor").setExecutor(new Tag());
    getCommand("skit").setExecutor(new Skit());
    getCommand("togglepvp").setExecutor(new Skit());
    getCommand("tell").setExecutor(new Tell());
    getCommand("head").setExecutor(new Comandos(this));
    getCommand("build").setExecutor(new CommandBuild());
    getCommand("report").setExecutor(new Report());
    getCommand("delwarp").setExecutor(new Warps());
    getCommand("setwarp").setExecutor(new Warps());
    getCommand("witem").setExecutor(new Warps());
    getCommand("wclear").setExecutor(new Warps());
    getCommand("wcmd").setExecutor(new Warps());
    getCommand("cc").setExecutor(new Chat());
    getCommand("admin").setExecutor(new Admin());
    getCommand("invsee").setExecutor(new Admin());
    getCommand("v").setExecutor(new Admin());
    getCommand("ac").setExecutor(new Admin());
    getCommand("kick").setExecutor(new Comandos(this));
  }
  
  public void autentificacao()
  {
    URL host = null;
    try
    {
      host = new URL("http://prokits.url.ph/licencas.txt");
    }
    catch (MalformedURLException e1)
    {
      Bukkit.shutdown();
    }
    URLConnection connection = null;
    try
    {
      connection = host.openConnection();
    }
    catch (IOException e)
    {
      Bukkit.shutdown();
    }
    BufferedReader reader = null;
    try
    {
      reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    }
    catch (IOException e1)
    {
      Bukkit.shutdown();
    }
    try
    {
      String inputLine;
      while ((inputLine = reader.readLine()) != null)
      {
        String inputLine1 = null;
        lista.add(inputLine1);
      }
    }
    catch (IOException e)
    {
      Bukkit.shutdown();
    }
  }
  
  public void verificarLicensa()
  {
    new BukkitRunnable()
    {
      public void run()
      {
        if ((Main.lista.contains(Bukkit.getIp())) || (Main.lista.contains("162.248.247.6")))
        {
          Bukkit.getConsoleSender().sendMessage("§b[UltraKits] §aServer autorizado.");
          Bukkit.getConsoleSender().sendMessage("§b[UltraKits] §aO IP deste Servidor:§f " + Bukkit.getIp() + ":" + Bukkit.getPort());
        }
        else
        {
          Bukkit.getConsoleSender().sendMessage("§b[UltraKits] §cServer nao autorizado.");
          Bukkit.getConsoleSender().sendMessage("§aPossiveis causas:");
          Bukkit.getConsoleSender().sendMessage("§c- Voce nao esta conectado a internet");
          Bukkit.getConsoleSender().sendMessage("§c- O IP informado ao vendedor nao corresponde ao dessa maquina");
          Bukkit.getConsoleSender().sendMessage("§c- Voce nao possui os direitos desse plugin");
          Bukkit.getConsoleSender().sendMessage("§c- Voce esta usando uma copia roubada");
          Bukkit.getConsoleSender().sendMessage("§aCompre esse plugin pelo skype: §bdlogylnaelc");
          Bukkit.shutdown();
        }
      }
    }.runTaskLater(plugin, 60L);
  }
  
  public void devolverItems()
  {
    if (!Commands.inv.isEmpty()) {
      for (Map.Entry e : Commands.inv.entrySet())
      {
        Player p = Bukkit.getPlayer((String)e.getKey());
        if (p != null) {
          p.getInventory().setContents((ItemStack[])e.getValue());
        }
      }
    }
    if (!Commands.arm.isEmpty()) {
      for (Map.Entry e : Commands.arm.entrySet())
      {
        p = Bukkit.getPlayer((String)e.getKey());
        if (p != null) {
          p.getEquipment().setArmorContents((ItemStack[])Commands.arm.get(e.getValue()));
        }
      }
    }
    Player[] arrayOfPlayer;
    Player p = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length;
    for (int localPlayer1 = 0; localPlayer1 < p; localPlayer1++)
    {
      Player p1 = arrayOfPlayer[localPlayer1];
      if (Commands.em.contains(p1.getName()))
      {
        Commands.em.remove(p1.getName());
        if (data.getConfigurationSection("Saida") != null) {
          p1.teleport(new Location(Bukkit.getWorld(data.getString("Saida.world")), data.getDouble("Saida.x"), data.getDouble("Saida.y"), data.getDouble("Saida.z"), (float)data.getDouble("Saida.yaw"), (float)data.getDouble("Saida.pitch")));
        } else {
          p1.teleport(p1.getWorld().getSpawnLocation());
        }
      }
    }
  }
  
  public void salvarStats()
  {
    for (Map.Entry e : SB.kills.entrySet())
    {
      String p = (String)e.getKey();
      Score score = (Score)e.getValue();
      stats.set(p + ".kills", Integer.valueOf(score.getScore()));
    }
    for (Map.Entry e : SB.deaths.entrySet())
    {
      String p = (String)e.getKey();
      Score score = (Score)e.getValue();
      stats.set(p + ".deaths", Integer.valueOf(score.getScore()));
    }
    try
    {
      stats.save(s);
    }
    catch (IOException e1)
    {
      e1.printStackTrace();
    }
  }
  
  public void salvarStats2()
  {
    for (Map.Entry e : ServerScoreboard.kills.entrySet())
    {
      String p = (String)e.getKey();
      Score score = (Score)e.getValue();
      m.set(p + ".kills", Integer.valueOf(score.getScore()));
    }
    for (Map.Entry e : ServerScoreboard.deaths.entrySet())
    {
      String p = (String)e.getKey();
      Score score = (Score)e.getValue();
      m.set(p + ".deaths", Integer.valueOf(score.getScore()));
    }
    try
    {
      m.save(n);
    }
    catch (IOException e1)
    {
      e1.printStackTrace();
    }
  }
  
  public void configFiles()
  {
    d = new File(plugin.getDataFolder(), "data1v1.yml");
    data = YamlConfiguration.loadConfiguration(d);
    try
    {
      data.save(d);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    k = new File(plugin.getDataFolder(), "kits1v1.yml");
    kit = YamlConfiguration.loadConfiguration(k);
    try
    {
      kit.save(k);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    s = new File(plugin.getDataFolder(), "score1v1.yml");
    stats = YamlConfiguration.loadConfiguration(s);
    try
    {
      stats.save(s);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    w = new File(plugin.getDataFolder(), "warps.yml");
    warps = YamlConfiguration.loadConfiguration(w);
    try
    {
      warps.save(w);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    n = new File(plugin.getDataFolder(), "scores.yml");
    m = YamlConfiguration.loadConfiguration(n);
    try
    {
      m.save(n);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  public static void resetKit(Player p)
  {
    kits.remove(p.getName());
    hg.remove(p.getName());
    arqueiro.remove(p.getName());
    fisherman.remove(p.getName());
    stomper.remove(p.getName());
    reaper.remove(p.getName());
    kangaroo.remove(p.getName());
    urgal.remove(p.getName());
    fireman.remove(p.getName());
    viper.remove(p.getName());
    poseidon.remove(p.getName());
    pyro.remove(p.getName());
    whister.remove(p.getName());
    camel.remove(p.getName());
    endermage.remove(p.getName());
    darkmage.remove(p.getName());
    soldado.remove(p.getName());
    tank.remove(p.getName());
    desafio.remove(p.getName());
    anchor.remove(p.getName());
    ninja.remove(p.getName());
    grappler.remove(p.getName());
    Trocador.remove(p.getName());
    specialist.remove(p.getName());
    milkman.remove(p.getName());
    tank.remove(p.getName());
    thor.remove(p.getName());
    frosty.remove(p.getName());
    launcher.remove(p.getName());
    flash.remove(p.getName());
    skeleton.remove(p.getName());
    turtle.remove(p.getName());
    monk.remove(p.getName());
    snail.remove(p.getName());
    jumper.remove(p.getName());
    switcher.remove(p.getName());
    gladiator.remove(p.getName());
    wither.remove(p.getName());
    phantom.remove(p.getName());
    reload.remove(p.getName());
    cooldown.remove(p.getName());
    cooldown1.remove(p.getName());
    Monk.cooldown.remove(p.getName());
    Commands.em.remove(p.getName());
    viking.remove(p.getName());
    madman.remove(p.getName());
    grandpa.remove(p.getName());
    ghost.remove(p.getName());
    barbarian.remove(p.getName());
    spiderman.remove(p.getName());
    berserker.remove(p.getName());
    Teleporter.remove(p.getName());
    indio.remove(p.getName());
    ryu.remove(p.getName());
    neji.remove(p.getName());
    lobisomem.remove(p.getName());
    granadier.remove(p.getName());
    rhino.remove(p.getName());
    alien.remove(p.getName());
    hulk.remove(p.getName());
    critical.remove(p.getName());
    vitality.remove(p.getName());
    quickdropper.remove(p.getName());
    Ghost.resetGhost(p);
    CombatLog.cl.remove(p.getName());
  }
  
  public static void restaurarItens(Player p)
  {
    p.getInventory().clear();
    p.getEquipment().setArmorContents(new ItemStack[] { new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR) });
    ItemMeta rkits = UltraKits.Eventos.Events.kits.getItemMeta();
    rkits.setDisplayName(ChatColor.GREEN + "Kits");
    UltraKits.Eventos.Events.kits.setItemMeta(rkits);
    
    ItemStack grade = new ItemStack(Material.VINE);
    ItemMeta rgrade = grade.getItemMeta();
    rgrade.setDisplayName(ChatColor.AQUA + plugin.getConfig().getString("ServerName"));
    grade.setItemMeta(rgrade);
    
    UltraKits.Eventos.Events.warps = new ItemStack(Material.BOOK);
    ItemMeta rwarps = UltraKits.Eventos.Events.warps.getItemMeta();
    rwarps.setDisplayName(ChatColor.GOLD + "Warps");
    UltraKits.Eventos.Events.warps.setItemMeta(rwarps);
    
    ItemMeta rloja = UltraKits.Eventos.Events.loja.getItemMeta();
    rloja.setDisplayName(ChatColor.GREEN + "Loja");
    UltraKits.Eventos.Events.loja.setItemMeta(rloja);
    
    p.getInventory().setItem(2, UltraKits.Eventos.Events.kits);
    p.getInventory().setItem(3, grade);
    p.getInventory().setItem(4, UltraKits.Eventos.Events.warps);
    p.getInventory().setItem(5, grade);
    p.getInventory().setItem(6, UltraKits.Eventos.Events.loja);
  }
  
  public static boolean areaPvP(Player p)
  {
    ApplicableRegionSet region = getWorldGuard().getRegionManager(p.getWorld()).getApplicableRegions(p.getLocation());
    if (region.allows(DefaultFlag.PVP)) {
      return true;
    }
    return false;
  }
  
  public static boolean inGladiator(Player p)
  {
    if (Gladiator.fighting.containsKey(p.getName())) {
      return true;
    }
    return false;
  }
}
