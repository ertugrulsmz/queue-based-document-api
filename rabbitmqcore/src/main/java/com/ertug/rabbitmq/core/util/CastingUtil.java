package com.ertug.rabbitmq.core.util;

import java.io.*;

public class CastingUtil {

    private CastingUtil(){

    }

    public static <T extends Serializable> T byteArrayToObject(byte[] array)
            throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(array))) {
            return (T) in.readObject();
        }

    }

    public static <T extends Serializable> byte[] objectToByteArray(T t) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(t);
            out.flush();
            return bos.toByteArray();
        }
    }

}
