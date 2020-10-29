package io.github.camshaft54.idlebot;

import org.bukkit.plugin.Plugin;
import org.javacord.api.DiscordApiBuilder;

public class DiscordAPIRunnable implements Runnable {
    private final Plugin plugin;

    public DiscordAPIRunnable(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        IdleBot.api = new DiscordApiBuilder().setToken(IdleBot.botToken).login().join(); // Call #onConnectToDiscord(...) after a successful login
        IdleBot.startPlugin();
    }
}