package demo.deliveryservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.cbor.Jackson2CborDecoder;
import org.springframework.http.codec.cbor.Jackson2CborEncoder;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.messaging.rsocket.annotation.support.RSocketMessageHandler;
import org.springframework.web.util.pattern.PathPatternRouteMatcher;

@Configuration
public class RSocketConfig {

  @Bean
  public RSocketMessageHandler rSocketMessageHandler() {
    RSocketMessageHandler handler = new RSocketMessageHandler();
    handler.setRSocketStrategies(rSocketStrategies());
    return handler;
  }

  @Bean
  public RSocketStrategies rSocketStrategies() {
    return RSocketStrategies.builder()
        .encoders(encoders -> encoders.add(new Jackson2CborEncoder()))
        .decoders(decoders -> decoders.add(new Jackson2CborDecoder()))
        .routeMatcher(new PathPatternRouteMatcher())
        .build();
  }

}
