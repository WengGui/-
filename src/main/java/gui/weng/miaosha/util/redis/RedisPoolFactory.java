package gui.weng.miaosha.util.redis;

import gui.weng.miaosha.exception.GobalException;
import gui.weng.miaosha.result.CodeMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service
public class RedisPoolFactory {

    @Autowired
    private RedisConfig redisConfig;

    @Bean
    public JedisPool JedisPoolFactory() {
        try {
            JedisPoolConfig poolConfig = new JedisPoolConfig();
            poolConfig.setMaxIdle(redisConfig.getPoolMaxIdle());
            poolConfig.setMaxTotal(redisConfig.getPoolMaxTotal());
            poolConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait() * 1000);
            JedisPool jp = new JedisPool(poolConfig, redisConfig.getHost(), redisConfig.getPort(),
                    redisConfig.getTimeout()*1000, redisConfig.getPassword(), 0);
            return jp;
        }catch (Exception e){
            throw new GobalException(CodeMsg.REDIS_ERROR);
        }
    }
}
