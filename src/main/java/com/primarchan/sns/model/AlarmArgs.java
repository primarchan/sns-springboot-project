package com.primarchan.sns.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AlarmArgs {

    // 알람을 발생시킨 유저
    private Integer fromUserId;

    private Integer targetId;

}
