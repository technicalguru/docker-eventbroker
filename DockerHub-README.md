# Supported tags and respective `Dockerfile` links

* [`1.1.0`](https://github.com/technicalguru/docker-eventbroker/blob/v1.1.0/Dockerfile), [`latest`](https://github.com/technicalguru/docker-eventbroker/blob/v1.1.0/Dockerfile)
* [`1.0.0`](https://github.com/technicalguru/docker-eventbroker/blob/v1.0.0/Dockerfile), [`latest`](https://github.com/technicalguru/docker-eventbroker/blob/v1.0.0/Dockerfile)

# What is EventBroker?
_EventBroker_  is a MQTT-alike message broker that enables losely coupled microservices. The broker connects to a JDBC data source in order to configure the messaging rules.

# Features
* Clients can publish MQTT-like topics and subscribe to them using wildcards
* API protection via Authorization Bearer token (see Issues below)
* Provides cron-like topics in order to trigger subscribers regulary
* Fail-safe and threaded topic distribution (subscribers can fail or not be available at all)

# License
_EventBroker_  is licensed under [GNU LGPL 3.0](https://github.com/technicalguru/docker-eventbroker/LICENSE.md).

As with all Docker images, these likely also contain other software which may be under other licenses (such as Bash, etc from the base distribution, along with any direct or indirect dependencies of the primary software being contained).

As for any pre-built image usage, it is the image user's responsibility to ensure that any use of this image complies with any relevant licenses for all software contained within.

# Usage
Issue the following command to run  _EventBroker_  in your Docker environment:

```
docker run -ti technicalguru/eventbroker  
```

This will run  _EventBroker_  on port 80 with a HSQLDB as storage backend. Several features can be configured:

* Set environment variable `EB_DB_CONFIG` to use your own database backend. See examples at [GitHub](https://github.com/technicalguru/docker-eventbroker/src/main/resources). Do not forget to mount your config XML file into the docker container.
* Set the listening port by running the command `/var/www/jersey/run.sh <port>`. 
* Set the specific listening interface by running the command `/var/www/jersey/run.sh <port> <ip-address>`
* Set environment variable `EB_SECURE_TOKEN` to a specific string in order to protect your API by a Bearer token.

# Documentation

More documentation is available at [GitHub]((https://github.com/technicalguru/docker-eventbroker).

# Issues
* MQTT feature `qos`, `retain` and `dup` are not processed - just forwarded.
* API authorization imitates OAuth token by checking the token against a fix environment variable. This token can not be limited to certain topics, clients or privileges (see [`EB-4`](https://jira.ralph-schuster.eu/browse/EB-4)).
* Shutdown can take up to 60 secs.
* Only HTTP is supported.

# Contribution
Report a bug or request an enhancement at the [JIRA Issue Tracker](https://jira.ralph-schuster.eu:8443/projects/EB/summary).
