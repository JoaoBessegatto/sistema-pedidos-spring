package br.com.fatec.Produtos.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class EmailService {
    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String remetente;

    @Async
    public void enviarEmailTexto(String destinatario, String assunto, String mensagem){
        try {
            SimpleMailMessage smm = new SimpleMailMessage();
            smm.setFrom(remetente);
            smm.setTo(destinatario);
            smm.setSubject(assunto);
            smm.setText(mensagem);
            javaMailSender.send(smm);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
    @Async
    public void enviarEmailComTemplate(String destinatario, String assunto, String template, Map<String, Object> variaveis) throws MessagingException {
            Context context = new Context();
            context.setVariables(variaveis);
            String corpoHtml = templateEngine.process("email-template", context);


            MimeMessage mensagem = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensagem, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

            helper.setTo(destinatario);
            helper.setSubject(assunto);
            helper.setText(corpoHtml, true);

            javaMailSender.send(mensagem);
    }
}