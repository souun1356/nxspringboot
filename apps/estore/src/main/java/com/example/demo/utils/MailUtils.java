package com.example.demo.utils;

import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtils {

    public static void sendMail(String email, String emailInfo)
            throws AddressException, MessagingException, GeneralSecurityException {
        Properties prop = new Properties();
        prop.put("mail.host", "smtp.163.com");
        prop.put("mail.transport.protocol", "smtp");
        prop.put("mail.smtp.auth", true);
        // 1.創建sesssion
        Session session = Session.getInstance(prop);
        // 開啟session的調試模式，可以查看當前郵件發送狀態
        session.setDebug(true);

        // 2.通過session獲取Transport對像（發送郵件的核心API）
        Transport ts = session.getTransport();
        // 3.通過郵件用戶名密碼（授權碼 ）鏈接
        ts.connect("17780713604@163.com", "hslzym1314");

        // 4.創建郵件
        Message msg = createSimpleMail(session, email, emailInfo);

        // 5.發送電子郵件
        ts.sendMessage(msg, msg.getAllRecipients());
    }

    private static Message createSimpleMail(Session session, String email, String emailInfo)
            throws AddressException, MessagingException {
        MimeMessage mm = new MimeMessage(session);
        // 設置發件人
        mm.setFrom(new InternetAddress("17780713604@163.com"));
        // 設置收件人
        mm.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
        mm.setSubject("測試郵件");
        mm.setContent(emailInfo, "text/html;charset=gbk");
        return mm;
    }
}
