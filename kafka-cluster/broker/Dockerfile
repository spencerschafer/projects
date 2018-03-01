FROM alpine:latest

# Default to UTF-8 file.encoding
ENV LANG C.UTF-8

# add a simple script that can auto-detect the appropriate JAVA_HOME value
# based on whether the JDK or only the JRE is installed
RUN { \
		echo '#!/bin/sh'; \
		echo 'set -e'; \
		echo; \
		echo 'dirname "$(dirname "$(readlink -f "$(which javac || which java)")")"'; \
	} > /usr/local/bin/docker-java-home \
	&& chmod +x /usr/local/bin/docker-java-home

ENV JAVA_HOME /usr/lib/jvm/java-1.8-openjdk/jre
ENV PATH $PATH:/usr/lib/jvm/java-1.8-openjdk/jre/bin:/usr/lib/jvm/java-1.8-openjdk/bin

ENV JAVA_VERSION 8u151
ENV JAVA_ALPINE_VERSION 8.151.12-r0

RUN set -x \ 
	&& apk add --no-cache openjdk8-jre="$JAVA_ALPINE_VERSION" \
	&& [ "$JAVA_HOME" = "$(docker-java-home)" ]

# /kafka dir that holds the bin, config and libs folder
RUN mkdir /kafka /data /logs

# Getting kafka tar
RUN wget "http://apache.is.co.za/kafka/1.0.0/kafka_2.11-1.0.0.tgz" -O /tmp/kafka.tgz

WORKDIR /tmp

RUN tar -xzf kafka.tgz && \
	rm -rf kafka.tgz

RUN mv kafka_2.11-1.0.0/bin /kafka/ && \
	mv kafka_2.11-1.0.0/libs /kafka/ && \
	mv kafka_2.11-1.0.0/config /kafka/

RUN rm -rf kafka_2.11-1.0.0

RUN apk update
RUN apk upgrade
RUN apk add bash

ENV BROKER_ID 0
ENV KAFKA_PORT 9092
ENV EXTERNAL_PORT 32400
ENV INTERNAL_IP kafka-broker0-svc
ENV KAFKA_NUM_PARTITIONS 1
#ENV ZOOKEEPER_CONNECTION_STRING

RUN rm -rf /kafka/config/server.properties

ADD config/server.properties /kafka/config/kafka-server.properties
ADD broker-start.sh /broker-start.sh
RUN chmod +x /broker-start.sh

# Set up a user to run Kafka
RUN adduser -D -u 1000 kafka && chown -R kafka:kafka /kafka /data /logs
USER kafka
WORKDIR /kafka

VOLUME ["/data", "/logs"]

CMD "/bin/bash" "/broker-start.sh"
