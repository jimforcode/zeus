package com.zeus.sftp;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by Administrator on 2016/11/26 0026.
 */
public class SFTPProcessor {

    /**
     * 利用JSch包实现SFTP下载、上传文件
     *
     * @param ip   主机IP
     * @param user 主机登陆用户名
     * @param psw  主机登陆密码
     * @param port 主机ssh2登陆端口，如果取默认值，传-1
     */
    public static String sshSftp(String ip, String user, String psw, int port, String path, String fileName) throws Exception {
        Session session = null;
        Channel channel = null;
        String text = null;
        JSch jsch = new JSch();

        if (port <= 0) {
            //连接服务器，采用默认端口
            session = jsch.getSession(user, ip);
        } else {
            //采用指定的端口连接服务器
            session = jsch.getSession(user, ip, port);
        }

        //如果服务器连接不上，则抛出异常
        if (session == null) {
            throw new Exception("session is null");
        }

        //设置登陆主机的密码
        session.setPassword(psw);//设置密码
        //设置第一次登陆的时候提示，可选值：(ask | yes | no)
        session.setConfig("StrictHostKeyChecking", "no");
        //设置登陆超时时间
        session.connect(30000);

        try {
            //创建sftp通信通道
            channel = (Channel) session.openChannel("sftp");
            channel.connect(1000);
            ChannelSftp sftp = (ChannelSftp) channel;

            //进入服务器指定的文件夹
            sftp.cd("/" + path);

            //列出服务器指定的文件列表
            /*Vector v = sftp.ls("*.csv");
            for (int i = 0; i < v.size(); i++) {
                System.out.println(v.get(i));
            }*/

            InputStream inputStream = sftp.get(fileName);
            Scanner scanner = new Scanner(inputStream, "UTF-8");
            text = scanner.useDelimiter("\\A").next();
            scanner.close();

        } finally {
            session.disconnect();
            channel.disconnect();
        }
        return text;
    }
}
