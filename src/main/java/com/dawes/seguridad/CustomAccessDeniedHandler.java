package com.dawes.seguridad;

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAccessDeniedHandler extends AccessDeniedHandlerImpl  {
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exc) throws IOException {
        response.sendRedirect("/403");
    }

}
