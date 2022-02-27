package xxx.config;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;

@Configuration
public class SpringConfig {

  @Value("${spring.kafka.producer.topic}")
  private String topicName;

  @Autowired
  private KafkaProperties kafkaProperties;
  
  @Bean
  public NewTopic topic() {

    return new NewTopic(topicName, 1, (short) 1);
  }

  @Bean
  public ProducerFactory<String, String> producerFactory() {

    Map<String, Object> config = new HashMap<>(kafkaProperties.buildAdminProperties());

    config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

    return new DefaultKafkaProducerFactory<>(config);
  }

  @Bean
  public KafkaTemplate<String, String> kafkaTemplate() {

    return new KafkaTemplate<>(producerFactory());
  }
}
