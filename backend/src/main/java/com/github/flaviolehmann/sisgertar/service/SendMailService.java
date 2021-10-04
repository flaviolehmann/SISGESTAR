package com.github.flaviolehmann.sisgertar.service;

import com.github.flaviolehmann.sisgertar.service.dto.EmailDTO;
import com.github.flaviolehmann.sisgertar.service.error.EmailNaoEnviadoException;
import com.github.flaviolehmann.sisgertar.service.properties.MailProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Slf4j
@Service
@RequiredArgsConstructor
public class SendMailService {

    private final JavaMailSender javaMailSender;
    private final MailProperties properties;

    public void sendMail(EmailDTO emailDTO) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            createEmail(emailDTO, mimeMessage);
            this.javaMailSender.send(mimeMessage);
        } catch (UnsupportedEncodingException | MessagingException | RuntimeException e) {
            log.error("Falha ao enviar email {}.", emailDTO, e);
            throw new EmailNaoEnviadoException();
        }
    }

    private void createEmail(EmailDTO emailDTO, MimeMessage mimeMessage) throws MessagingException, UnsupportedEncodingException {
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        message.setTo(emailDTO.getDestinatario());
        message.setFrom(properties.getEnderecoRemetente(), properties.getNomeRemetente());
        message.setSubject(emailDTO.getAssunto());
        message.setText(emailDTO.getCorpo(), true);
    }
}
