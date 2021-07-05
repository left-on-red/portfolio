package us.mattgreen;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Zoo {
    final static List<Talkable> list = new ArrayList<Talkable>();
    private static Scanner scanner = new Scanner(System.in);

    static Talkable prompt() {

        System.out.print("what do you want to add to the zoo > ");
        String type = scanner.nextLine().toLowerCase();

        Talkable obj = null;

        if (type.equals("cat")) {
            System.out.print("how many mice has the cat killed > ");
            int killed = Integer.parseInt(scanner.nextLine());

            System.out.print("enter the name of the cat > ");
            String name = scanner.nextLine();

            obj = new Cat(killed, name);
        }

        else if (type.equals("dog")) {
            System.out.print("is the dog friendly (y/n) > ");
            boolean friendly = scanner.nextLine().equalsIgnoreCase("y");

            System.out.print("enter the name of the dog > ");
            String name = scanner.nextLine();

            obj = new Dog(friendly, name);
        }

        else if (type.equals("teacher")) {
            System.out.print("how old is your teacher > ");
            int age = Integer.parseInt(scanner.nextLine());

            System.out.print("enter the name of your teacher > ");
            String name = scanner.nextLine();

            obj = new Teacher(age, name);
        }

        return obj;
    }

    static void add(Talkable obj) { Zoo.list.add(obj); }

    static void promptAdd() {
        Talkable obj = Zoo.prompt();
        if (obj != null) { Zoo.add(obj); }
    }
}
