package br.com.catalogo.mediacollectioncatalog.messaging.consumer;

import br.com.catalogo.mediacollectioncatalog.messaging.config.RabbitMQConfig;
import br.com.catalogo.mediacollectioncatalog.messaging.event.MidiaCadastrada;
import br.com.catalogo.mediacollectioncatalog.messaging.history.HistoricoMidia;
import br.com.catalogo.mediacollectioncatalog.messaging.history.HistoricoMidiaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MidiaCadastradaConsumer {

    private final HistoricoMidiaRepository historicoMidiaRepository;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void consumir(MidiaCadastrada evento) {
        HistoricoMidia historico = new HistoricoMidia();

        historico.setMidiaId(evento.midiaId());
        historico.setUsuarioId(evento.usuarioId());
        historico.setDataHora(evento.dataHora());

        historicoMidiaRepository.save(historico);
    }
}