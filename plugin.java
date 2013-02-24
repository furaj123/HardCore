package me.furaj123.pl;

import java.io.File;
import java.util.logging.Logger;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class HardCore extends JavaPlugin{
    private static final Logger LOG = Logger.getLogger(HardCore.class.getName());
    protected ListStore banList;
    
    public static Logger getLOG() {
        return LOG;
    }
    private Configuration config;
    
    @Override
    public void onLoad() {
        super.onLoad();
        LOG.info("Wczytuje sie plugin...");
    }

    @Override
    public void onDisable() {
        super.onDisable();
        LOG.info("Plugin jest wylanczony!");
        banList.save();
    }

    @Override
    public void onEnable() {
        super.onEnable();
        LOG.info("Plugin jest wlanczony!");
        
        String pluginFolder = this.getDataFolder().getAbsolutePath();
        
        (new File(pluginFolder)).mkdirs();
        this.banList = new ListStore(new File(pluginFolder + File.separator + "zbanowani-gracze.txt"));
        banList.load();
        
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new PlayerListener(this), this);
        pm.registerEvents(new PlayerLoginListener(this), this);
        
        
        
    }
}
