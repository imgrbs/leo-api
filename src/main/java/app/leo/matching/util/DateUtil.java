package app.leo.matching.util;

import java.time.LocalDate;
import java.time.ZoneId;

public class DateUtil {

    public LocalDate getCurrentDate(){
        return LocalDate.now(ZoneId.of("Asia/Bangkok"));
    }


}
