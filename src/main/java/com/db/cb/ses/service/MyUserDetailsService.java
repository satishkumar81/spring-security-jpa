package com.db.cb.ses.service;

import com.db.cb.ses.models.User;
import com.db.cb.ses.respository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRespository userRespository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRespository.findByUserName(userName);

        user.orElseThrow(()->new UsernameNotFoundException("Not Found"));

        return user.map(MyUserDetails::new).get();
    }
}
