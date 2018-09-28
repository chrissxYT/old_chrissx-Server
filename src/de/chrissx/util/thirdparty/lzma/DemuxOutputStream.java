package de.chrissx.util.thirdparty.lzma;

import java.io.IOException;
import java.io.OutputStream;

public class DemuxOutputStream extends OutputStream {
    private final InheritableThreadLocal<OutputStream> outputStreamThreadLocal = new InheritableThreadLocal<>();

    public OutputStream bindStream(final OutputStream output) {
        final OutputStream stream = outputStreamThreadLocal.get();
        outputStreamThreadLocal.set(output);
        return stream;
    }

    @Override
    public void close() throws IOException {
        final OutputStream output = outputStreamThreadLocal.get();
        if (null != output) {
            output.close();
        }
    }

    @Override
    public void flush() throws IOException {
        final OutputStream output = outputStreamThreadLocal.get();
        if (output != null) {
            output.flush();
        }
    }

    @Override
    public void write(final int ch) throws IOException {
        final OutputStream output = outputStreamThreadLocal.get();
        if (null != output) {
            output.write(ch);
        }
    }
}
