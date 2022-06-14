package utils;

import java.time.LocalDate;
import java.time.LocalTime;

public class DateConverter {
    public static LocalDate convertStringToLocalDate(String date) {
        try {
            String[] array = date.split("-");
            LocalDate result = LocalDate.of(Integer.parseInt(array[0]), Integer.parseInt(array[1]),
                    Integer.parseInt(array[2]));
            return result;
        }catch (Exception e){
            System.out.println("Kein Datum angegeben!");
            return null;
        }
    }

    public static LocalTime convertStringToLocalTime(String time) {
        try{
            String[] array = time.split(":");
            LocalTime result = LocalTime.of(Integer.parseInt(array[0]), Integer.parseInt(array[1]));
            return result;
        }catch (Exception e){
            System.out.println("Kein Datum angegeben!");
            return null;
        }

    }
}
