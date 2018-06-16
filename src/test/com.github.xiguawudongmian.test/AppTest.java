package com.github.xiguawudongmian.test;

import com.github.xiguawudongmian.core.context.BoxContext;
import com.github.xiguawudongmian.core.factory.BoxFactory;
import com.github.xiguawudongmian.core.factory.SimpleBoxFactory;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class AppTest {

    @Test
    public void testBoxContext(){
        BoxFactory factory = new SimpleBoxFactory();
        Map list = factory.create(Map.class, null);
        System.out.println(list.getClass().getName());
    }
}
