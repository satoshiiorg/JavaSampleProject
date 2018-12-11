package org.satoshii.portfolio.model;

import lombok.*;

@Data
public class MEmp {
    private int empNo;
    private String empNm;
    private int deptNo;
    private int sexFlg;
    
    public Sex getSex() {
        return Sex.of(sexFlg);
    }
}
