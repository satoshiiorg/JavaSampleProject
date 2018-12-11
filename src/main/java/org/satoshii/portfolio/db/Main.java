package org.satoshii.portfolio.db;

import org.satoshii.portfolio.db.*;
import org.satoshii.portfolio.bean.*;

import java.sql.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.*;

// 生JDBC+BeanUtilsでのマッピング版
public class Main {
    public static void main(String[] args) {
        System.out.println("--------問1--------");
        q1();
        System.out.println("--------問2--------");
        q2();
    }
    
    public static void q1() {
        getOvertimeView("201405").forEach(System.out::println);
    }
    
    // 部署ごとに指定月の部署名・合計残業時間・平均残業時間を出力
    public static List<Overtime> getOvertimeView(String yearMonth) {
        List<MEmp> empList;
        List<MDept> deptList;
        List<TRoster> rosterList;
        
        try(DB db = JavaTestHelper.getLevel2_1Db()) {
            empList = db.select().from("m_emp").getResult(MEmp.class);
            deptList = db.select().from("m_dept").getResult(MDept.class);
            rosterList = db.select().from("t_roster").getResult(TRoster.class);
        } catch(SQLException e) {
            // 実務ならSQLExceptionはここで処理したい
            throw new RuntimeException(e);
        }
        
        List<Overtime> overtimeList = new ArrayList<>();
        for(MDept dept: deptList) {
            // この部署の社員番号一覧
            List<Integer> empNoList
                = empList.stream()
                        .filter(emp -> emp.getDeptNo() == dept.getDeptNo())
                        .map(MEmp::getEmpNo)
                        .collect(toList());
            
            // この部署の当該月の勤怠一覧
            List<TRoster> filteredRosterList
                = rosterList.stream()
                        // TODO どっちから比較した方が速い？
                        .filter(roster -> roster.getRosterDay().startsWith(yearMonth)
                                    && empNoList.contains(roster.getEmpNo()))
                        .collect(toList());
            
            // この部署の残業計
            int overtimeMinutes
                = filteredRosterList.stream()
                        .collect(summingInt(TRoster::getOvertimeMinutes));
            
            // 一応社員数ゼロの部署が存在している可能性を考慮
            int overtimeHoursAvg;
            if(empNoList.isEmpty()) {
                overtimeHoursAvg = 0;
            } else {
                // TODO 丸め確認
                overtimeHoursAvg = overtimeMinutes / empNoList.size() / 60;
            }
            
            Overtime overtime = new Overtime();
            overtime.setDeptNm(dept.getDeptNm());
            overtime.setOvertimeMinutesSum(overtimeMinutes);
            overtime.setOvertimeHoursAvg(overtimeHoursAvg);
            overtimeList.add(overtime);
        }
        return overtimeList;
    }
    
    
    public static void q2() {
        String year = "2014";
        String month = "04";
        String yearMonth = year + month;
        int overtimeHour = 2;
        int overtimeMinutes = overtimeHour * 60;
        
        EnumMap<Sex, Integer> map = countOverworkersBySex(yearMonth, overtimeMinutes);
        
        System.out.printf("%s年%s月に%d時間以上残業した社員数:%n", year, month, overtimeHour);
        map.entrySet().forEach(e -> System.out.printf("%s %d人%n", e.getKey().getString(), e.getValue()));
    }
    
    // 指定月にovertimeMinutes分以上残業した男性社員数と女性社員数
    // EnumMapなのでEnumの定義順でイテレートされる
    public static EnumMap<Sex, Integer> countOverworkersBySex(String yearMonth, int overtimeMinutes) {
        List<MEmp> empList;
        List<MDept> deptList;
        List<TRoster> rosterList;
        
        try(DB db = JavaTestHelper.getLevel2_1Db()) {
            empList = db.select().from("m_emp").getResult(MEmp.class);
            deptList = db.select().from("m_dept").getResult(MDept.class);
            rosterList = db.select().from("t_roster").getResult(TRoster.class);
        } catch(SQLException e) {
            // 実務ならSQLExceptionはここで処理したい
            throw new RuntimeException(e);
        }
        
        // 従業員ごとの残業時間(分)
        Map<Integer, Integer> overtimeMinutesMap
            = rosterList.stream()
                .filter(roster -> roster.getRosterDay().startsWith(yearMonth))
                .collect(groupingBy(TRoster::getEmpNo, summingInt(TRoster::getOvertimeMinutes)));
        
        // 性別ごとの人数
        // ここではゼロ人の場合エントリーが作成されないので注意
        Map<Integer, Integer> sexFlgCountMap
            = empList.stream()
                .filter(emp -> overtimeMinutesMap.getOrDefault(emp.getEmpNo(), 0) >= overtimeMinutes)
                // couting() だと Long なので一応 Integer で集計
                // 意味的にはキーは Sex だが MEmp::getSex だと変換が無駄なので
                .collect(groupingBy(MEmp::getSexFlg, reducing(0, e -> 1, Integer::sum)));
        
        // キーを変換しつつゼロ人の場合を設定
        EnumMap<Sex, Integer> sexCountMap = new EnumMap<>(Sex.class);
        for(Sex sex: Sex.values()) {
            sexCountMap.put(sex, sexFlgCountMap.getOrDefault(sex.getFlg(), 0));
        }
        
        return sexCountMap;
    }
    
}
