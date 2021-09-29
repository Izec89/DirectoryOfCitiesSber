package com.izec;

import com.izec.exception.FileParseException;
import com.izec.service.CityService;
import com.izec.service.ConsoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;


@SpringBootApplication
public class Application implements CommandLineRunner {

    private CityService cityService;
    private ConsoleService consoleService;

    @Autowired
    public Application(CityService cityService, ConsoleService consoleService) {
        this.cityService = cityService;
        this.consoleService = consoleService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    /**
     * Запуск консоли в вечном цикле
     * @param args
     * @throws InterruptedException
     * @throws FileParseException
     */
    @Override
    public void run(String... args) throws InterruptedException, FileParseException {
        consoleService.startProgram();
        Scanner scanner = new Scanner(System.in);
        consoleService.help();
        String command = scanner.nextLine();
        while (!command.equalsIgnoreCase("exit")) {
            if (command.equalsIgnoreCase("1")) {
                cityService.getAll().forEach(city -> {
                    System.out.println(city.toString());
                });
            } else if (command.equalsIgnoreCase("2")) {
                cityService.getSortAllByName().forEach(city -> {
                    System.out.println(city.toString());
                });
            } else if (command.equalsIgnoreCase("3")) {
                cityService.getSortAllByDistrictAndName().forEach(city -> {
                    System.out.println(city.toString());
                });
            } else if (command.equalsIgnoreCase("4")) {
                System.out.println(cityService.getTheMostNumerous().toString());
            } else if (command.equalsIgnoreCase("5")) {
                cityService.numberByRegion().forEach((k, v) -> {
                    System.out.println(k + " - " + v);
                });
            } else {
                consoleService.help();
            }
            command = scanner.nextLine();
        }
        consoleService.stopProgram();
    }
}