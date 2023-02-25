package dk.manaxi.soundtest;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    @Getter
    private static Main instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        new TestAddSound();
        new TestPlay();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
