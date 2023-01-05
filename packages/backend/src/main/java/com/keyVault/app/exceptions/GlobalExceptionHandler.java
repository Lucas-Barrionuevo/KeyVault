package com.keyVault.app.exceptions;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.keyVault.app.dto.ErrorDetails;

@ControllerAdvice
public class GlobalExceptionHandler  {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> manejarResourceNotFoundException(ResourceNotFoundException exception,WebRequest webRequest){
		ErrorDetails errorDetails = new ErrorDetails(new Date(),exception.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(KeyVaultAppException.class)
	public ResponseEntity<ErrorDetails> manejarBlogAppException(KeyVaultAppException exception,WebRequest webRequest){
		ErrorDetails errorDetails = new ErrorDetails(new Date(),exception.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> manejarGlobalException(Exception exception,WebRequest webRequest){
		ErrorDetails errorDetails = new ErrorDetails(new Date(),exception.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError)error).getField();
			String message = error.getDefaultMessage();
			
			errors.put(fieldName, message);
		});
		
		return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
	}
}
/*@RestControllerAdvice
public class ValidController {
	
	public Map<String, String>handleValidateExceptions(MethodArgumentNotValidException ex){
		Map<String, String> errors = new HashMap<String, String>();
		ex.getBindingResult().getAllErrors().forEach((error)-> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			
			errors.put(fieldName, message);
		});
		return errors;
	}
}*/

