package com.primarchan.sns.fixture;

import com.primarchan.sns.model.entity.UserEntity;

/**
 * 테스트를 위한 UserEntity
 */
public class UserEntityFixture {

    public static UserEntity get(String userName, String password, Integer userId) {
        UserEntity result = new UserEntity();
        result.setId(userId);
        result.setUserName(userName);
        result.setPassword(password);

        return result;
    }
}
