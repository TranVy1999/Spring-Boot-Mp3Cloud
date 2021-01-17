package io.github.mp3cloud.security;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSercurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private CustomUser customUser;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("http://localhost:3000");
		config.addAllowedOrigin("http://localhost:3001");
		config.addAllowedHeader("*");
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("DELETE");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
	
	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Bean
	public JWTAuthenticationFilter jwtAuthenticationFilter() {
		return new JWTAuthenticationFilter();
	}

	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUser).passwordEncoder(passwordEncoder());
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper m = new ModelMapper();
		m.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		m.getConfiguration().setPropertyCondition(Conditions.isNotNull());
		return m;
	}

	public void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
		http.authorizeRequests().// allow anonymous resource requests
		 antMatchers(
				 HttpMethod.GET,
				 "/*.html",
				 "/favicon.ico",
				 "/**/*.html",
				 "/**/*.css",
				 "/**/*.js"
				 ).permitAll()
		 .antMatchers("/forgot_password*").permitAll() // Login end-point
		 .antMatchers("/signin").permitAll()
		 .antMatchers("/signup").permitAll()
		 .antMatchers("/").permitAll()
		 .antMatchers("/home").permitAll()
		 .antMatchers("/reset_password*").permitAll()
		 
		 .and()
		 .authorizeRequests()
//		 .antMatchers("user/*").hasAnyRole("ROLE_USER","ROLE_ADMIN")
//		 .antMatchers("/admin/*").hasAnyRole("ROLE_ADMIN")
//		 .antMatchers("/staff/**").hasRole("STAFF")
		 .anyRequest().authenticated()
		 
		 .and();
	//	 .exceptionHandling()
	//	 .accessDeniedPage("/403");
		
//		antMatchers("/signup").permitAll().antMatchers("/signin").permitAll()
//				.antMatchers("/forgot_password**").permitAll().antMatchers("/reset_password**").permitAll().anyRequest()
//				.authenticated();
////		http.authorizeRequests().anyRequest().permitAll(); 
		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
