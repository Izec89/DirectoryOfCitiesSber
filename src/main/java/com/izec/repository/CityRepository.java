package com.izec.repository;

import com.izec.domain.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Repository
public interface CityRepository extends CrudRepository<City, Integer> {

    City findByName(String name);

}
