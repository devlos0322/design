package org.devlos.design;

import org.devlos.design.singleton.AClazz;
import org.devlos.design.singleton.BClazz;
import org.devlos.design.singleton.SocketClient;

public class Main {

    public static void main(String[] args) {
	    // write your code here
        AClazz aClazz = new AClazz();
        BClazz bClazz = new BClazz();

        SocketClient aClient = aClazz.getSocketClient();
        SocketClient bClient = bClazz.getSocketClient();

        System.out.println("두개의 객체가 동일한가?");
        System.out.println(aClient.equals(bClient));
    }
}
