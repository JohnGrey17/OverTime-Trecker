package com.example.OvertimeTracker.service.email;

import com.example.OvertimeTracker.exceptions.types.EmailSenderException;
import com.example.OvertimeTracker.service.codeGeneratorService.GeneratorCodeService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class EmailSenderServiceImpl implements EmailSenderService {

    private static final String FROM = "serhii.hainovskyi@vyriy.com";

    private final JavaMailSender mailSender;



    public void sentTempCode(String userEmail, String verificationCode) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom(FROM);
            helper.setTo(userEmail);
            helper.setSubject("Restore code");


            helper.setText(verificationCode, true);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new EmailSenderException("Email sending failed", e);
        } catch (Exception e) {
            throw new EmailSenderException("Unexpected error occurred", e);
        }
    }

}
