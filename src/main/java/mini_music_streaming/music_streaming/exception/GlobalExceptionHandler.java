package mini_music_streaming.music_streaming.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler 
{
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse>
    handleNotFound(
            ResourceNotFoundException ex)
    {
        return new ResponseEntity<>(
                new ErrorResponse(
                        ex.getMessage(),
                        404),
                HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse>
    handleUserExists(
            UserAlreadyExistsException ex)
    {
        return new ResponseEntity<>(
                new ErrorResponse(
                        ex.getMessage(),
                        409),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(
            MethodArgumentNotValidException.class)

    public ResponseEntity<ErrorResponse>
    handleValidation(
            MethodArgumentNotValidException ex)
    {
        String message =
            ex.getBindingResult()
              .getFieldErrors()
              .stream()
              .findFirst()
              .map(error ->
                      error.getDefaultMessage())
              .orElse(
                      "Validation failed");
                      
        return new ResponseEntity<>(

                new ErrorResponse(
                        message,
                        400),

                HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse>
    handleGeneral(
            Exception ex)
    {
        return new ResponseEntity<>(
                new ErrorResponse(
                        ex.getMessage(),
                        500),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
