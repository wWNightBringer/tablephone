package com.example.tablephone.controllers;

import com.example.tablephone.impl.PhonebookImpl;
import com.example.tablephone.model.Autorize;
import com.example.tablephone.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.SpringBootWebSecurityConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
/*@EnableWebSecurity*/
public class VerifyController /*extends WebSecurityConfigurerAdapter*/ {
    private int id;
    private Autorize autorize;
    private String login;
    private String password;
    private PhonebookImpl phonebook;
    @Value("${phonebook.error.loginOrPassword}")
    private String error;

    @Autowired
    public VerifyController(Autorize autorize, PhonebookImpl phonebook) throws Exception {
        this.autorize = autorize;
        this.phonebook = phonebook;

    }

  /*  @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers().permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/verify")
                .permitAll()
                .and()
                .logout().logoutUrl("/phonebook").logoutSuccessUrl("/phonebook");
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

    }

    @Bean

    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("4862")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }*/

    @GetMapping("/verify")
    public String verify(Model model) {
        model.addAttribute("autorize", autorize);
        return "verify";
    }

    @PostMapping("/verify")
    public String postVerify(@ModelAttribute Autorize autorize, Model model) {
        login = autorize.getLogin();
        password = autorize.getPassword();
        for (Person person : phonebook.getAllPerson()) {
            if (person.getLogin().equalsIgnoreCase(login) && person.getPassword().equalsIgnoreCase(password)) {
                setId(person.getId());
                return "redirect:/phonebook";
            }
        }
        model.addAttribute("error", error);
        return "verify";
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
