package com.jamiechung.springboot.demo.artshop.service.impl;

import com.jamiechung.springboot.demo.artshop.domain.vo.UserVo;
import com.jamiechung.springboot.demo.artshop.dao.UserRepository;
import com.jamiechung.springboot.demo.artshop.domain.entity.User;
import com.jamiechung.springboot.demo.artshop.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class UsersServiceImpl implements UsersService {
    private UserRepository userRepository;

    @Autowired
    public UsersServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        User user = userOpt.orElseThrow(() -> new RuntimeException("Did not find user id - " + id));
        return user;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public User findUserByEmail(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email).stream().findFirst();
        if (userOpt.isPresent())
            return userOpt.get();
        else
            return null;
    }

    @Override
    public UserVo login(User user) {
        // 檢查帳號是否存在
        User data = findUserByEmail(user.getEmail());
        if (data == null)
            return null;

        // 使用資料庫鹽值對輸入密碼進行加密
        String md5Password = getMd5Password(user.getPassword(), data.getSalt());

        // 比對密碼是否相等
        if (!md5Password.equals(data.getPassword()))
            return null;

        // 組合UserVo資料
        UserVo userVo = new UserVo();
        userVo.setEmail(data.getEmail());
        userVo.setFirstName(data.getFirstName());
        return userVo;
    }

    @Override
    public Optional<String> register(UserVo userVo) {
        //檢查兩次密碼有沒有一致
        if (!userVo.getPassword().equals(userVo.getCheckPassword()))
            return Optional.of("The passwords you typed do not match.");

        // 檢查帳號是否重複註冊
        User data = findUserByEmail(userVo.getEmail());
        if (data != null)
            return Optional.of("The account has already been taken.");

        // 產生鹽值
        String salt = UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");

        // 密碼加密
        String md5Password = getMd5Password(userVo.getPassword(), salt);

        // 新增MemberAccount 資料
        User user = new User();
        user.setEmail(userVo.getEmail());
        user.setFirstName(userVo.getFirstName());
        user.setPassword(md5Password);
        user.setSalt(salt);
        user.setCreateUser(userVo.getFirstName());
        user.setCreateDate(LocalDateTime.now());
        User saveUser = userRepository.save(user);
        if (saveUser.getId() == 0) return Optional.of("An error occurred while creating a new member account.");
        return Optional.empty();
    }

    private String getMd5Password(String password, String salt) {
        // 對password + salt 進行三次加密
        String str = password + salt;
        for (int i = 0; i < 3; i++) {
            str = DigestUtils.md5DigestAsHex(str.getBytes()).toUpperCase();
        }
        return str;
    }

    public Optional<String> ValidateUsers(User user) {
        if (!StringUtils.hasLength(user.getEmail())) {
            return Optional.of("Email can't be blank.");
        }

        if (!StringUtils.hasLength(user.getPassword())) {
            return Optional.of("Password can't be blank.");
        }

        return Optional.empty();
    }

    public Optional<String> ValidateUserVo(UserVo user) {
        if (!StringUtils.hasLength(user.getEmail())) {
            return Optional.of("Email can't be blank.");
        } else {
            String regex = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";
            Pattern p = Pattern.compile(regex);
            if (!p.matcher(user.getEmail()).find())
                return Optional.of("Email format is incorrect.");
        }

        if (!StringUtils.hasLength(user.getPassword())) {
            return Optional.of("Password can't be blank.");
        } else {
            String regex = "^(?=.*d)(?=.*[a-zA-Z]).{8,30}$";
            Pattern p = Pattern.compile(regex);
            if (!p.matcher(user.getPassword()).find())
                return Optional.of("Password format is incorrect.");
        }

        if (!StringUtils.hasLength(user.getCheckPassword())) {
            return Optional.of("Check password can't be blank.");
        }

        if (!StringUtils.hasLength(user.getFirstName())) {
            return Optional.of("First name can't be blank.");
        }

        return Optional.empty();
    }

}
