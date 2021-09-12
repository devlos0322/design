package org.devlos.design.singleton;

public class SocketClient {
    //자기 자신을 객체로 가지고 있어야함
    private static SocketClient socketClient = null;
    //기본 생성자를 private로 막아야함
    private SocketClient(){

    }
    //static method를 이용해서 get 메소드를 제공해야함
    public static SocketClient getInstance() {
        //Null 인 경우 생성한 후 리턴
        //Null 이 아닐 경우 생성해서 리턴
        if(socketClient == null) {
            socketClient = new SocketClient();
        }
        return socketClient;
    }

    public void connect() {
        System.out.println("connect");
    }
}
