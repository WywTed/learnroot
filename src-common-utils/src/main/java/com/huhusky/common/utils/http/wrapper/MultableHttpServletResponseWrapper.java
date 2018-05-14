package com.huhusky.common.utils.http.wrapper;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * @author wuhuhu
 * @create 2017/4/26 14:47
 */
public class MultableHttpServletResponseWrapper extends HttpServletResponseWrapper {

    private PrintWriter writer;
    private ByteArrayOutputStream buffer;
    private WrapperServletOutputStream out;

    public MultableHttpServletResponseWrapper(HttpServletResponse response) {
        super(response);
        try {
            buffer = new ByteArrayOutputStream();
            out = new WrapperServletOutputStream(buffer);
            writer = new PrintWriter(new OutputStreamWriter(buffer, this.getCharacterEncoding()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServletOutputStream getOutputStream() throws IOException{
        return out;
    }

    @Override
    public PrintWriter getWriter() {
        return writer;
    }

    @Override
    public void flushBuffer() throws IOException {
        if (out != null) {
            out.flush();
        }
        if (writer != null) {
            writer.flush();
        }
    }

    @Override
    public void reset() {
        buffer.reset();
    }

    public ByteArrayOutputStream getBuffer() {
        return buffer;
    }

    public void setBuffer(ByteArrayOutputStream buffer) {
        this.buffer = buffer;
    }
}
