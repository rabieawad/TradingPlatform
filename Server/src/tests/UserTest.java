package org.boes.pratikum;

import org.boes.praktikum.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void getUserById() {
        var r = new User("rabie","1234",1000);
        var d = new User("daria","1234",1000);
        User.userList.add(r);
        User.userList.add(d);

        Assertions.assertEquals(User.getUserById("rabie").getUsername(), "rabie");
        assertEquals(User.getUserById("daria").getUsername(), "daria");

    }
}