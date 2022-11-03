package com.primarchan.sns.controller;

import com.primarchan.sns.controller.request.UserJoinRequest;
import com.primarchan.sns.controller.request.UserLoginRequest;
import com.primarchan.sns.controller.response.AlarmResponse;
import com.primarchan.sns.controller.response.Response;
import com.primarchan.sns.controller.response.UserJoinResponse;
import com.primarchan.sns.controller.response.UserLoginResponse;
import com.primarchan.sns.model.User;
import com.primarchan.sns.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    // TODO :implement
    @PostMapping("/join")
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest request) {
        User user = userService.join(request.getName(), request.getPassword());
        return Response.success(UserJoinResponse.fromUser(user));
    }

    @PostMapping("/login")
    public Response<UserLoginResponse> login(@RequestBody UserLoginRequest request) {
        String token = userService.login(request.getName(), request.getPassword());
        return Response.success(new UserLoginResponse(token));
    }

    @GetMapping("/alarm")
    public Response<Page<AlarmResponse>> alarm(Pageable pageable, Authentication authentication) {
        return Response.success(userService.alarmList(authentication.getName(), pageable).map(AlarmResponse::fromAlarm));
    }

}
