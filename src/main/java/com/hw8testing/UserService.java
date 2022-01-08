package com.hw8testing;



import java.util.List;

public interface UserService {
    List<User> getAll();

    User getOne(String email);

    void add(User user);
}