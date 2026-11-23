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

        StringBuilder texto = new StringBuilder();

        texto.append("Olá!\n\n");
        texto.append("Sua mídia foi cadastrada com sucesso no Media Collection Catalog.\n\n");
        texto.append("Detalhes do cadastro:\n");
        texto.append("Título: ").append(notificacao.titulo()).append("\n");
        texto.append("Tipo de mídia: ").append(notificacao.tipoMidia()).append("\n");
        texto.append("Ano de lançamento: ").append(notificacao.anoLancamento()).append("\n");

        notificacao.detalhes().forEach((chave, valor) ->
                texto.append(chave)
                        .append(": ")
                        .append(valor)
                        .append("\n")
        );

        texto.append("Data do cadastro: ").append(notificacao.dataCadastro()).append("\n\n");
        texto.append("Obrigado por utilizar nossa aplicação!");

        mensagem.setText(texto.toString());

        mailSender.send(mensagem);
    }
}
