package catsmarketplacetsap.spacecatsmarketplace.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@Getter
@ConfigurationProperties(prefix = "feature")
@RequiredArgsConstructor
public class FeatureProperties {

    private final Map<String, Boolean> toggles;

}
