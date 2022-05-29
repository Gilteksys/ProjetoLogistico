package com.projeto.log.logap.excepitionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

import com.projeto.log.logap.domain.exception.Exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@ControllerAdvice
public class AppExcecoes extends ResponseEntityExceptionHandler {
	
	private MessageSource messageSorce;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Problemas.Campo> campos = new ArrayList<>();
		
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField(); 
			String mensagem = messageSorce.getMessage(error, LocaleContextHolder.getLocale());
			
			campos.add(new Problemas.Campo(nome, mensagem));
		}
		
		Problemas problemas = new Problemas();
		problemas.setStatus(status.value());
		problemas.setDataHora(LocalDateTime.now());
		problemas.setTitulo("Preencha todos os campos e tente novamente");
		problemas.setCampos(campos);
		
		return handleExceptionInternal(ex,problemas, headers, status, request);
			
	}
	
	@ExceptionHandler(Exceptions.class)
	public ResponseEntity<Object> handleNegocio(Exceptions ex, WebRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Problemas problemas = new Problemas();
		problemas.setStatus(status.value());
		problemas.setDataHora(LocalDateTime.now());
		problemas.setTitulo(ex.getMessage());
				
		return handleExceptionInternal(ex, problemas, new HttpHeaders(), status, request);
		
	}
	

}
