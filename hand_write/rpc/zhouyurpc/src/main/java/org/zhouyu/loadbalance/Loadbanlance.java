package org.zhouyu.loadbalance;

import org.zhouyu.common.URL;

import java.util.List;
import java.util.Random;

public class Loadbanlance {

    public static URL random(List<URL> urls) {
        Random random = new Random();
        int i = random.nextInt(urls.size());
        return urls.get(i);
    }
}
