package ru.javawebinar.topjava.model;

import java.util.List;

public interface MealToDAO {
    List<MealTo> allMealTo();
    void add(MealTo mealTo);
    void delete(MealTo mealTo);
    void edit(MealTo mealTo);
    MealTo getById(int id);
}
