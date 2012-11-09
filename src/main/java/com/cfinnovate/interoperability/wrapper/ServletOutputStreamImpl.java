
package com.cfinnovate.interoperability.wrapper;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;

class ServletOutputStreamImpl extends ServletOutputStream
{
    private OutputStream os;

    public ServletOutputStreamImpl(OutputStream os)
    {
        super();
        this.os = os;
    }

    @Override
    public void write(byte[] b) throws IOException
    {
        if (os != null) {
            os.write(b);
        }
    }

    @Override
    public void write(byte[] b, int index, int len) throws IOException
    {
        if (os != null) {
            os.write(b, index, len);
        }
    }

    @Override
    public void write(int b) throws IOException
    {
        if (os != null) {
            os.write(b);
        }
    }
}
