package ana.ifpr.foz.controlebiblioteca.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static String toLocaleDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String data = formatter.format(date);

        return data;
    }
}
