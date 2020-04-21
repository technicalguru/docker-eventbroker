FROM debian:10.2
LABEL maintainer="Ralph Schuster <github@ralph-schuster.eu>"

# setup environment
ENV JAVA_HOME /usr/local/java
ENV JAVA_VERSION 14
ENV JDK_TGZ_URL="http://download.ralph-schuster.eu/jdk/jdk${JAVA_VERSION}.tar.gz"

RUN apt-get update \
	&& apt-get install -y --no-install-recommends curl \
	&& rm -rf /var/lib/apt/lists/* 

RUN cd /usr/local \
	&& curl -kfSL "$JDK_TGZ_URL" -o jdk.tar.gz \
        && tar -xvf jdk.tar.gz \
        && rm jdk.tar.gz 

# Setup links in /usr/local/bin
RUN ln -s /usr/local/jdk-$JAVA_VERSION /usr/local/java; \
    ln -s /usr/local/jdk-$JAVA_VERSION /usr/local/java/jre; \
	cd /usr/local/java/jre/bin; \
	for f in *; \
	do \
		if [ -f "$f" ]; \
		then \
			ln -s /usr/local/java/jre/bin/$f /usr/local/bin/$f; \
		fi; \
	done; \
	cd /usr/local/java/bin; \
	for f in *; \
	do \
		if [ ! -f "/usr/local/bin/$f" ]; \
		then \
			ln -s /usr/local/java/bin/$f /usr/local/bin/$f; \
		fi; \
	done

RUN mkdir /var/www && mkdir /var/www/jersey && mkdir /var/www/jersey/dependency/
COPY target/eventbroker.jar /var/www/jersey/eventbroker.jar
COPY target/dependency/ /var/www/jersey/dependency/
COPY src/main/bin/ /var/www/jersey/
RUN chmod 777 /var/www/jersey/*.sh

########################
# OCI labels
########################
ARG ARG_CREATED
ARG ARG_URL=https://github.com/technicalguru/docker-eventbroker
ARG ARG_SOURCE=https://github.com/technicalguru/docker-eventbroker
ARG ARG_VERSION=1.0.1
ARG ARG_REVISION
ARG ARG_VENDOR=technicalguru
ARG ARG_TITLE=technicalguru/eventbroker
ARG ARG_DESCRIPTION="A MQTT-like event broker for synchronizing microservices"
ARG ARG_DOCUMENTATION=https://github.com/technicalguru/docker-eventbroker
ARG ARG_AUTHORS=technicalguru
ARG ARG_LICENSES=GPL-3.0-or-later

LABEL org.opencontainers.image.created=$ARG_CREATED
LABEL org.opencontainers.image.url=$ARG_URL
LABEL org.opencontainers.image.source=$ARG_SOURCE
LABEL org.opencontainers.image.version=$ARG_VERSION
LABEL org.opencontainers.image.revision=$ARG_REVISION
LABEL org.opencontainers.image.vendor=$ARG_VENDOR
LABEL org.opencontainers.image.title=$ARG_TITLE
LABEL org.opencontainers.image.description=$ARG_DESCRIPTION
LABEL org.opencontainers.image.authors=$ARG_AUTHORS
LABEL org.opencontainers.image.licenses=$ARG_LICENSES

########################
# Running the container
########################
EXPOSE 80
CMD ["/var/www/jersey/run.sh"]
