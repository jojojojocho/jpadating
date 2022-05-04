package sajudating.jpadating.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sajudating.jpadating.apiResponse.exception.ErrorCode;

@AllArgsConstructor
@Getter
public class FileSaveException extends RuntimeException{
    private final ErrorCode errorCode;
}
