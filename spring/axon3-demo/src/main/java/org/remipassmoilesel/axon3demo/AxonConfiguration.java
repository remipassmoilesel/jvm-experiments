package org.remipassmoilesel.axon3demo;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.config.DefaultConfigurer;
import org.axonframework.config.EventHandlingConfiguration;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.remipassmoilesel.axon3demo.axon3.MessageEventHandler;
import org.remipassmoilesel.axon3demo.axon3.MessageEventHandler2;
import org.remipassmoilesel.axon3demo.axon3.MessagesAggregate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

@Configuration
public class AxonConfiguration {

    private static final Logger logger = Logger.getLogger(AxonConfiguration.class.getName());

    //
    // WARNING: event stores are event bus now, and we must take care that only one class
    // is used as bus (only one bean)
    //

    @Bean
    public EventStore eventStore(EmbeddedEventStore embeddedEventStore) {
        logger.severe("Instantiating InMemoryEventStorageEngine");
        return embeddedEventStore;
    }

    @Bean
    public EventBus eventBus(EmbeddedEventStore embeddedEventStore) {
        logger.severe("Instantiating InMemoryEventStorageEngine");
        return embeddedEventStore;
    }

    @Bean
    public CommandGateway commandGateway(CommandBus commandBus) {
        logger.severe("Instantiating CommandGateway");
        return new DefaultCommandGateway(commandBus);
    }

    @Bean
    public EmbeddedEventStore embeddedEventStore(){
        return new EmbeddedEventStore(new InMemoryEventStorageEngine());
    }

//    // Manual configuration
//    @Bean
//    public org.axonframework.config.Configuration configuration(EventStore eventStore) {
//        logger.severe("Configuring axon");
//
//        EventHandlingConfiguration ehConfiguration = new EventHandlingConfiguration()
//                .registerEventHandler(conf -> new MessageEventHandler())
//                .registerEventHandler(conf -> new MessageEventHandler2());
//
//        org.axonframework.config.Configuration configuration = DefaultConfigurer.defaultConfiguration()
//                .configureEventStore(conf -> eventStore)
//                .configureAggregate(MessagesAggregate.class)
//                .registerModule(ehConfiguration)
//                .buildConfiguration();
//
//        configuration.start();
//        return configuration;
//    }

}
