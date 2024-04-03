package me.endistic.jsonmc.tree;

import me.endistic.jsonmc.tree.values.EventTargets;

public class Condition {
    public Selection selection;
    public String playerName;

    public boolean execute(EventTargets targets) {
        var entities = selection.toEntityList(targets);
        return true;
    }
}
