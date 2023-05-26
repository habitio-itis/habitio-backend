package ru.itis.habitio.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import ru.itis.habitio.exception.AuthorizationException;
import ru.itis.habitio.exception.NotFoundException;
import ru.itis.habitio.service.UserService;
import ru.itis.habitio.service.jwt.JwtTokenService;

import java.io.IOException;

public class JwtAuthenticationFilter extends RequestHeaderAuthenticationFilter {

    private final JwtTokenService jwtTokenService;

    private final UserService userService;


    public JwtAuthenticationFilter(JwtTokenService jwtTokenService, UserService userService, AuthenticationManager authenticationManager) {
        this.jwtTokenService = jwtTokenService;
        this.userService = userService;
        this.setAuthenticationManager(authenticationManager);
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        var request = (HttpServletRequest) servletRequest;
        var response = (HttpServletResponse) servletResponse;
        var header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
        } else {
            try {
                var token = header.split(" ")[1].trim();
                var subject = jwtTokenService.validateAndExtractSubject(token);
                var userResponse = userService.getByUsername(subject);

                Authentication authentication = new PreAuthenticatedAuthenticationToken(userResponse, null);
                SecurityContextHolder.setContext(new SecurityContextImpl(authentication));
            } catch (AuthorizationException | NotFoundException e) {
                SecurityContextHolder.clearContext();
                response.sendError(403, "Token is not valid");
                return;
            }
            chain.doFilter(request, response);

        }
    }
}
