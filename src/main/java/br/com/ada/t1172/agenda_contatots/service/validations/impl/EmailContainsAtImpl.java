package br.com.ada.t1172.agenda_contatots.service.validations.impl;

import br.com.ada.t1172.agenda_contatots.model.Contato;
import br.com.ada.t1172.agenda_contatots.service.validations.Validation;

public class EmailContainsAtImpl implements Validation {

    @Override
    public boolean isValid(Contato contato) {
        String mail = contato.getEmail();
        if(!mail.contains("@") || mail.startsWith("@") || mail.endsWith("@"))
            throw new IllegalArgumentException("O e-mail do contato é obrigatório e deve ser válido.");
        return true;
    }
}
