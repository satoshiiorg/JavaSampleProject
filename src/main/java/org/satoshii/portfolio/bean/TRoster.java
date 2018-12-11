package org.satoshii.portfolio.bean;

import lombok.*;

import java.util.*;
import java.time.*;
import java.time.format.*;
import java.time.temporal.*;

@Data
public class TRoster {
    private int empNo;
    // 1:出勤, 0:欠勤
    private int workFlg;
    private String rosterDay;
    // TODO 型
    private String timeFrom;
    private String timeTo;
    // 1:休日 0:平日
    private int holidayFlg;
    
    // @AllArgsConstructor
    // @Getter
    // private static enum Days {
    //     WEEKDAY(0),
    //     HOLIDAY(1);
    //     public static Days of(int flg) {
    //         return flg == 0 ? WEEKDAY : HOLIDAY;
    //     }
    // }
    
    public boolean isHoliday() {
        return holidayFlg == 1;
    }
    
    
    public static final DateTimeFormatter HHMM = DateTimeFormatter.ofPattern("HHmm");
    public int getOvertimeMinutes() {
        LocalTime from = LocalTime.parse(timeFrom, HHMM);
        LocalTime to = LocalTime.parse(timeTo, HHMM);
        //FIXME 要仕様確認 現状休日出勤はすべて残業時間として計算
        // 標準労働時間: 休日は0時間、平日は8時間
        int standardWorkTime = isHoliday() ? 0 : 8;
        // 除外時間: 標準労働時間と休憩時間
        int exclusiveTime = standardWorkTime + 1;
        // 標準労働時間の8時間と休憩の1時間を引く
        return (int)Duration.between(from, to).minus(exclusiveTime , ChronoUnit.HOURS).toMinutes();
    }
    
    // TODO 重かったら曜日はコードのまま渡す？結局こっちで変換必要になるから微妙？
    // TODO こっちのメソッドを主にして旧DB用はデフォルト値渡しでオーバーロードする
    public int getOvertimeMinutes(int exclusiveTime, List<DayOfWeek> holidays) {
        return (int)(Math.random() * 10);
        // FIXME まだロジック直してない
        
        // LocalTime from = LocalTime.parse(timeFrom, HHMM);
        // LocalTime to = LocalTime.parse(timeTo, HHMM);
        // //FIXME 要仕様確認 現状休日出勤はすべて残業時間として計算
        // // 標準労働時間: 休日は0時間、平日は8時間
        // int standardWorkTime = isHoliday() ? 0 : 8;
        // // 除外時間: 標準労働時間と休憩時間
        // int exclusiveTime = standardWorkTime + 1;
        // // 標準労働時間の8時間と休憩の1時間を引く
        // return (int)Duration.between(from, to).minus(exclusiveTime , ChronoUnit.HOURS).toMinutes();
    }
}
