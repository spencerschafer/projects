#/bin/bash

echo "Saving ENVs to /kafka/config/server.properties"

cat /kafka/config/kafka-server.properties | sed \
	-e "s|{{BROKER_ID}}|$BROKER_ID|g" \
    -e "s|{{KAFKA_PORT}}|$KAFKA_PORT|g" \
    -e "s|{{EXTERNAL_PORT}}|$EXTERNAL_PORT|g" \
    -e "s|{{EXTERNAL_IP}}|$HOST_IP|g" \
    -e "s|{{INTERNAL_IP}}|$INTERNAL_IP|g" \
    -e "s|{{KAFKA_NUM_PARTITIONS}}|$KAFKA_NUM_PARTITIONS|g" \
    -e "s|{{ZOOKEEPER_CONNECTION_STRING}}|$ZOOKEEPER_CONNECTION_STRING|g" \
> /kafka/config/server.properties

if [ -z $KAFKA_JMX_OPTS ]; then
    KAFKA_JMX_OPTS="-Dcom.sun.management.jmxremote=true"
    KAFKA_JMX_OPTS="$KAFKA_JMX_OPTS -Dcom.sun.management.jmxremote.authenticate=false"
    KAFKA_JMX_OPTS="$KAFKA_JMX_OPTS -Dcom.sun.management.jmxremote.ssl=false"
    KAFKA_JMX_OPTS="$KAFKA_JMX_OPTS -Dcom.sun.management.jmxremote.rmi.port=$JMX_PORT"
    KAFKA_JMX_OPTS="$KAFKA_JMX_OPTS -Djava.rmi.server.hostname=${JAVA_RMI_SERVER_HOSTNAME:-$INTERNAL_IP} "
    export KAFKA_JMX_OPTS
fi

echo "Saving done"

echo "Starting kafka server with BROKER_ID=${BROKER_ID}"
/kafka/bin/kafka-server-start.sh /kafka/config/server.properties