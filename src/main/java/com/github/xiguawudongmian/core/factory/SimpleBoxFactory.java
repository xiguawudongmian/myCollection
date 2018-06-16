package com.github.xiguawudongmian.core.factory;

import com.github.xiguawudongmian.core.context.BoxContext;
import com.github.xiguawudongmian.core.context.XMLConfigurationContext;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class SimpleBoxFactory implements BoxFactory {

    private BoxContext boxContext;

    public void setBoxContext(BoxContext boxContext) {
        this.boxContext = boxContext;
    }

    public void initDefaultContext(){
        this.boxContext = new XMLConfigurationContext("applicationContext.xml");
    }

    @Override
    public <T> T create(Class<T> boxType, Object... args) {
        if(boxContext == null){
            initDefaultContext();
        }
        Class<? extends T> targetClass = boxContext.get(boxType);
        try {
            return targetClass.getConstructor().newInstance();
        } catch (Exception e) {
            if(targetClass == null){
                throw new RuntimeException("class="+boxType.getName()+",args="+ Arrays.toString(args)+"未注册到容器中");
            }
            throw new RuntimeException(targetClass.getName() + "不存在空参构造方法", e);
        }
    }
}
