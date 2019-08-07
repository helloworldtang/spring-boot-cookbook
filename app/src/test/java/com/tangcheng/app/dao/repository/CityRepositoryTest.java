package com.tangcheng.app.dao.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by tangcheng on 6/28/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CityRepositoryTest {

    @Autowired
    private CityRepository cityRepository;

    @Test
    public void list() throws Exception {
        //SELECT * from city
        cityRepository.list(null, null, null);
        //SELECT * from city WHERE id=?
        cityRepository.list(1L, null, null);
        //SELECT * from city WHERE NAME =?
        cityRepository.list(null, "name", null);
        //SELECT * from city WHERE state=?
        cityRepository.list(null, null, "state");
        //SELECT * from city WHERE id=? AND state=?
        cityRepository.list(1L, null, "state");
        //SELECT * from city WHERE NAME =? AND state=?
        cityRepository.list(null, "name", "state");
    }

}