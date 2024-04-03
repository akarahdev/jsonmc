package me.endistic.jsonmc.tree;

import me.endistic.jsonmc.JsonMC;
import me.endistic.jsonmc.tree.values.ActionValue;
import me.endistic.jsonmc.tree.values.EventTargets;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Action {
    public Selection selection;
    public ActionValue sendMessage;
    public boolean sendDebugTimestamp = false;
    public ActionValue delay;

    public Object execute(EventTargets targets, Instructions instructions) {
        var computeSelection = selection.toEntityList(targets);

        System.out.println("Running action");
        if(sendMessage != null) {
            computeSelection.forEach((entity) -> {
                if(entity instanceof Player player)
                    player.sendMessage(sendMessage.toString(targets, selection));
            });
        }

        if(delay != null) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(
                    JsonMC.instance,
                    () -> instructions.tickInstructions(targets),
                    delay.toNumber(targets, selection).longValue());
        } else {
            instructions.tickInstructions(targets);
        }

        return null;
    }
}
