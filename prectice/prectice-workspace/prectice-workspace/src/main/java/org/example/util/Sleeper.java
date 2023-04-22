package org.example.util;

import lombok.SneakyThrows;

public class Sleeper {

    @SneakyThrows
    public static void sleep(long seconds) {
        Thread.sleep(1000 * seconds);
    }
}
