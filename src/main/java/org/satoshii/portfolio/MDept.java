package org.satoshii.portfolio;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class MDept {
    public abstract int deptNo();
    public abstract String deptNm();
    public static MDept create(int deptNo, String deptNm) {
        return new AutoValue_MDept(deptNo, deptNm);
    }
}
