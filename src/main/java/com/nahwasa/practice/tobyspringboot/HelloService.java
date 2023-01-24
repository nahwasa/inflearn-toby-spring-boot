package com.nahwasa.practice.tobyspringboot;

public interface HelloService {
    String sayHello(String name);

    default int countOf(String name) {  // 원랜 default가 아니었는데, HelloControllerTest 에서 Service를 직접 구현해줘야 하게 변경됨으로써 이걸 해결하기 위해 꼼수로 일단 넣고 넘어가겠다고 하심.
        return 0;
    }
}
