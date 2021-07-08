package xyz.kit00.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.kit00.blog.dao.UserMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service("UserDetailsService")
public class UserLoginService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Map<String,String> map=new HashMap<>();
        map.put("username",username);
        List userList=userMapper.selectByCondition(map);
        if(userList.size()==0)
            throw new UsernameNotFoundException("用户名不存在");
        else{
            xyz.kit00.blog.bean.User user=(xyz.kit00.blog.bean.User)userList.get(0);
            List<GrantedAuthority> authorities= AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_user");
            return new User(user.getUsername(),new BCryptPasswordEncoder().encode(user.getPassword()),authorities);
        }

    }
}
