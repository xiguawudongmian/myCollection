package com.github.xiguawudongmian.core.factory;

public interface BoxFactory {

    /**
     * 构建容器
     * @param boxType
     * @param args
     * @param <T>
     * @return
     */
    <T> T create(Class<T> boxType,Object... args);
}
