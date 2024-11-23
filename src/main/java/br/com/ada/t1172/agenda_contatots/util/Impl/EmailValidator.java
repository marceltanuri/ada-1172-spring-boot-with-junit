package br.com.ada.t1172.agenda_contatots.util.Impl;

import br.com.ada.t1172.agenda_contatots.util.Validator;
import org.springframework.context.annotation.Bean;

import java.util.regex.Pattern;

public class EmailValidator implements Validator {

    private String email;
    private static final Pattern EMAIL_REGEX_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]{4,}@[a-zA-Z0-9.-]{4,}\\.[a-zA-Z]{2,}$");
    public EmailValidator(String mail) {
        this.email = mail;
    }

    @Bean
    @Override
    public boolean validate() {
        if (email == null || email.trim().isEmpty()) return false;
        return EMAIL_REGEX_PATTERN.matcher(email).matches();
    }
}
