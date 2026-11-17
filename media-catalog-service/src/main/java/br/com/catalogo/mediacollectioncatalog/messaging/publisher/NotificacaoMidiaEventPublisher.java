package br.com.catalogo.mediacollectioncatalog.messaging.publisher;

import br.com.catalogo.mediacollectioncatalog.messaging.config.RabbitMQConfig;
import br.com.catalogo.mediacollectioncatalog.messaging.event.NotificacaoCadastroMidia;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificacaoMidiaEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publicarNotificacao(NotificacaoCadastroMidia evento) {

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.NOTIFICATION_ROUTING_KEY,
                evento
        );
    }
}
