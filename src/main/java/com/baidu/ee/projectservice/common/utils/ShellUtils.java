package com.baidu.ee.projectservice.common.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qinghai on 2015/3/11.
 */
public class ShellUtils {
    public static void runCmd(String cmd) {
        try {
            // 执行 CMD 命令
            Process process = Runtime.getRuntime().exec(cmd);

            // 从输入流中读取文本
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line = null;

            // 循环读取
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            // 关闭输出流
            process.getOutputStream().close();

            System.out.println("程序执行完毕!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 运行shell
     *
     * @param shStr
     *            需要执行的shell
     * @return
     * @throws java.io.IOException
     */
    public static List runShell(String shStr) throws Exception {
        List<String> strList = new ArrayList();

        Process process;
        process = Runtime.getRuntime().exec(new String[]{"/bin/sh","-c",shStr},null,null);
        InputStreamReader ir = new InputStreamReader(process
                .getInputStream());
        LineNumberReader input = new LineNumberReader(ir);
        String line;
        process.waitFor();
        while ((line = input.readLine()) != null){
            System.out.println(line);
            strList.add(line);
        }

        return strList;
    }
}
