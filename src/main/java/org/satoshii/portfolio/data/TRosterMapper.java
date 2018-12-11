package org.satoshii.portfolio.data;

import org.satoshii.portfolio.model.*;
import org.apache.ibatis.annotations.Select;
import java.util.*;

public interface TRosterMapper {
    @Select("SELECT * FROM t_roster")
    List<TRoster> selectTRosters();
}
