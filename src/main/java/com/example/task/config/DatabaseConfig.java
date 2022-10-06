package com.example.task.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories
public class DatabaseConfig {

    @Bean
    public JedisConnectionFactory connectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName("localhost");
        configuration.setPort(6379);
        return new JedisConnectionFactory(configuration);
    }

    @Bean
    public RedisTemplate<String, Object> meetings() {
        RedisTemplate<String, Object> meetings = new RedisTemplate<>();
        meetings.setConnectionFactory(connectionFactory());
        meetings.setKeySerializer(new StringRedisSerializer());
        meetings.setHashKeySerializer(new StringRedisSerializer());
        meetings.setHashKeySerializer(new JdkSerializationRedisSerializer());
        meetings.setValueSerializer(new JdkSerializationRedisSerializer());
        meetings.setEnableTransactionSupport(true);
        meetings.afterPropertiesSet();
        return meetings;
    }
}

