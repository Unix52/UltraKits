package UltraKits;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

public class SettingsManager
  implements Listener
{
  static SettingsManager instance = new SettingsManager();
  Plugin p;
  static FileConfiguration config;
  static File cfile;
  static FileConfiguration data;
  static File dfile;
  static FileConfiguration duel;
  static File filed;
  
  public static SettingsManager getInstance()
  {
    return instance;
  }
  
  public void setup(Plugin p)
  {
    cfile = new File(p.getDataFolder(), "config.yml");
    config = p.getConfig();
    if (!p.getDataFolder().exists()) {
      p.getDataFolder().mkdir();
    }
    dfile = new File(p.getDataFolder(), "data.yml");
    if (!dfile.exists()) {
      try
      {
        dfile.createNewFile();
      }
      catch (IOException e)
      {
        Bukkit.getServer().getLogger().severe(ChatColor.RED + "Nao foi possivel criar a pasta data.yml");
      }
    }
    data = YamlConfiguration.loadConfiguration(dfile);
    
    filed = new File(p.getDataFolder(), "config.yml");
    if (!filed.exists()) {
      try
      {
        filed.createNewFile();
      }
      catch (IOException e)
      {
        Bukkit.getServer().getLogger().severe(ChatColor.RED + "Nao foi possivel criar a pasta config.yml!");
      }
    }
    config = YamlConfiguration.loadConfiguration(filed);
  }
  
  public FileConfiguration getDuel()
  {
    return config;
  }
  
  public void saveDuel()
  {
    try
    {
      config.save(filed);
    }
    catch (IOException e)
    {
      Bukkit.getServer().getLogger().severe(ChatColor.RED + "Nao foi possivel salvar config.yml!");
    }
  }
  
  public void reloadDuel()
  {
    duel = YamlConfiguration.loadConfiguration(filed);
  }
  
  public FileConfiguration getData()
  {
    return data;
  }
  
  public void saveData()
  {
    try
    {
      data.save(dfile);
    }
    catch (IOException e)
    {
      Bukkit.getServer().getLogger().severe(ChatColor.RED + "Nao foi possivel salvar data.yml!");
    }
  }
  
  public void reloadData()
  {
    data = YamlConfiguration.loadConfiguration(dfile);
  }
  
  public FileConfiguration getConfig()
  {
    return config;
  }
  
  public void saveConfig()
  {
    try
    {
      config.save(cfile);
    }
    catch (IOException e)
    {
      Bukkit.getServer().getLogger().severe(ChatColor.RED + "Nao foi possivel salvar config.yml!");
    }
  }
  
  public void reloadConfig()
  {
    config = YamlConfiguration.loadConfiguration(cfile);
  }
  
  public PluginDescriptionFile getDesc()
  {
    return this.p.getDescription();
  }
}
