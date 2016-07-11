package com.bosscs.spark.commons.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.JobID;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.TaskAttemptID;
import org.apache.hadoop.mapreduce.TaskType;
import org.apache.log4j.Logger;

import com.bosscs.spark.commons.exception.HadoopInstantiationException;

/**
 * Created by Jerry Xiong on 22/12/15.
 */
public class SparkHadoopMapReduceUtil {

    private static final Logger LOG = Logger.getLogger(SparkHadoopMapReduceUtil.class);

    public static JobContext newJobContext(Configuration conf, JobID jobId) {
        try {
            Class clazz = firstAvailableClass(
                    "org.apache.hadoop.mapreduce.task.JobContextImpl",  // hadoop2, hadoop2-yarn
                    "org.apache.hadoop.mapreduce.JobContext");           // hadoop1

            Constructor constructor = clazz.getDeclaredConstructor(Configuration.class, JobID.class);
            return (JobContext) constructor.newInstance(conf, jobId);
            
        } catch (NoSuchMethodException | IllegalAccessException 
                | InvocationTargetException | InstantiationException e) {
            LOG.error("to use hadoop classes, we need them in the classpath " + e.getMessage());
            throw new HadoopInstantiationException(
                    "to use hadoop classes, we need them in the classpath  " + e.getMessage());
        } 
    }

    public static TaskAttemptContext newTaskAttemptContext(Configuration conf, TaskAttemptID attemptId) {
        try {
            Class clazz = firstAvailableClass(
                    "org.apache.hadoop.mapreduce.task.TaskAttemptContextImpl",  // hadoop2, hadoop2-yarn
                    "org.apache.hadoop.mapreduce.TaskAttemptContext");           // hadoop1
            Constructor constructor = clazz.getDeclaredConstructor(Configuration.class, TaskAttemptID.class);
            return (TaskAttemptContext) constructor.newInstance(conf, attemptId);

        } catch (NoSuchMethodException | InstantiationException 
                | IllegalAccessException | InvocationTargetException e) {
            LOG.error("to use hadoop classes, we need them in the classpath " + e.getMessage());
            throw new HadoopInstantiationException(
                    "to use hadoop classes, we need them in the classpath  " + e.getMessage());
        }
    }

    public static TaskAttemptID newTaskAttemptID(String jtIdentifier, int jobId, boolean isMap, int taskId,
                                                 int attemptId) {
        Class clazz = null;
        try {
            clazz = Class.forName("org.apache.hadoop.mapreduce.TaskAttemptID");
            Constructor constructor = clazz
                    .getDeclaredConstructor(String.class, int.class, boolean.class, int.class, int.class);
            return (TaskAttemptID) constructor.newInstance(jtIdentifier, jobId, isMap, taskId, attemptId);
        } catch (ClassNotFoundException | NoSuchMethodException 
                | InstantiationException | IllegalAccessException | InvocationTargetException e) {

            try {

                // If that failed, look for the new constructor that takes a TaskType (not available in 1.x)
                //                    Class taskTypeClass = Class.forName("org.apache.hadoop.mapreduce.TaskType");
                //
                //                    Method taskType = taskTypeClass.getMethod("valueOf", String.class);

                TaskType type = null;
                if (isMap) {
                    type = TaskType.MAP;
                    //                        taskType.invoke(taskTypeClass, "MAP");
                } else {
                    type = TaskType.REDUCE;
                    //                        taskType.invoke(taskTypeClass, "REDUCE");
                }

                Constructor constructor = clazz.getDeclaredConstructor(String.class, int.class, TaskType.class,
                        int.class, int.class);
                return (TaskAttemptID) constructor.newInstance(jtIdentifier, jobId, type, taskId,
                        attemptId);
            } catch (NoSuchMethodException | InstantiationException 
                    | IllegalAccessException | InvocationTargetException e1) {
                LOG.error("to use hadoop classes, we need them in the classpath " + e.getMessage());
                throw new HadoopInstantiationException(
                        "to use hadoop classes, we need them in the classpath  " + e1.getMessage());
            }
        }
    }

    private static Class firstAvailableClass(String first, String second) {

        try {
            return Class.forName(first);
        } catch (ClassNotFoundException e) {
            try {
                return Class.forName(second);
            } catch (ClassNotFoundException e1) {
                return null;
            }
        }
    }

}
