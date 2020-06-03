package com.qyf.jlearn.net.socket;

import java.io.*;
import java.net.Socket;

/**
 * 类描述：
 * Socket 客户端实例
 * 如下的 GreetingClient 是一个客户端程序，该程序通过 socket 连接到服务器并发送一个请求，然后等待一个响应
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/6/3 18:29
 */
public class GreetingClient {
    public static void main(String[] args) {
        //String serverName = args[0];
        //int port = Integer.parseInt(args[1]);
        String serverName = "localhost";
        int port = 6666;
        try {
            System.out.println("连接到主机：" + serverName + " ，端口号：" + port);

            Socket client = new Socket(serverName, port);
            System.out.println("远程主机地址：" + client.getRemoteSocketAddress());

            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            out.writeUTF("Hello from " + client.getLocalSocketAddress());

            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            System.out.println("服务器响应： " + in.readUTF());

            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
