package br.com.ada.t1172.agenda_contatots.service.validations.impl;

import br.com.ada.t1172.agenda_contatots.model.Contato;
import br.com.ada.t1172.agenda_contatots.service.validations.Validation;

public class EmailEndsWithDotComImpl implements Validation {
    @Override
    public boolean isValid(Contato contato) {
        if(contato.getEmail().endsWith(".com"))
            return true;

        throw new IllegalArgumentException("O e-mail do contato é obrigatório e deve ser válido.");
    }
}
