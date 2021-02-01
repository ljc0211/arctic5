package com.util;

import java.io.*;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Database {

    /**
     * 数据文件存放目录，如没有则应新建。
     */
    private static String datafileDirectory = System.getProperty("user.dir") + File.separator + "database";

    /**
     * 这是在数据文件存放目录下，用于存放用户注册数据的目录
     */
    private static String filePath_user = datafileDirectory + File.separator + "user";

    public Database() {
        init();
    }

    /**
     * 创建数据库，初始化，应当在程序运行之初执行。
     */
    public static void init() {
        createDirectoryIfNotExist(new File(datafileDirectory));
        createDirectoryIfNotExist(new File(filePath_user));

        System.out.println("数据库的位置：" + datafileDirectory);
    }

    /**
     * 供用户注册时调用，写入用户注册信息。
     * 用户名，密码
     */
    public static void createUser(String userName, String passWord) {

        String userPath = filePath_user + File.separator + userName + ".txt";

        File userFile = new File(userPath);

        BufferedWriter writer = null;
        try {
            try {
                writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(userFile, true), "UTF-8"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


            assert writer != null;

            writer.write("用户名：");
            writer.write(userName);
            writer.newLine();//写换行符

            writer.write("密码：");
            writer.write(passWord);
            writer.newLine();//写换行符

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
//    return userFile;
    }

    /**
     * 供用户注册时调用，写入用户注册信息。
     * 用户名，密码
     */
    public static ArrayList<String> readUser(String userName) {

        //  arrayList_user的第一项为用户名，第二项为密码
        ArrayList<String> arrayList_user = new ArrayList<>();

        File userFile = new File(filePath_user + File.separator + userName + ".txt");

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(userFile), "utf-8")
            );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String line = null;
        String[] getLine = new String[2];
        while(true){
            try {
                if (!((line=reader.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            getLine = line.split("：");
            arrayList_user.add(getLine[1]);
        }

        return arrayList_user;
    }

    /**
     * 如没有数据文件目录则应新建。
     */
    public static void createDirectoryIfNotExist(File directory) {
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    /**
     * 判断数据文件是否过大，如果数据记录（即作为参数的file文件大小）超过1k，则返回true，表示应当创建新数据文件。
     */
    public static boolean isLarger(File file) {
        int length = 1000000;
        if (file.length() > length) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 返回一个有格式的当前系统时间
     */
    public static String getDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd HH_mm_ss");//设置日期格式
        String filename = df.format(new Date());

        return filename;
    }
}