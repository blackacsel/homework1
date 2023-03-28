package ru.liga;
import ru.liga.models.Command;
import ru.liga.models.Curs;
import ru.liga.services.Console;
import ru.liga.services.Predict;
import java.io.File;
import java.util.List;
import java.util.Scanner;
import static java.lang.System.in;

public class Main {
    public static void main(String[] args) {
        Console console = new Console(new Scanner(in));
        Predict predict = new Predict();
        File file = new File("src/main/resources/dollar_evro_lira.csv");

        Command command;
        while (!(command = console.parseCommand()).isExit()) {
            if (command.isCorrect()) {
                List<Curs> predicts = predict.getPredict(file, command);
                console.print(predicts);
            } else {
                System.out.println("Введите команду заново");
            }
        }
    }
}