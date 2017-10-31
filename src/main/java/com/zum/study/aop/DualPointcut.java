package com.zum.study.aop;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.util.PatternMatchUtils;

/**
 * Created by Joeylee on 2017-10-31.
 */
public class DualPointcut extends NameMatchMethodPointcut {

    public void setMappedClassName(String mappedClassName) {
        setClassFilter(new SimpleClassFilter(mappedClassName));
    }

    static class SimpleClassFilter implements ClassFilter {

        String mappedName;

        private SimpleClassFilter(String mappedName) {
            this.mappedName = mappedName;
        }

        public boolean matches(Class<?> type) {
            return PatternMatchUtils.simpleMatch(mappedName, type.getSimpleName());
        }
    }
}
