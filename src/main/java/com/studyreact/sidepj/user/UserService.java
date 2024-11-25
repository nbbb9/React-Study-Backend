package com.studyreact.sidepj.user;

import com.studyreact.sidepj.user.dto.UserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    public final UserRepository userRepository;

    public void signup(UserRequest request){
        User user = UserRequest.toUser(request);
        userRepository.save(user);
    }

}
