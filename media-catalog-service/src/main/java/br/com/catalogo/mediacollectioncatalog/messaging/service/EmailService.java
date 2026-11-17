package br.com.catalogo.mediacollectioncatalog.messaging.service;

import br.com.catalogo.mediacollectioncatalog.messaging.event.NotificacaoCadastroMidia;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void enviarNotificacao(NotificacaoCadastroMidia notificacao) {

        SimpleMailMessage mensagem = new SimpleMailMessage();

        mensagem.setTo(notificacao.email());
        mensagem.setSubject("Cadastro de mídia realizado com sucesso");

        mensagem.setText(
                "Olá!\n\n" +
                        "Sua mídia foi cadastrada com sucesso no Media Collection Catalog.\n\n" +
                        "Detalhes do cadastro:\n" +
                        "Título: " + notificacao.titulo() + "\n" +
                        "Tipo de mídia: " + notificacao.tipoMidia() + "\n" +
                        "Ano de lançamento: " + notificacao.anoLancamento() + "\n" +
                        "Artista: " + notificacao.artista() + "\n" +
                        "Formato do disco: " + notificacao.formatoDisco() + "\n" +
                        "Tipo: " + notificacao.tipo() + "\n" +
                        "Data do cadastro: " + notificacao.dataCadastro() + "\n\n" +
                        "Obrigado por utilizar nossa aplicação!"
        );

        mailSender.send(mensagem);
    }
}
