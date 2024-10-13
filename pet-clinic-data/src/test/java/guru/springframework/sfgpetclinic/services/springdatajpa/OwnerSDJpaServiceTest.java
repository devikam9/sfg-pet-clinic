package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    public static final String LAST_NAME = "Smith";

    @Mock
     OwnerRepository ownerRepository;
    @Mock
     PetRepository petRepository;
    @Mock
     PetTypeRepository petTypeRepository;

    @InjectMocks
     OwnerSDJpaService service;

    Owner returnOwner = new Owner();

    @BeforeEach
    void setUp() {
        returnOwner.setId(1L);
        returnOwner.setLastName(LAST_NAME);
        service.save(returnOwner);
    }

    @Test
    void findByLastName() {

        Owner returnOwner = new Owner();
        returnOwner.setId(1L);


        when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);

        Owner smith = service.findByLastName(LAST_NAME);

        assertEquals(LAST_NAME,smith.getLastName());

        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void findAll() {

        Set<Owner> returnOwnersSet = new HashSet<>();
        Owner owner1 = new Owner();
        owner1.setId(1L);
        returnOwnersSet.add(owner1);
//        returnOwnersSet.add(Owner.builder().id(1l).build());
        Owner owner2 = new Owner();
        owner2.setId(2L);
        returnOwnersSet.add(owner2);

        when(ownerRepository.findAll()).thenReturn(returnOwnersSet);

        Set<Owner> owners = service.findAll();

        assertNotNull(owners);
        assertEquals(2, owners.size());
    }

    @Test
    void findById() {

        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));

        Owner owner = service.findById(1L);

        assertNotNull(owner);
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Owner owner = service.findById(1L);

        assertNull(owner);
    }


    @Test
    void save() {
        Owner ownerToSave = new Owner();
        ownerToSave.setId(1L);

        returnOwner.setId(1L);

        when(ownerRepository.save(any())).thenReturn(returnOwner);

        Owner savedOwner = service.save(ownerToSave);

        assertNotNull(savedOwner);
        assertEquals(1L,savedOwner.getId());

        verify(ownerRepository).save(eq(ownerToSave));
    }
    @Test
    void delete() {
        service.delete(returnOwner);

        //default is 1 times
        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {

        service.deleteById(1L);

        verify(ownerRepository).deleteById(anyLong());
    }
}