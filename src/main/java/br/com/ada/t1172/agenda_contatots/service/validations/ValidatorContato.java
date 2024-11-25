package br.com.ada.t1172.agenda_contatots.service.validations;

import br.com.ada.t1172.agenda_contatots.model.Contato;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ValidatorContato {

    public static ValidatorContato instance;
    private List<Validation> validations = new ArrayList<Validation>();

    private ValidatorContato() {
        chargeValidators();
        System.out.println(validations.size());
    }

    private void chargeValidators() {
        Reflections reflections = new Reflections("br.com.ada.t1172.agenda_contatots.service.validations.impl");
        Set<Class<? extends Validation>> classes = reflections.getSubTypesOf(Validation.class);

        for(Class<? extends Validation> clazz : classes) {
            try {
                validations.add(clazz.getDeclaredConstructor().newInstance());
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static synchronized ValidatorContato getInstance() {
        if(instance == null) {
            instance = new ValidatorContato();
        }
        return instance;
    }

    public void isValid(Contato contato) throws IllegalArgumentException{
        for(Validation validation : validations) {
            validation.isValid(contato);
        }
    }
}
