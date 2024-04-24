// package ru.ssau.todo.weblab2.config;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;

// @Configuration
// public class SecurityConfig {

//     @Value("${security.logins}")
//     private String[] logins;

//     @Value("${security.passwords}")
//     private String[] passwords;

//     @Value("${security.roles}")
//     private String[] roles;
    
//     @Bean
//     public UserDetailsService userDetailsService() {
//         UserDetails[] userDetailsArray = new UserDetails[logins.length];

//         for (int i = 0; i < logins.length; i++) {
//             userDetailsArray[i] = User.withUsername(logins[i])
//                     .password(passwordEncoder().encode(passwords[i]))
//                     .roles(roles[i])
//                     .build();
//         }

//         return new InMemoryUserDetailsManager(userDetailsArray);
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

    
// }