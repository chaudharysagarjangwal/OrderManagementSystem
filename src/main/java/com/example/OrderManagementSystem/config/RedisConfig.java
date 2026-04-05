package com.example.OrderManagementSystem.config;

import com.example.OrderManagementSystem.dto.ProductResponseDto;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.util.List;

@Configuration
@EnableCaching
public class RedisConfig {

        @Bean
        public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {

            ObjectMapper objectMapper = new ObjectMapper();

            // Serializer for List<ProductResponseDto>
            JavaType listType = objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, ProductResponseDto.class);
            Jackson2JsonRedisSerializer<List<ProductResponseDto>> listSerializer =
                    new Jackson2JsonRedisSerializer<>(objectMapper, listType);

            // Serializer for single ProductResponseDto
            Jackson2JsonRedisSerializer<ProductResponseDto> singleSerializer =
                    new Jackson2JsonRedisSerializer<>(objectMapper, ProductResponseDto.class);

            // "products" cache → stores List
            RedisCacheConfiguration productsConfig = RedisCacheConfiguration.defaultCacheConfig()
                    .serializeValuesWith(RedisSerializationContext.SerializationPair
                            .fromSerializer(listSerializer))
                    .entryTtl(Duration.ofMinutes(10));

            // "product" cache → stores single object
            RedisCacheConfiguration productConfig = RedisCacheConfiguration.defaultCacheConfig()
                    .serializeValuesWith(RedisSerializationContext.SerializationPair
                            .fromSerializer(singleSerializer))
                    .entryTtl(Duration.ofMinutes(10));

            return RedisCacheManager.builder(connectionFactory)
                    .withCacheConfiguration("products", productsConfig)
                    .withCacheConfiguration("product", productConfig)
                    .build();
        }
    }
