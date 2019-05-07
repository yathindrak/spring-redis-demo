package com.yathindra.redis.springredisdemo;

import com.yathindra.redis.springredisdemo.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("user")
public class UserResource {
    private UserRepository userRepository;

    public UserResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/add/{id}/{name}")
    public User add(@PathVariable("id") final String id,
                    @PathVariable("name") final String name) {

        this.userRepository.save(new User(id, name, 20000L));

        return this.userRepository.findById(id);
    }

    @GetMapping("/update/{id}/{name}")
    public User update(@PathVariable("id") final String id,
                    @PathVariable("name") final String name) {

        this.userRepository.update(new User(id, name, 10000L));

        return this.userRepository.findById(id);
    }

    @GetMapping("/all")
    public Map<String, User> update() {
        Map<String, User> usersMap =  this.userRepository.findAll();
        System.out.println(usersMap);
        return usersMap;
    }
}
