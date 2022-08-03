package pe.com.project.bank.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.ReactiveRedisMessageListenerContainer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import pe.com.project.bank.model.Client;

@Configuration
public class ClientConfiguration {
    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("clients:queue");
    }

    @Bean
    ReactiveRedisMessageListenerContainer container(ReactiveRedisConnectionFactory factory) {

        ReactiveRedisMessageListenerContainer container = new ReactiveRedisMessageListenerContainer(factory);
        container.receive(topic());

        return container;
    }

    @Bean
    ReactiveRedisOperations<String, Client> redisOperations(ReactiveRedisConnectionFactory factory) {
        Jackson2JsonRedisSerializer<Client> serializer = new Jackson2JsonRedisSerializer<>(Client.class);

        RedisSerializationContext.RedisSerializationContextBuilder<String, Client> builder =
                RedisSerializationContext.newSerializationContext(new StringRedisSerializer());

        RedisSerializationContext<String, Client> context = builder.value(serializer).build();

        return new ReactiveRedisTemplate<>(factory, context);
    }
}
