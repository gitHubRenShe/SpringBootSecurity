package com.shuaicai;

import com.shuaicai.domain.User;
import com.shuaicai.mapper.MenuMapper;
import com.shuaicai.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

/**
 * @ClassName MapperTest
 * @Description TODO
 * @Author shuai cai
 * @Date 2022/8/3 20:55
 * @PackagePath com.shuaicai
 * @Version 1.0
 */
@SpringBootTest
public class MapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void TestBCryptPasswordEncoder(){
//        $2a$10$bDyRsDpt6Y3NUnHvqGPi6eF5YXS.Pxp027OiiIeuYGCbmhPakmvaG
        System.out.println(passwordEncoder.matches("1234",
                "$2a$10$bDyRsDpt6Y3NUnHvqGPi6eF5YXS.Pxp027OiiIeuYGCbmhPakmvaG"));
//        String encode = passwordEncoder.encode("1234");
//        String encode1 = passwordEncoder.encode("1234");
//        System.out.println(encode);
//        System.out.println(encode1);
    }

    @Test
    public void testMapper(){
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void testSelectPermsByUserId(){
        List<String> list = menuMapper.selectPermsByUserId(2L);
        System.out.println(list);
    }

}
