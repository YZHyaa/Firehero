//package com.xusm.firehero.testService;
//
//import com.xusm.firehero.dao.UserDao;
//import com.xusm.firehero.entity.User;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class Testuser {
//
//    @Autowired
//    private UserDao userDao;
//
//    @Test
//    public void test01() {
//        User user = new User();
//        user.setPhone("15389093292");
//        User u = this.userDao.selectOne(user);
//        System.out.println(u.getUsername());
//    }
//
//    @Test
//    public void test02() {
//        User user = new User();
//        user.setUsername("admin01");
//        User user1 = this.userDao.selectOne(user);
//        System.out.println(user1.getUid());
//    }
//
//    @Test
//    public void test03() {
//        User user = new User(1);
//        User user1 = this.userDao.selectOne(user);
//        System.out.println(user1);
//    }
//
//    @Test
//    public void test04() {
//
//        User user1 = this.userDao.selectUserByUid(1);
//        System.out.println(user1);
//    }
//
//    @Test
//    public void test05() {
//        User user = new User();
//        user.setEmail("");
//    }
//
//}
