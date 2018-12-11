package org.satoshii.portfolio.data;

import org.satoshii.portfolio.bean.*;

import java.util.*;
import java.io.*;

import org.apache.ibatis.session.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MDeptMapperTest {
    
    // @BeforeEach
    // void setup() {
    // }
    
    @Test
    @DisplayName("変更前 m_dept")
    void testSelectMDepts() throws IOException {
        try (InputStream in = MDeptMapperTest.class.getResourceAsStream("/mybatis-config-db1.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
            try (SqlSession session = factory.openSession()) {
                MDeptMapper mDeptMapper = session.getMapper(MDeptMapper.class);
                List<MDept> depts = mDeptMapper.selectMDepts();
                // テスト側でソート
                depts.sort(MDept.NATURAL_ORDER);
                assertEquals(depts.toString(), "[MDept(deptNo=10, deptNm=sales), MDept(deptNo=21, deptNm=dev1), MDept(deptNo=22, deptNm=dev2), MDept(deptNo=30, deptNm=account), MDept(deptNo=40, deptNm=newbiz)]");
            }
        }
    }
}
