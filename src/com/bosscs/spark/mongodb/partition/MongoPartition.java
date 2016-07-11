/*
 * Copyright 2016, Jerry Xiong, BOSSCS
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.bosscs.spark.mongodb.partition;

import com.bosscs.spark.commons.impl.HadoopPartition;
import com.bosscs.spark.commons.rdd.TokenRange;
import com.bosscs.spark.mongodb.utils.UtilMongoDB;

/**
 * Created by Jerry Xiong on 7/02/16.
 */
public class MongoPartition extends HadoopPartition {

    /**
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = 3740901434550932793L;

    /**
     * The Key.
     */
    private String key = UtilMongoDB.MONGO_DEFAULT_ID;

    /**
     * Instantiates a new Mongo partition.
     *
     * @param rddId the rdd id
     * @param idx   the idx
     * @param range the range
     * @param key   the key
     */
    public MongoPartition(int rddId, int idx, TokenRange range, String key) {
        super(rddId, idx, range);
        this.key = key;
    }

    /**
     * Gets key.
     *
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets key.
     *
     * @param key the key
     */
    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer(super.toString() + "MongoPartition{");
        sb.append("key='").append(key).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
