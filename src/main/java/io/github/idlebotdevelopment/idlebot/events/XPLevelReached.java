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

package io.github.idlebotdevelopment.idlebot.events;

import io.github.idlebotdevelopment.idlebot.IdleBot;
import io.github.idlebotdevelopment.idlebot.util.IdleBotUtils;
import io.github.idlebotdevelopment.idlebot.util.IdleCheck;
import io.github.idlebotdevelopment.idlebot.util.MessageHelper;
import io.github.idlebotdevelopment.idlebot.util.PersistentDataUtils;
import io.github.idlebotdevelopment.idlebot.util.enums.DataValue;
import io.github.idlebotdevelopment.idlebot.util.enums.MessageLevel;
import org.bukkit.entity.Player;

public class XPLevelReached implements IdleCheck {
    @Override
    public DataValue getDataValue() {
        return DataValue.EXPERIENCE_ALERT;
    }

    // Checks if player has reached a certain xp level and sends them a message if they have
    public void check(Player player) {
        int levelDesired = PersistentDataUtils.getIntData(player, DataValue.EXPERIENCE_LEVEL_DESIRED);
        if (levelDesired == -1) return;
        if (player.getLevel() >= levelDesired && !IdleBot.getPlugin().getEventManager().XPLevelReachedPlayers.containsKey(player)) {
            MessageHelper.sendMessage(player.getDisplayName() + " is idle and at the desired XP level!", MessageLevel.INFO);
            IdleBotUtils.sendPlayerMessage(player, player.getDisplayName() + " is at the desired XP level!", player.getDisplayName() + " reached the desired XP level!");
            IdleBot.getPlugin().getEventManager().XPLevelReachedPlayers.put(player, 0);
        }
    }
}
