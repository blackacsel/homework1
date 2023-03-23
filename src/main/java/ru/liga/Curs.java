package ru.liga;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Date;

@AllArgsConstructor
@Getter
public class Curs {
    private Integer nominal;
    private Date date;
    private Double curs;
    private String cdx;
}