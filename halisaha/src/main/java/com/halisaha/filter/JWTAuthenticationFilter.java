package com.halisaha.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.halisaha.filter.TokenAuthenticationService;

public class JWTAuthenticationFilter extends GenericFilterBean{
	
	@Override
	public void doFilter(ServletRequest req,ServletResponse res,FilterChain chain) throws IOException, ServletException{
		Authentication authentication = TokenAuthenticationService.getAuthentication((HttpServletRequest) req);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}
	
}
