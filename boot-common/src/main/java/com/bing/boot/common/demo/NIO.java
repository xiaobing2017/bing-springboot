package com.bing.boot.common.demo;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
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

            long timeBegin = System.currentTimeMillis();

            ByteBuffer buff = ByteBuffer.allocate((int) aFile.length());
            buff.clear();
            fileChannel.read(buff);

//            ByteBuffer buf = ByteBuffer.allocate(1024);
//            int bytesRead = fileChannel.read(buf);
//            System.out.println(bytesRead);
//
//            while (bytesRead != -1) {
//                buf.flip();
//                while (buf.hasRemaining()) {
//                    System.out.print((char) buf.get());
//                }
//                buf.compact();// or buf.clear();
//                bytesRead = fileChannel.read(buf);
//            }
            long timeEnd = System.currentTimeMillis();
            System.out.println("Read time: "+(timeEnd-timeBegin)+"ms");
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

    @Test
    public void nioMap() {
        RandomAccessFile aFile = null;
        try {
            aFile = new RandomAccessFile("/Users/finup/Desktop/POIExcelUtils.java", "rw");
            FileChannel fileChannel = aFile.getChannel();

            long timeBegin = System.currentTimeMillis();

            MappedByteBuffer mbb = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, aFile.length());
//            for (int i = 0; i < aFile.length(); i++) {
//                System.out.print((char) mbb.get(i));
//            }
//            System.out.println((char) mbb.get((int)aFile.length()/2-1));
//            System.out.println((char) mbb.get((int)aFile.length()/2));
//            System.out.println((char) mbb.get((int)aFile.length()/2+1));
//            System.out.print((char) mbb.get(3));//'\n' ASCII码:10 换行

            long timeEnd = System.currentTimeMillis();
            System.out.println("Read time: "+(timeEnd-timeBegin)+"ms");
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
