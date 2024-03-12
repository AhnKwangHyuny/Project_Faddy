package faddy.backend.auth.handler;
import faddy.backend.api.Dto.ResponseDto;
import faddy.backend.global.Utils.FormatConverter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import java.io.IOException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.access.AccessDeniedException;
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setHeader("Content-Type", "applicaiton/json");

        response.getWriter().write(FormatConverter.toJson(
                ResponseDto.response(
                        "401",
                        "접근 권한이 없습니다."
                )
        ));

        response.getWriter().flush();
        response.getWriter().close();
    }
}


