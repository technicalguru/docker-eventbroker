# DefaultApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**consume**](DefaultApi.md#consume) | **POST** /consume | Consume event
[**publish**](DefaultApi.md#publish) | **POST** /publish | Publish event
[**subscribe**](DefaultApi.md#subscribe) | **POST** /subscribe | Subscribe topic
[**unsubscribe**](DefaultApi.md#unsubscribe) | **POST** /unsubscribe | Unsubscribe topic


<a name="consume"></a>
# **consume**
> RestResultEventData consume(eventData)

Consume event

    Consume a MQTT event for testing purposes

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **eventData** | [**EventData**](..//Models/EventData.md)|  | [optional]

### Return type

[**RestResultEventData**](..//Models/RestResultEventData.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

<a name="publish"></a>
# **publish**
> RestResultPublishResultData publish(eventData)

Publish event

    Publish an MQTT event on a topic

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **eventData** | [**EventData**](..//Models/EventData.md)|  | [optional]

### Return type

[**RestResultPublishResultData**](..//Models/RestResultPublishResultData.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

<a name="subscribe"></a>
# **subscribe**
> RestResultSubscribeResultData subscribe(subscribeData)

Subscribe topic

    Subscribe to a topic

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **subscribeData** | [**SubscribeData**](..//Models/SubscribeData.md)|  | [optional]

### Return type

[**RestResultSubscribeResultData**](..//Models/RestResultSubscribeResultData.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

<a name="unsubscribe"></a>
# **unsubscribe**
> RestResultUnsubscribeResultData unsubscribe(unsubscribeData)

Unsubscribe topic

    Unsubscribe from a topic

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **unsubscribeData** | [**UnsubscribeData**](..//Models/UnsubscribeData.md)|  | [optional]

### Return type

[**RestResultUnsubscribeResultData**](..//Models/RestResultUnsubscribeResultData.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

