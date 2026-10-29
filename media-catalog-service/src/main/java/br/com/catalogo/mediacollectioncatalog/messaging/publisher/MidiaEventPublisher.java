package br.com.catalogo.mediacollectioncatalog.messaging.publisher;

import br.com.catalogo.mediacollectioncatalog.messaging.config.RabbitMQConfig;
import br.com.catalogo.mediacollectioncatalog.messaging.event.MidiaCadastrada;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class MidiaEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publicarMidiaCadastrada(Long midiaId, Long usuarioId) {

        MidiaCadastrada evento = new MidiaCadastrada(
                midiaId,
                usuarioId,
                Instant.now()
        );

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.ROUTING_KEY,
                evento
        );
    }
}
