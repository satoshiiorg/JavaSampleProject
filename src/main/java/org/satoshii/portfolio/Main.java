package org.satoshii.portfolio;

import org.satoshii.portfolio.data.*;
import org.satoshii.portfolio.bean.*;

import java.io.*;
import java.util.*;

import org.apache.ibatis.session.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static void main(String[] args) throws Exception {
        
        Logger logger = LoggerFactory.getLogger(Main.class);
        
        // 変更前DB
        try (InputStream in = Main.class.getResourceAsStream("/mybatis-config-db1.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
            try (SqlSession session = factory.openSession()) {
                // MEmpMapper mEmpMapper = session.getMapper(MEmpMapper.class);
                // List<MEmp> result = mEmpMapper.selectMEmps();
                // System.out.println(result);
                
                MDeptMapper mDeptMapper = session.getMapper(MDeptMapper.class);
                List<MDept> depts = mDeptMapper.selectMDepts();
                System.out.println(depts);
                logger.debug("変更前m_dept: {}", depts);
                
                // TRosterMapper tRosterMapper = session.getMapper(TRosterMapper.class);
                // List<TRoster> rosters = tRosterMapper.selectTRosters();
                // System.out.println(rosters);
            }
        }
        
        // 変更後DB
        try (InputStream in = Main.class.getResourceAsStream("/mybatis-config-db2.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
            try (SqlSession session = factory.openSession()) {
                // MEmpMapper mEmpMapper = session.getMapper(MEmpMapper.class);
                // List<MEmp> result = mEmpMapper.selectMEmps();
                // System.out.println(result);
                
                MDeptWithWorkTimeMapper mDeptMapper = session.getMapper(MDeptWithWorkTimeMapper.class);
                List<MDeptWithWorkTime> depts = mDeptMapper.selectMDeptWithWorkTimes();
                System.out.println(depts);
                logger.debug("変更後m_dept: {}", depts);
                
                // TRosterMapper tRosterMapper = session.getMapper(TRosterMapper.class);
                // List<TRoster> rosters = tRosterMapper.selectTRosters();
                // System.out.println(rosters);
            }
        }
    }
}
