package com.hajres.petclinic.bootstrap;

import com.hajres.petclinic.model.Owner;
import com.hajres.petclinic.model.Pet;
import com.hajres.petclinic.model.PetType;
import com.hajres.petclinic.model.Speciality;
import com.hajres.petclinic.model.Vet;
import com.hajres.petclinic.model.Visit;
import com.hajres.petclinic.service.OwnerService;
import com.hajres.petclinic.service.PetTypeService;
import com.hajres.petclinic.service.SpecialityService;
import com.hajres.petclinic.service.VetService;
import com.hajres.petclinic.service.VisitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();
        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = PetType.builder().name("Dog").build();
        dog = petTypeService.save(dog);

        PetType cat = PetType.builder().name("Cat").build();
        cat = petTypeService.save(cat);

        Owner owner1 = Owner.builder()
                .firstName("Michael")
                .lastName("Weston")
                .address("123 Brickerel")
                .city("Miami")
                .telephone("123456789")
                .build();
        Pet doggo1 = Pet.builder()
                .owner(owner1)
                .petType(dog)
                .birthDate(LocalDate.now())
                .name("Rosco")
                .build();
        owner1.addPet(doggo1);
        ownerService.save(owner1);

        Owner owner2 = Owner.builder()
                .firstName("Fiona")
                .lastName("Glenanne")
                .address("123 Brickerel")
                .city("Miami")
                .telephone("123456789").build();
        Pet cat1 = Pet.builder()
                .owner(owner2)
                .petType(cat)
                .birthDate(LocalDate.now())
                .name("Missy")
                .build();
        owner2.addPet(cat1);
        ownerService.save(owner2);

        Visit catVisit = Visit.builder()
                .pet(cat1)
                .date(LocalDate.now())
                .description("Sneezy Kitten")
                .build();
        cat1.addVisit(catVisit);
        visitService.save(catVisit);

        log.info("Loaded Owners . . .");

        Speciality radiology = Speciality.builder().description("Radiology").build();
        radiology = specialityService.save(radiology);

        Speciality surgery = Speciality.builder().description("Surgery").build();
        surgery = specialityService.save(surgery);

        Speciality dentistry = Speciality.builder().description("Dentistry").build();
        dentistry = specialityService.save(dentistry);

        Vet vet1 = Vet.builder()
                .firstName("Sam")
                .lastName("Axe")
                .build();
        vet1.getSpecialities().add(radiology);
        vetService.save(vet1);

        Vet vet2 = Vet.builder()
                .firstName("Jessie")
                .lastName("Porter")
                .build();
        vet2.getSpecialities().add(dentistry);
        vet2.getSpecialities().add(surgery);
        vetService.save(vet2);
        log.info("Loaded Vets . . .");
    }
}
