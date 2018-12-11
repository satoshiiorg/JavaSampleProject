package org.satoshii.portfolio.db;

import org.satoshii.portfolio.db.*;
import java.sql.*;

public class JavaTestHelper {
    public static DB getLevel2_1Db() throws SQLException {
        return new DB("db/Portfolio");
    }
    public static DB getLevel2_2Db() throws SQLException {
        return new DB("db/Portfolio2");
    }
}
