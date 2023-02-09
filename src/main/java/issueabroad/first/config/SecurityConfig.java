package issueabroad.first.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 아래 부분이 있으면 접근 제한 걸림!
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * 접근 제한을 설정할 수 있다 ...
         */

        http.authorizeRequests()
                .antMatchers("/").hasRole("USER");

        http.formLogin();
        http.csrf().disable();
        http.logout();

    }

}
