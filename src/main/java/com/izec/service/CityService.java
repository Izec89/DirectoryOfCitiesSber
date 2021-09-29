package com.izec.service;

import com.izec.domain.City;
import com.izec.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CityService {

    private CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    /**
     * Метод добавляет в базу данных только уникальный город
     *
     * @param city
     * @return
     */
    public boolean addCity(City city) {
        City cityFromDB = cityRepository.findByName(city.getName());

        if (cityFromDB != null) {
            if (cityFromDB.equals(city)) {
                return false;
            }
        }

        cityRepository.save(city);
        return true;
    }

    /**
     * Метод возвращает полный список городов
     *
     * @return
     */
    public Iterable<City> getAll() {
        return cityRepository.findAll();
    }

    /**
     * Метод возвращает отсортированный А -> Я список городов
     *
     * @return
     */
    public List<City> getSortAllByName() {
        return StreamSupport.stream(getAll().spliterator(), false)
                .sorted(Comparator.comparing(City::getName)).collect(Collectors.toList());
    }

    /**
     * Метод возвращает список городов, отсортированный по
     * федеральному округу и имени
     *
     * @return
     */
    public List<City> getSortAllByDistrictAndName() {
        return StreamSupport.stream(cityRepository.findAll().spliterator(), false)
                .sorted(Comparator.comparing(City::getDistrict).thenComparing(City::getName))
                .collect(Collectors.toList());
    }

    /**
     * Метод возвращает самый большой по численности город
     *
     * @return
     */
    public City getTheMostNumerous() {
        return StreamSupport.stream(cityRepository.findAll().spliterator(), false)
                .max(Comparator.comparing(City::getPopulation)).get();
    }

    /**
     * Метод возвращает ассоциативный массив из регионов и количества
     * городов каждого региона
     *
     * @return
     */
    public Map<String, Long> numberByRegion() {
        return StreamSupport.stream(cityRepository.findAll().spliterator(), false)
                .collect(Collectors.groupingBy(City::getRegion, Collectors.counting()));
    }

}
