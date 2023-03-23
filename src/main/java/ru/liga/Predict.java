package ru.liga;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Predict {
    public static List<Curs> getPredict(Command command) {
        List<Curs> curses = CursFileReader.read(7, command.getCdx());
        List<Curs> result = new ArrayList<>();

        if (command.getRate().equals("tomorrow")) {
            result.add(new Curs(1, Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    avr(curses), command.getCdx()));
            return result;
        }

        for (int i = 0; i < 7; ++i) {
            result.add(new Curs(1, Date.from(LocalDate.now().plusDays(i).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    avr(curses), command.getCdx()));
            curses.set(i, result.get(i));
        }

        return result;
    }

    private static double avr(List<Curs> curses) {
        return curses
                .stream()
                .map(curs -> curs.getCurs() / curs.getNominal())
                .reduce(0.0, Double::sum) / curses.size();
    }
}