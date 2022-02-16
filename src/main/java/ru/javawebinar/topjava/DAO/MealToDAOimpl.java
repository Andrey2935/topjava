package ru.javawebinar.topjava.DAO;

import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.model.MealToDAO;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MealToDAOimpl implements MealToDAO {
    private static final AtomicInteger AUTO_ID = new AtomicInteger(0);
    private static final Map<Integer, MealTo> meals = new HashMap<>();
    private static final int caloriesPerDay = 2000;

    static {
        List<MealTo> mealTo = MealsUtil.unfilteredByStreams(MealsUtil.meals, 2000);
        for (MealTo a : mealTo){
            a.setId(AUTO_ID.getAndIncrement());
            meals.put(a.getId(), a);
        }
        MealTo mealTo1 = new MealTo();
        mealTo1.setId(AUTO_ID.getAndIncrement());
        mealTo1.setDateTime(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0));
        meals.put(mealTo1.getId(), mealTo1);

        MealTo mealTo2 = new MealTo();
        mealTo2.setId(AUTO_ID.getAndIncrement());
        mealTo2.setDateTime(LocalDateTime.of(2020, Month.JANUARY, 30, 11, 0));
        meals.put(mealTo2.getId(), mealTo2);
    }
    private List<MealTo> getExcess(){

        Map<LocalDate, Integer> caloriesSumByDate = MealToDAOimpl.meals.values().stream()
                .collect(
                        Collectors.groupingBy(MealTo::getDate, Collectors.summingInt(MealTo::getCalories))
                );
        return MealToDAOimpl.meals.values().stream()
                .map(meal -> new MealTo(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(),
                        caloriesSumByDate.get(meal.getDate()) > MealToDAOimpl.caloriesPerDay))
                .collect(Collectors.toList());
    }

    @Override
    public List<MealTo> allMealTo() {
        return getExcess();
    }

    @Override
    public void add(MealTo mealTo) {
        mealTo.setId(AUTO_ID.getAndIncrement());
        meals.put(mealTo.getId(), mealTo);
    }

    @Override
    public void delete(MealTo mealTo) {
        meals.remove(mealTo.getId());
    }

    @Override
    public void edit(MealTo mealTo) {
        meals.put(mealTo.getId(), mealTo);
    }

    @Override
    public MealTo getById(int id) {
        return meals.get(id);
    }
}
