package me.endistic.jsonmc.tree;

import me.endistic.jsonmc.tree.values.EventTargets;

public class Event {
    public String on;
    public Instructions instructions;

    public void startEvent(EventTargets targets) {
        instructions.remake().tickInstructions(targets);
    }
}
