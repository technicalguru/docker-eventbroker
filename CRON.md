# Cron Feature at EventBroker

## Overview
EventBroker has built in a timer that signals every minute on a specific topic. The event is signaled at the first second of a minute where the exact moment within this minute cannot be guaranteed.

## Topic
The topics is built as:

```
timer/min/40/hour/14/day/3/weekday/1/month/11
```

The numbers in this topic depend on the time of the signal. The signal above was created on Sunday, November 3rd, at 14:40. 

## Payload
The topic's payload will mention the timezone that this event was created for. It is the timezone of the EventBroker.

## How to subscribe
Subscription is handled as every other subscription of EventBroker, by either dynamically registering with the timer topic (via API call) or by manually entering the subscription into the subscription list (directly at database or via Admin UI).

## Example Event
```
GET /my/subscription HTTP/1.1
Host: my.client.com
Content-Type: application/json
Content-Length: 201

{
	"packetId"   : "78131jh12314o87z",
	"topicName"  : "timer/min/40/hour/14/day/3/weekday/1/month/11",
	"qos"        : 0,
	"payload"    : "Europe/Berlin",
	"retainFlag" : false,
	"dupFlag"    : false
}
```