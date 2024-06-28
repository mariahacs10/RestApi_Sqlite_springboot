package com.example.artworkapi;

import java.io.IOException;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.artworkapi.service.ApiKeyService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {
    private final ApiKeyService apiKeyService;
    private static final Logger log = LoggerFactory.getLogger(ApiKeyFilter.class);

    public ApiKeyFilter(ApiKeyService apiKeyService) {
        this.apiKeyService = apiKeyService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String apiKey = request.getHeader("X-Api-Key");
        log.info("Received API key: {}", apiKey);

        if (apiKey != null && !apiKey.isEmpty()) {
            if (isSearchRequest(request)) {
                if (apiKeyService.validateApiKey(apiKey)) {
                    setSearchAuthentication(apiKey, request);
                    filterChain.doFilter(request, response);
                    return;
                }
            } else if (isArtworkRequest(request)) {
                if (apiKeyService.validateArtworkApiKey(apiKey)) {
                    setAuthentication(apiKey, "apiKey");
                    filterChain.doFilter(request, response);
                    return;
                }
            } else {
                if (apiKeyService.validateApiKey(apiKey)) {
                    setAuthentication(apiKey, "apiKey");
                    filterChain.doFilter(request, response);
                    return;
                }
            }
        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Invalid API key.");
    }

    private boolean isArtworkRequest(HttpServletRequest request) {
        String requestUrl = request.getRequestURI();
        return requestUrl.startsWith("/erikasArtWork");
    }

    private boolean isSearchRequest(HttpServletRequest request) {
        String requestUrl = request.getRequestURI();
        return requestUrl.startsWith("/api/search");
    }

    private void setAuthentication(String apiKey, String authority) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(apiKey, null, 
            Collections.singletonList(new SimpleGrantedAuthority(authority)));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private void setSearchAuthentication(String apiKey, HttpServletRequest request) {
        String authority = isArtworkRequest(request) ? "erikasArtWorkApiKey" : "animeConventionApiKey";
        setAuthentication(apiKey, authority);
    }
}