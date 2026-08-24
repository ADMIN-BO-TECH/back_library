package co.com.botech.config;

import com.google.maps.GeoApiContext;
import co.com.botech.util.geocoding.GeocodingService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnClass(GeoApiContext.class)                                    // ← NUEVO
@ConditionalOnExpression("#{environment.containsProperty('GEOCODING_API_KEY')}")
public class BotechGeocodingAutoConfiguration {

    // Beans for GeoApiContext and GeocodingService
    @Bean
    @ConditionalOnMissingBean(GeoApiContext.class)
    public GeoApiContext geoApiContext() {
        return new GeoApiContext.Builder()
                .apiKey(System.getenv("GEOCODING_API_KEY"))
                .build();
    }

    @Bean
    @ConditionalOnMissingBean(GeocodingService.class)
    public GeocodingService geocodingService(GeoApiContext geoApiContext) {
        return new GeocodingService(geoApiContext);
    }
}