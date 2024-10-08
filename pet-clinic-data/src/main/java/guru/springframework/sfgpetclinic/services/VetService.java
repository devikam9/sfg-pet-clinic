package guru.springframework.sfgpetclinic.services;

import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.model.Owner;


import java.util.Set;

public interface VetService extends CrudService<Vet, Long>{

   Vet findByLastName(String lastName);
}
