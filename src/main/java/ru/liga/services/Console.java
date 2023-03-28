package ru.liga.services;
import ru.liga.models.Command;
import ru.liga.models.Curs;
import java.util.*;

public class Console {
    private final Scanner scanner;
    private final Map<String, String> cdxDict = new HashMap<>(Map.of(
            "USD", "Доллар США",
            "TRY", "Турецкая лира",
            "EVR", "Евро"));
    private final static String DELIMITER = " ";
    private final static String REGEX = "(rate (USD|TRY|EVR) (tomorrow|week))|(exit)";
    private final static Integer CDX_INDEX = 1;
    private final static Integer RANGE_INDEX = 2;

    public Console(Scanner scanner) {
        this.scanner = scanner;
    }

    public Command parseCommand() {
        String line = scanner.nextLine();

        if (line == null || !line.matches(REGEX)) {
            return new Command("", "incorrect");
        }

        if (line.contains("exit")) {
            return new Command("", "exit");
        }

        List<String> keyWords = Arrays.asList(line.split(DELIMITER));

        String cdx = keyWords.get(CDX_INDEX);
        String range = keyWords.get(RANGE_INDEX);
        return new Command(cdxDict.get(cdx), range);
    }


    public void print(List<Curs> courses) {
        courses.forEach(curs -> System.out.println(curs.getCurs()));
    }
}