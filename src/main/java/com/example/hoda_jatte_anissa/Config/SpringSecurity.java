package com.example.hoda_jatte_anissa.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/register/**").permitAll()
                                .requestMatchers("/index").permitAll()
                                .requestMatchers("/Acceuil").permitAll()
                                .requestMatchers("/Index-Admin").hasAnyAuthority("Admin")
                                .requestMatchers("/edit-encadrant/{id}").hasAnyAuthority("Admin")
                                .requestMatchers("/Index-User").hasAnyAuthority("User")
                                .requestMatchers("/fonts/**").permitAll()
                                .requestMatchers("Formulaire/images/icon_form.png").permitAll()
                                .requestMatchers("Form/fontsH/material-icon/cssHoda/material-design-iconic-font.min.css" ).permitAll()
                                .requestMatchers("Form/vendorH/jquery-ui/jquery-ui.min.css" ).permitAll()
                                .requestMatchers("Form/vendorH/jqueryH/jquery.min.js" ).permitAll()
                                .requestMatchers("Form/vendorH/jquery-ui/jquery-ui.min.js" ).permitAll()
                                .requestMatchers("Form/vendorH/jquery-validation/dist/jquery.validate.min.js" ).permitAll()
                                .requestMatchers("https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" ).permitAll()
                                .requestMatchers("https://code.jquery.com/jquery-3.6.0.min.js" ).permitAll()
                                .requestMatchers("https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" ).permitAll()
                                .requestMatchers("/static/**").permitAll()
                                .requestMatchers("/static/css/**").permitAll()
                                .requestMatchers("/static/modules/**").permitAll()
                                .requestMatchers("/static/**").permitAll()
                                .requestMatchers("/static/js/**").permitAll()
                                .requestMatchers("/images/favicon.png").permitAll()
                                .requestMatchers("/css/bootstrap.min.css").permitAll()
                                .requestMatchers("style.css").permitAll()
                                .requestMatchers("/css/responsive.css").permitAll()
                                .requestMatchers("/css/colors.css").permitAll()
                                .requestMatchers("/css/bootstrap-select.css").permitAll()
                                .requestMatchers("/css/perfect-scrollbar.css").permitAll()
                                .requestMatchers("/css/custom.css").permitAll()
                                .requestMatchers("/js/semantic.min.css").permitAll()
                                .requestMatchers("/images/logo/logo.png").permitAll()
                                .requestMatchers("/js/jquery.min.js").permitAll()
                                .requestMatchers("/js/popper.min.js").permitAll()
                                .requestMatchers("/js/bootstrap.min.js").permitAll()
                                .requestMatchers("/js/animate.js").permitAll()
                                .requestMatchers("/js/bootstrap-select.js").permitAll()
                                .requestMatchers("/js/perfect-scrollbar.min.js").permitAll()
                                .requestMatchers("/js/custom.js").permitAll()
                                .requestMatchers("images/authentification.jpg").permitAll()
                                .requestMatchers("/Formulaire/formulaire2.css").permitAll()
                                .requestMatchers("css/perfect-scrollbar.min.js").permitAll()
                                .requestMatchers("images/edit.png").permitAll()
                                .requestMatchers("images/user_img.jpg").permitAll()
                                .requestMatchers("/js/semantic.min.css").permitAll()
                                .requestMatchers("/ListeDemandes/ListeDemandeGeneral.css").permitAll()
                                .requestMatchers("/js/popper.min.js").permitAll()
                                .requestMatchers("/js/popper.min.js").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/Acceuil",true)
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll())
                .exceptionHandling().accessDeniedPage("/access-denied");
        return http.build();
    }
}













                                /*.defaultSuccessUrl("/users", true)
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}*/



