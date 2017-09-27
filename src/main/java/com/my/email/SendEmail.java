package com.my.email;



import javax.mail.Transport;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;


/**
 * Created by Administrator on 2017/2/16.
 */

public class SendEmail {
    /*qq邮箱*/
   /* public static final String HOST = "smtp.qq.com";
    public static final String PROTOCOL = "smtp";
    public static final int PORT = 465;
    public static final String FROM = "741940091@qq.com";//发件人的email
    public static final String PWD = "tlgymddcxyrjbfei";//发件人密码*/
    /*163*/
    public static final String HOST;
    public static final String PROTOCOL;
    public static final int PORT;
    public static final String FROM;//发件人的email
    public static final String PWD;//发件人密码
    static {
        ResourceBundle resource = ResourceBundle.getBundle("email");
        HOST = resource.getString("HOST");
        PROTOCOL = resource.getString("PROTOCOL");
        PORT = Integer.valueOf(resource.getString("PORT")) ;
        FROM = resource.getString("FROM");
        PWD = resource.getString("PWD");
    }



    /**
     * 获取Session
     * @return
     */
    private static Session getSession() {
        Properties props = new Properties();
        props.put("mail.smtp.host", HOST);//设置服务器地址
        props.put("mail.store.protocol" , PROTOCOL);//设置协议
        props.put("mail.smtp.port", PORT);//设置端口
        props.put("mail.smtp.auth" , true);

        Authenticator authenticator = new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, PWD);
            }

        };
        Session session = Session.getDefaultInstance(props , authenticator);

        return session;
    }

    public static void send(String toEmail , String content) {
        Session session = getSession();
        try {
            System.out.println("--send--"+content);
            // Instantiate a message
            Message msg = new MimeMessage(session);

            //Set message attributes
            msg.setFrom(new InternetAddress(FROM));
            InternetAddress[] address = {new InternetAddress(toEmail)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject("账号激活邮件");
            msg.setSentDate(new Date());
            msg.setContent(content , "text/html;charset=utf-8");

            //Send the message
            Transport.send(msg);
        }
        catch (Exception mex) {
            mex.printStackTrace();
        }
    }
}
