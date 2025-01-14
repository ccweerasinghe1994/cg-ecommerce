package com.cgnexus.ecommerce.seed;

import com.cgnexus.ecommerce.model.Category;
import com.cgnexus.ecommerce.repositories.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Slf4j
@Configuration
@Profile("local")
public class CategorySeeder {

    @Bean
    public CommandLineRunner seedCategories(CategoryRepository categoryRepository) {
        return args -> {
            log.info("Seeding categories started... 🌿🌿🌿🌿🌿🌿🌿🌿🌿🌿🌿🌿");

            Category category1 = new Category();
            category1.setCategoryName("Electronics");

            Category category2 = new Category();
            category2.setCategoryName("Clothing");

            Category category3 = new Category();
            category3.setCategoryName("Home & Garden");

            Category category4 = new Category();
            category4.setCategoryName("Books");

            Category category5 = new Category();
            category5.setCategoryName("Sports & Outdoors");

            Category category6 = new Category();
            category6.setCategoryName("Beauty & Personal Care");

            Category category7 = new Category();
            category7.setCategoryName("Toys & Games");

            Category category8 = new Category();
            category8.setCategoryName("Automotive");

            Category category9 = new Category();
            category9.setCategoryName("Health & Wellness");

            Category category10 = new Category();
            category10.setCategoryName("Jewelry");

            Category category11 = new Category();
            category11.setCategoryName("Pet Supplies");

            Category category12 = new Category();
            category12.setCategoryName("Food & Beverages");

            Category category13 = new Category();
            category13.setCategoryName("Office Supplies");

            Category category14 = new Category();
            category14.setCategoryName("Musical Instruments");

            Category category15 = new Category();
            category15.setCategoryName("Art & Crafts");

            Category category16 = new Category();
            category16.setCategoryName("Baby Products");

            Category category17 = new Category();
            category17.setCategoryName("Tools & Hardware");

            Category category18 = new Category();
            category18.setCategoryName("Computers & Accessories");

            Category category19 = new Category();
            category19.setCategoryName("Cameras & Photography");

            Category category20 = new Category();
            category20.setCategoryName("Mobile Phones");

            Category category21 = new Category();
            category21.setCategoryName("Furniture");

            Category category22 = new Category();
            category22.setCategoryName("Kitchen & Dining");

            Category category23 = new Category();
            category23.setCategoryName("Watches");

            Category category24 = new Category();
            category24.setCategoryName("Shoes");

            Category category25 = new Category();
            category25.setCategoryName("Bags & Luggage");

            Category category26 = new Category();
            category26.setCategoryName("Video Games");

            Category category27 = new Category();
            category27.setCategoryName("Movies & TV");

            Category category28 = new Category();
            category28.setCategoryName("Music");

            Category category29 = new Category();
            category29.setCategoryName("Grocery");

            Category category30 = new Category();
            category30.setCategoryName("Appliances");

            Category category31 = new Category();
            category31.setCategoryName("Fitness Equipment");

            Category category32 = new Category();
            category32.setCategoryName("Outdoor Living");

            Category category33 = new Category();
            category33.setCategoryName("Party Supplies");

            Category category34 = new Category();
            category34.setCategoryName("Travel Gear");

            Category category35 = new Category();
            category35.setCategoryName("Bath & Bedding");

            Category category36 = new Category();
            category36.setCategoryName("Collectibles");

            Category category37 = new Category();
            category37.setCategoryName("Industrial Supplies");

            Category category38 = new Category();
            category38.setCategoryName("Smart Home");

            Category category39 = new Category();
            category39.setCategoryName("Lighting");

            Category category40 = new Category();
            category40.setCategoryName("Holiday & Seasonal");

            Category category41 = new Category();
            category41.setCategoryName("Stationery");

            Category category42 = new Category();
            category42.setCategoryName("Digital Downloads");

            Category category43 = new Category();
            category43.setCategoryName("Safety & Security");

            Category category44 = new Category();
            category44.setCategoryName("Educational Supplies");

            Category category45 = new Category();
            category45.setCategoryName("Wedding Shop");

            Category category46 = new Category();
            category46.setCategoryName("Vintage & Antiques");

            Category category47 = new Category();
            category47.setCategoryName("Garden Tools");

            Category category48 = new Category();
            category48.setCategoryName("Window Treatments");

            Category category49 = new Category();
            category49.setCategoryName("Plumbing");

            Category category50 = new Category();
            category50.setCategoryName("Storage & Organization");

            if (categoryRepository.findAll().isEmpty()) {
                categoryRepository.saveAll(
                        List.of(
                                category1, category2, category3, category4, category5, category6, category7, category8,
                                category9, category10, category11, category12, category13, category14, category15, category16,
                                category17, category18, category19, category20, category21, category22, category23, category24,
                                category25, category26, category27, category28, category29, category30, category31, category32,
                                category33, category34, category35, category36, category37, category38, category39, category40,
                                category41, category42, category43, category44, category45, category46, category47, category48,
                                category49, category50
                        )
                );
            } else {
                log.info("skipped due to Categories already seeded... 🥶🥶🥶🥶🥶 ");
            }

            log.info("Seeding categories ended... 🌿🌿🌿🌿🌿🌿🌿🌿🌿🌿🌿🌿");
        };
    }
}
