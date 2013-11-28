package org.lab.javaee.chat;

import javax.inject.Qualifier;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Antoine Sabot-Durand
 */
@Qualifier
@Documented
@Retention(RUNTIME)
public @interface BadWord {
}
