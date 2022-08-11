package vttp2022.app.ssfworkshop17.config;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import vttp2022.app.ssfworkshop17.model.Currency;

@Configuration
public class ReDisConfig {
    
     private static final Logger logger = LoggerFactory.getLogger(ReDisConfig.class);

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private Optional<Integer> redisPort;

    @Value("${spring.redis.password}")
    private String redisPassword;

//     @Bean 
//     @Scope("singleton")
//     public RedisTemplate<String, Object> RedisTemplate(){
//         final RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
//         config.setHostName(redisHost);
//         config.setPort(redisPort.get());
//         config.setPassword(redisPassword);

//         final JedisClientConfiguration jedisClient = JedisClientConfiguration.builder().build();
//         final JedisConnectionFactory jedisFac = new JedisConnectionFactory(config, jedisClient);
//         jedisFac.afterPropertiesSet();
//         logger.info("redis host port > {redisHost} {redisPort}", redisHost, redisPort);
//         RedisTemplate<String, Object> template = new RedisTemplate();
        
//         template.setConnectionFactory(jedisFac);
//         template.setKeySerializer(new StringRedisSerializer());

//         RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer(getClass().getClassLoader());
//         template.setValueSerializer(
//             serializer
//         );
//         return template;
//     }
    
//         //RedisTemplate<String,Currency> template2 = new RedisTemplate();

        

        @Bean
        @Scope("singleton")
        public RedisTemplate<String, Currency> redisTemplate() {
        final RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(redisHost);
        config.setPort(redisPort.get());
        config.setPassword(redisPassword);
        Jackson2JsonRedisSerializer jackson2JsonJsonSerializer = new Jackson2JsonRedisSerializer(Currency.class);

        final JedisClientConfiguration jedisClient = JedisClientConfiguration.builder().build();
        final JedisConnectionFactory jedisFac = new JedisConnectionFactory(config, jedisClient);
        jedisFac.afterPropertiesSet();
        RedisTemplate<String, Currency> template = new RedisTemplate<String, Currency>();
        template.setConnectionFactory(jedisFac);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(jackson2JsonJsonSerializer);
        template.setHashKeySerializer(template.getKeySerializer());
        template.setHashValueSerializer(template.getValueSerializer());
        return template;
        }
        //The above is to save and get Java Objects (eg Student, BoardGame) from Redis
}
