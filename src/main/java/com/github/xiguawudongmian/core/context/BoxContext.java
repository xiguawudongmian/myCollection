package com.github.xiguawudongmian.core.context;

import java.util.Collection;

/**
 * 容器上下文
 */
public interface BoxContext {

    /**
     *
     * @param boxType
     * @param args
     * @param <T>
     * @return
     */
    <T> Class<? extends T> get(Class<T> boxType,Object... args);


    void put(Class<? extends Collection> boxType,Class<?>  implType);

}
