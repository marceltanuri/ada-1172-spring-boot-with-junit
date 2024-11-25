package br.com.ada.t1172.agenda_contatots.service.validations.impl.emailValidations;

import br.com.ada.t1172.agenda_contatots.model.Contato;
import br.com.ada.t1172.agenda_contatots.service.validations.Validation;

public class EmailValidationImpl implements Validation {

    @Override
    public boolean isValid(Contato contato) {

        String mail = contato.getEmail();
        if(mail == null)
            throw new IllegalArgumentException("O e-mail do contato é obrigatório e deve ser válido.");

        boolean[] valid = new boolean[4];

        valid[0] = mail.contains("@");
        valid[1] = mail.endsWith(".com");
        valid[2] = !mail.isEmpty() || !mail.isBlank();
        valid[3] = !mail.startsWith("@");

        for (int i = 0; i < 4; i++) {
            if (!valid[i]) {
                throw new IllegalArgumentException("O e-mail do contato é obrigatório e deve ser válido.");
            }
        }
        return true;
    }
}
