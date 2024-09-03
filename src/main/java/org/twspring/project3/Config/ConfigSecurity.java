package org.twspring.project3.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.twspring.project3.Service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfigSecurity {

    private final MyUserDetailsService userDetailsService;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and() //Authorization
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/**").permitAll()
                //add login?
                //ADMIN
                .requestMatchers("/api/v1/customer/get-all",
                        "api/v1/employee/get-all",
                        "api/v1/account/get-all").hasAuthority("ADMIN")
                //Employee
                .requestMatchers("api/v1/employee/update-my-info",
                        "api/v1/employee/delete-my-account").hasAuthority("EMPLOYEE")
                //CUSTOMER
                .requestMatchers("/api/v1/customer/update-my-info",
                        "/api/v1/customer/delete-my-account",
                        "api/v1/account/get-account-details/{accountId}",
                        "api/v1/account/get-my-accounts",
                        "api/v1/account/add-new-account",
                        "api/v1/account/deposit-money/{accountId}/amount/{amount}",
                        "api/v1/account/withdraw-money/{accountId}/amount/{amount}",
                        "api/v1/account/transfer-money-from/{accountId}/to/{receivingAccountId}/amount/{amount}",
                        "api/v1/account/delete-my-account/{accountId}").hasAuthority("CUSTOMER")
                //ADMIN/EMPLOYEE
                .requestMatchers(
                        "api/v1/account/activate-account/{accountId}",
                        "api/v1/account/block/{accountId}").hasAnyAuthority("ADMIN","EMPLOYEE")
                .and() //logout
                .logout().logoutUrl("/api/v1/auth/logout").logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();

        return http.build();
    }
}
