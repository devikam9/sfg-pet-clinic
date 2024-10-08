package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialitiesService specialitiesService;
    private final VisitService visitService;


    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialitiesService specialitiesService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialitiesService = specialitiesService;
        this.visitService = visitService;
    }


    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if(count == 0) {
            loadData();
        }
    }

    private void loadData() {
        System.out.println("DataLoader run method is executing...");

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);


        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialitiesService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialitiesService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialitiesService.save(dentistry);

        Owner owner1 = new Owner();
//        owner1.setId(1L);
        owner1.setFirstName("Devika");
        owner1.setLastName("Sri");
        owner1.setAddress("123 Bricker");
        owner1.setCity("Miami");
        owner1.setTelephone("1231231234");

        Pet devikaPet = new Pet();
        devikaPet.setPetType(savedDogPetType);
        devikaPet.setOwner(owner1);
        devikaPet.setBirthDate(LocalDate.now());
        devikaPet.setName("Tommy");
        owner1.getPets().add(devikaPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
//        owner2.setId(2L);
        owner2.setFirstName("Aleikhya");
        owner2.setLastName("Devi");
        owner2.setAddress("123 Bricker");
        owner2.setCity("Miami");
        owner2.setTelephone("1231231234");

        Pet alipet = new Pet();
        alipet.setName("Jessy");
        alipet.setBirthDate(LocalDate.now());
        alipet.setPetType(savedCatPetType);
        alipet.setOwner(owner2);
        owner2.getPets().add(alipet);


        ownerService.save(owner2);

        Visit aliVisit = new Visit();
        aliVisit.setPet(alipet);
        aliVisit.setDate(LocalDate.now());
        aliVisit.setDescription("Sneezy Kitty");

        visitService.save(aliVisit);

        System.out.println("Loaded Owners.........");

        Vet vet1 = new Vet();
//        vet1.setId(1L);
        vet1.setFirstName("Micheal");
        vet1.setLastName("Weston");
        vet1.getSpecialities().add(savedRadiology);

        vetService.save(vet1);


        Vet vet2 = new Vet();
//        vet2.setId(2L);
        vet2.setFirstName("Daniel");
        vet2.setLastName("Phillip");
        vet2.getSpecialities().add(savedSurgery);
        vetService.save(vet2);

        System.out.println("Loaded Vets.........");
    }
}
