package Util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * @author qiaojiyuan
 * @date 2021/2/25
 */
public class WriteThread implements Runnable {
    private File file;
    private Object obj;

    public WriteThread(File file, Object obj) {
        this.file = file;
        this.obj = obj;
    }

    @Override
    public void run() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(file, obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
