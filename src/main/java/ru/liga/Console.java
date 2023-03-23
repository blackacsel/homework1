package ru.liga;
import java.util.*;
import static java.lang.System.in;

public class Console {
    private static final Scanner scanner = new Scanner(in);

    private static final Map<String, String> cdx = new HashMap<>(Map.of(
            "USD", "Доллар США",
            "TRY", "Турецкая лира",
            "EVR", "Евро"));

    public static Command getCommand() {
        String line = scanner.nextLine();

        if (line == null) return new Command("error", "exit");

        List<String> words = Arrays.asList(line.split(" "));

        if (words.size() != 3 || !words.get(0).equals("rate") || !cdx.containsKey(words.get(1))
                || (!words.get(2).equals("week") && !words.get(2).equals("tomorrow"))) {
            System.out.println("Incorrect command");
            return new Command("", "incorrect");
        }
        return new Command(cdx.get(words.get(1)), words.get(2));
    }

    public static void print(List<Curs> courses) {
        courses.forEach(curs -> System.out.println(curs.getCurs()));
    }
}