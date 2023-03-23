package ru.liga;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CursFileReader {
    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public static Curs read() {
        try (InputStream inputStream = CursFileReader.class.getResourceAsStream("/dollar_evro_lira.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            reader.readLine();
            return parseToCurs(Arrays.asList(reader.readLine().split(";")));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Curs> read(int count, String cdx) {
        List<Curs> curses = new ArrayList<>();

        try (InputStream inputStream = CursFileReader.class.getResourceAsStream("/dollar_evro_lira.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            List<String> lines = null;

            while (reader.ready()) {
                lines = Arrays.asList(reader.readLine().split(";"));
                if (lines.get(3).equals(cdx)) break;
            }

            curses.add(parseToCurs(lines));

            for (int i = 0; i < count - 1; ++i) {
                lines = Arrays.asList(reader.readLine().split(";"));
                curses.add(parseToCurs(lines));
            }

            return curses;
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static Curs parseToCurs(List<String> line) throws ParseException {
        if (line == null) throw new IllegalArgumentException();
        return new Curs(Integer.parseInt(line.get(0)), formatter.parse(line.get(1)),
                Double.parseDouble(line.get(2)), line.get(3));
    }
}