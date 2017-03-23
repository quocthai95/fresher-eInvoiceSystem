package csc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/index.html", "/login.html", "/partials/**", "/", "/error/**", "/user/register", "/user/getEmail/**"
				 );
	}

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/dashboard.html", "/user/getReport/**", "/invoice/**","/customer", "/user/getExpensesReport/**").hasRole("MEMBER")
                .antMatchers("/user/**", "/parameter/**").hasRole("ADMIN")
                .antMatchers("/", "/mail/send").permitAll()                
                .and()
            .formLogin()
            	.loginPage("/login")
            	.usernameParameter("username")
            	.passwordParameter("password")
            	.defaultSuccessUrl("/dashboard")
            	.failureUrl("/login-error.html").permitAll()
            	.and().logout().logoutSuccessUrl("/#/login?logout").permitAll()
            	.and()
            	.csrf()
                .ignoringAntMatchers("/user/**", "/parameter/**", "/invoice/**", "/user/getEmail/**", "/user/getReport/**","/customer/**")                
                .and()
        	.exceptionHandling()
    			.accessDeniedPage("/403");
    }

}
