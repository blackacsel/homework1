package ru.liga;
import ru.liga.models.Command;
import ru.liga.models.Curs;
import java.util.*;

public class Console {
    private final Scanner scanner;
    private final Map<String, String> cdxMap = new HashMap<>(Map.of(
            "USD", "Доллар США",
            "TRY", "Турецкая лира",
            "EVR", "Евро"));

    public Console(Scanner scanner) {
        this.scanner = scanner;
    }

    public Command parseCommand() {
        String line = scanner.nextLine();

        if (line == null) throw new IllegalArgumentException();

        List<String> keyWords = Arrays.asList(line.split(" "));

        if (validateKeyWords(keyWords)) {
            String cdx = keyWords.get(1);
            String range = keyWords.get(2);
            return new Command(cdxMap.get(cdx), range);
        }

        System.out.println("Incorrect command");
        return new Command("", "incorrect");
    }

    private boolean validateKeyWords(List<String> keyWords) {
        String rate = keyWords.get(0);
        String cdx = keyWords.get(1);
        String range = keyWords.get(2);
        return keyWords.size() == 3 && rate.equals("rate")
                && cdxMap.containsKey(cdx) && (range.equals("tomorrow") || range.equals("week"));
    }

    public void print(List<Curs> courses) {
        courses.forEach(curs -> System.out.println(curs.getCurs()));
    }
}