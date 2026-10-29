package br.com.catalogo.mediacollectioncatalog.messaging.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


    @Configuration
    public class RabbitMQConfig {

        public static final String EXCHANGE_NAME = "media.events";
        public static final String QUEUE_NAME = "media.cadastrada.queue";
        public static final String ROUTING_KEY = "media.cadastrada";

        @Bean
        public DirectExchange mediaExchange() {
            return new DirectExchange(EXCHANGE_NAME);
        }

        @Bean
        public Queue mediaCadastradaQueue() {
            return new Queue(QUEUE_NAME, true);
        }

        @Bean
        public Binding mediaCadastradaBinding(
                Queue mediaCadastradaQueue,
                DirectExchange mediaExchange) {

            return BindingBuilder
                    .bind(mediaCadastradaQueue)
                    .to(mediaExchange)
                    .with(ROUTING_KEY);
        }
    }
