package com.zhoulei.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KeyUtilTest {

    @Test
    public void uniqueKey(){
        Random random = new Random();

        Integer number=random.nextInt(900000)+100000;
        System.out.println(number);
    }
}