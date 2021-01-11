package io.github.camshaft54.idlebot.commands;

import io.github.camshaft54.idlebot.util.IdleBotCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/*
 *    Copyright (C) 2020 Camshaft54, MetalTurtle18
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

public class IdleBotCommandManager implements CommandExecutor {

    ArrayList<IdleBotCommand> idleBotCommands = new ArrayList<>();

    public IdleBotCommandManager() {
        // Add /idlebot commands to list of commands
        idleBotCommands.add(new AFKModeCommand());
        idleBotCommands.add(new AFKTimeCommand());
        idleBotCommands.add(new AlertCommand());
        idleBotCommands.add(new LinkCommand());
        idleBotCommands.add(new SetAFKCommand());
        idleBotCommands.add(new UnlinkCommand());
        idleBotCommands.add(new XPLevelCommand());
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String str, String[] args) {
        if (!(commandSender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_PURPLE + "[IdleBot] " + ChatColor.BLUE + "This command can be run only by players!");
            return true;
        }
        Player player = (Player) commandSender;

        if (args.length <= 0) {
            // send an information "packet"
            return true;
        }
        for (IdleBotCommand eachCommand : idleBotCommands) {
            if (eachCommand.getCommandName().equalsIgnoreCase(args[0])) {
                if (!eachCommand.runCommand(player, args)) {
                    player.sendMessage(ChatColor.DARK_PURPLE + "[IdleBot] " + ChatColor.BLUE + "Command usage: " + eachCommand.getCommandUsage());
                    return false;
                } else {
                    return true;
                }
            }
        }
        return false;
    }
}
