package tacos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    DataSource dataSource;

    @Autowired
    private UserRepositoryUserDetailsService userDetailsService;


    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/design", "/orders").access("hasRole('ROLE_USER')")
            .antMatchers("/","/**").access("permitAll")
            .and().csrf().ignoringAntMatchers("/h2-console/**")
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
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery(
//                        "select username, password, enabled from Users " +
//                                "where username=?")
//                .authoritiesByUsernameQuery("select username, authority from UserAuthorities " +
//                        "where username=?")
//        .passwordEncoder(new StandardPasswordEncoder("53cr3t"));

//        auth.inMemoryAuthentication()
//                .withUser("buzz")
//                    .password("infinity")
//                    .authorities("ROLE_USER")
//                .and()
//                .withUser("woody")
//                    .password("bullseye")
//                    .authorities("ROLE_USER");

//        auth.ldapAuthentication()
//            .userSearchBase("ou=people")
//            .userSearchFilter("({uid=0}}")
//            .groupSearchBase("ou=groups")
//            .groupSearchFilter("member={0}")
//            .passwordCompare() // disable default bind check and check the password attribute
//            .passwordEncoder(new BCryptPasswordEncoder())
//            .passwordAttribute("passcode") // userPassword attribute is used by default
//            .and().contextSource()
////        .url("ldap://tacocloud.com:389/dc=tacocloud,dc=com"); //for separate server, default 33389 at localhost
//            .root("dc=tacocloud,dc=com") // for embedded ldap server
//        .ldif("classpath:users.ldif") // spring will search for any ldif file in classpath otherwise
//        ;
    }

}
