package com.huhusky.common.utils.http.wrapper;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author wuhuhu
 * @create 2017/5/4 10:16
 */
public class WrapperServletInputStream extends ServletInputStream {
    private boolean finished;
    private ByteArrayInputStream bais;
    private int length;

    public WrapperServletInputStream(ByteArrayInputStream bais) {
        this.bais = bais;
    }


    public boolean isFinished() {
        return finished;
    }

    public boolean isReady() {
        return true;
    }

    public void setReadListener(ReadListener readListener) {
    }

    @Override
    public int read() throws IOException {
        int b = bais.read();
        if (b != -1) {
            length++;
        }
        finished = b == -1;
        return b;
    }

    public int getLength() {
        return length;
    }
}
