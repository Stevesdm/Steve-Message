###启动rocketmq
./mqnamesrv
./mqbroker -n "localhost:9876"

启动broker的时候，添加参数autoCreateTopicEnable=true，自动创建没有的topic
