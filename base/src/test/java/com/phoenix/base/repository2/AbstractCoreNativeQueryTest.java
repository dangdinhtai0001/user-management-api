package com.phoenix.base.repository2;

import com.google.gson.Gson;
import com.phoenix.common.exceptions.SupportException;
import com.phoenix.common.structure.DefaultTuple;
import com.phoenix.common.structure.imp.TripleDefaultTupleImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SpringBootTest
@ActiveProfiles(value = "dev")
public class AbstractCoreNativeQueryTest {
    @Autowired
    @Qualifier("AbstractCoreNativeQueryImp")
    private AbstractCoreNativeQueryImp coreNativeQueryImp;


    @Test
    public void testExecuteSqlContainMultiExpression() {
        List<?> list = coreNativeQueryImp.executeNativeQuery("select * from fw_user");

        for (Object o : list) {
            //System.out.println(Arrays.toString(o));
            if (o instanceof Object[]) {
                System.out.println(Arrays.toString((Object[]) o));
            } else {
                System.out.println(o);
            }
        }
    }

    @Test
    public void testExecuteSqlSingleExpression() {
        List<?> list = coreNativeQueryImp.executeNativeQuery("select id from fw_user");

        for (Object o : list) {
            System.out.println(o);
        }
    }

    @Test
    public void testExecuteSqlSingleExpression2() {
        List<?> list = coreNativeQueryImp.executeNativeQuery("select id from fw_user where id = 6");

        for (Object o : list) {
            System.out.println(o);
        }
    }

    @Test
    public void testExecuteSqlWithParams() {
        List<?> list = coreNativeQueryImp.executeNativeQuery("select * from fw_user where id < ?", 11);

        for (Object o : list) {
            //System.out.println(Arrays.toString(o));
            if (o instanceof Object[]) {
                System.out.println(Arrays.toString((Object[]) o));
            } else {
                System.out.println(o);
            }
        }
    }

    @Test
    public void testParseResult() throws SupportException {
        List<?> list = coreNativeQueryImp.executeNativeQuery("select id, username, password from fw_user where id < ?", 11);
        Map<String, Object> tuple = coreNativeQueryImp.parseResult(list.get(0),
                new String[]{"id", "username", "password"}
        );

        System.out.println(tuple);
    }

    @Test
    public void testParseResult2() throws SupportException {
        List<?> list = coreNativeQueryImp.executeNativeQuery("select id, username, password from fw_user where id = ?", 11);
        Map<String, Object> tuple = coreNativeQueryImp.parseResult(list.get(0),
                new String[]{"id", "username", "password"}
        );

        System.out.println(tuple);
    }

    @Test
    public void testParseResult3() throws SupportException {
        List<?> list = coreNativeQueryImp.executeNativeQuery("select id, username from fw_user where id = ?", 11);
        Map<String, Object> tuple = coreNativeQueryImp.parseResult(list.get(0), new String[]{"id"});

        Gson gson = new Gson();
        System.out.println(gson.toJson(tuple));
    }

    @Test
    public void testParseResultWithList() throws SupportException {
        List<?> list = coreNativeQueryImp.executeNativeQuery("select id, username from fw_user where id > ?", 1);
        List<Map<String, Object>> tuples = coreNativeQueryImp.parseResult(list, new String[]{"id", "username"});

        Gson gson = new Gson();
        System.out.println(gson.toJson(tuples));
    }
}
