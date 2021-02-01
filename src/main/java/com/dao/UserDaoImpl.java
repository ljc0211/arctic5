package com.dao;
import com.entiy.User;
import com.util.Database;

import java.io.File;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    /**
     * 数据文件存放目录，如没有则应新建。
     */
    private static String datafileDirectory = System.getProperty("user.dir") + File.separator + "database";

    /**
     * 这是在数据文件存放目录下，用于存放用户注册数据的目录
     */
    private static String filePath_user = datafileDirectory + File.separator + "user";

    public UserDaoImpl() {
        Database.init();
    }

    @Override
    public boolean login(String name, String pwd) {
        //  arrayList_user的第一项为用户名，第二项为密码
        ArrayList<String> arrayList_user = new ArrayList<>();
        arrayList_user = Database.readUser(name);

        return pwd.equals(arrayList_user.get(1));

    }

    //  这里有问题，明显不存在的file也会报exist()存在。
    @Override
    public boolean register(User user) {
//        String path = filePath_user + File.separator + user.getName() + ".txt";

        Database.createUser(user.getName(), user.getPwd());


        return true;
    }

    @Override
    public List<User> searchAll() {
        return null;
    }

    @Override
    public boolean update(int id, String name, String pwd, String sex, String home, String info) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}