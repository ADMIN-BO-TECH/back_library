package co.com.botech.config;

import com.google.maps.GeoApiContext;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnExpression("#{environment.containsProperty('GEOCODING_API_KEY')}")
public class BotechGeocodingAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public GeoApiContext geoApiContext() {
        return new GeoApiContext.Builder()
                .apiKey(System.getenv("GEOCODING_API_KEY"))
                .build();
    }
}
