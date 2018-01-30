package com.steve.cache.cache;

/**
 * @Description:
 * @Author: stevejobson
 * @CreateDate: 2018/1/30 上午10:47
 */
public interface DataSyncEventHandler {

    /**
     * 返回对应的Redis Channel
     *
     * @return
     */
    public String getChannel();

    /**
     * 事件处理的具体逻辑
     *
     * @param object 从Redis事件广播接收的对象
     */
    public void handle(Object object);

}
