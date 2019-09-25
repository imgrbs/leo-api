package app.leo.matching.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
public class DateUtil {

    public Date getCurrentDate(){
        return java.sql.Date.valueOf(LocalDate.now(ZoneId.of("Asia/Bangkok")));
    }


}
