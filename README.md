# EventBroker

## Overview
_EventBroker_  is a MQTT-alike message broker that enables losely coupled microservices. The broker connects to a JDBC data source in order to configure the messaging rules.

## Features
* Clients can publish MQTT-like topics and subscribe to them using wildcards
* API protection via Authorization Bearer token (see Issues below)
* Provides cron-like topics in order to trigger subscribers regulary
* Fail-safe and threaded topic distribution (subscribers can fail or not be available at all)

## Project Status
_EventBroker_  is stable and used in a few production environments.

## License
_EventBroker_  is licensed under [GNU LGPL 3.0](LICENSE.md).

## Download
_EventBroker_  is available as a Docker image at [DockerHub](https://hub.docker.com/r/technicalguru/eventbroker) and in Maven repositories. The current version is v1.0.

```
<dependency>
  <groupId>eu.ralph-schuster</groupId>
  <artifactId>eventbroker</artifactId>
  <version>1.0.0</version>
</dependency>
```

## Running the Docker Image
To run  _EventBroker_  in your environment, start it in Docker:

```
docker run --env EB_SECURE_TOKEN=my-secure-token technicalguru/eventbroker /var/www/jersey/run.sh 
```

This will run  _EventBroker_  on port 80 with a HSQLDB as storage backend. Several features can be configured:

* Set environment variable `EB_DB_CONFIG` to use your own database backend. See examples at [src/main/resources](src/main/resources). Do not forget to mount your config XML file into the docker container.
* Set the listening port by running `/var/www/jersey/run.sh <port>`. 
* Set the specific listening interface by running `/var/www/jersey/run.sh <port> <ip-address>`

## Documentation

More documentation is available here:
* [Source Code](https://github.com/technicalguru/docker-eventbroker)
* [API Documentation](https://download.ralph-schuster.eu/eu.ralph-schuster.eventbroker/1.0.0/apidocs)
* [Maven Site](https://download.ralph-schuster.eu/eu.ralph-schuster.eventbroker/1.0.0/)
* [Change Log](CHANGES.md)

## Issues
* MQTT feature "qos", "retain" and "dup" are not processed - just forwarded.
* API authorization imitates OAuth token by checking the token against a fix environment variable. This token can not be limited to certain topics, clients or privileges (see [EB-4](https://jira.ralph-schuster.eu/browse/EB-4)).
* Shutdown can take up to 60 secs.
* Only HTTP is supported.
* Cron-feature not yet documented (see [EB-3](https://jira.ralph-schuster.eu/browse/EB-3)). Refer to the [source code](https://github.com/technicalguru/docker-eventbroker/blob/master/src/main/java/rs/eventbroker/queue/TimerSignaling.java) meanwhile.
* The database structure is not yet documented (see [EB-5](https://jira.ralph-schuster.eu/browse/EB-5)). Please refer to [Hibernate configuration](https://github.com/technicalguru/docker-eventbroker/tree/master/src/main/resources/hbm) and [Subscriber source code](https://github.com/technicalguru/docker-eventbroker/blob/master/src/main/java/rs/eventbroker/db/subscriber/) meanwhile.

## Contribution
Report a bug or request an enhancement at the [JIRA Issue Tracker](https://jira.ralph-schuster.eu/projects/EB/summary).
