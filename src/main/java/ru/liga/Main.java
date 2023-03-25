package ru.liga;
import ru.liga.models.Command;
import ru.liga.models.Curs;
import ru.liga.utils.CursFileReader;
import java.io.File;
import java.util.List;
import java.util.Scanner;
import static java.lang.System.in;

public class Main {
    public static void main(String[] args) {
        Console console = new Console(new Scanner(in));
        Predict predict = new Predict();
        File file = new File("src/main/resources/dollar_evro_lira.csv");

        Command command = console.parseCommand();

        if (command.isCorrect()) {
            File processedFile = CursFileReader.handle(file, command.getCdx());
            List<Curs> predicts = predict.getPredict(processedFile, command);
            console.print(predicts);
            processedFile.delete();
        } else {
            System.out.println("Command is incorrect");
        }
    }
}