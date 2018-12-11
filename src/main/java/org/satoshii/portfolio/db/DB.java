package org.satoshii.portfolio.db;

import java.util.*;
import java.sql.*;

public class DB implements AutoCloseable {
    // private final String name;
    private final Connection con;
    public DB(String name) throws SQLException {
        // this.name = name;
        con = DriverManager.getConnection(String.format("jdbc:derby:%s", name));
    }
    
    @Override
    public void close() throws SQLException {
        if(con != null) {
            con.close();
        }
    }
    
    /**
     * 新しいSqlExecutorを返す。
     * 個々のSqlExecutorがselect結果を保持するため、
     * SqlExecutorを使いまわすことはできないことに注意。
     * @return 新規SqlExecutor
     */
    public SqlExecutor select() throws SQLException {
        return new SqlExecutor(con);
    }
}
