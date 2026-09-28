package catsmarketplacetsap.spacecatsmarketplace.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CosmicWordValidator.class)
public @interface CosmicWordCheck  {

    String message() default "Name must contain a cosmic word (star, galaxy, comet)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
