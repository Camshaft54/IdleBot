/*
 * Copyright (C) 2020-2022 Camshaft54, MetalTurtle18
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package io.github.idlebotdevelopment.idlebot.commands;

import io.github.idlebotdevelopment.idlebot.IdleBot;
import io.github.idlebotdevelopment.idlebot.util.ConfigManager;
import io.github.idlebotdevelopment.idlebot.util.IdleBotCommand;
import io.github.idlebotdevelopment.idlebot.util.MessageHelper;
import io.github.idlebotdevelopment.idlebot.util.PersistentDataUtils;
import io.github.idlebotdevelopment.idlebot.util.enums.DataValue;
import io.github.idlebotdevelopment.idlebot.util.enums.MessageLevel;
import org.bukkit.entity.Player;

public class ChannelCommand implements IdleBotCommand {
    @Override
    public String getCommandName() {
        return "channel";
    }

    @Override
    public String getCommandUsage() {
        return "/idlebot channel <public | private>";
    }

    @Override
    public boolean runCommand(Player player, String[] args) {
        if (args.length < 2) return false;
        ConfigManager configManager = IdleBot.getPlugin().getConfigManager();
        if (args[1].equalsIgnoreCase("public")) {
            if (!configManager.PUBLIC_CHANNEL_MESSAGES_ENABLED) {
                MessageHelper.sendMessage(player, "You are not allowed to use a public channel on this server!", MessageLevel.INCORRECT_COMMAND_USAGE);
                return true;
            }
            PersistentDataUtils.setData(player, DataValue.DIRECT_MESSAGE_MODE, false);
            MessageHelper.sendMessage(player, "Set your alerts channel to public", MessageLevel.INFO);
            return true;
        } else if (args[1].equalsIgnoreCase("private")) {
            if (!configManager.PRIVATE_CHANNEL_MESSAGES_ENABLED) {
                MessageHelper.sendMessage(player, "You are not allowed to use a private channel on this server!", MessageLevel.INCORRECT_COMMAND_USAGE);
                return true;
            }
            PersistentDataUtils.setData(player, DataValue.DIRECT_MESSAGE_MODE, true);
            MessageHelper.sendMessage(player, "Set your alerts channel to private", MessageLevel.INFO);
            return true;
        }
        return false;
    }
}
