package org.satoshii.portfolio.data;

import org.satoshii.portfolio.model.*;
import org.apache.ibatis.annotations.Select;
import java.util.*;

public interface MEmpMapper {
//   @Select("SELECT * FROM m_dept WHERE id = #{id}")
//   MDept selectMDept(int id);

    // @Select("SELECT * FROM m_emp")
    List<MEmp> selectMEmps();
}
