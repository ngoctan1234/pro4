package com.pro.woo.configuration;

import com.pro.woo.filters.JwtTokenFilter;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.boot.actuate.autoconfigure.security.reactive.EndpointRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;

import static org.springframework.http.HttpMethod.*;


@EnableWebMvc
@Configuration
@EnableWebSecurity
@AllArgsConstructor
//@RequiredArgsConstructor
public class WebSecurityConfig {
    private final UserDetailsService userDetailsService;
   private  final JwtTokenFilter jwtTokenFilter;
//    @Value("${api.prefix}")
    private final String apiPrefix="api";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
               .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                //.cors(Customizer.withDefaults())
//                .and()
                .authorizeRequests()
                .requestMatchers(HttpMethod.POST, "api/users/login1").permitAll()
                .requestMatchers(HttpMethod.POST, "api/users/login").permitAll()
                .requestMatchers(HttpMethod.POST, "api/users/login2").permitAll()
                .requestMatchers(HttpMethod.POST, "api/categories").permitAll()
                .requestMatchers(HttpMethod.GET, "swagger-ui/index.html").permitAll()
                .requestMatchers(HttpMethod.POST,
                        String.format("%s/users/register", apiPrefix),
                        String.format("%s/users/refresh-token", apiPrefix)
                ).permitAll()

                .requestMatchers(GET,
                        String.format("%s/roles**", apiPrefix)).permitAll()

                .requestMatchers(GET,
                        String.format("%s/policies/**", apiPrefix)).permitAll()

                .requestMatchers(GET,
                        String.format("%s/categories/**", apiPrefix)).permitAll()

                .requestMatchers(GET,
                        String.format("%s/products/**", apiPrefix)).permitAll()

                .requestMatchers(GET,
                        String.format("%s/products/images/*", apiPrefix)).permitAll()

                .requestMatchers(GET,
                        String.format("%s/orders/**", apiPrefix)).permitAll()
                .requestMatchers(GET,
                        String.format("%s/users/profile-images/**", apiPrefix))
                .permitAll()

                .requestMatchers(GET,
                        String.format("%s/order_details/**", apiPrefix)).permitAll()

                .anyRequest().authenticated();
//                .and()
//                .csrf(AbstractHttpConfigurer::disable);

        //http.securityMatcher(String.valueOf(EndpointRequest.toAnyEndpoint()));
        return http.build();
    }

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // Thay đổi theo yêu cầu của bạn
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
//        configuration.setAllowCredentials(true);
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }


}
//spring.security.user.name=admin
//spring.security.user.password=admin
