package com.parts.carpartsapi.initial_data_seed;

import com.parts.carpartsapi.entity.Car;
import com.parts.carpartsapi.entity.CarPart;
import com.parts.carpartsapi.entity.ServiceAction;
import com.parts.carpartsapi.manager.CarPartManager;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
public class TheMightySeeder {


    public CarPartManager carPartManager;

    public TheMightySeeder(CarPartManager carPartManager) {
        this.carPartManager = carPartManager;
    }




    @EventListener(value = ApplicationReadyEvent.class)
    private void PopulateDB() {

        //CARS
        Car car = new Car();
        CarPart carPart = new CarPart();
        ServiceAction serviceAction = new ServiceAction();
        car.setBrand("HONDA");
        car.setModel("ACCORD");
        car.setProdStartDate(LocalDate.of(2002, 5, 12));
        car.setProdEndDate((LocalDate.of(2010, 12, 01)));

        carPart.setCpartname("Amortyzator XYZ");
        carPart.setDescription("Amortyzator firmy XYZ. Opis ......... Wymiary 122x322x122...");
        carPart.setPrice(199.99);
        carPart.setShippingdays(1);
        carPart.setTags(Arrays.asList("Doskonaly wybor", "Swietna marka", "Ceniony na rynku"));


        serviceAction.setActname("Wymiana sprezyny");
        serviceAction.setServStartDate(LocalDate.of(2020, 06, 01));
        serviceAction.setServEndDate(LocalDate.of(2020, 06, 15));
        carPart.setServiceAction(Arrays.asList(serviceAction));
        serviceAction.setCarParts(carPart);
        car.setParts(Arrays.asList(carPart));

        carPart.setCars(Arrays.asList(car));
        carPartManager.save(carPart);


        Car car2 = new Car();
        car2.setBrand("AUDI");
        car2.setModel("A4 B6");
        car2.setProdStartDate(LocalDate.of(2000, 01, 01));
        car2.setProdEndDate(LocalDate.of(2004, 06, 23));
        Car car3 = new Car();
        car3.setBrand("AUDI");
        car3.setModel("A6 C5");
        car3.setProdStartDate(LocalDate.of(1995, 10, 01));
        car3.setProdEndDate(LocalDate.of(1999, 10, 01));
        CarPart carPart2 = new CarPart();
        carPart2.setCpartname("Lozysko QWE");
        carPart2.setPrice(100.00);
        carPart2.setShippingdays(2);
        carPart2.setDescription("Lozysko firmy QWE. Rozmiar..... Wymiary.... Zastosowanie...");
        carPart2.setTags(Arrays.asList("Doskonaly wybor", "Swietna marka", "Ceniony na rynku"));
        carPart2.setServiceAction(null);
        carPart2.setCars(Arrays.asList(car2, car3));
        CarPart carPart3 = new CarPart();
        carPart3.setCpartname("Sprezyna amortyzatora ABC");
        carPart3.setDescription("Sprezyna amortyzatora firmy ABC. Wysokosc/szerokosc/twardosc...");
        carPart3.setTags(Arrays.asList("Doskonaly", "Rewelacyjna jakosc", "10/10 w testach"));
        carPart3.setServiceAction(null);
        carPart3.setShippingdays(14);
        carPart3.setPrice(59.99);
        carPart3.setCars(Arrays.asList(car2, car3));
        car2.setParts(Arrays.asList(carPart2, carPart3));
        car3.setParts(Arrays.asList(carPart2, carPart3));
        carPartManager.save(carPart2);

        Car car5 = new Car();
        car5.setBrand("AUDI");
        car5.setModel("A8");
        car5.setProdEndDate(LocalDate.of(2010,01,01));
        car5.setProdStartDate(LocalDate.of(2018,05,03));


        CarPart carPart4 = new CarPart();
        carPart4.setCpartname("Kierownica AUDI");
        carPart4.setTags(Arrays.asList("Najlepsza w swoim rodzaju", "Najlepsza jakosc"));
        carPart4.setPrice(399);
        carPart4.setDescription("Wspaniala kierownica do Twojego AUDI");
        carPart4.setCars(Arrays.asList(car5));


        CarPart carPart5 = new CarPart();
        carPart5.setCpartname("GRILL AUDI");
        carPart5.setTags(Arrays.asList("Swietny element", "Ponadprzecietna zywotnosc"));
        carPart5.setPrice(199);
        carPart5.setDescription("Chromowany grill");
        carPart5.setCars(Arrays.asList(car5));
        car5.setParts(Arrays.asList(carPart5,carPart4));
        carPart4.setShippingdays(3);
        carPart4.setShippingdays(4);
        carPartManager.save(carPart4);
        carPartManager.save(carPart5);

        Car car4 = new Car();
        car4.setBrand("OPEL");
        car4.setModel("ASTRA H");
        car4.setProdStartDate(LocalDate.of(2000,01,01));
        car4.setProdEndDate(LocalDate.of(2010,01,01));

        ServiceAction serviceAction1 = new ServiceAction();
        serviceAction1.setActname("Nawlekanie nowego uzwojenia");
        serviceAction1.setServStartDate(LocalDate.of(2020,02,01));
        serviceAction1.setServEndDate(LocalDate.of(2020,02,13));
        ServiceAction serviceAction2 = new ServiceAction();
        serviceAction2.setActname("Wymiana opornika");
        serviceAction2.setServStartDate(LocalDate.of(2020,03,01));
        serviceAction2.setServEndDate(LocalDate.of(2020,03,05));

        CarPart carPart6 = new CarPart();
        carPart6.setCpartname("Silnik wycieraczek Opel");
        carPart6.setTags(Arrays.asList("Swietny element", "Ponadprzecietna zywotnosc"));
        carPart6.setPrice(299);
        carPart6.setDescription("Silnik wycieraczek firmy ABC");
        carPart6.setCars(Arrays.asList(car4));
        carPart6.setServiceAction(Arrays.asList(serviceAction1, serviceAction2));
        serviceAction1.setCarParts(carPart6);
        serviceAction2.setCarParts(carPart6);
        CarPart carPart7 = new CarPart();
        carPart7.setCpartname("Szyba");
        carPart7.setDescription("Szyba przednia");
        carPart7.setTags(Arrays.asList("Doskonala jakosc", "Krystaliczny widok"));
        carPart7.setShippingdays(3);
        carPart7.setPrice(599);
        carPart7.setCars(Arrays.asList(car4));
        CarPart carPart8 = new CarPart();
        carPart8.setCpartname("Szyba");
        carPart8.setDescription("Szyba tylnia");
        carPart8.setTags(Arrays.asList("Doskonala jakosc", "Krystaliczny widok"));
        carPart8.setShippingdays(3);
        carPart8.setPrice(400);
        carPart8.setCars(Arrays.asList(car4));
        CarPart carPart9 = new CarPart();
        carPart9.setCpartname("Szyba");
        carPart9.setDescription("Szyba boczna przod");
        carPart9.setTags(Arrays.asList("Doskonala jakosc", "Krystaliczny widok"));
        carPart9.setShippingdays(3);
        carPart9.setPrice(400);
        carPart9.setCars(Arrays.asList(car4));
        CarPart carPart10 = new CarPart();
        carPart10.setCpartname("Szyba");
        carPart10.setDescription("Szyba boczna przod");
        carPart10.setTags(Arrays.asList("Doskonala jakosc", "Krystaliczny widok"));
        carPart10.setShippingdays(3);
        carPart10.setPrice(400);
        carPart10.setCars(Arrays.asList(car4));
        car4.setParts(Arrays.asList(carPart7,carPart6,carPart8,carPart9,carPart10));
        carPartManager.save(carPart6);
        carPartManager.save(carPart7);
        carPartManager.save(carPart8);
        carPartManager.save(carPart9);





    }
}
