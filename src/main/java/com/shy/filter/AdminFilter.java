package com.shy.filter;

import javax.servlet.*;
import java.io.IOException;

public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        String role = req.getParameter("role");

        if ("0".equals(role)) {
            req.getRequestDispatcher(
                    "/admin/login").forward(req, resp);
        } else {
            req.getRequestDispatcher("/login"
            ).forward(req, resp);
        }
    }
}
