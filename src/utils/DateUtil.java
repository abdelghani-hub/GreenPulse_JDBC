package utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DateUtil {
    public static boolean isNoCommonDate(LocalDate periodStart, LocalDate periodEnd, List<LocalDate> Dates) {
        // TODO : refactor using stream
        for (LocalDate iDate = periodStart; !iDate.isAfter(periodEnd); iDate = iDate.plusDays(1)) {
            if (Dates.contains(iDate)) return false;
        }
        return true;
    }

    public static List<LocalDate> getDatesBetween(LocalDate start, LocalDate end){
        List<LocalDate> datesList = new ArrayList<>();
        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)){
            datesList.add(date);
        }
        return datesList;
    }
}
