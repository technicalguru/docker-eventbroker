#!/bin/bash

DIR=/var/www/jersey

FIRSTJAR=$(ls $DIR/eventbroker.jar)
CLASSPATH=$FIRSTJAR

for F in $DIR/dependency/*.jar
do
	CLASSPATH=$CLASSPATH:$F
done

/usr/local/bin/java -cp $CLASSPATH rs.eventbroker.Main

