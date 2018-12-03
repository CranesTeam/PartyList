package com.cranesteam.partylist.Configs;

import com.cranesteam.partylist.Services.UserServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author ilyaivankin
 *
 * Web config for security
 *
 * */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private  UserServices userServices;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

//    @Autowired
//    public WebSecurityConfig(UserServices userServices,
//                             BCryptPasswordEncoder bCryptPasswordEncoder) {
//        this.userServices = userServices;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//    }


    /**
     * todo: activate passwordEncoder
     *
     * @param auth AuthenticationManagerBuilder
     * @throws Exception ex
     *
     * @see AuthenticationManagerBuilder
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServices)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    /**
     * @param http HttpSecurity
     * @throws Exception ex
     *
     * @see HttpSecurity
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/login").permitAll()
                    .antMatchers("/registration").permitAll()
                    .antMatchers("/views/**").hasAuthority("USER").anyRequest()
                .authenticated().and().csrf().disable().formLogin()
                    .loginPage("/login")
                    .failureUrl("/login?error=true")
                    .defaultSuccessUrl("/views/parties")
                    .usernameParameter("username")
                    .passwordParameter("password")
                .and().logout().deleteCookies("JSESSIONID")
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/").and().exceptionHandling()
                    .accessDeniedPage("/access-denied");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**",
                        "/js/**", "/images/**");
    }
}
