package org.lab.javaee.chat;

import javax.interceptor.Interceptor;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Antoine Sabot-Durand
 */
@Interceptor
@Documented
@Retention(RUNTIME)
public @interface Log {
}