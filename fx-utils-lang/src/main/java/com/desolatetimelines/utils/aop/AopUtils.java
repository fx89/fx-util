package com.desolatetimelines.utils.aop;

import java.lang.annotation.Annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

public class AopUtils {
    public static <T extends Annotation> T getAnnotationFromJoinPoint(JoinPoint joinPoint, Class<T> tClass) {
        return ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(tClass);
    }
}
