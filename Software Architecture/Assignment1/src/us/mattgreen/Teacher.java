package us.mattgreen;

/**
 * Created by mgreen14 on 12/27/17.
 */
public class Teacher extends Person implements Talkable {
    private int age;

    public Teacher(int age, String name) {
        super(name);
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String talk() {
        return "Don't forget to do the assigned reading!";
    }
}
