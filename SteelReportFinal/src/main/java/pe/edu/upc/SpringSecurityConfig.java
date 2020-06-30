package pe.edu.upc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import pe.edu.upc.serviceimpl.JpaUserDetailsService;;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration

public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private AuthenticationSuccessHandler authenticationsuccessHandler;
	
	@Autowired
	private JpaUserDetailsService userDetailsService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	
	
	@Autowired
    public SpringSecurityConfig(AuthenticationSuccessHandler authenticationsuccessHandler) {
        this.authenticationsuccessHandler = authenticationsuccessHandler;
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
			
		try {
			
			
			http.authorizeRequests().antMatchers("/", "/files/**","/files_login/**","/imagenes/**").permitAll().anyRequest()
			.authenticated().and().formLogin().loginPage("/login").successHandler(authenticationsuccessHandler)
			.permitAll().and().logout().permitAll().and().exceptionHandling().accessDeniedPage("/error_403");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
		build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

	}
}
