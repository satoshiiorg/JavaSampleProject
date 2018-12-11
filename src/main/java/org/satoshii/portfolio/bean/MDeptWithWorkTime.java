package org.satoshii.portfolio.bean;

import java.time.*;
import java.util.*;
import lombok.*;

@Data
public class MDeptWithWorkTime {
    private int deptNo;
    private String deptNm;
    private String regularTime;
    private int regularHour;
    private String lunchTime;
    private int lunchMinutes;
    private int holiday1;
    private int holiday2;
    
    /**
     * 日曜:0 ～ 土曜:6 のコード体系を
     * 月曜:1 ～ 日曜:7 のコード体系に変換
     */
    private static int dayCodeToIso8601(int dayOfWeek) {
        return dayOfWeek == 0 ? 7 : dayOfWeek;
    }
    
    // 意味的にはSetだがJava8ベースで書いている (Set#of が使えない) ので簡単のため
    /**
     * この部署の定休曜日をリストで返す。
     */
    public List<DayOfWeek> getHolidays() {
        return Arrays.asList(DayOfWeek.of(dayCodeToIso8601(holiday1)),
                             DayOfWeek.of(dayCodeToIso8601(holiday2)));
    }
}
