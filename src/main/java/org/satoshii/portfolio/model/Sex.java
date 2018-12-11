package org.satoshii.portfolio.model;

import lombok.*;

@AllArgsConstructor
@Getter
public enum Sex {
    //TODO 外部化
    MAN  (0, "男性"),
    WOMAN(1, "女性");
    private final int flg;
    private final String string;
    
    public static Sex of(int flg) {
        return flg == 0 ? MAN : WOMAN;
    }
}
