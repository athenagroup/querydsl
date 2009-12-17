/*
 * Copyright (c) 2009 Mysema Ltd.
 * All rights reserved.
 * 
 */
package com.mysema.query.serialization;

import java.lang.reflect.Field;

import com.mysema.query.types.Templates;
import com.mysema.query.types.operation.OperatorImpl;
import com.mysema.query.types.operation.Ops;


/**
 * JavaTemplates extends Template to provide Java syntax compliant serialization 
 * of Querydsl expressions
 * 
 * @author tiwe
 *
 */
public class JavaTemplates extends Templates {
    
    public JavaTemplates() {
        add(Ops.EQ_PRIMITIVE, "{0} == {1}");
        add(Ops.EQ_OBJECT, "{0} == {1}");
        add(Ops.IS_NULL, "{0} == null");
        add(Ops.IS_NOT_NULL, "{0} != null");
        add(Ops.INSTANCE_OF, "{0} instanceof {1}");

        // collection
        add(Ops.IN, "{1}.contains({0})");
        add(Ops.COL_IS_EMPTY, "{0}.isEmpty()");
        add(Ops.COL_SIZE, "{0}.size()");
        
        // array
        add(Ops.ARRAY_SIZE, "{0}.length");
        
        // map
        add(Ops.MAP_ISEMPTY, "{0}.isEmpty()");
        add(Ops.MAP_SIZE, "{0}.size()");
        add(Ops.CONTAINS_KEY, "{0}.containsKey({1})");
        add(Ops.CONTAINS_VALUE, "{0}.containsValue({1})");
                
        // Comparable
        add(Ops.BETWEEN, "{1} < {0} && {0} < {2}");
        
        // String
        add(Ops.CHAR_AT, "{0}.charAt({1})");
        add(Ops.LOWER, "{0}.toLowerCase()");        
        add(Ops.SUBSTR_1ARG, "{0}.substring({1})");
        add(Ops.SUBSTR_2ARGS, "{0}.substring({1},{2})");
        add(Ops.TRIM, "{0}.trim()");
        add(Ops.UPPER, "{0}.toUpperCase()");
        add(Ops.MATCHES, "{0}.matches({1})");
        add(Ops.STRING_LENGTH, "{0}.length()");        
        add(Ops.STRING_IS_EMPTY, "{0}.isEmpty()");
        add(Ops.STRING_CONTAINS, "{0}.contains({1})");
        add(Ops.STRING_CONTAINS_IC, "{0l}.contains({1l})");
        add(Ops.STARTS_WITH, "{0}.startsWith({1})");
        add(Ops.STARTS_WITH_IC, "{0l}.startsWith({1l})");         
        add(Ops.INDEX_OF, "{0}.indexOf({1})");
        add(Ops.INDEX_OF_2ARGS, "{0}.indexOf({1},{2})");
        add(Ops.EQ_IGNORE_CASE, "{0}.equalsIgnoreCase({1})");
        add(Ops.ENDS_WITH, "{0}.endsWith({1})");
        add(Ops.ENDS_WITH_IC, "{0l}.endsWith({1l})");
        add(Ops.StringOps.SPLIT, "{0}.split({1})");
        add(Ops.StringOps.LAST_INDEX, "{0}.lastIndexOf({1})");
        add(Ops.StringOps.LAST_INDEX_2ARGS, "{0}.lastIndexOf({1},{2})");
        
        // Date and Time
        add(Ops.DateTimeOps.DAY_OF_MONTH, "{0}.getDayOfMonth()");
        add(Ops.DateTimeOps.DAY_OF_WEEK, "{0}.getDayOfWeek()");
        add(Ops.DateTimeOps.DAY_OF_YEAR, "{0}.getDayOfYear()");
        add(Ops.DateTimeOps.HOUR, "{0}.getHour()");
        add(Ops.DateTimeOps.MINUTE, "{0}.getMinute()");
        add(Ops.DateTimeOps.MONTH, "{0}.getMonth()");
        add(Ops.DateTimeOps.MILLISECOND, "{0}.getMilliSecond()");
        add(Ops.DateTimeOps.SECOND, "{0}.getSecond()");
        add(Ops.DateTimeOps.WEEK, "{0}.getWeek()");
        add(Ops.DateTimeOps.YEAR, "{0}.getYear()");
        add(Ops.DateTimeOps.YEAR_MONTH, "{0}.getYear() * 100 + {0}.getMonth()");
        
        // case
        add(Ops.CASE, "({0})");
        add(Ops.CASE_WHEN,  "({0}) ? ({1}) : ({2})");
        add(Ops.CASE_ELSE,  "{0}");

        // case eq
        add(Ops.CASE_EQ, "({0})");
        add(Ops.CASE_EQ_WHEN,  "({0} == {1}) ? ({2}) : ({3})");
        add(Ops.CASE_EQ_ELSE,  "{0}");

        
        // Math
        try {
            for (Field f : Ops.MathOps.class.getFields()) {
                OperatorImpl<?> op = (OperatorImpl<?>) f.get(null);
                add(op, "Math." + getTemplate(op));
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        add(Ops.MOD, "{0} % {0}");
        
    }

}
