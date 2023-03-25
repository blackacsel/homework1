package ru.liga.models;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.util.Date;

@AllArgsConstructor
@Getter
@Builder
public class Curs {
    private Integer nominal;
    private Date date;
    private Double curs;
    private String cdx;
}