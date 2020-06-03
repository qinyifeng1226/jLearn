package com.qyf.jlearn.net.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * 类描述：
 * Socket 服务端实例
 * 如下的GreetingServer 程序是一个服务器端应用程序，使用 Socket 来监听一个指定的端口。
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2020/6/3 18:30
 */
public class GreetingServer extends Thread {
    private ServerSocket serverSocket;

    public GreetingServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        // 设置超时时间
        serverSocket.setSoTimeout(10000);
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("等待远程连接，端口号为：" + serverSocket.getLocalPort() + "...");

                Socket server = serverSocket.accept();
                System.out.println("远程主机地址：" + server.getRemoteSocketAddress());

                DataInputStream in = new DataInputStream(server.getInputStream());
                System.out.println(in.readUTF());

                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF("谢谢连接我：" + server.getLocalSocketAddress() + "\nGoodbye!");

                server.close();
                System.out.println("关闭连接管道");
            } catch (SocketTimeoutException s) {
                System.out.println("Socket timed out!");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    /**
     * 编译以上两个 java 文件代码，并执行以下命令来启动服务，使用端口号为 6066：
     * <p>
     * $ javac GreetingServer.java
     * $ java GreetingServer 6066
     * 等待远程连接，端口号为：6066...
     * 新开一个命令窗口，执行以上命令来开启客户端：
     * <p>
     * $ javac GreetingClient.java
     * $ java GreetingClient localhost 6066
     * 连接到主机：localhost ，端口号：6066
     * 远程主机地址：localhost/127.0.0.1:6066
     * 服务器响应： 谢谢连接我：/127.0.0.1:6066
     * Goodbye!
     *
     * @param args
     */
    public static void main(String[] args) {
        int port = args.length > 0 ? Integer.parseInt(args[0]) : 6666;
        try {
            Thread t = new GreetingServer(port);
            t.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}