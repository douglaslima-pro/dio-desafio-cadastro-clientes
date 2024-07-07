package edu.douglaslima.cadastroclientes.api.exception;

import java.util.Date;

public class ResponseError {

	private Date timestamp = new Date();
	private String status = "error";
	private int statusCode;
	private String error;

	/**
	 * <p>
	 * Cria um objeto do tipo {@code ResponseError} a partir de um
	 * <strong>statusCode</strong> e uma <strong>mensagem de erro</strong>.
	 * </p>
	 * 
	 * @param statusCode código de status da resposta, pode ser obtido a partir do
	 *                   enum {@code HttpStatus}
	 * @param error      mensagem de erro da resposta
	 * @see <a href=
	 *      'https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/HttpStatus.html'>HttpStatus</a>
	 */
	public ResponseError(int statusCode, String error) {
		this.statusCode = statusCode;
		this.error = error;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
