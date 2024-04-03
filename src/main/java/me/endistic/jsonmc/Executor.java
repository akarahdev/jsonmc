package me.endistic.jsonmc;

import me.endistic.jsonmc.tree.Event;
import me.endistic.jsonmc.tree.values.EventTargets;
import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Executor {
    public static List<Event> events = new ArrayList<>();

    public static void executeEvent(String eventName, Entity defaultEntity) {
        JsonMC.instance.getServer().getScheduler().scheduleSyncDelayedTask(
                JsonMC.instance,
                () -> {
                    System.out.println(events);
                    events.forEach((it) -> {
                        if(Objects.equals(it.on, eventName)) {
                            it.startEvent(new EventTargets(
                                    defaultEntity
                            ));
                        }
                    });
                },
                0
        );
    }
}
