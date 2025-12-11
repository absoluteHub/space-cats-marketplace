package catsmarketplacetsap.spacecatsmarketplace.config;

import catsmarketplacetsap.spacecatsmarketplace.domain.Product;
import catsmarketplacetsap.spacecatsmarketplace.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Profile("dev")
public class DataInitializer implements CommandLineRunner {

    private final ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        productRepository.save(new Product(null, "Космічний кокосовий лате",
                "Лате зроблений найкращими котиками-баристами", 7.0));
        productRepository.save(new Product(null, "Космічний баскетбольний м'яч",
                "Баскетбольний м'яч для найкращих котиків-данкерів", 10.0));
        productRepository.save(new Product(null, "Книжка \"Космічні прибульці\" ",
                "Книжка про космічних прибульців", 3.0));
    }
}
