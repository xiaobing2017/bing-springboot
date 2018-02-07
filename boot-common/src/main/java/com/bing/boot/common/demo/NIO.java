package com.bing.boot.common.demo;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Description:
 * Author: zhangfusheng
 * Date: 2018/2/7 上午11:43
 */
public class NIO {

    @Test
    public void io() {
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream("/Users/finup/Desktop/POIExcelUtils.java"));

            byte[] buf = new byte[1024];
            int bytesRead = in.read(buf);
            while (bytesRead != -1) {
                for (int i = 0; i < bytesRead; i++) {
                    System.out.print((char) buf[i]);
                }
                bytesRead = in.read(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // http://www.importnew.com/19816.html
    @Test
    public void nio() {
        RandomAccessFile aFile = null;
        try {
            aFile = new RandomAccessFile("/Users/finup/Desktop/POIExcelUtils.java", "rw");
            FileChannel fileChannel = aFile.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(1024);

            int bytesRead = fileChannel.read(buf);
            System.out.println(bytesRead);

            while (bytesRead != -1) {
                buf.flip();
                while (buf.hasRemaining()) {
                    System.out.print((char) buf.get());
                }
                buf.compact();// or buf.clear();
                bytesRead = fileChannel.read(buf);
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (aFile != null) {
                    aFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
