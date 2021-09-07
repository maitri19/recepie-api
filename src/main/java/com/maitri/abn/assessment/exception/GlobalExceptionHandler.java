package com.maitri.abn.assessment.exception;

import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

// TODO: Auto-generated Javadoc
/**
 * The Class GlobalExceptionHandler is for exception handling.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * Resource not found exception.
	 *
	 * @param ex      the ex
	 * @param request the request
	 * @return the response entity
	 */
	@ExceptionHandler(RecipeNotFoundException.class)
	public ResponseEntity<ErrorDetails> resourceNotFoundException(RecipeNotFoundException ex, WebRequest request) {
		LOGGER.error(ex.getMessage(), ex);
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	/**
	 * Recipe attribute not found exception.
	 *
	 * @param ex      the ex
	 * @param request the request
	 * @return the response entity
	 */
	@ExceptionHandler(RecipeAttributeNotFoundException.class)
	public ResponseEntity<ErrorDetails> recipeAttributeNotFoundException(RecipeAttributeNotFoundException ex,
			WebRequest request) {
		LOGGER.error(ex.getMessage(), ex);
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	/**
	 * Handle constraint violation exception.
	 *
	 * @param exception the exception
	 * @param request   the request
	 * @return the response entity
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorDetails> handleConstraintViolationException(ConstraintViolationException exception,
			WebRequest request) {
		LOGGER.error(exception.getMessage(), exception);
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);

	}

	/**
	 * Handle constraint violation exception hibernate.
	 *
	 * @param exception the exception
	 * @param request   the request
	 * @return the response entity
	 */
	@ExceptionHandler(org.hibernate.exception.ConstraintViolationException.class)
	public ResponseEntity<ErrorDetails> handleConstraintViolationExceptionHibernate(
			org.hibernate.exception.ConstraintViolationException exception, WebRequest request) {
		LOGGER.error(exception.getMessage(), exception);
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);

	}

	/**
	 * Handle method argument not valid exception.
	 *
	 * @param exception the exception
	 * @param request   the request
	 * @return the response entity
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDetails> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception,
			WebRequest request) {
		LOGGER.error(exception.getMessage(), exception);
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);

	}

	/**
	 * Handle method argument type mismatch exception.
	 *
	 * @param exception the exception
	 * @param request   the request
	 * @return the response entity
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorDetails> handleMethodArgumentTypeMismatchException(
			MethodArgumentTypeMismatchException exception, WebRequest request) {
		LOGGER.error(exception.getMessage(), exception);
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);

	}

	/**
	 * Global excpetion handler.
	 *
	 * @param ex      the ex
	 * @param request the request
	 * @return the response entity
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> globalExcpetionHandler(Exception ex, WebRequest request) {
		LOGGER.error(ex.getMessage(), ex);
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}