package me.endistic.jsonmc.tree.values;

import me.endistic.jsonmc.tree.Selection;
import org.bukkit.entity.Damageable;

import java.time.Clock;

public class ActionValue {
    public String string;
    public Double number;
    public String variable;
    public String value;
    public ActionValue[] interpolated;

    public String interpolateString(String origin, ActionValue[] interpolated, EventTargets targets, Selection selection) {
        if(interpolated == null)
            interpolated = new ActionValue[]{};
        for(var i : interpolated) {
            origin = origin.replaceFirst("\\{}", i.toString(targets, selection));
        }
        return origin;
    }

    public String toString(EventTargets targets, Selection selection) {
        if(string != null)
            return this.interpolateString(string, interpolated, targets, selection);
        if(number != null)
            return number.toString();
        if(variable != null)
            return "VariableTmp{" + variable + "}";
        if(value != null)
            switch(value) {
                case "health" -> {
                    if (selection.first(targets) instanceof Damageable d)
                        return "" + d.getHealth();
                }
                case "max_health" -> {
                    if(selection.first(targets) instanceof Damageable d)
                        return "" + d.getHealth();
                }
                case "timestamp" -> {
                    return "" + Clock.systemUTC().instant().getNano();
                }
            }
        return "";
    }

    public Double toNumber(EventTargets targets, Selection selection) {
        if(string != null)
            try {
                return Double.parseDouble(string);
            } catch(Exception e) {
                return 0.0;
            }
        if(number != null)
            return number;
        return 0.0;
    }

}
