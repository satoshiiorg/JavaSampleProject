package org.satoshii.portfolio;

import org.apache.ibatis.annotations.Select;
import java.util.*;

public interface MDeptMapper {
//   @Select("SELECT * FROM m_dept WHERE id = #{id}")
//   MDpet selectMDept(int id);
    // @Select("SELECT * FROM m_dept")
    List<MDept> selectMDepts();
}