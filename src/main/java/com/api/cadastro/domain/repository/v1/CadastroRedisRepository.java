package com.api.cadastro.domain.repository.v1;

import com.api.cadastro.config.RedisConfigDatabaseOne;
import com.api.cadastro.domain.model.v1.CadastroRedis;
import com.api.cadastro.domain.model.v1.CadastroRedisConfigKey;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class CadastroRedisRepository {
    private static final String TOPIC_INFORMATION = "TOPIC_INFORMATION";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final RedisTemplate<String, Long> redisTemplateDatabaseOne;

    private HashOperations<String, Object, Object> hashOperations ;

    public CadastroRedisRepository(
                    RedisConfigDatabaseOne redisConfigDatabaseOne) {
        this.redisTemplateDatabaseOne = redisConfigDatabaseOne.redisTemplateOne();
    }

    public void insertHashKey(String topicName, Map<Object, Object> redisMap) {
        hashOperations =redisTemplateDatabaseOne.opsForHash();
        hashOperations.putAll(topicName, redisMap);
    }

    public void deleteAllKeys() {

        redisTemplateDatabaseOne.execute((RedisCallback<String>) connection -> {
            connection.flushDb();
            return "db1 was flushed!";
        });

    }

    public void insertKeyName(String keyName,CadastroRedisConfigKey cadastroRedisConfigKey) {
        redisTemplateDatabaseOne.opsForHash().put(keyName, 0, objectMapper.convertValue(cadastroRedisConfigKey, Map.class));
    }


}
