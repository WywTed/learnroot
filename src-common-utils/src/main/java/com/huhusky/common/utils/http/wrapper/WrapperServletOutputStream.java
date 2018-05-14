package com.huhusky.common.utils.http.wrapper;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author wuhuhu
 * @create 2017/5/4 10:16
 */
public class WrapperServletOutputStream extends ServletOutputStream {

    private ByteArrayOutputStream bos = null;

    public WrapperServletOutputStream(ByteArrayOutputStream stream) throws IOException {
        bos = stream;
    }

    @Override
    public void write(int b) throws IOException {
        bos.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        bos.write(b, 0, b.length);
    }

    public boolean isReady() {
        return true;
    }

    public void setWriteListener(WriteListener writeListener) {

    }
}
