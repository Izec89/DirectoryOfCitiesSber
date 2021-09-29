package com.izec.service;


import com.izec.domain.City;
import com.izec.exception.FileParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ConsoleService {

    private CityService cityService;
    private FileService fileService;

    @Autowired
    public ConsoleService(CityService cityService, FileService fileService) {
        this.cityService = cityService;
        this.fileService = fileService;
    }

    public void startProgram() throws InterruptedException, FileParseException {
        List<String> list = fileService.fileParsing();
        if (list != null) {
            saveAll(list);
        }
        System.out.println("Добро пожаловать в электронный справочник городов!");
        Thread.sleep(500);
        System.out.println("Нажмите нужную цифру...");
    }

    public void stopProgram() {
        System.out.println("До свидания!");
        System.exit(0);
    }

    public void help() {
        System.out.println("1  - Вывести список городов");
        System.out.println("2  - Вывести список городов а->я");
        System.out.println("3  - Вывести список городов, отсортированных по региону и имени");
        System.out.println("4  - Вывести самый многочисленный город");
        System.out.println("5  - Вывести количество городов по регионам");
        System.out.println("exit - Выход");
    }

    public void saveAll(List<String> list) {
        list.forEach(city -> {
            String[] arr = city.split(";");
            cityService.addCity(new City(arr[0], arr[1], arr[2], Integer.parseInt(arr[3]), Integer.parseInt(arr[4])));
        });
    }
}
