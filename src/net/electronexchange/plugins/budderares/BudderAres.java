package net.electronexchange.plugins.budderares;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Builder;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;


public class BudderAres extends JavaPlugin implements Listener {
	public void onEnable(){
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event){
		Player player = event.getEntity();
		Player killer = player.getKiller();
		World world = player.getWorld();
		
		//Check if player is budder knight
		if(killer.getItemInHand().getType().equals(Material.GOLD_SWORD)) {
			//Set new death message
			event.setDeathMessage(ChatColor.stripColor(player.getName() + " was slain by Budder Knight " + killer.getName() + "'s ") 
		      + ChatColor.GOLD + "BUDDER SWORD" + ChatColor.stripColor("!"));
			//Launch budder fireworks				
			Builder b = FireworkEffect.builder();
			b.with(FireworkEffect.Type.BALL_LARGE);
			b.withColor(Color.YELLOW);
			b.withTrail();
			FireworkEffect fe = b.build();   
			Firework firework = (Firework) world.spawnEntity(player.getLocation(), EntityType.FIREWORK);
			FireworkMeta fm = firework.getFireworkMeta();
			fm.addEffect(fe);
			fm.setPower(1);
			firework.setFireworkMeta(fm);
		}
	}
}
