package no.systema.main.model;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)

public @interface UrlDataStoreAnnotationForField {
    public String name();
    public String description();
    
}
