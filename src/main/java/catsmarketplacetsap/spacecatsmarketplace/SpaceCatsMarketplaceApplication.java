package catsmarketplacetsap.spacecatsmarketplace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SpaceCatsMarketplaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpaceCatsMarketplaceApplication.class, args);
    }

}
