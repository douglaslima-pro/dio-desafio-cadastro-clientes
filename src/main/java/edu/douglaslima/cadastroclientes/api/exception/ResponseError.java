package edu.douglaslima.cadastroclientes.api.exception;

import java.util.Date;

public class ResponseError {

	/**
	 * Data e hora do erro.
	 */
	private Date timestamp = new Date();
	/**
	 * Código de status HTTP da resposta.
	 */
	private int status;
	/**
	 * Identificador único do erro. Por exemplo: "error-0001"
	 */
	private String error;
	/**
	 * Uma mensagem curta que deve ser traduzida para o usuário de acordo com o
	 * cabeçalho <em>Accept-Language</em>.
	 */
	private String message;
	/**
	 * Instruções para os desenvolvedores da aplicação cliente.
	 */
	private String detail;
	/**
	 * Um <strong>link</strong> para um site que contenha mais informações sobre o erro.
	 */
	private String help;

	/**
	 * <p>
	 * Cria um objeto do tipo {@code ResponseError} a partir de um <strong>código de
	 * status</strong>, um <strong>identificador do erro</strong> e uma <strong>mensagem de erro</strong>.
	 * </p>
	 * 
	 * @param status código de status da resposta, pode ser obtido a partir do enum
	 *               {@code HttpStatus}
	 * @param error  identificador do erro
	 * @param message mensagem de erro
	 * @see <a href=
	 *      'https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/HttpStatus.html'>HttpStatus</a>
	 */
	public ResponseError(int status, String error, String message) {
		this.status = status;
		this.error = error;
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public String getDetail() {
		return detail;
	}

	public String getHelp() {
		return help;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public void setHelp(String help) {
		this.help = help;
	}

}
