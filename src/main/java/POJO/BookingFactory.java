package POJO;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class BookingFactory {

    final static Faker faker = new Faker();

    // Define the array of meal types
    static String[] mealTypes = {"Breakfast", "Lunch", "Dinner", "Snack"};

    // Minimum and maximum gap in days
    static int minGap = 3;
    static int maxGap = 15;

    // Generate the first date: at least 1 day from now
    static LocalDate firstDate = LocalDate.now().plusDays(ThreadLocalRandom.current().nextLong(1, 30));

    // Generate a random gap between minGap and maxGap
    static long gap = ThreadLocalRandom.current().nextLong(minGap, maxGap + 1);

    // Second date = firstDate + gap
    static LocalDate secondDate = firstDate.plusDays(gap);

    public static Booking createRandomBooking(){
        //choose the dates
        BookingDates dates = new BookingDates(
                firstDate.toString(),
                secondDate.toString()
        );

        //generate random booking data
        return new Booking(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.number().numberBetween(100, 500),
                true,
                dates,
                faker.options().option(mealTypes)
        );
    }
}
