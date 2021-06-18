package tacos.security;

import com.sun.xml.bind.api.impl.NameConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username, password, enabled from Users " +
                                "where username=?")
                .authoritiesByUsernameQuery("select username, authority from UserAuthorities " +
                        "where username=?")
        .passwordEncoder(new StandardPasswordEncoder("53cr3t"));

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
//            .passwordAttribute("passcode") // userPassword attribute is ised by default
//            .and().contextSource()
////        .url("ldap://tacocloud.com:389/dc=tacocloud,dc=com"); //for separate server, default 33389 at localhost
//            .root("dc=tacocloud,dc=com") // for embedded ldap server
//        .ldif("classpath:users.ldif") // spring will search for any ldif file in classpath otherwise
        ;
    }

}
