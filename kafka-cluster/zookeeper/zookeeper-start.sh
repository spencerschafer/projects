#!/bin/bash

# replacing files values with ENV variables
cat /zookeeper/config/kafka-zookeeper.properties | sed \
	-e "s|{{MAX_CLIENT_CONNECTIONS}}|$MAX_CLIENT_CONNECTIONS|g" \
> /zookeeper/config/zookeeper.properties

# modifying zookeeper.properties
if [ "$ZOOKEEPER_ID" == "1" ]; then
	sed -i -e  "s/zookeeper-svc-1/0.0.0.0/g" /zookeeper/config/zookeeper.properties
elif [ "$ZOOKEEPER_ID" == "2" ]; then
	sed -i -e  "s/zookeeper-svc-2/0.0.0.0/g" /zookeeper/config/zookeeper.properties
elif [ "$ZOOKEEPER_ID" == "3" ]; then
	sed -i -e  "s/zookeeper-svc-3/0.0.0.0/g" /zookeeper/config/zookeeper.properties
fi

# creating directory
echo $ZOOKEEPER_ID > /zookeeper/data/myid

# starting zookeeper
echo "[Starting Zookeeper server]"
/zookeeper/bin/zookeeper-server-start.sh /zookeeper/config/zookeeper.properties
