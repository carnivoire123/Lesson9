package ru.gb.lesson9;

import java.util.ArrayList;
import java.util.List;

public class InMemoryCityRepo implements Repo {

    private int counter = 0;
    private List<City> cities = new ArrayList<>();

    private InMemoryCityRepo()
    {
        create("London", 11_000_000);
        create("Paris", 8_500_000);
        create("Prague", 3_500_000);
        create("Tokyo", 20_000_000);
        create("Melbourne", 5_500_000);
        create("Mexico", 22_000_000);
    }

    private static InMemoryCityRepo instance;

    public static InMemoryCityRepo getInstance()
    {
        if(instance == null)
            instance = new InMemoryCityRepo();
        return instance;
    }


    @Override
    public int create(String name, int population) {
        City city = new City(++counter, name, population);
        cities.add(city);
        return city.getId();
    }

    @Override
    public List<City> getAll() {
        return cities;
    }

    @Override
    public void update(City city) {
        for(int i = 0; i < cities.size(); i++)
        {
            if(city.getId() == cities.get(i).getId())
            {
                cities.set(i, city);
                break;
            }
        }
    }

    @Override
    public void delete(City city) {
        for(int i = 0; i < cities.size(); i++)
        {
            if(city.getId() == cities.get(i).getId())
            {
                cities.remove(i);
                break;
            }
        }
    }
}
