import java.util.*;

public class HelloWorld {
    public static void main (String[] args) {
        System.out.println("Hello World int IntelliJ");

        Scanner input = new Scanner(System.in);
        input.useLocale(Locale.US);

        System.out.println("Welcome!");
        System.out.println("What is your name?");

        String name = input.nextLine();
        System.out.println("Hello " + name);

    }
}
