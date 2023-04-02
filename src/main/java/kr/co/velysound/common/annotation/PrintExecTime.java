package kr.co.velysound.common.annotation;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ConditionalOnProperty(value = "spring.profiles.include", havingValue = "cw", matchIfMissing = false)
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PrintExecTime {

}
