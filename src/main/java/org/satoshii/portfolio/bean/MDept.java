package org.satoshii.portfolio.bean;

import lombok.*;
import java.util.Comparator;
import static java.util.Comparator.*;

@Data
public class MDept implements Comparable<MDept> {
    private int deptNo;
    private String deptNm;
    
    
    public static final Comparator<MDept> NATURAL_ORDER
        = comparingInt(MDept::getDeptNo).thenComparing(comparing(MDept::getDeptNm));
    
    @Override
    public int compareTo(MDept other) {
        return NATURAL_ORDER.compare(this, other);
    }
}
