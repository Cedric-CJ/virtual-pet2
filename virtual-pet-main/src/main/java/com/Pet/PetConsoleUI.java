package com.Pet;

import java.util.Scanner;

public class PetConsoleUI {
    private static PetService petService; // Dienst zur Verwaltung der Haustiere

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Geben Sie den Namen Ihres Haustiers ein:");
        String petName = scanner.nextLine();

        while (true) {
            System.out.println("Wählen Sie eine Option:");
            System.out.println("[1] Haustier anzeigen");
            System.out.println("[2] Füttern");
            System.out.println("[3] Wasser geben");
            System.out.println("[4] Schlafen legen");
            System.out.println("[5] Streicheln");
            System.out.println("[6] Spielen");
            System.out.println("[7] Duschen");
            System.out.println("[0] Programm beenden");

            int input = scanner.nextInt();
            if (input == 0) break;
            handleInput(input, petName);
        }
        scanner.close();
    }

    private static void handleInput(int input, String petName) {
        switch (input) {
            case 1:
                showPet(petName);
                break;
            case 2:
                petService.essen(petName);
                break;
            case 3:
                petService.trinken(petName);
                break;
            case 4:
                petService.schlafen(petName);
                break;
            case 5:
                petService.streicheln(petName);
                break;
            case 6:
                petService.spielen(petName);
                break;
            case 7:
                petService.duschen(petName);
                break;
        }
        showPet(petName);  // Zeigt den aktualisierten Zustand des Haustiers nach jeder Aktion.
    }

    private static void showPet(String petName) {
        Pet pet = petService.getPetDetails(petName);
        System.out.println(pet.getAsciiArt());
        System.out.println("Hunger: " + pet.getHunger());
        System.out.println("Durst: " + pet.getDurst());
        System.out.println("Energie: " + pet.getEnergie());
        System.out.println("Komfort: " + pet.getKomfort());
    }
}