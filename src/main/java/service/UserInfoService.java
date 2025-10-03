package service;

import entity.UserInfo;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import repository.UserInfoRepository;

@Service
public class UserInfoService implements UserDetailsService {

    private final UserInfoRepository repository;

    public UserInfoService(UserInfoRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo ui = repository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return new UserInfoUserDetails(ui);
    }
}
