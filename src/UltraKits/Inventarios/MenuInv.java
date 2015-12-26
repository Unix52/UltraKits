package UltraKits.Inventarios;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import UltraKits.KitItems;
import UltraKits.Main;

public class MenuInv
  implements Listener
{
  @SuppressWarnings({ "unchecked", "rawtypes" })
public static void guiKits(Player p)
  {
    Inventory inv = Bukkit.getServer().createInventory(p, 54,"§a§lSeus Kits");
    
    ItemStack vidro = new ItemStack(Material.THIN_GLASS);
    ItemMeta metav = vidro.getItemMeta();
    metav.setDisplayName(ChatColor.AQUA + Main.plugin.getConfig().getString("ServerName"));
    vidro.setItemMeta(metav);
    if ((p.hasPermission("kit.pvp")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.STONE_SWORD);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "PvP");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Kit sem habilidade!");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.arqueiro")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.BOW);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Arqueiro");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Mate seus inimigos com seu arco e flecha");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.shooter")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.NETHER_STAR);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Shooter");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Jogue efeitos de wither em seu inimigo");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.pyro")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.FIREWORK_CHARGE);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Pyro");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Jogue bolas de fogo!");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.ninja")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.COMPASS);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Ninja");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Bata em um jogador e se agache");
      descpyro.add(ChatColor.WHITE + "para se teleportar ate ele");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.fisherman")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.FISHING_ROD);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Fisherman");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Use sua vara de pesca para puxar jogadores");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.soldado")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.IRON_CHESTPLATE);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Soldado");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Use sua espada para saltar");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.urgal")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.POTION, 1, (short)8201);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Urgal");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Receba 3 pocoes de forca");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.trocador")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.DIAMOND_CHESTPLATE);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Trocador");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Aperte shift e mude sua armadura!");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.darkmage")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.COAL);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Darkmage");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Ao bater em jogadores voce tem 33% de chance");
      descpyro.add(ChatColor.WHITE + "de dar a eles o efeito de Cegueira!");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.rhino")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.SAPLING);
      pyro.setDurability((short)5);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Rhino");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Se abaixe, carregue sua força e");
      descpyro.add(ChatColor.WHITE + "mande o inimigo pelos ares!");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.poseidon")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.WATER_BUCKET);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Poseidon");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Ao entrar em contato com a agua ganhe forcas!");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.granadier")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.TNT);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Granadier");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Lance granadas contra os inimigos!");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.launcher")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.SPONGE);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Launcher");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Com sua vara de pesca");
      descpyro.add(ChatColor.WHITE + "Jogue os caras pra cima!");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.milkman")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.MILK_BUCKET);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Milkman");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Ao clicar em seu balde de leite voce tera");
      descpyro.add(ChatColor.WHITE + "Efeitos!");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.turtle")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.LEATHER_CHESTPLATE);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Turtle");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Ao cair de qualquer lugar");
      descpyro.add(ChatColor.WHITE + "Segurando o shift voce nao morrera!");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.skeleton")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.BONE);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Skeleton");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Apos bater em qualquer jogador");
      descpyro.add(ChatColor.WHITE + "voce o matara!");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.camel")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.SAND);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Camel");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Ganhe velocidade e regeneracao na areia");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.thor")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.STONE_AXE);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Thor");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Solte raios com seu machado!");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.frosty")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.SNOW_BLOCK);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Frosty");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Ganhe velocidade e regeneracao na neve");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.specialist")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.EXP_BOTTLE);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Specialist");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Tenha uma mesa de encantamento portatil!");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.endermage")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.PORTAL);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Endermage");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Puxe jogadores com seu portal!");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.stomper")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.IRON_BOOTS);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Stomper");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Recebe 2 coracoes de dano");
      descpyro.add(ChatColor.WHITE + "e ao cair em cima de jogadores");
      descpyro.add(ChatColor.WHITE + "voce acabara matando-os a menos que estejam no shift!");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.kangaroo")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.FIREWORK);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Kangaroo");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Com seu firework use para");
      descpyro.add(ChatColor.WHITE + "Dar pulos duplos!");
      descpyro.add(ChatColor.WHITE + "Um otimo kit para fugir de inimigos");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.reaper")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.WOOD_HOE);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Reaper");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Com sua enchada");
      descpyro.add(ChatColor.WHITE + "bate em jogadores e evenene-os!");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.fireman")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.LAVA_BUCKET);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Fireman");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Use seu kit para");
      descpyro.add(ChatColor.WHITE + "nao receber dano de fogo!");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.anchor")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.ANVIL);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Anchor");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Use seu kit para nao");
      descpyro.add(ChatColor.WHITE + "receber e nem levar knocback!");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.flash")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.REDSTONE_TORCH_ON);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Flash");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Use seu kit para");
      descpyro.add(ChatColor.WHITE + "Teleportar-se!");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.viper")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.SPIDER_EYE);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Viper");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Ao bater em jogadores voce tem 33% de chance");
      descpyro.add(ChatColor.WHITE + "de dar a eles o efeito de Veneno!");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.monk")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.BLAZE_ROD);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Monk");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Coloque items em um local");
      descpyro.add(ChatColor.WHITE + "aleatorio no inventario do inimigo!");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.snail")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.SOUL_SAND);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Snail");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Ao bater em jogadores voce tem 33% de chance");
      descpyro.add(ChatColor.WHITE + "de dar a eles o efeito de Lentidao!");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.wither")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.SKULL_ITEM);
      pyro.setDurability((short)1);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Wither");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Ao bater em jogadores voce tem 33% de chance");
      descpyro.add(ChatColor.WHITE + "de dar a eles o efeito do Wither!");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.gladiator")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.IRON_FENCE);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Gladiator");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Crie uma arena e fique frente a frente");
      descpyro.add(ChatColor.WHITE + "contra os seus inimigos!");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.switcher")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.SNOW_BALL);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Switcher");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Use suas bolas de neve para trocar");
      descpyro.add(ChatColor.WHITE + "de lugar com seus jogadores!");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.jumper")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.TRAP_DOOR);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Jumper");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Use esse kit e jogue");
      descpyro.add(ChatColor.WHITE + "seus inimigos pra cima!");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.vitality")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.MUSHROOM_SOUP);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Vitality");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Ao matar um player seu inventario");
      descpyro.add(ChatColor.WHITE + "será preenchido com sopas!");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.madman")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.JACK_O_LANTERN);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Madman");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Ao bater em jogadores voce tem 33% de chance");
      descpyro.add(ChatColor.WHITE + "de dar a eles Nausea e Cegueira.");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.viking")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.GOLD_AXE);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Viking");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Ao bater em jogadores com machados");
      descpyro.add(ChatColor.WHITE + "o dano será 2 vezes maior.");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.grandpa")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = KitItems.grandpa;
      pyro.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Grandpa");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Aplique Knockback nos seus adversários!");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.ghost")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.IRON_HOE);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Ghost");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Transforme em um fantasma e");
      descpyro.add(ChatColor.WHITE + "assombre seus inimigos!");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.quickdropper")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.BOWL);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "QuickDropper");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Ao tomar uma sopa o pote será");
      descpyro.add(ChatColor.WHITE + "dropado automaticamente!");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.teleporter")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.ENDER_PEARL);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Teleporter");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Se teleporte com perolas do fim!");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.spiderman")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.WEB);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "SpiderMan");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Atire teia em seus inimigos!");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.barbarian")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.IRON_ORE);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Barbarian");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Mate um player com a espada");
      descpyro.add(ChatColor.WHITE + "e faça um UPGRADE!");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.berserker")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.REDSTONE);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Berserker");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Mate um player e ganhe força!");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.indio")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.PUMPKIN_SEEDS);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Indio");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Atire dardos venenosos em seus inimigos!");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.ryu")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.BEACON);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Ryu");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Dê um HADOUKEN em seus inimigos!");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.neji")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.EYE_OF_ENDER);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Neji");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Faça um HAKKESHOU KAITEN (rotacao) e");
      descpyro.add(ChatColor.WHITE + "afaste quem estiver por perto!");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.lobisomem")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.MONSTER_EGG);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Lobisomem");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Ganhe efeitos e receba ajuda de");
      descpyro.add(ChatColor.WHITE + "seus amigos Lobos!");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.phantom")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.FEATHER);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Phantom");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Use a pena e voe por 5 segundos!");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.alien")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.ENDER_PORTAL_FRAME);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Alien");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Use seus poderes de E.T. para ");
      descpyro.add(ChatColor.WHITE + "levitar players e infecta-los!");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.hulk")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.SADDLE);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Hulk");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "Pegue e lance seus inimigos!");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    if ((p.hasPermission("kit.assassin")) || (p.hasPermission("kit.*")))
    {
      ItemStack pyro = new ItemStack(Material.GOLDEN_APPLE);
      pyro.setDurability((short)1);
      ItemMeta metapyro = pyro.getItemMeta();
      metapyro.setDisplayName(ChatColor.GREEN + "Assassin");
      ArrayList<String> descpyro = new ArrayList();
      descpyro.add(ChatColor.WHITE + "30% de chance de dar um ");
      descpyro.add(ChatColor.WHITE + "golpe critico ao bater! ");
      metapyro.setLore(descpyro);
      pyro.setItemMeta(metapyro);
      inv.addItem(new ItemStack[] { pyro });
    }
    ItemStack[] arrayOfItemStack;
    int descpyro = (arrayOfItemStack = inv.getContents()).length;
    for (int metapyro = 0; metapyro < descpyro; metapyro++)
    {
      ItemStack item = arrayOfItemStack[metapyro];
      if (item == null) {
        inv.setItem(inv.firstEmpty(), vidro);
      }
    }
    p.openInventory(inv);
    p.playSound(p.getLocation(), Sound.CHEST_OPEN, 40.0F, 1.0F);
  }
}
