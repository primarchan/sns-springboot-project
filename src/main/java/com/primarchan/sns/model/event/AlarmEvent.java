package com.primarchan.sns.model.event;

import com.primarchan.sns.model.AlarmArgs;
import com.primarchan.sns.model.AlarmType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlarmEvent {

    private Integer receiveUserId;
    private AlarmType alarmType;
    private AlarmArgs args;

}
