package issueabroad.first.config;

import issueabroad.first.security.service.MemberUserDetailsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
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
         * 애너테이션 기반으로도 가능한데, 모든 리소스에 접근 제한을 설정하는 경우니까 이게 더 괜찮은 것 같다.
         */

        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/").hasRole("USER");

        http.formLogin().loginPage("/login").permitAll();

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .deleteCookies("JSESSIONID", "remember-me");



    }

}
