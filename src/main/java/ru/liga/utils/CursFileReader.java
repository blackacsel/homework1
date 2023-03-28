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
    private static final Integer NOMINAL_INDEX = 0;
    private static final Integer DATE_INDEX = 1;
    private static final Integer CURS_INDEX = 2;
    private static final Integer CDX_INDEX = 3;


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
                    .nominal(parseInt(line.get(NOMINAL_INDEX)))
                    .date(formatter.parse(line.get(DATE_INDEX)))
                    .curs(parseDouble(line.get(CURS_INDEX)))
                    .cdx(line.get(CDX_INDEX))
                    .build();
        } catch (ParseException e) {
            throw new RuntimeException("Файл содержит некорректные данные");
        }
    }
}