package com.primarchan.sns.fixture;

import com.primarchan.sns.model.entity.PostEntity;
import com.primarchan.sns.model.entity.UserEntity;

/**
 * 테스트를 위한 PostEntity
 */
public class PostEntityFixture {

    public static PostEntity get(String userName, Integer postId, Integer userId) {
        UserEntity user = new UserEntity();
        user.setId(userId);
        user.setUserName(userName);

        PostEntity result = new PostEntity();
        result.setUser(user);
        result.setId(postId);

        return result;
    }
}
