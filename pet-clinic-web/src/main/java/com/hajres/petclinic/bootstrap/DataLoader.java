package com.hajres.petclinic.bootstrap;

import com.hajres.petclinic.model.Owner;
import com.hajres.petclinic.model.Pet;
import com.hajres.petclinic.model.PetType;
import com.hajres.petclinic.model.Vet;
import com.hajres.petclinic.service.OwnerService;
import com.hajres.petclinic.service.PetTypeService;
import com.hajres.petclinic.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        PetType dog = new PetType();
        dog.setName("Dog");
        dog = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        cat = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("123 Brickerel");
        owner1.setCity("Miami");
        owner1.setTelephone("123456789");

        Pet doggo1 = new Pet();
        doggo1.setOwner(owner1);
        doggo1.setPetType(dog);
        doggo1.setBirthDate(LocalDate.now());
        doggo1.setName("Rosco");

        owner1.getPets().add(doggo1);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("123 Brickerel");
        owner2.setCity("Miami");
        owner2.setTelephone("123456789");

        Pet cat1 = new Pet();
        cat1.setOwner(owner2);
        cat1.setPetType(cat);
        cat1.setBirthDate(LocalDate.now());
        cat1.setName("Missy");

        owner2.getPets().add(cat1);

        ownerService.save(owner2);

        System.out.println("Loaded Owners . . .");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");

        vetService.save(vet2);
        System.out.println("Loaded Vets . . .");
    }
}
