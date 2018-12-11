package org.satoshii.portfolio.data;

import org.satoshii.portfolio.bean.*;
import org.apache.ibatis.annotations.Select;
import java.util.*;

public interface MEmpMapper {
    @Select("SELECT * FROM m_emp")
    List<MEmp> selectMEmps();
}
