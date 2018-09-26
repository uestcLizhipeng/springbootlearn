package com.lzp.springbootlearn.cache.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RedisUtil {
    private RedisTemplate<String,Object> redisTemplate;

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * @Description:
     * @Param:  * @param key
     * @param time
     * @return: boolean
     * @Author: lzp
     * @Date: 2018/9/26
     */

    public boolean expire(String key,long time){
        try {
            if (time > 0){
                redisTemplate.expire(key,time,TimeUnit.SECONDS);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    /**
     * @Description:
     * @Param:  * @param key
     * @return: long
     * @Author: lzp
     * @Date: 2018/9/26
     */
    public long getExpire(String key){
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }

    /**
     * @Description:
     * @Param:  * @param key
     * @return: boolean
     * @Author: lzp
     * @Date: 2018/9/26
     */
    public boolean hasKey(String key){

        try {
            return redisTemplate.hasKey(key);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @Description:
     * @Param:  * @param key
     * @return: void
     * @Author: lzp
     * @Date: 2018/9/26
     */
    @SuppressWarnings("unchecked")
    public void del(String ... key){

        if (key != null && key.length > 0){
            if (key.length == 1){
                redisTemplate.delete(key[0]);
            }else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    public Object get(String key){
        return key == null?null:redisTemplate.opsForValue().get(key);
    }

    public boolean set(String key,Object value){
        try {
            redisTemplate.opsForValue().set(key,value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean set(String key,Object value,long time){
        try {
            if (time > 0){
                redisTemplate.opsForValue().set(key,value,time,TimeUnit.SECONDS);
            }else {
                set(key,value);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public long incr(String key,long delta){
        if (delta < 0){
            throw new RuntimeException("delta must >0");
        }
        return redisTemplate.opsForValue().increment(key,delta);
    }

    public long decr(String key,long delta){
        if (delta < 0){
            throw new RuntimeException("delta must >0");
        }
        return redisTemplate.opsForValue().increment(key,-delta);
    }

    public Object hget(String key,String item){
        return redisTemplate.opsForHash().get(key,item);
    }
    public Map<Object,Object> hmget(String key){
        return redisTemplate.opsForHash().entries(key);
    }

    public boolean hmset(String key,Map<String,Object> map){
        try {
            redisTemplate.opsForHash().putAll(key,map);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean hmset(String key,Map<String,Object> map,long time){
        try {
            redisTemplate.opsForHash().putAll(key,map);
            if (time > 0){
                expire(key,time);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean hset(String key,String item,Object value){
        try {
            redisTemplate.opsForHash().put(key,item,value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean hset(String key,String item,Object value,long time){
        try {
            redisTemplate.opsForHash().put(key,item,value);
            if (time > 0){
                expire(key,time);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void hdel(String key,Object ... item){
        redisTemplate.opsForHash().delete(key,item);
    }

    public boolean hHasKey(String key,String item){
        return redisTemplate.opsForHash().hasKey(key,item);
    }
}
