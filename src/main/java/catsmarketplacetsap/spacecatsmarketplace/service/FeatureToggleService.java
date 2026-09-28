package catsmarketplacetsap.spacecatsmarketplace.service;

import catsmarketplacetsap.spacecatsmarketplace.config.FeatureProperties;
import org.springframework.stereotype.Service;

@Service
public class FeatureToggleService {

    private final FeatureProperties properties;

    public FeatureToggleService(FeatureProperties properties) {
        this.properties = properties;
    }

    public boolean check(String featureName) {
        if (properties.getToggles() == null) {
            return false;
        }
        return properties.getToggles().getOrDefault(featureName, false);
    }
}