//package faddy.backend.Email.service;
//
//import faddy.backend.email.service.MailSendService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessagePreparator;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//
//
//public class MailSendServiceTest {
//
//    @Mock
//    private JavaMailSender mailSender;
//
//    @InjectMocks
//    private MailSendService mailSendService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void testJoinEmail() {
//        String email = "agh0314@gmail.com";
//
//        mailSendService.joinEmail(email);
//
//        verify(mailSender, times(1)).send(any(MimeMessagePreparator.class));
//    }
//}