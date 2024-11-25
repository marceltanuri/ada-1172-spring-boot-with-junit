package br.com.ada.t1172.agenda_contatots.service.validations.impl;

import br.com.ada.t1172.agenda_contatots.model.Contato;
import br.com.ada.t1172.agenda_contatots.service.validations.Validation;

public class NameValidationImpl implements Validation {

    @Override
    public boolean isValid(Contato contato){
        String name = contato.getNome();
        if(name.isBlank() || name.isEmpty()){
            throw new IllegalArgumentException("O nome do contato é obrigatório.");
        }
        return true;
    }
}
