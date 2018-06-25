package me.sergivb01.welcomeplayers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class WelcomePlugin extends JavaPlugin implements Listener{

	@Override
	public void onEnable(){
		final File configFile = new File(this.getDataFolder() + "/config.yml");
		if(!configFile.exists()){
			this.saveDefaultConfig();
		}
		this.getConfig().options().copyDefaults(true);

		Bukkit.getPluginManager().registerEvents(this, this);
		getLogger().info("Loaded Welcome plugin - sergivb01");
	}

	@EventHandler
	public void onDisable(){
		getLogger().info("Disabled Welcome plugin - sergivb01");
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();

		if(!player.hasPlayedBefore()){
			Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
					getConfig().getString("broadcast-message"))
					.replace("%player_name%", player.getName())
					.replace("%player_displayname%", player.getDisplayName())
					.replace("%all_players%", Bukkit.getOfflinePlayers().length + "")
			);
		}
	}


}
