package faddy.backend.email.service;

import faddy.backend.global.Utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

/**
 *  이메일 인증번호 전송 서비스를 제공
 */
@Service
public class MailSendService {

    @Autowired
    private JavaMailSender mailSender; // dafault injected bean is google mailsender

    @Autowired
    private RedisUtil redisUtil;

    private int authNumber;

    /**
     * 임의의 6자리 양수를 생성하고 저장한다.
     */
    public void makeRandomNumber() {
        Random r = new Random();
        String randomNumber = "";
        for(int i = 0; i < 6; i++) {
            randomNumber += Integer.toString(r.nextInt(10));
        }

        authNumber = Integer.parseInt(randomNumber);
    }

    /**
     * 사용자가 입력한 인증번호와 실제 인증 번호를 비교한다
     */
    public boolean CheckAuthNum(String email,String authNum){
        if(redisUtil.getData(authNum)==null){
            return false;
        }
        else if(redisUtil.getData(authNum).equals(email)){
            return true;
        }
        else{
            return false;
        }
    }


    /**
     * 회원 가입 인증 이메일을 생성하고 전송합니다.
     *
     * @param email 인증 이메일을 받을 사용자의 이메일 주소
     * @return 생성된 인증 번호
     */
    public String joinEmail(String email) {
        makeRandomNumber();
        String setFrom = "agh0314@gmail.com";
        String toMail = email;
        String title = "회원 가입 인증 이메일 입니다.";
        String content =
                "나의 APP을 방문해주셔서 감사합니다." +
                        "<br><br>" +
                        "인증 번호는 " + authNumber + "입니다." +
                        "<br>" +
                        "인증번호를 제대로 입력해주세요";

        mailSend(setFrom, toMail, title, content);
        return Integer.toString(authNumber);
    }

    /**
     * 이메일을 전송합니다.
     *
     * @param setFrom 발신자의 이메일 주소
     * @param toMail 수신자의 이메일 주소
     * @param title 이메일의 제목
     * @param content 이메일의 내용
     */
    public void mailSend(String setFrom, String toMail, String title, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
            helper.setFrom(setFrom);
            helper.setTo(toMail);
            helper.setSubject(title);
            helper.setText(content,true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
