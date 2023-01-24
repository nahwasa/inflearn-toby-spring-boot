package com.nahwasa.practice.tobyspringboot;

public interface HelloRepository {
    Hello findHello(String name);

    void increaseCount(String name);

    default int countOf(String name) {  // 인터페이스에 이렇게 디폴트 메소드를 추가할 수 있다. 자바의 Comparator 보면 어떻게 사용하는지 알 수 있음.
        Hello hello = findHello(name);
        return hello == null ? 0 : hello.getCount();
    }
}
