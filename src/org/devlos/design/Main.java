package org.devlos.design;

import org.devlos.design.adapter.*;
import org.devlos.design.singleton.AClazz;
import org.devlos.design.singleton.BClazz;
import org.devlos.design.singleton.SocketClient;

public class Main {

    public static void main(String[] args) {
	    // 싱글톤 테스트
        /*
        AClazz aClazz = new AClazz();
        BClazz bClazz = new BClazz();

        SocketClient aClient = aClazz.getSocketClient();
        SocketClient bClient = bClazz.getSocketClient();

        System.out.println("두개의 객체가 동일한가?");
        System.out.println(aClient.equals(bClient));
        */

        // 어댑터 패턴
        // 인터페이스가 맞지 않을 때 연결시켜주는 역할
        HairDryer hairDryer = new HairDryer();
        connect(hairDryer);

        Cleaner cleaner = new Cleaner();
        Electronic110V adapter = new SocketAdapter(cleaner);
        connect(adapter);

        AirConditioner airConditioner = new AirConditioner();
        Electronic110V airAdapter = new SocketAdapter(airConditioner);
        connect(airAdapter);

    }
    //콘센트
    public static void connect(Electronic110V electronic110V) {
        electronic110V.powerOn();
    }
}
