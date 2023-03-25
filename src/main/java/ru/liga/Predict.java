package ru.liga;
import ru.liga.models.Command;
import ru.liga.models.Curs;
import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static ru.liga.utils.CursFileReader.read;

public class Predict {
    public List<Curs> getPredict(File file, Command command) {
        List<Curs> curses = read(file, 7);
        List<Curs> result = new ArrayList<>();

        if (command.getRate().equals("tomorrow")) {
            Curs curs = Curs.builder()
                    .nominal(1)
                    .date(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                    .curs(avr(curses))
                    .cdx(command.getCdx())
                    .build();
            result.add(curs);
        } else {
            for (int i = 0; i < 7; ++i) {
                Curs curs = Curs.builder()
                        .nominal(1)
                        .date(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                        .curs(avr(curses))
                        .cdx(command.getCdx())
                        .build();
                result.add(curs);
                curses.set(i, result.get(i));
            }
        }

        return result;
    }

    private double avr(List<Curs> curses) {
        return curses
                .stream()
                .map(curs -> curs.getCurs() / curs.getNominal())
                .reduce(0.0, Double::sum) / curses.size();
    }
}