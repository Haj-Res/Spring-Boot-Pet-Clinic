package com.hajres.petclinic.service.map;

import com.hajres.petclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class OwnerMapServiceTest {
    private static final Long OWNER_ID = 1L;
    private static final String LAST_NAME = "Smith";
    OwnerMapService service;

    @BeforeEach
    void setUp() {
        service = new OwnerMapService(new PetTypeMapService(), new PetMapService());
        service.save(Owner.builder().id(OWNER_ID).lastName(LAST_NAME).build());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = service.findAll();
        assertEquals(1, ownerSet.size());
    }

    @Test
    void findById() {
        Owner owner = service.findById(OWNER_ID);
        assertEquals(OWNER_ID, owner.getId());
    }

    @Test
    void save() {
        long id = 2L;
        Owner owner = Owner.builder().id(id).build();
        Owner savedOwner = service.save(owner);
        assertEquals(id, savedOwner.getId());
    }

    @Test
    void saveNoId() {
        Owner savedOwner = service.save(Owner.builder().build());
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());

    }

    @Test
    void delete() {
        service.delete(service.findById(OWNER_ID));
        assertEquals(0, service.findAll().size());

    }

    @Test
    void deleteById() {
        service.deleteById(OWNER_ID);
        assertEquals(0, service.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner owner = service.findByLastName(LAST_NAME);
        assertNotNull(owner);
        assertEquals(OWNER_ID, owner.getId());
    }

    @Test
    void findByLastNameNotFound() {
        Owner owner = service.findByLastName("foo");
        assertNull(owner);
    }
}