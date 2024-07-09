package edu.douglaslima.cadastroclientes.api.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import edu.douglaslima.cadastroclientes.api.exception.*;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	private HttpHeaders header() {
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		return header;
	}
	
	@ExceptionHandler(ClienteNaoEncontradoException.class)
	public ResponseEntity<Object> handleClienteNaoEncontradoException(ClienteNaoEncontradoException exception, WebRequest request) {
		ResponseError responseBody = new ResponseError(HttpStatus.NOT_FOUND.value(), "Cliente não encontrado", exception.getMessage());
		return super.handleExceptionInternal(exception, responseBody, this.header(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(CepNaoEncontradoException.class)
	public ResponseEntity<Object> handleCepNaoEncontradoException(CepNaoEncontradoException exception, WebRequest request) {
		ResponseError responseBody = new ResponseError(HttpStatus.NOT_FOUND.value(), "CEP não encontrado", exception.getMessage());
		return super.handleExceptionInternal(exception, responseBody, this.header(), HttpStatus.NOT_FOUND, request);
	}
	
}
