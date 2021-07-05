package us.mattgreen;

/**
 * Created by mgreen14 on 12/27/17.
 */
public abstract class Pet {
    protected String name;

    public Pet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}