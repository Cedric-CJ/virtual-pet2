package com.Pet;

import com.User.ApplicationUser;
import com.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    private PetRepository petRepository;

    @Autowired
    private PetService petService;

    @Autowired
    public UserRepository userRepository;

    @GetMapping("/{id}")
    public Pet getPetById(@PathVariable Long id) {
        return petRepository.findById(id).orElseThrow(() -> new RuntimeException("Pet not found"));
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllPets() {
        try {
            return new ResponseEntity<>(petService.getAllPets(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPet(@RequestBody Pet pet) {
        try {
            Pet newPet = petService.createPet(pet.getType(), pet.getName());
            return new ResponseEntity<>(newPet, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/registration")
    public String showRegistrationPage(Model model) {
        model.addAttribute("User", new ApplicationUser());
        return "register";
    }

    @PostMapping("/registration_process")
    public String handleRegistrationRequest(@RequestBody ApplicationUser applicationUser) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(applicationUser.getPassword());
        applicationUser.setPassword(encodedPassword);
        userRepository.save(applicationUser);
        return "registrationSuccessful";
    }

    @GetMapping("/pet")
    public ResponseEntity<?> getPetDetails(@RequestParam String name) {
        try {
            Pet pet = petService.getPetDetails(name);
            return ResponseEntity.ok().body(Map.of("status", "success", "data", pet));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("status", "error", "message", e.getMessage()));
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getPet(@PathVariable String name) {
        try{
            Pet pet = petService.getPetDetails(name);
            return ResponseEntity.ok().body(Map.of("status", "success", "data", pet));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("status", "error", "message", e.getMessage()));
        }
    }

    @PostMapping("/{name}/feed")
    public ResponseEntity<String> essen(@PathVariable String name) {
        try {
            petService.essen(name);
            return ResponseEntity.ok("Haustier gefüttert: " + name);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Haustier nicht gefunden: " + name);
        }
    }

    @PostMapping("/{name}/water")
    public ResponseEntity<String> trinken(@PathVariable String name) {
        try {
            petService.trinken(name);
            return ResponseEntity.ok("Wasser gegeben an: " + name);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Haustier nicht gefunden: " + name);
        }
    }

    @PostMapping("/{name}/sleep")
    public ResponseEntity<String> schlafen(@PathVariable String name) {
        try {
            petService.schlafen(name);
            return ResponseEntity.ok("Haustier schläft jetzt: " + name);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Haustier nicht gefunden: " + name);
        }
    }

    @PostMapping("/{name}/pet")
    public ResponseEntity<String> streicheln(@PathVariable String name) {
        try {
            petService.streicheln(name);
            return ResponseEntity.ok("Haustier gestreichelt: " + name);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Haustier nicht gefunden: " + name);
        }
    }

    @PostMapping("/{name}/clean")
    public ResponseEntity<String> duschen(@PathVariable String name) {
        try {
            petService.duschen(name);
            return ResponseEntity.ok("Haustier geduscht: " + name);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Haustier nicht gefunden: " + name);
        }
    }

    @PostMapping("/{name}/play")
    public ResponseEntity<String> spielen(@PathVariable String name) {
        try {
            petService.spielen(name);
            return ResponseEntity.ok("Haustier spielt: " + name);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Haustier nicht gefunden: " + name);
        }
    }
}