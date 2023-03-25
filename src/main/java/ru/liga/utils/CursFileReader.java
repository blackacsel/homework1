package ru.liga.utils;
import ru.liga.models.Curs;
import java.io.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class CursFileReader {
    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    private static final String DELIMITER = ";";
    private static final String TITLES = "nominal;date;curs;cdx";

    public static List<Curs> read(File file, int count) {
        List<Curs> curses = new ArrayList<>();

        try (InputStream inputStream = new FileInputStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            for (int i = 0; i < count; ++i) {
                List<String> lines = Arrays.asList(reader.readLine().split(DELIMITER));
                curses.add(parseToCurs(lines));
            }

            return curses;
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static File handle(File originalFile, String cdx) {
        String path = String.format("src/main/resources/processedFile_%s.csv", cdx);
        File processedFile = new File(path);

        try (InputStream inputStream = new FileInputStream(originalFile);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
             Writer writer = new FileWriter(processedFile, false)) {

            String titles = reader.readLine();
            if (titles.equals(TITLES)) {
                reader.lines()
                        .map(line -> {
                            try { return parseToCurs(Arrays.asList(line.split(DELIMITER))); }
                            catch (ParseException e) { throw new RuntimeException(e); }
                        })
                        .filter(curs -> curs.getCdx().equals(cdx) && curs.getDate().before(Date.valueOf(LocalDate.now())))
                        .sorted(Comparator.comparing(Curs::getDate).reversed())
                        .map(curs -> String.format("%s;%s;%s;%s",
                                     curs.getNominal().toString(),
                                     formatter.format(curs.getDate()),
                                     curs.getCurs().toString(),
                                     curs.getCdx()))
                        .forEach(line -> {
                            try {
                                writer.write(line);
                                writer.write("\n");
                            } catch (IOException e) { throw new RuntimeException(e); }
                        });
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IOException exc) {
            throw new IllegalArgumentException();
        }
        return processedFile;
    }

    public static Curs parseToCurs(List<String> line) throws ParseException {
        if (line == null) throw new IllegalArgumentException();

        return Curs.builder()
                .nominal(parseInt(line.get(0)))
                .date(formatter.parse(line.get(1)))
                .curs(parseDouble(line.get(2)))
                .cdx(line.get(3))
                .build();
    }
}