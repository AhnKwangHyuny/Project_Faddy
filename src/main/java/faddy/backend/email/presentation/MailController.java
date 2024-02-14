package faddy.backend.email.presentation;


import faddy.backend.email.dto.EmailCheckDto;
import faddy.backend.email.service.MailSendService;
import faddy.backend.user.dto.request.EmailRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;


@RestController
@RequiredArgsConstructor
public class MailController {

    private final MailSendService mailSendService;


    @PostMapping("/mailSend")
    public String mailSend(@RequestBody @Valid EmailRequestDto emailDto) {
        System.out.println("이메일 인증 요청이 들어옴");
        System.out.println("이메일 인증 이메일 :" + emailDto.getEmail());
        return mailSendService.joinEmail(emailDto.getEmail());
    }

    @PostMapping("/mailauthCheck")
    public String AuthCheck(@RequestBody @Valid EmailCheckDto emailCheckDto){
        Boolean Checked=mailSendService.CheckAuthNum(emailCheckDto.getEmail(),emailCheckDto.getAuthNum());
        if(Checked){
            return "ok";
        }
        else{
            throw new NullPointerException("뭔가 잘못!");
        }
    }
}