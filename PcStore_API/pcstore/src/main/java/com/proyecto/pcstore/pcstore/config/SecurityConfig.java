package com.proyecto.pcstore.pcstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable().authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/articles").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                .anyRequest().hasAuthority("ROLE_ADMIN").and().httpBasic();
        return http.build();
    }
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("admin"))
				.authorities("ROLE_ADMIN").build();
		UserDetails owner = User.builder().username("owner").password(passwordEncoder().encode("owner"))
				.authorities("ROLE_ADMIN").build();
		UserDetails dani = User.builder().username("dani").password(passwordEncoder().encode("dani"))
				.authorities("ROLE_USER").build();
		UserDetails javi = User.builder().username("javi").password(passwordEncoder().encode("javi"))
				.authorities("ROLE_USER").build();
		UserDetails david = User.builder().username("david").password(passwordEncoder().encode("david"))
				.authorities("ROLE_USER").build();

		return new InMemoryUserDetailsManager(admin, owner, dani, javi, david);
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*");
            }
        };
    }
}
