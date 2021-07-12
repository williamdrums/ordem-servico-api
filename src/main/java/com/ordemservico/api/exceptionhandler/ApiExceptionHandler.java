package com.ordemservico.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ordemservico.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request){
		var status = HttpStatus.BAD_REQUEST;
		
		var error = new ErrorException();
		error.setStatus(status.value());
		error.setTitulo(ex.getMessage());
		error.setDataHora(LocalDateTime.now());
		
		return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		var mensagemCampos =  new ArrayList<MensagemCampo>();

		for(ObjectError error : ex.getBindingResult().getAllErrors()) {

			String nome = ((FieldError)error).getField();
			
			//Locale esta pegando a linguagem local que esta sendo utilizada
			String  mensagem  = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			mensagemCampos.add(new MensagemCampo(nome, mensagem));

		}

		var error  = new  ErrorException();
		error.setStatus(status.value());
		error.setTitulo("Um ou mais campos estão inválidos."+
				"Faça o preenchimento correto e tente novamente");
		error.setDataHora(LocalDateTime.now());
		error.setCampos(mensagemCampos);


		return super.handleExceptionInternal(ex, error ,headers, status, request);
	}
}