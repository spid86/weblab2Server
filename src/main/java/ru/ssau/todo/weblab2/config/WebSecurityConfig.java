// package ru.ssau.todo.weblab2.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// @EnableWebSecurity
//     public class WebSecurityConfig {

//         @Bean
//         public SecurityFilterChain configure(HttpSecurity http) throws Exception {
//             http
//                     .authorizeHttpRequests(authorizeHttpRequests ->
//                             authorizeHttpRequests
                                    
//                                     .requestMatchers("/api/projects/*/tasks/**").hasAnyRole("USER", "ADMIN")
//                                     .requestMatchers("/api/projects/**").hasRole("ADMIN")
//                                     .anyRequest().authenticated()
//                     )
//                     .formLogin(formLogin ->
//                             formLogin
//                                     .defaultSuccessUrl("/api/projects")
//                                     .permitAll()
//                     )
//                     .logout(logout ->
//                             logout
//                                     .permitAll()
//                     )
//                     .csrf(csrf -> csrf.disable());;
//                 return http.build();
//         }
//     }
    