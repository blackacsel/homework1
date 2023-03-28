package ru.liga.utils;
import ru.liga.models.Curs;
import java.io.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class CursFileReader {
    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    private static final String DELIMITER = ";";
    private static final String TITLES = "nominal;date;curs;cdx";

    public static List<Curs> read(File file, int count, String cdx) {
        try (InputStream inputStream = new FileInputStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String titles = reader.readLine();
            if (titles.equals(TITLES)) {
                    return reader.lines()
                            .map(line -> parseToCurs(Arrays.asList(line.split(DELIMITER))))
                            .filter(curs -> curs.getCdx().equals(cdx) && curs.getDate().before(Date.valueOf(LocalDate.now())))
                            .sorted(Comparator.comparing(Curs::getDate).reversed())
                            .limit(count)
                            .collect(Collectors.toList());
            } else {
                throw new RuntimeException("В файле отсутствуют заголовки");
            }
        } catch (IOException exc) {
            throw new IllegalArgumentException();
        }
    }

    public static Curs parseToCurs(List<String> line) {
        if (line == null) {
            throw new IllegalArgumentException();
        }

        try {
            return Curs.builder()
                    .nominal(parseInt(line.get(0)))
                    .date(formatter.parse(line.get(1)))
                    .curs(parseDouble(line.get(2)))
                    .cdx(line.get(3))
                    .build();
        } catch (ParseException e) {
            throw new RuntimeException("Файл содержит некорректные данные");
        }
    }
}