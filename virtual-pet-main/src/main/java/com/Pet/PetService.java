package com.Pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public Pet createPet(String type, String name) {
        Pet newPet = new Pet(name, type, "Generierte ASCII Art"); // 'Generierte ASCII Art' kann durch eine tatsächliche ASCII Art ersetzt werden
        return petRepository.save(newPet);
    }

    public Pet getPetDetails(String name) {
        return petRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Haustier nicht gefunden: " + name));
    }

    public void essen(String name) {
        Pet pet = getPetDetails(name);
        pet.essengeben();
        petRepository.save(pet);
    }

    public void trinken(String name) {
        Pet pet = getPetDetails(name);
        pet.wassergeben();
        petRepository.save(pet);
    }

    public void schlafen(String name) {
        Pet pet = getPetDetails(name);
        pet.schlafen();
        petRepository.save(pet);
    }

    public void streicheln(String name) {
        Pet pet = getPetDetails(name);
        pet.streicheln();
        petRepository.save(pet);
    }

    public void spielen(String name) {
        Pet pet = getPetDetails(name);
        pet.spielen();
        petRepository.save(pet);
    }

    public void duschen(String name) {
        Pet pet = getPetDetails(name);
        pet.duschen();
        petRepository.save(pet);
    }
}