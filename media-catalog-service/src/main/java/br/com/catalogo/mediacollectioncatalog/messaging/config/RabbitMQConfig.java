package br.com.catalogo.mediacollectioncatalog.messaging.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


    @Configuration
    public class RabbitMQConfig {

        public static final String EXCHANGE_NAME = "media.events";

        // Fila do histórico
        public static final String QUEUE_NAME = "media.cadastrada.queue";
        public static final String ROUTING_KEY = "media.cadastrada";

        // Fila de notificações
        public static final String NOTIFICATION_QUEUE_NAME = "media.notification.queue";
        public static final String NOTIFICATION_ROUTING_KEY = "media.notification";

        @Bean
        public DirectExchange mediaExchange() {
            return new DirectExchange(EXCHANGE_NAME);
        }

        // Configuração da fila do histórico
        @Bean
        public Queue mediaCadastradaQueue() {
            return new Queue(QUEUE_NAME, true);
        }

        @Bean
        public Binding mediaCadastradaBinding(
                @Qualifier("mediaCadastradaQueue") Queue mediaCadastradaQueue,
                DirectExchange mediaExchange) {

            return BindingBuilder
                    .bind(mediaCadastradaQueue)
                    .to(mediaExchange)
                    .with(ROUTING_KEY);
        }

        // Configuração da fila de notificações
        @Bean
        public Queue mediaNotificationQueue() {
            return new Queue(NOTIFICATION_QUEUE_NAME, true);
        }

        @Bean
        public Binding mediaNotificationBinding(
                @Qualifier("mediaNotificationQueue") Queue mediaNotificationQueue,
                DirectExchange mediaExchange) {

            return BindingBuilder
                    .bind(mediaNotificationQueue)
                    .to(mediaExchange)
                    .with(NOTIFICATION_ROUTING_KEY);
        }


        @Bean
        public MessageConverter messageConverter() {
            return new Jackson2JsonMessageConverter();
        }
    }
