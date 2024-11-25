package br.com.ada.t1172.agenda_contatots.service.validations.impl;

import br.com.ada.t1172.agenda_contatots.service.validations.Validation;
import br.com.ada.t1172.agenda_contatots.model.Contato;

public class EmptyMailImpl implements Validation {

    @Override
    public boolean isValid(Contato contato) {
        String mail = contato.getEmail();

        if(mail == null || mail.isEmpty() || mail.isBlank())
            throw new IllegalArgumentException("O e-mail do contato é obrigatório e deve ser válido.");
        return true;
    }
}
