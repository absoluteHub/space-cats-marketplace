package catsmarketplacetsap.spacecatsmarketplace.aspect;

import catsmarketplacetsap.spacecatsmarketplace.annotation.FeatureToggle;
import catsmarketplacetsap.spacecatsmarketplace.service.FeatureToggleService;
import catsmarketplacetsap.spacecatsmarketplace.service.exception.FeatureNotAvailableException;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class FeatureToggleAspect {

    private final FeatureToggleService featureToggleService;

    @Around("@annotation(featureToggle)")
    public Object checkFeature(ProceedingJoinPoint joinPoint, FeatureToggle featureToggle) throws Throwable {
        String featureName = featureToggle.value();

        if (featureToggleService.check(featureName)) {
            return joinPoint.proceed();
        } else {
            throw new FeatureNotAvailableException (
                    String.format("Feature '%s' is currently disabled!", featureName)
            );
        }
    }
}