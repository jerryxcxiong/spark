/*
 * Copyright 2016, Jerry Xiong, BOSSCS
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bosscs.spark.jdbc.extractor;

import com.bosscs.spark.commons.exception.TransformException;
import com.bosscs.spark.jdbc.config.JdbcDeepJobConfig;
import com.bosscs.spark.jdbc.utils.UtilJdbc;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Implementation of JdbcNativeExtractor for Deep Entity objects.
 */
public class JdbcNativeEntityExtractor<T> extends JdbcNativeExtractor<T, JdbcDeepJobConfig<T>> {

    private static final long serialVersionUID = 957463022436044036L;

    /**
     * Instatiates a new entity extractor.
     * @param t Deep entity.
     */
    public JdbcNativeEntityExtractor(Class<T> t) {
        this.jdbcDeepJobConfig = new JdbcDeepJobConfig<>(t);
    }

    /**
     * Transforms a database row represented as a Map into a Deep Entity.
     * @param entity Database row represented as a Map of column_name:column_value.
     * @return Deep Entity carrying row data.
     */
    @Override
    protected T transformElement(Map<String, Object> entity) {
        try {
            return (T)UtilJdbc.getObjectFromRow(jdbcDeepJobConfig.getEntityClass(), entity, jdbcDeepJobConfig);
        } catch(IllegalAccessException | InvocationTargetException | InstantiationException e) {
            throw new TransformException(e);
        }
    }

    /**
     * Trasforms a Deep Entity into a database row represented as a Map.
     * @param entity Deep entity.
     * @return Database row represented as a Map of column_name:column_value.
     */
    @Override
    protected Map<String, Object> transformElement(T entity) {
        try {
            return UtilJdbc.getRowFromObject(entity);
        } catch(IllegalAccessException | InvocationTargetException | InstantiationException e) {
            throw new TransformException(e);
        }
    }

}
