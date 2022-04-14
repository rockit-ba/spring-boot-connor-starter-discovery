package cn.pan.connor.annos;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 处理Connor server的响应的 处理器
 * 如果有多个请添加 @order 注解
 * @Author Lucky Pan
 * @Date 2022/4/13 22:52
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface ConnorRespHandle {
}
