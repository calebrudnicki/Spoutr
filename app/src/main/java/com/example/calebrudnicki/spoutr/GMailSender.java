package com.example.calebrudnicki.spoutr;

/**
 * Created by Jack on 4/18/2017.
 */

import android.util.Log;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Security;
import java.util.Properties;
import android.os.AsyncTask;

public class GMailSender extends javax.mail.Authenticator {
    private String mailhost = "smtp.gmail.com";
    private String user;
    private String password;
    private Session session;

    static {
        Security.addProvider(new com.example.calebrudnicki.spoutr.JSSEProvider());
    }

    public GMailSender(String user, String password) {
        this.user = user;
        this.password = password;

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", mailhost);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.quitwait", "false");

        session = Session.getDefaultInstance(props, this);
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(user, password);
    }

    public synchronized void sendMail(String subject, String body, String sender, String recipients) throws Exception {
        final String sender1 = sender;
        final String body1 = body;
        final String subject1 = subject;
        final String recipients1 = recipients;
        new AsyncTask<Void, Void, Void>() {
            @Override
            public Void doInBackground(Void... arg) {
                try {
                    MimeMessage message = new MimeMessage(session);
                    DataHandler handler = new DataHandler(new ByteArrayDataSource(body1.getBytes(), "text/plain"));
                    message.setSender(new InternetAddress(sender1));
                    message.setSubject(subject1);
                    message.setDataHandler(handler);
                    if (recipients1.indexOf(',') > 0)
                        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients1));
                    else
                        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients1));
                    Transport.send(message);
                } catch (Exception e) {

                }
                return null;
            }
        }.execute();
    }

    public class ByteArrayDataSource implements DataSource {
        private byte[] data;
        private String type;

        public ByteArrayDataSource(byte[] data, String type) {
            super();
            this.data = data;
            this.type = type;
        }

        public ByteArrayDataSource(byte[] data) {
            super();
            this.data = data;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getContentType() {
            if (type == null)
                return "application/octet-stream";
            else
                return type;
        }

        public InputStream getInputStream() throws IOException {
            return new ByteArrayInputStream(data);
        }

        public String getName() {
            return "ByteArrayDataSource";
        }

        public OutputStream getOutputStream() throws IOException {
            throw new IOException("Not Supported");
        }
    }
}