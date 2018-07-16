package gui.weng.miaosha.util.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import com.alibaba.fastjson.JSON;


@Service
public class RedisService {

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private RedisConfig redisConfig;

    public <T>T get(String key , Class<T> clazz){
        Jedis jedis = null;
        try  {
            jedis = jedisPool.getResource();
            String str = jedis.get(key);
            T t = stringToBean(str,clazz);
            return t;
        }finally {
            returnToPoll(jedis);
        }
    }

    public <T>boolean set(String key , T value){
        Jedis jedis = null;
        try  {
            jedis = jedisPool.getResource();
            String str = beanToString(value);

           jedis.set(key,str);
            return true;
        }finally {
            returnToPoll(jedis);
        }
    }

    private void returnToPoll(Jedis jedis) {
        if(jedis!=null){
            jedis.close();
        }
    }

    private <T> T stringToBean(String str ,Class<T> clazz) {
        if(str == null || str.length() <= 0 || clazz ==null){
            return null;
        }

        if (clazz == int.class || clazz == Integer.class){
            return (T) Integer.valueOf(str);
        }else if(clazz == String.class){
            return (T)str;
        }else if (clazz == long.class || clazz==Long.class){
            return (T)Long.valueOf(str);
        }else {
            return JSON.toJavaObject(JSON.parseObject(str),clazz);
        }
    }

    private <T> String beanToString(T value) {
        if(value == null){
            return null;
        }

        Class<?> clazz= value.getClass();
        if (clazz == int.class || clazz == Integer.class){
            return value+"";
        }else if(clazz == String.class){
            return (String)value;
        }else if (clazz == long.class || clazz==Long.class){
            return value+"";
        }else{
            return JSON.toJSONString(value);
        }
    }

}














































