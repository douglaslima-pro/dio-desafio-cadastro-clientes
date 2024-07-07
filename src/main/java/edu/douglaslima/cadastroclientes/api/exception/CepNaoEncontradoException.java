package edu.douglaslima.cadastroclientes.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Não foram encontrados dados do CEP informado!")
public class CepNaoEncontradoException extends RuntimeException {

	public CepNaoEncontradoException() {
		
	}
	
	public CepNaoEncontradoException(String errorMessage) {
		super(errorMessage);
	}
	
	public CepNaoEncontradoException(String errorMessage, Object... params) {
		super(String.format(errorMessage, params));
	}

	public CepNaoEncontradoException(String errorMessage, Throwable cause) {
		super(errorMessage, cause);
	}

	public CepNaoEncontradoException(String errorMessage, Throwable cause, Object... params) {
		super(String.format(errorMessage, params), cause);
	}
	
}
