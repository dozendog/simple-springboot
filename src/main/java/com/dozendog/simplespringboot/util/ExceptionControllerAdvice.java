package com.dozendog.simplespringboot.util;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionControllerAdvice {

	private final Logger logger = LogManager.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler(Exception ex) {
        logger.error(ExceptionUtils.getStackTrace(ex));
        return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
