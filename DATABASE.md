# Database Setup and Configuration

## Database Setup

EventBroker requires two database tables: 

| Table Name | Description |
|------------|-------------|
|`eb_subscribers`| Lists all active subscriptions to topics |
|`eb_tokens`| Holds all authorization tokens |

You will find a [`create-tables.sql`](create-tables.sql) file that can be used in MySQL and MariaDB databases.

## Configuring Database Connection

The database configuration file can be set via environment variable `EB_DB_CONFIG`. EventBroker comes with
ready to use drivers for MySQL, MariaDB and HSQLDB. the src/main/resources directory holds some examples how to
use these drivers.

* [`dbconfig.xml`](src/main/resources/dbconfig.xml) - HSQLDB example
* [`dbconfig-mysql.xml`](src/main/resources/dbconfig-mysql.xml) - MySQL example
* [`dbconfig-mariadb.xml`](src/main/resources/dbconfig-mariadb.xml) - MariaDB example

Of course, you are free to use any JDBC compatible driver and database.