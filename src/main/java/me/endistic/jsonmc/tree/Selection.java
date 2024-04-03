package me.endistic.jsonmc.tree;

import me.endistic.jsonmc.tree.values.EventTargets;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class Selection {
    public String target;
    public String playerName;
    public String playerUuid;

    public List<Entity> toEntityList(EventTargets targets) {
        var list = new ArrayList<Entity>();
        switch(target) {
            case "default" -> list.add(targets.defaultTarget);
            case "all_players" -> list.addAll(Bukkit.getServer().getOnlinePlayers());
        }
        return list;
    }

    public Entity first(EventTargets targets) {
        var list = this.toEntityList(targets);
        if(!list.isEmpty())
            return list.get(0);
        return null;
    }
}
