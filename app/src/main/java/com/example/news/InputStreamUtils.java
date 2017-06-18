package com.example.news;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by Ирина on 07.06.2017.
 */

public class InputStreamUtils {
    public static String toString(InputStream istream) throws IOException {
        Writer writer = new StringWriter();
        char[] buffer = new char[8192];
        Reader reader = new BufferedReader(new InputStreamReader(istream, "UTF-8"));
        int n;
        while ((n = reader.read(buffer)) != -1) {
            writer.write(buffer, 0, n);
        }
        return writer.toString();
    }

    public static void close(Closeable stream) {
        try {
            if (stream != null) {
                stream.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
