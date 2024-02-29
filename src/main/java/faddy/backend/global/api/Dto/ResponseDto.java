package faddy.backend.global.api.Dto;


import lombok.*;

import javax.xml.transform.Result;

@Getter
@Builder
@AllArgsConstructor
public class ResponseDto<T> {

    private String responseCode;
    private String responseMessage;
    private T data;

    public ResponseDto(final String responseCode, final String responseMessage){
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.data = null;
    }

    public static <T> ResponseDto<T> response(final String responseCode, final String responseMessage){
        return response(responseCode, responseMessage, null);
    }

    public static <T> ResponseDto<T> response(final String responseCode, final String responseMessage, final T data){
        return ResponseDto.<T>builder()
                .responseCode(responseCode)
                .responseMessage(responseMessage)
                .data(data)
                .build();
    }
}
