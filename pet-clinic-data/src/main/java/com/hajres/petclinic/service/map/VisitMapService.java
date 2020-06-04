package com.hajres.petclinic.service.map;

import com.hajres.petclinic.model.Pet;
import com.hajres.petclinic.model.Visit;
import com.hajres.petclinic.service.PetService;
import com.hajres.petclinic.service.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class VisitMapService extends AbstractMapService<Visit, Long> implements VisitService {
    private final PetService petService;

    public VisitMapService(PetService petService) {
        this.petService = petService;
    }

    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Visit object) {
        super.delete(object);
    }

    @Override
    public Visit save(Visit object) {
        if (object.getPet() != null) {
            Pet pet = object.getPet();
            if (pet.getId() == null) {
                Pet savedPet = petService.save(pet);
                pet.setId(savedPet.getId());
            }
            return super.save(object);
        } else {
            throw new RuntimeException("Visit requires Pet object");
        }
    }

    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }
}
