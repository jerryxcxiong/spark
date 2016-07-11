package com.bosscs.spark.commons.rdd;

import org.apache.spark.Partition;

/**
 * Created by Jerry Xiong on 18/12/15.
 */
public interface IHadoopPartition extends Partition {

    TokenRange splitWrapper();
}
