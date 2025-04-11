package vn.hoidanit.laptopshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.session.security.web.authentication.SpringSessionRememberMeServices;

import jakarta.servlet.DispatcherType;
import vn.hoidanit.laptopshop.service.UserService;
import vn.hoidanit.laptopshop.service.validator.CustomUserDetailsService;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {
        @Bean
        public PasswordEncoder encoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public UserDetailsService userDetailsService(UserService userService) {
                return new CustomUserDetailsService(userService);
        }

        @Bean
        public AuthenticationSuccessHandler authenticationSuccessHandler() {
                return new CustomSuccessHandler();
        }

        @Bean
        public DaoAuthenticationProvider daoAuthenticationProvider(PasswordEncoder passwordEncoder,
                        UserDetailsService userDetailsService) throws Exception {
                DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
                daoAuthenticationProvider.setUserDetailsService(userDetailsService);
                daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
                daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
                return daoAuthenticationProvider;
        }

        @Bean
        public SpringSessionRememberMeServices springSessionRememberMeServices() {
                SpringSessionRememberMeServices springSessionRememberMeServices = new SpringSessionRememberMeServices();
                // optionally customize
                springSessionRememberMeServices.setAlwaysRemember(false);
                return springSessionRememberMeServices;
        }

        @Bean
        SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests(authorize -> authorize
                                                .dispatcherTypeMatchers(DispatcherType.FORWARD,
                                                                DispatcherType.INCLUDE)
                                                .permitAll()

                                                .requestMatchers("/login", "/register","/404",
                                                                "/", "/product/**", "/client/**", "/css/**", "/js/**",
                                                                "/images/**")
                                                .permitAll()

                                                .requestMatchers("/admin/**").hasRole("ADMIN")

                                                .anyRequest().authenticated())
                                // permitAll
                                // authenticated xác thực all
                                .formLogin(formLogin -> formLogin
                                                .loginPage("/login")
                                                .failureUrl("/login?error")
                                                .successHandler(
                                                                authenticationSuccessHandler())
                                                .permitAll())

                                .exceptionHandling(exception -> exception.accessDeniedPage("/access-deny"))

                                .sessionManagement((sessionManagement) -> sessionManagement
                                                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                                                .invalidSessionUrl("/logout?expired")
                                                .maximumSessions(1)
                                                .maxSessionsPreventsLogin(false))

                                .logout(logout -> logout.deleteCookies("JSESSIONID",
                                                "remember-me").invalidateHttpSession(true))

                                .rememberMe((rememberMe) -> rememberMe
                                                .rememberMeServices(springSessionRememberMeServices()));
                return http.build();
        }

}
