package com.demo.examples.eventhubtriggered;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

import java.util.*;

/**
 * Azure Functions with Event Hub trigger.
 */
public class EventHubTriggeredFunction {
    /**
     * This function will be invoked when an event is received from Event Hub.
     */
    @FunctionName("EventHubTriggeredFunction")
    public void run(
            @EventHubTrigger(name = "message", eventHubName = "EventHubName", connection = "EventHubConnectionString", consumerGroup = "$Default", cardinality = Cardinality.MANY) List<String> message,
            final ExecutionContext context,
            @BindingName("Properties") Map<String, Object> properties,
            @BindingName("SystemProperties") Map<String, Object> systemProperties
    ) {
        context.getLogger().info("Java Event Hub trigger function executed.");
        context.getLogger().info("Length: " + message.size());
        context.getLogger().info("Properties: " + properties);
        context.getLogger().info("SystemProperties: " + systemProperties);

        message.forEach(singleMessage -> context.getLogger().info(singleMessage));
    }
}
