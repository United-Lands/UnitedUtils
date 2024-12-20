package org.unitedlands.unitedUtils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import me.clip.placeholderapi.PlaceholderAPI;

public class Commands implements CommandExecutor, TabCompleter {

    private final JavaPlugin plugin;
    private Player player;

    public Commands(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        FileConfiguration config = plugin.getConfig();

        // Reload command.
        if (label.equalsIgnoreCase("unitedutils") && args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("united.utils.admin")) {
                sender.sendMessage(Objects.requireNonNull(config.getString("messages.no-permission")));
                return true;
            }
            plugin.reloadConfig();
            ((UnitedUtils) plugin).reloadPluginConfig();
            sender.sendMessage(Objects.requireNonNull(config.getString("messages.reload")));
            return true;
        }

        // Map command.
        if (label.equalsIgnoreCase("map")) {
            if (!sender.hasPermission("united.utils.player")) {
                sender.sendMessage(Objects.requireNonNull(config.getString("messages.no-permission")));
                return true;
            }
            List<String> mapMessage = config.getStringList("messages.map");
                for (String line : mapMessage) {
                    sender.sendMessage(line);
                }
            return true;
        }

        // Discord command.
        if (label.equalsIgnoreCase("discord")) {
            if (!sender.hasPermission("united.utils.player")) {
                sender.sendMessage(Objects.requireNonNull(config.getString("messages.no-permission")));
                return true;
            }
            List<String> mapMessage = config.getStringList("messages.discord");
            for (String line : mapMessage) {
                sender.sendMessage(line);
            }
            return true;
        }

        // Wiki command.
        if (label.equalsIgnoreCase("wiki")) {
            if (!sender.hasPermission("united.utils.player")) {
                sender.sendMessage(Objects.requireNonNull(config.getString("messages.no-permission")));
                return true;
            }
            List<String> mapMessage = config.getStringList("messages.wiki");
            for (String line : mapMessage) {
                sender.sendMessage(line);
            }
            return true;
        }

        // Shop command.
        if (label.equalsIgnoreCase("shop")) {
            if (!sender.hasPermission("united.utils.player")) {
                sender.sendMessage(Objects.requireNonNull(config.getString("messages.no-permission")));
                return true;
            }
            List<String> mapMessage = config.getStringList("messages.shop");
            for (String line : mapMessage) {
                sender.sendMessage(line);
            }
            return true;
        }

        // Greylist command.
        if (label.equalsIgnoreCase("greylist")) {
            if (!sender.hasPermission("united.utils.player")) {
                sender.sendMessage(Objects.requireNonNull(config.getString("messages.no-permission")));
                return true;
            }
            List<String> mapMessage = config.getStringList("messages.greylist");
            for (String line : mapMessage) {
                sender.sendMessage(line);
            }
            return true;
        }

        // Top Time command.
        if (label.equalsIgnoreCase("toptime")) {
            if (!sender.hasPermission("united.utils.player")) {
                sender.sendMessage(Objects.requireNonNull(config.getString("messages.no-permission")));
                return true;
            }
            List<String> mapMessage = config.getStringList("messages.toptime");
            for (String line : mapMessage) {
                String parsedLine = PlaceholderAPI.setPlaceholders(player, line);
                sender.sendMessage(parsedLine);
            }
            return true;
        }

        // Default invalid command message.
        sender.sendMessage(Objects.requireNonNull(config.getString("messages.invalid-command")));
        return true;
    }

    @Override
    @Nullable
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (!sender.hasPermission("united.utils.admin")) {
            // Return no suggestions if no permission.
            return Collections.emptyList();
        }
        if (args.length == 1) {
            // Provide suggestions for the first argument.
            List<String> suggestions = new ArrayList<>();
            if ("reload".startsWith(args[0].toLowerCase())) {
                suggestions.add("reload");
            }
            return suggestions;
        }
        return Collections.emptyList();
    }
}

