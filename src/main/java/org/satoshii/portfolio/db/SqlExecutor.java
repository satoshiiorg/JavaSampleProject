package org.satoshii.portfolio.db;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.*;
import java.util.function.*;
// import java.beans.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.*;
import org.apache.commons.lang3.text.WordUtils;
// import org.apache.commons.text.WordUtils;

// DBさえクローズすればconは閉じるのでここはCloseableにする必要はない
// というかここでクローズするとDBでも使えなくなる
// やるのであれば参照カウンタ方式で
public class SqlExecutor {
    private final Connection con;
    private final List<Map<String, String>> result = new ArrayList<>();
    
    // リソースリーク注意
    SqlExecutor(Connection con) {
        this.con = con;
    }
    
    /**
     * 指定のテーブルのすべての行を取得したSqlExecutorを返す。
     * 
     * @param name 取得対象のテーブル名
     * @return 全件取得した結果
     */
    // TODO nameのエスケープ
    public SqlExecutor from(String name) throws SQLException {
        // 実装は単にresultに結果を格納してthisを返す
        try(PreparedStatement pstmt = con.prepareStatement(String.format("SELECT * FROM %s", name))) {
            ResultSet rs = pstmt.executeQuery();
            // 列名のリスト
            ResultSetMetaData rsmd= rs.getMetaData();
            List<String> columnNameList = new ArrayList<>();
            for(int i = 1, count = rsmd.getColumnCount(); i <= count; i++) {
                columnNameList.add(rsmd.getColumnName(i));
            }
            // MetaData取る関係でforにはしてない
            while(rs.next()) {
                Map<String, String> map = new HashMap<>();
                for(String columnName: columnNameList) {
                    map.put(columnName, rs.getString(columnName));
                }
                result.add(map);
            }
        }
        return this;
    }
    
    // mapToBeanのタイミングで変換するのが筋ではあるが
    private static String snakeToCamel(String s) {
        return StringUtils.remove(WordUtils.uncapitalize(WordUtils.capitalizeFully(s, '_')), "_");
    }
    
    // TODO emp_nm -> empName みたいなのアノテーション使ってマッピングしたい
    // TODO クラス自体がジェネリクスならここでClassをもらわなくてもBeanを生成できる
    private static <T> T mapToBean(Map<String, String> map, Class<T> clazz) {
        try {
            T bean = clazz.newInstance();
            Map<String, String> camelMap
                = map.entrySet()
                    .stream()
                    .collect(Collectors.toMap(e -> snakeToCamel(e.getKey()), Entry::getValue));
            BeanUtils.populate(bean, camelMap);
            return bean;
        } catch(InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }
    
    public List<Map<String, String>> getResult() {
        return result;
    }
    
    public <T> List<T> getResult(Class<T> clazz) {
        return result.stream()
                    .map(map -> mapToBean(map, clazz))
                    .collect(Collectors.toList());
    }
    
    // 今ならリフレクションじゃなくジェネリクス+Supplierとかかなあ
    // このクラス自体をジェネリクス化して
    // public List<T> getResult() {
    //     return result.stream()
    //                 .map(map -> mapToBean(map, this.getClass().getGenericSuperclass()))
    //                 .collect(Collectors.toList());
    // }
}
