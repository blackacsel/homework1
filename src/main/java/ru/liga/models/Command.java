package ru.liga.models;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Command {
    private final String cdx;
    private final String rate;

    public boolean isCorrect() {
        return !rate.equals("incorrect");
    }

    public boolean isExit() {
        return rate.equals("exit");
    }
}