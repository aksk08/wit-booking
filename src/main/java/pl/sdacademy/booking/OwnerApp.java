package pl.sdacademy.booking;

import pl.sdacademy.booking.controller.ItemController;

import java.util.Scanner;

public class OwnerApp {

    public static void main(String[] args) {

        System.out.println("Witam w salonie XXX");
        System.out.println("Menu:");
        System.out.println("1: - pokaz katalog");
        System.out.println("2: - pokaz wydarzenia");

        Scanner scanner = new Scanner(System.in);
        int inputValue = scanner.nextInt();
        if (inputValue == 1) {
           // new ItemController(itemService, eventService).presentCatalog();
        } else if (inputValue == 2) {
            //new ItemController(itemService, eventService).presentEvents();
        }

    }
}
