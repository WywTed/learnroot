package com.huhusky.wechat.service;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.lang3.exception.ExceptionUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wuhuhu
 * @create 2017/5/18 14:18
 */
@Slf4j
public class WechatQueueService {


    private static LinkedBlockingQueue<Callable<Object>> taskQueue = new LinkedBlockingQueue<>();

    private static ExecutorService exec = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

    public static void exec(){
        while(true){
            Callable task;
            synchronized (taskQueue) {
                while (taskQueue.isEmpty()) {
                    try {
                        log.info("## 任务队列为空，等待中...");
                        taskQueue.wait();
                    } catch (InterruptedException e) {
                        log.error(ExceptionUtils.getStackTrace(e));
                    }
                }
                task = taskQueue.poll();
            }
            if(task != null){
                exec.submit(task);
                /*try {
                    Future ret = exec.submit(task);
                    if(ret != null && ret.get() != null){
                        log.info(ret.get().toString());
                    }
                } catch (ExecutionException e) {
                    log.error(ExceptionUtils.getStackTrace(e));
                } catch (InterruptedException e) {
                    log.error(ExceptionUtils.getStackTrace(e));
                }*/
            }
        }
    }


    public static void pushTask(Callable task){
        if(task != null){
            synchronized (taskQueue) {
                try {
                    taskQueue.put(task);
                    taskQueue.notifyAll();
                } catch(InterruptedException e){
                    log.error(ExceptionUtils.getStackTrace(e));
                }
            }
        }
    }
}
