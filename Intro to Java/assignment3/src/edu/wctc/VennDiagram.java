package edu.wctc;

import java.util.*;

public class VennDiagram<T> {

    // support for an unlimited number diagram circles
    private final HashSet<String> labels;
    private final HashMap<String, Set<T>> sets;

    // just so if a specified label doesn't exist, it at least returns an empty HashSet<T>
    // only because creating a new emptySet<T> for every instance that one is needed would probably be more expensive than just creating one and reusing it.
    // I also wasn't satisfied with just returning null if an error occured.
    private final Set<T> emptySet = new HashSet<T>();

    public VennDiagram(String... labels) {
        this.labels = new HashSet<String>();
        this.sets = new HashMap<String, Set<T>>();
        for (String label : labels) {
            this.labels.add(label);
            this.sets.put(label, new HashSet<T>());
        }
    }

    private Set<T> getCircleForLabel(String label) {
        if (this.labels.contains(label)) { return this.sets.get(label); }
        else { return this.emptySet; }
    }

    public void add(T item, String... labels) {
        for (String label : labels) {
            if (this.sets.containsKey(label)) {
                Set<T> circle = this.getCircleForLabel(label);
                circle.add(item);
            }
        }
    }

    // items in EITHER circle
    public Set<T> unionOf(String first, String second) {
        Set<T> circle1 = this.getCircleForLabel(first);
        Set<T> circle2 = this.getCircleForLabel(second);

        Set<T> returnSet = new HashSet<T>();

        returnSet.addAll(circle1);
        returnSet.addAll(circle2);

        return returnSet;
    }

    // only items in BOTH circles
    public Set<T> intersectionOf(String first, String second) {
        Set<T> circle1 = this.getCircleForLabel(first);
        Set<T> circle2 = this.getCircleForLabel(second);

        Set<T> returnSet = new HashSet<T>();

        for (T v : circle1) { if (circle2.contains(v)) { returnSet.add(v); } }

        return returnSet;
    }

    // only items in the first circle that are NOT in the second circle
    public Set<T> complementOf(String first, String second) {
        Set<T> circle1 = this.getCircleForLabel(first);
        Set<T> circle2 = this.getCircleForLabel(second);

        Set<T> returnSet = new HashSet<T>();

        for (T v : circle1) { if (!circle2.contains(v)) { returnSet.add(v); } }

        return returnSet;
    }

    // only items that are in ALL circles
    public Set<T> diagramCenter() {
        Set<T> returnSet = new HashSet<T>();

        // had to do a few embedded loops to check all of the circles (a variable amount)
        for (String l1 : this.labels) {
            Set<T> c1 = this.getCircleForLabel(l1);
            for (T v : c1) {
                boolean invalid = false;
                if (!returnSet.contains(v)) {
                    for (String l2 : this.labels) {
                        Set<T> c2 = this.getCircleForLabel(l2);
                        if (!c2.contains(v)) { invalid = true; }
                    }
                }

                if (!invalid) { returnSet.add(v); }
            }
        }

        return returnSet;
    }
}
