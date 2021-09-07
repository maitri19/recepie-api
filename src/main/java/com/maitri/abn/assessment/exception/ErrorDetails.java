package com.maitri.abn.assessment.exception;

import java.util.Date;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * To string.
 *
 * @return the java.lang. string
 */
@Data

/**
 * Instantiates a new error details.
 *
 * @param timestamp the timestamp
 * @param message the message
 * @param details the details
 */
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ErrorDetails {

	/** The timestamp. */
	private Date timestamp;

	/** The message. */
	private String message;

	/** The details. */
	private String details;
}