package org.devlos.design;

import org.devlos.design.adapter.*;
import org.devlos.design.aop.AopBrowser;
import org.devlos.design.decorator.*;
import org.devlos.design.facade.Ftp;
import org.devlos.design.facade.Reader;
import org.devlos.design.facade.SftpClient;
import org.devlos.design.facade.Writer;
import org.devlos.design.observer.Button;
import org.devlos.design.observer.IButtonListener;
import org.devlos.design.proxy.Browser;
import org.devlos.design.proxy.BrowserProxy;
import org.devlos.design.proxy.IBrowser;
import org.devlos.design.singleton.AClazz;
import org.devlos.design.singleton.BClazz;
import org.devlos.design.singleton.SocketClient;
import org.devlos.design.strategy.*;

import java.util.concurrent.atomic.AtomicLong;

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
        /*
        HairDryer hairDryer = new HairDryer();
        connect(hairDryer);

        Cleaner cleaner = new Cleaner();
        Electronic110V adapter = new SocketAdapter(cleaner);
        connect(adapter);

        AirConditioner airConditioner = new AirConditioner();
        Electronic110V airAdapter = new SocketAdapter(airConditioner);
        connect(airAdapter);
        */

        // Proxy patterm
        // Proxy는 대리인이라는 뜻으로, 무엇인가를 대신하여 처리하는 것이다.
        // Proxy Class를 통해서 대신 전달 하는 형태로 설계 되며, 실제 Client는 Proxy로 부터 결과를 받는다.
        // Cache의 기능으로도 활용이 가능하다.
        // SOLID중에서 개방 페쇄 원칙 (OCP)과 의존 역전 원칙(DIP)를 따른다.
        /*
        Browser browser = new Browser("www.naver.com");
        browser.show();
        browser.show();
        browser.show();
        browser.show();
        browser.show();
        browser.show();
         */
        /*
        IBrowser browser = new BrowserProxy("www.naver.com");
        browser.show();
        browser.show();
        browser.show();
        browser.show();
        */
        // AOP: 특정한 메소드 전후에 작업을 하는 경우. 메소드의 실행시간 측정등 진행
        /*
        AtomicLong start = new AtomicLong();
        AtomicLong end = new AtomicLong();

        IBrowser aopBrowser = new AopBrowser("www.naver.com",
                () -> {
                    System.out.println("before");
                    start.set(System.currentTimeMillis());
                },
                ()-> {
                    long now = System.currentTimeMillis();
                    end.set(now - start.get());
                }
        );
        aopBrowser.show();
        System.out.println("Loading time: "+end.get());

        aopBrowser.show();
        System.out.println("Loading time: "+end.get());
        */
        //데코레이터 패턴: 기존 뼈대(클래스)는 유지하되, 이후 필요한 형태로 꾸밀 때 사용한다. 확장이 필요한 경우 상속의 대안으로도 활용된다. SOLID 중에서 개방 폐쇠 원칙(OCP) 과 의존 역전 원칙(DIP)를 따른다.
        //자동차를 가지고 예제를 진행. 등급에 따라 가격이 바뀜
        /*
        ICar audi = new Audi(1000);
        audi.showPrice();

        // a3
        ICar audi3 = new A3(audi, "A3");
        audi3.showPrice();
        // a4
        ICar audi4 = new A4(audi, "A4");
        audi4.showPrice();
        // a5
        ICar audi5 = new A5(audi, "A5");
        audi5.showPrice();
        */
        //Observer pattern:  관찰자 패턴은 변화가 일어 났을 때, 미리 등록된 다른 클래스에 통보해주는 패턴을 구현한 것이다. 많이 보이는 곳은 이벤트 리스너가 이 패턴의 표준적인 예.
        /*
        Button button = new Button("버튼");
        button.addListener(new IButtonListener() {
            @Override
            public void clickEvent(String event) {
                System.out.println(event);
            }
        });
        button.click("메시지 전달: click1");
        button.click("메시지 전달: click2");
        button.click("메시지 전달: click3");
        button.click("메시지 전달: click4");
        */
        //Facade pattern: Facade는 건물의 앞쪽 정면 이라는 뜻을 가진다. 여러 개의 객체와 실제 사용하는 서브 객체의 사이에 복잡한 의존관계가 있을 때, 중간에 facade라는 객체를 두고, 여기서 제공하는 interface 만을 활용하여 기능을 사용하는 방식이다.
        //Facade는 자신이 가지고 있는 각 클래스의 기능을 명확힐 알아야 한다.
        //Client가 FTP 기능을 사용할 경우, FTP, WRITER, READER 클래스의 open close를 모두 명시해서 사용해야하는데, 이것을 간편하게 사용할 수 있도록 한다.
        //일반 사용
        /*
        Ftp ftpClient = new Ftp("www.foo.co.kr", 22, "/home/etc");
        ftpClient.connect();
        ftpClient.moveDirectory();
        Writer writer = new Writer("text.tmp");
        writer.fileConnect();
        writer.write();
        Reader reader = new Reader("text.tmp");
        reader.fileConnect();
        reader.fileRead();

        reader.fileDisconnect();
        writer.fileDisconnect();
        ftpClient.disConnect();
        //파사드 패턴 사용. 파사드 객체를 이용하여 새로운 인터페이스를 제공함. 의존성을 안쪽으로 숨겨준다.
        SftpClient sftpClient = new SftpClient("www.foo.co.kr", 22, "/home/etc", "text.tmp");
        sftpClient.connect();
        sftpClient.writer();
        sftpClient.read();
        sftpClient.disConnect();
        */
        // 전략패턴: 객체지향의 꽃. 유사한 행위들을 캡슐화 하여, 객체의 행위를 바꾸고 싶은 경우 직접 변경하는 것이 아닌 전략만 변경하여, 유연하게 확장하는 패턴 SOLID 중에서 개방폐쇠 원칙 (OCP)과 의존 역전 원칙(DIP)를 따른다.
        //전략 메서드를 가진 전략 객체. 인코딩 (Normal Strategy, Base64 Strategy)
        //전략 객체를 사용하는 컨텍스트(Encoder)
        //전략 객체를 생성해 컨텍스트에 주입하는 클라이언트
        Encoder encoder = new Encoder();
        //base64
        EncodingStrategy base64 = new Base64Strategy();

        //Normal
        EncodingStrategy normal = new NormalStrategy();

        String message = "hello java";
        encoder.setEncodingStrategy(base64);
        String base64Result = encoder.getMessage(message);
        System.out.println(base64Result);

        encoder.setEncodingStrategy(normal);
        String normalResult = encoder.getMessage(message);
        System.out.println(normalResult);

        encoder.setEncodingStrategy(new AppendStrategy());
        String appendResult = encoder.getMessage(message);
        System.out.println(appendResult);
    }
    //콘센트
    public static void connect(Electronic110V electronic110V) {
        electronic110V.powerOn();
    }
}
