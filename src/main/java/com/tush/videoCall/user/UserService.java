package com.tush.videoCall.user;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;

@Service
public class UserService {

    private static final List<User> USER_LIST = new ArrayList<>();

    static {
        USER_LIST.add(new User("piyush", "piyush@gmail.com", "123", "online"));
        USER_LIST.add(new User("tarun", "tarun@gmail.com", "123", "online"));
        USER_LIST.add(new User("shanu", "shanu@gmail.com", "123", "online"));
    }

    public void register(User user) {
        user.setStatus("online");
        USER_LIST.add(user);
    }

    public User login(User user) {
        var userIndex = IntStream.range(0, USER_LIST.size())
                .filter(i -> USER_LIST.get(i).getEmail().equals(user.getEmail()))
                .findAny().orElseThrow(() -> new RuntimeException("User not found"));

        var cUser = USER_LIST.get(userIndex);
        if (!cUser.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("Password incorrect");
        }
        cUser.setStatus("online");
        return cUser;

    }

    public void logout(String email) {
        var userIndex = IntStream.range(0, USER_LIST.size())
                .filter(i -> USER_LIST.get(i).getEmail().equals(email))
                .findAny().orElseThrow(() -> new RuntimeException("User not found"));

        USER_LIST.get(userIndex).setStatus("offline");
    }

    public List<User> findAll() {
        return USER_LIST;
    }

    public static List<User> getUserList() {
        return USER_LIST;
    }

    public static void printAllUser() {
        USER_LIST.forEach(user -> {
            System.out.println(user);
        });
    }
}
