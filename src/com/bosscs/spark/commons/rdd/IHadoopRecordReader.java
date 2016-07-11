package com.bosscs.spark.commons.rdd;

import java.io.Serializable;

/**
 * Created by Jerry Xiong on 18/12/15.
 */
public interface IHadoopRecordReader<T> extends AutoCloseable  {

    boolean hasNext();

    T next();

}
