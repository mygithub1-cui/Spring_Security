package com.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class SecurtiyService implements UserDetailsService {
   static Map<String, User> map = new HashMap<>();
    static {
        User user1 = new User();
        user1.setUsername("admin");
        user1.setPassword("$2a$10$IdDJmV01tQgINR7RVo6ZDelzm2uWttUOjlrormJ8EMdGm3NgS.eoa");
        user1.setTelephone("123");

        User user2 = new User();
        user2.setUsername("zhangsan");
        user2.setPassword("123");
        user2.setTelephone("321");
        map.put(user1.getUsername(),user1);
        map.put(user2.getUsername(),user2);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        User user = map.get(username);
        if(user!=null){
            String password =user.getPassword();
            List<GrantedAuthority> lists = new ArrayList<>();
            lists.add(new SimpleGrantedAuthority("add"));
            lists.add(new SimpleGrantedAuthority("delete"));
            lists.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            return new org.springframework.security.core.userdetails.User(username,password,lists);
        }else {
            return null;
        }
    }
}
