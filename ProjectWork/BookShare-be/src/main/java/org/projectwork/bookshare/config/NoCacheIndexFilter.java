package org.projectwork.bookshare.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoCacheIndexFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpResp = (HttpServletResponse) response;

        String uri = httpReq.getRequestURI();

        // Applica SOLO a index.html
        if (uri.endsWith("index.html")) {

            // Anti-cache classici
            httpResp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
            httpResp.setHeader("Pragma", "no-cache");
            httpResp.setHeader("Expires", "0");

            // 🔥 La riga che risolve il problema su TUTTI i PC
            httpResp.setHeader("Clear-Site-Data", "\"cache\", \"cookies\", \"storage\"");
        }

        chain.doFilter(request, response);
    }
}