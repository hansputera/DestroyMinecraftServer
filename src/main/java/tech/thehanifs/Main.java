package tech.thehanifs;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import tech.thehanifs.listeners.PlayerJoinListener;
import tech.thehanifs.threads.SystemThread;

import java.util.logging.Logger;

public class Main extends JavaPlugin {
    private static final Logger logger = Logger.getLogger("DestroyServerMinecraft");
    private final SystemThread thread_sys = new SystemThread();
    public FileConfiguration configuration = this.getConfig();
    @Override
    public void onEnable() {
        logger.info("Saving default configuration");
        configuration.addDefault("system", true);
        configuration.addDefault("game", false);
        configuration.addDefault("delay", 1);
        saveConfig();
        logger.info("Configuration saved");

        new PlayerJoinListener(this);
        if (this.configuration.getBoolean("system")) this.thread_sys.run();
    }

    public void onDisable() {
        logger.info("Server shutdown, stopping all threads");
        this.thread_sys.stopped = true;
        for (SystemThread.InternalThread thread : this.thread_sys.threads) {
            if (thread.isAlive()) thread.interrupt();
            logger.info("Stopped thread: " + thread.getId());
        }
    }
}
