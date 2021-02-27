package com.java.fx.Util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.fx.model.JsonEntity.Conf;

import java.io.File;
import java.io.IOException;

/**
 * @author qiaojiyuan
 * @date 2021/2/25
 */
public class DataUtil {
    private static ObjectMapper mapper = new ObjectMapper();

    public static void write(File file, Object obj) {
        write(file, obj, true);
    }
    public static void write(File file, Object obj, boolean thread) {
        if (thread) {
            new Thread(new WriteThread(file, obj)).start();
        } else {
            try {
                mapper.writeValue(file, obj);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static <T> T read(File file, Class<T> typeReference) {
        try {
            return mapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static <T> T read(File file, TypeReference<T> typeReference) {
        try {
            return mapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
