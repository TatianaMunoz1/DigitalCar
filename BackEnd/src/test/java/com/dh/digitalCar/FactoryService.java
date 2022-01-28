package com.dh.digitalCar;

import com.dh.digitalCar.dtos.CarDto;
import com.dh.digitalCar.entities.*;
import com.dh.digitalCar.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//@Profile("test")
@Service
public class FactoryService {
    private ModelMapper modelMapper;
    private CityRepository cityRepository;
    private CategoryRepository categoryRepository;
    private FeatureRepository featureRepository;
    private ImageRepository imageRepository;
    private CarRepository carRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BookingRepository bookingRepository;
    private PoliticsRepository politicsRepository;
//    private static List<>

    public FactoryService(ModelMapper modelMapper, CityRepository cityRepository, CategoryRepository categoryRepository, FeatureRepository featureRepository, ImageRepository imageRepository, CarRepository carRepository, UserRepository userRepository, RoleRepository roleRepository, BookingRepository bookingRepository) {
        this.modelMapper = modelMapper;
        this.cityRepository = cityRepository;
        this.categoryRepository = categoryRepository;
        this.featureRepository = featureRepository;
        this.imageRepository = imageRepository;
        this.carRepository = carRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bookingRepository = bookingRepository;
    }

    public List<City> cities(Integer quantity, Boolean persisted) {
        List<City> cityList = new ArrayList<>();

        City city;
        for (int i = 0; i < quantity; i++) {
            city = new City("type" + i, "name" + i, "country" + i, (double) i, (double) i);

            cityList.add(city);
        }

        if (persisted)
            for (City city1 : cityList) {
                city1 = cityRepository.save(city1);
            }

        return cityList;
    }

    public List<Category> categories(Integer quantity, Boolean persisted) {
        List<Category> categoryList = new ArrayList<>();

        Category category;
        for (int i = 0; i < quantity; i++) {
            category = new Category("title" + i, "description" + i, "www.imgUrl" + i + ".com");

            categoryList.add(category);
        }

        if (persisted)
            for (Category category1 : categoryList) {
                category1 = categoryRepository.save(category1);
            }

        return categoryList;
    }

    public List<Feature> features(Integer quantity, Boolean persisted) {
        List<Feature> featureList = new ArrayList<>();

        Feature feature;
        for (int i = 0; i < quantity; i++) {
            feature = new Feature("name" + i, "icon" + i);

            featureList.add(feature);
        }

        if (persisted)
            for (Feature feature1 : featureList) {
                feature1 = featureRepository.save(feature1);
            }

        return featureList;
    }

    public List<Image> images(Integer quantity, Boolean persisted) {
        List<Image> imageList = new ArrayList<>();

        Image image;
        for (int i = 0; i < quantity; i++) {
            image = new Image("title" + i, i, "www.url" + i + ".com/img");

            imageList.add(image);
        }

        if (persisted)
            for (Image image1 : imageList) {
                image1 = imageRepository.save(image1);
            }

        return imageList;
    }

    public List<Car> cars(Integer quantity, Boolean persisted) {
        List<Car> carList = new ArrayList<>();

        Car car;
        for (int i = 0; i < quantity; i++) {
            car = new Car("name" + i, "description" + i);

            car.setFeature(features(1, persisted).get(0));
            car.setImage(images(1, persisted).get(0));
            car.setCategory(categories(1, persisted).get(0));
            car.setCity(cities(1, persisted).get(0));
            car.setAddress("address" + i);

            carList.add(car);
        }

        if (persisted)
            for (Car car1 : carList) {
                car1 = carRepository.save(car1);
            }

        return carList;
    }

    public List<CarDto> carDtos(Integer quantity, Boolean persisted) {
        List<Car> carList = cars(quantity, persisted);
        List<CarDto> carDtoList = new ArrayList<>();

        for (Car car : carList) {
            carDtoList.add(modelMapper.map(car, CarDto.class));
        }

        return carDtoList;
    }

    public List<User> users(Integer quantity, Boolean persisted, String rol) {
        List<User> userList = new ArrayList<>();

        User user;
        for (int i = 0; i < quantity; i++) {
            user = new User("name" + i, "lastName" + i, "name" + i + "@email.com",
                    "password" + i, null);

            if (rol != null) {
                if (rol.equals("user")) {
                    if (persisted)
                        user.setRole(roleRepository.save(new Role("user")));
                    else
                        user.setRole(new Role("user"));
                } else if (rol.equals("admin")) {
                    if (persisted)
                        user.setRole(roleRepository.save(new Role("admin")));
                    else
                        user.setRole(new Role("admin"));
                }

            }

            userList.add(user);
        }

        if (persisted)
            for (User user1 : userList) {
                user1 = userRepository.save(user1);
            }

        return userList;
    }

    public Booking booking(Integer quantity, Boolean persisted, String start, String end) {
        Car car = cars(1, persisted).get(0);
        User user = users(1, persisted, "user").get(0);

        LocalDateTime startDateTime = LocalDateTime.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        Booking booking = new Booking(car, user, startDateTime, endDate);

        booking = bookingRepository.save(booking);

        return booking;
    }

    public Politics politics(Boolean persisted) {
        List<City> cityList = new ArrayList<>();

        Politics politics = new Politics();
        politics.setCancellation("cancellation");
        politics.setHealthAndSecurity("healthAndSecurity");
        politics.setDelivery("delivery");

        if (persisted)
            politics = politicsRepository.save(politics);

        return politics;
    }

//    @Autowired
//    public void setModelMapper(ModelMapper modelMapper) {
//        this.modelMapper = modelMapper;
//    }
//
//    @Autowired
//    public void setCityRepository(CityRepository cityRepository) {
//        this.cityRepository = cityRepository;
//    }
//
//    @Autowired
//    public void setCategoryRepository(CategoryRepository categoryRepository) {
//        this.categoryRepository = categoryRepository;
//    }
//
//    @Autowired
//    public void setFeatureRepository(FeatureRepository featureRepository) {
//        this.featureRepository = featureRepository;
//    }
//
//    @Autowired
//    public void setImageRepository(ImageRepository imageRepository) {
//        this.imageRepository = imageRepository;
//    }
//
//    @Autowired
//    public void setCarRepository(CarRepository carRepository) {
//        this.carRepository = carRepository;
//    }
//
//    @Autowired
//    public void setUserRepository(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Autowired
//    public void setRoleRepository(RoleRepository roleRepository) {
//        this.roleRepository = roleRepository;
//    }
//
//    @Autowired
//    public void setBookingRepository(BookingRepository bookingRepository) {
//        this.bookingRepository = bookingRepository;
//    }
//
//    @Autowired
//    public void setPoliticsRepository(PoliticsRepository politicsRepository) {
//        this.politicsRepository = politicsRepository;
//    }
}
