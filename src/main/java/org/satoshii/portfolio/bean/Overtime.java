package org.satoshii.portfolio.bean;

import lombok.*;

// 残業時間ビュー
@Data
public class Overtime {
    private String deptNm;
    private int overtimeMinutesSum;
    private float overtimeHoursAvg;
    @Override
    public String toString() {
        return String.format("部署:%s, 残業計%d時間, 平均%d時間", deptNm, overtimeMinutesSum / 60, (int)overtimeHoursAvg);
        // return String.format("%10s, 部署計%10d時間, 平均%2d時間", deptNm, overtimeMinutesSum / 60, (int)overtimeHoursAvg);
    }
}
