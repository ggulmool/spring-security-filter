package me.ggulmool.springsecurityfilter.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@Configuration
public class SecurityConfig {

    @Configuration
    @Order(Ordered.LOWEST_PRECEDENCE - 100)
    protected static class AppAuthenticationConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/app/**")
            .addFilterBefore(new AppCustomFilter(), UsernamePasswordAuthenticationFilter.class);
        }
    }

    @Configuration
    @Order(Ordered.LOWEST_PRECEDENCE - 90)
    protected static class WebAuthenticationConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/web/**")
            .addFilterBefore(new WebCustomFilter(), UsernamePasswordAuthenticationFilter.class);
        }
    }



    private static class AppCustomFilter implements Filter {

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {

        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            String auth = String.class.cast(HttpServletRequest.class.cast(request).getHeader(HttpHeaders.AUTHORIZATION));
            log.info("auth : {}", auth);
            log.info("AppCustomFilter");
            if (auth.contains("abc")) {
                log.info("token found!");
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken("user", "pw", AuthorityUtils.createAuthorityList("ADMIN"));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            chain.doFilter(request, response);
        }

        @Override
        public void destroy() {

        }
    }

    private static class WebCustomFilter implements Filter {

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {

        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            log.info("WebCustomFilter");
            chain.doFilter(request, response);
        }

        @Override
        public void destroy() {

        }
    }
}
