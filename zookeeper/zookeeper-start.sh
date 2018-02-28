#!/bin/bash

# replacing files values with ENV variables
echo "[Saving ENVs to /kafka/config/zookeeper.properties]"
cat /zookeeper/config/kafka-zookeeper.properties | sed \
	-e "s|{{MAX_CLIENT_CONNECTIONS}}|$MAX_CLIENT_CONNECTIONS|g" \
> /zookeeper/config/zookeeper.properties
echo "[Saving done]"

# creating directory
echo $ZOOKEEPER_ID > /zookeeper/data/myid

# starting zookeeper
echo "[Starting Zookeeper server]"
/zookeeper/bin/zookeeper-server-start.sh /zookeeper/config/zookeeper.properties
