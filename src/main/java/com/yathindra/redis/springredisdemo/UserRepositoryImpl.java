package com.yathindra.redis.springredisdemo;

import com.yathindra.redis.springredisdemo.model.User;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {

    // redis template is used to access redis
    private RedisTemplate<String, User> redisTemplate;

    // should use hash operations to access redis template
    private HashOperations hashOperations;

    public UserRepositoryImpl(RedisTemplate<String, User> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(User user) {
        this.hashOperations.put("USER", user.getId(), user);
    }

    @Override
    public Map<String, User> findAll() {
        return this.hashOperations.entries("USER");
    }

    @Override
    public User findById(String id) {
        return (User)this.hashOperations.get("USER", id);
    }

    @Override
    public void update(User user) {
        save(user);
    }

    @Override
    public void delete(String id) {
        this.hashOperations.delete("USER", id);
    }
}
