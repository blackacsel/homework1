package ru.liga;

public class Main {
    public static void main(String[] args) {
        while (true) {
            Command command = Console.getCommand();
            if (command.isCorrect()) Console.print(Predict.getPredict(command));
        }
    }
}