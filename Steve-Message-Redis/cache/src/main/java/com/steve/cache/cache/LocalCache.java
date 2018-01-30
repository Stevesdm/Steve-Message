package com.steve.cache.cache;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: stevejobson
 * @CreateDate: 2018/1/30 上午11:04
 */
@Component
public class LocalCache {

    Map<String,String> demoMap = Collections.synchronizedMap(new HashMap<>());

    public String getDemoInfo(String name){
        if (demoMap.containsKey(name)){
            return demoMap.get(name);
        }else{

            //从库中查找数据
            String value = "demo-steve";
            demoMap.put(name,value);
            return value;
        }
    }

    public void removeDemoCache(String key){
        demoMap.remove(key);
    }

    public void removeAllDemoCache(){
        demoMap.clear();
    }

    public void addLocalDemoCache(Map map){
        demoMap.putAll(map);
    }

}