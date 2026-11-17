package br.com.catalogo.mediacollectioncatalog.messaging.consumer;

import br.com.catalogo.mediacollectioncatalog.messaging.config.RabbitMQConfig;
import br.com.catalogo.mediacollectioncatalog.messaging.event.NotificacaoCadastroMidia;
import br.com.catalogo.mediacollectioncatalog.messaging.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificacaoMidiaEventConsumer {

    private final EmailService emailService;

    @RabbitListener(queues = RabbitMQConfig.NOTIFICATION_QUEUE_NAME)
    public void consumirNotificacao(NotificacaoCadastroMidia notificacao) {

        log.info("Notificação de cadastro recebida!");
        log.info("Destinatário: {}", notificacao.email());
        log.info("Mídia: {}", notificacao.titulo());
        log.info("Tipo de mídia: {}", notificacao.tipoMidia());
        log.info("Ano de lançamento: {}", notificacao.anoLancamento());
        log.info("Artista: {}", notificacao.artista());
        log.info("Formato do disco: {}", notificacao.formatoDisco());
        log.info("Tipo: {}", notificacao.tipo());
        log.info("Data do cadastro: {}", notificacao.dataCadastro());

        emailService.enviarNotificacao(notificacao);

        log.info("E-mail de confirmação enviado para: {}", notificacao.email());
    }
}
