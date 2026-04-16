package org.services;

import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.value.ValueCommands;
import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RedisService {
    
    private ValueCommands<String, String> valueCommands;

    @Inject
    public RedisService(RedisDataSource ds) {
        this.valueCommands = ds.value(String.class);
    }

    public void guardar(String key, String value) {
        valueCommands.set(key, value);
    }

    public String obtener(String key) {
        return valueCommands.get(key);
    }

    public void guardarConExpiracion(String key, String value, long segundos) {
        valueCommands.setex(key, segundos, value);
    }
    
}
