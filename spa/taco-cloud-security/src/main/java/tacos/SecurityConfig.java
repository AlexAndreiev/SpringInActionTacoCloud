package tacos;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserRepositoryUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS).permitAll()
            .antMatchers("/design", "/orders").permitAll()
            .antMatchers(HttpMethod.PATCH, "/ingredients").permitAll()
            .antMatchers("/**").access("permitAll")
//            .antMatchers("/**").permitAll()
//            .antMatchers("/design", "/orders").access("hasRole('ROLE_USER')")
//            .antMatchers("/","/**").access("permitAll")
//            .and().csrf().ignoringAntMatchers("/h2-console/**")
            .and().headers().frameOptions().sameOrigin() // for correct h2-console displaying
            .and()
        .formLogin()
            .loginPage("/login")
//        .loginProcessingUrl("/authenticate") // for listening to handle login submission
//        .usernameParameter("user") // `username` by default
//        .passwordParameter("pwd") //  `password` by default
            .defaultSuccessUrl("/design", true)
        .and().logout().logoutSuccessUrl("/")
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(encoder());
    }

}
