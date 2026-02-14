package com.example.notificationSender.Configuration;

import com.example.notificationSender.Model.EventJob;
import com.example.notificationSender.Model.VerifySignUp;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
// MODERN IMPORTS (No "2", uses "Jackson")
import org.springframework.kafka.support.converter.StringJacksonJsonMessageConverter;
import org.springframework.kafka.support.mapping.DefaultJacksonJavaTypeMapper;
import org.springframework.kafka.support.mapping.JacksonJavaTypeMapper;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {

    @Bean
    public ConsumerFactory<String, Object> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "email-group");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

        // Always use String for the base layer
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());

        // NEW: StringJacksonJsonMessageConverter (Jackson 3 ready)
        factory.setRecordMessageConverter(new StringJacksonJsonMessageConverter());
        return factory;
    }

    @Bean
    public DefaultJacksonJavaTypeMapper typeMapper() {
        // NEW: DefaultJacksonJavaTypeMapper
        DefaultJacksonJavaTypeMapper typeMapper = new DefaultJacksonJavaTypeMapper();

        // Trust your package path
        typeMapper.addTrustedPackages("com.example.notificationSender.Model");

        // Use ID headers for mapping
        typeMapper.setTypePrecedence(JacksonJavaTypeMapper.TypePrecedence.TYPE_ID);

        // Map tokens to actual classes
        Map<String, Class<?>> mappings = new HashMap<>();
        mappings.put("event", EventJob.class);
        mappings.put("verify", VerifySignUp.class);

        typeMapper.setIdClassMapping(mappings);
        return typeMapper;
    }
}