package br.com.fatec.Produtos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    JavaMailSender javaMailSender;

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
}