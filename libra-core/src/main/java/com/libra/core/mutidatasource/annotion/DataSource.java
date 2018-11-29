package com.libra.core.mutidatasource.annotion;

import java.lang.annotation.*;

/**
 * @author Libra
 * @date 2018/11/28
 * @description 多数据源标识
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface DataSource {
    String name() default "";
}
