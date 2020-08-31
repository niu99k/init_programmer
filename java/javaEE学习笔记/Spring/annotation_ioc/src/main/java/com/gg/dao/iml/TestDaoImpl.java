package com.gg.dao.iml;

import com.gg.dao.TestDao;
import org.springframework.stereotype.Repository;

@Repository("testDao")
public class TestDaoImpl implements TestDao {
    public void testSuc() {
        System.out.println("test Suc");
    }
}
