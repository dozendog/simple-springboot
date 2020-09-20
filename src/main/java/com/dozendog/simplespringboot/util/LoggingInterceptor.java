package com.dozendog.simplespringboot.util;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoggingInterceptor extends HandlerInterceptorAdapter {
	
	private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        // Read inputStream from requestCacheWrapperObject and log it
        HttpServletRequest requestCacheWrapperObject = new ContentCachingRequestWrapper(request);
        HandlerMethod convertHandler = (HandlerMethod) handler;
        logger.info("==================== " + convertHandler.getMethod().getName() + "==================== ");
        logger.info("Address: " + requestCacheWrapperObject.getRequestURI());
        logger.info("Request URL: "+ requestCacheWrapperObject.getMethod() + " " + request.getRequestURL().toString());
     
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    	super.afterCompletion(request, response, handler, ex);
    
    }
}
