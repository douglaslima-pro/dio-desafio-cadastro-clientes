package edu.douglaslima.cadastroclientes.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Nenhum cliente foi encontrado!")
public class ClienteNaoEncontradoException extends RuntimeException {

	public ClienteNaoEncontradoException() {
		
	}
	
	public ClienteNaoEncontradoException(String errorMessage) {
		super(errorMessage);
	}
	
	public ClienteNaoEncontradoException(String errorMessage, Object... params) {
		super(String.format(errorMessage, params));
	}

	public ClienteNaoEncontradoException(String errorMessage, Throwable cause) {
		super(errorMessage, cause);
	}

	public ClienteNaoEncontradoException(String errorMessage, Throwable cause, Object... params) {
		super(String.format(errorMessage, params), cause);
	}
	
}
