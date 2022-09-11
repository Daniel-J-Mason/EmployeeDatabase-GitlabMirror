package com.dev.wheeledrescue.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Autowired
    private LoginSuccessHandler loginSuccessHandler;
    @Autowired
    private LoginDeniedHandler loginDeniedHandler;
    
    @Autowired
    private DataSource dataSource;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/css/*.css", "/createUser").permitAll()
                .antMatchers("/", "/**","/css/**", "/static/**", "/static/templates/**", "/static/templates/homepage/**").permitAll() //hasAnyRole("ADMIN", "USER")
                .antMatchers("/restricted/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().successHandler(loginSuccessHandler)
                .loginPage("/login")
                .permitAll()
                .and()
                .logout().invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(loginDeniedHandler).and().csrf().disable();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        
        
        auth.jdbcAuthentication().dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("select username as username, password, true"
                + " from user_account where username=?")
                .authoritiesByUsernameQuery("SELECT user_account.username as username, role.role as role \n" +
                        "        FROM user_account \n" +
                        "        INNER JOIN user_role ON user_account.pk = user_role.user_id \n" +
                        "        INNER JOIN role ON user_role.role_id = role.pk\n" +
                        "        WHERE user_account.username = ?  ");
         
    }
    
    
}
