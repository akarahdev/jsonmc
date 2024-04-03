package me.endistic.jsonmc.tree;

import me.endistic.jsonmc.tree.values.EventTargets;

public class Instructions {
    public Action[] actions;
    public int index = 0;

    public boolean tickInstructions(EventTargets targets) {
        if(index <= actions.length-1) {
            index++;
            actions[index-1].execute(targets, this);

            return true;
        }
        return false;
    }

    public Instructions remake() {
        var i = new Instructions();
        i.actions = actions;
        return i;
    }
}
