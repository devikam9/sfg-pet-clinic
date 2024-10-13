package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasSize;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @InjectMocks
    OwnerController controller;

    @Mock
    OwnerService ownerService;

    Set<Owner> owners;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {

        owners = new HashSet<>();

        Owner owner = new Owner();
        owner.setId(1L);
        owners.add(owner);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owners.add(owner2);


        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }

    @Test
    void listOwners() throws Exception {

        when(ownerService.findAll()).thenReturn(owners);

        mockMvc.perform(get("/owners")).andExpect(status().isOk())
                .andExpect(view().name("owners/index")) // Ensure it returns the correct view
                .andExpect(model().attribute("owners", hasSize(2)));
    }

    @Test
    void listOwnersByIndex() throws Exception {

        when(ownerService.findAll()).thenReturn(owners);

        mockMvc.perform(get("/owners/index")).andExpect(status().isOk())
                .andExpect(view().name("owners/index")) // Ensure it returns the correct view
                .andExpect(model().attribute("owners", hasSize(2)));
    }


    @Test
    void findOwners() throws Exception {

        mockMvc.perform(get("/owners/find")).andExpect(status().isOk())
                .andExpect(view().name("notimplemented")); // Ensure it returns the correct view

        verifyNoInteractions(ownerService);

    }
}