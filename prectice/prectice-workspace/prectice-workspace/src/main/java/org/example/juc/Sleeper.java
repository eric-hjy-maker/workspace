package org.example.juc;

import lombok.SneakyThrows;

public class Sleeper {

    @SneakyThrows
    public static void sleep(int seconds) {
        Thread.sleep(1000 * seconds);
    }
}
