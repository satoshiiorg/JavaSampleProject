package org.satoshii.portfolio;

import org.satoshii.portfolio.data.*;
import org.satoshii.portfolio.model.*;
import java.io.*;
import java.util.*;
import org.apache.ibatis.session.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // List<User> users = Arrays.asList(
        //     User.create(1, "Foo"),
        //     User.create(2, "Bar"),
        //     User.create(3, "Baz"));
        // users.stream().map()
        // System.out.println(users);
        try (InputStream in = Main.class.getResourceAsStream("/org/satoshii/portfolio/data/mybatis-config.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
            try (SqlSession session = factory.openSession()) {
                MEmpMapper mapper = session.getMapper(MEmpMapper.class);
                List<MEmp> result = mapper.selectMEmps();
                System.out.println(result);
                // result.forEach(row -> {
                //     System.out.println("---------------");
                //     row.forEach((columnName, value) -> {
                //         System.out.printf("columnName=%s, value=%s%n", columnName, value);
                //     });
                // });
                
                // // MDeptMapper mapper = session.getMapper(MDeptMapper.class);
                // // List<MDept> depts = mapper.selectMDepts();
                // // System.out.println(depts);
                // MEmpMapper mapper = session.getMapper(MEmpMapper.class);
                // // List<MEmp> emps = mapper.selectMEmps();
                // // System.out.println(emps);
                // MEmp emp = mapper.selectMEmp(10);
                // System.out.println(emp);
            }
        }
    }
}
