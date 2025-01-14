package com.cgnexus.ecommerce.seed;

import com.cgnexus.ecommerce.model.Category;
import com.cgnexus.ecommerce.model.Product;
import com.cgnexus.ecommerce.repositories.CategoryRepository;
import com.cgnexus.ecommerce.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;


@Slf4j
@Configuration
@Profile("local")
public class ProductSeeder {

    @Bean
    public CommandLineRunner seedProducts(CategoryRepository categoryRepository, ProductRepository productRepository) {
        return args -> {
//            only for testing purposes to clear the database
//            productRepository.deleteAll();
            if (!productRepository.findAll().isEmpty()) {
                log.info("Products already seeded... 🥶");
                return;
            }

            log.info("Seeding Products started... 🌿");

            Integer totalCategories = categoryRepository.findAll().size();

            if (totalCategories.equals(0)) {
                log.info("No categories found. Please seed categories first.");
                return;
            }

            List<Category> categories = categoryRepository.findAll();
            List<String> randomProductNames = List.of("Samsung Galaxy S21", "Apple iPhone 12", "Google Pixel 5", "OnePlus 9 Pro", "Xiaomi Mi 11", "Oppo Find X3 Pro", "Sony Xperia 1 III", "Huawei P40 Pro", "LG Wing", "Motorola Edge Plus");
            List<String> randomProductDescription = List.of(
                    "Samsung Galaxy S21 5G | Factory Unlocked Android Cell Phone",
                    "Apple iPhone 12, 64GB, Black - Fully Unlocked (Renewed)",
                    "Google Pixel 5 - 5G Android Phone - Water Resistant - Unlocked Smartphone with Night Sight and Ultrawide Lens - Just Black",
                    "OnePlus 9 Pro 5G LE2120 256GB 12GB RAM Factory Unlocked (GSM Only | No CDMA - not Compatible with Verizon/Sprint) International Version - Stellar Black",
                    "Xiaomi Mi 11 5G (128GB, 8GB) 6.81” Quad HD+ Display, 108MP Camera, Snapdragon 888, 4600mAh Battery, Dual SIM GSM Unlocked (US + Global) 5G Volte International Model (Midnight Gray)",
                    "Oppo Find X3 Pro 5G CPH2173 256GB 12GB RAM Factory Unlocked (GSM Only | No CDMA - not Compatible with Verizon/Sprint) International Version - Gloss Black",
                    "Sony Xperia 1 III 5G XQ-BC72 256GB 12GB RAM Factory Unlocked (GSM Only | No CDMA - not Compatible with Verizon/Sprint) International Version - Frosted Black",
                    "Huawei P40 Pro 5G ELS-NX9 256GB 8GB RAM International Version - Silver Frost",
                    "LG Wing 5G 256GB 8GB RAM LM-F100N Factory Unlocked (GSM Only | No CDMA - not Compatible with Verizon/Sprint) All CDMA Carriers Compatible - Aurora Gray",
                    "Motorola Edge Plus 256GB 12GB RAM XT2061-3 Factory Unlocked (GSM Only | No CDMA - not Compatible with Verizon/Sprint) International Version - Smoky Sangria"
            );
            List<Double> randomPrices = List.of(799.99, 899.99, 999.99, 1099.99, 1199.99, 1299.99, 1399.99, 1499.99, 1599.99, 1699.99);
            List<Double> randomDiscounts = List.of(0.0, 5.0, 10.0, 15.0, 20.0, 25.0, 30.0, 35.0, 40.0, 45.0, 50.0);
            List<Integer> randomQuantities = List.of(100, 200, 300, 400, 500, 600, 700, 800, 900, 1000);
            categories.forEach(category -> {
                log.info("Category: {}", category.getCategoryName());
                Double discount = randomDiscounts.get((int) (Math.random() * randomDiscounts.size()));
                double price = randomPrices.get((int) (Math.random() * randomPrices.size()));
                double discountAmount = price * discount / 100;
                double specialPrice = price - discountAmount;
//                only show 2 decimal places
                specialPrice = Math.round(specialPrice * 100.0) / 100.0;
                Product product = Product.builder()
                        .productName(randomProductNames.get((int) (Math.random() * randomProductNames.size())))
                        .description(randomProductDescription.get((int) (Math.random() * randomProductDescription.size())))
                        .category(category)
                        .price(price)
                        .quantity(randomQuantities.get((int) (Math.random() * randomQuantities.size())))
                        .image("https://m.media-amazon.com/images/I/61O5bX5ZJTL._AC_SL1500_.jpg")
                        .discount(discount)
                        .specialPrice(specialPrice)
                        .build();
                productRepository.save(product);
                log.info("Product saved successfully: {}", product.getProductName());
            });

            log.info("Seeding Products completed... 🌿");
        };
    }
}
