package org.zhouyu.register;

import org.zhouyu.common.URL;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapRemoteRegister {

    static Map<String, List<URL>> map = new HashMap<>();


    private static void saveFile() {
        try {
            FileOutputStream fos = new FileOutputStream("/map.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Map<String, List<URL>> getFile() {
        try {
            FileInputStream fis = new FileInputStream("/map.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            return (Map<String, List<URL>>) ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void regist(String interfaceName, URL url) {
        List<URL> list = map.get(interfaceName);
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(url);
        map.put(interfaceName, list);

        saveFile();
    }

    public static List<URL> get(String interfaceName) {
        map = getFile();

        return map.get(interfaceName);
    }

}
