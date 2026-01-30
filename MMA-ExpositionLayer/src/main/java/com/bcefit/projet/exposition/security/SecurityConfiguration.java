package com.bcefit.projet.exposition.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SecurityConfiguration {

    @Bean
    GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults(""); // Remove the ROLE_ prefix
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors();
        /*http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/v1/userAccount/create").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/v1/userAccount").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/v1/userAccount/update").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/v1/userAccount/all").hasRole("ADMIN");


        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/v1/watch/movie").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/v1/watch/movie/all/").hasRole("USER");
        //http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/v1/watch/movie").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/v1/watch/movie/*").hasRole("USER");

        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/v1/wish/movie").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/v1/wish/movie/all/").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/v1/wish/movie").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/v1/wish/movie/*").hasRole("USER");

        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/v1/watch/episode").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/v1/watch/episode/all/").hasRole("USER");
        //http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/v1/watch/episode").hasRole("USER");
        //http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/v1/watch/episode/*").hasRole("USER");
        //http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/v1/watch/tv").hasRole("USER");
        //http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/v1/watch/tv").hasRole("USER");
        //http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/v1/watch/season").hasRole("USER");
        //http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/v1/watch/season").hasRole("USER");

        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/v1/wish/episode").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/v1/wish/episode/all/").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/v1/wish/episode").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/v1/wish/episode/*").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/v1/wish/tv").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/v1/wish/tv").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/v1/wish/season").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/v1/wish/season").hasRole("USER");


         */

        http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        http.csrf().disable();
        http.formLogin().disable();
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource()
    {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.applyPermitDefaultValues();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200", "https://develop--moviemgr.netlify.app", "https://moviemgr.netlify.app"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE", "PUT", "PATCH"));
        configuration.setAllowedHeaders(Arrays.asList("X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

}
