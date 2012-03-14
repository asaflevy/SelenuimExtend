package com.outbrain.selenium.util;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collections;

import com.thoughtworks.selenium.Selenium;

public class JavascriptInjection {

  public static String inputStream2UTF8(final InputStream in) throws IOException {
    String ret = null;
    final BufferedInputStream bin = new BufferedInputStream(in);
    final InputStreamReader isr = new InputStreamReader(bin, "UTF-8");
    final StringBuilder sb = new StringBuilder();
    int iread = -1;
    while ((iread = isr.read()) != -1) {
      sb.append((char) iread);
    }
    ret = sb.toString();
    return ret;
  }

  /**
   * Merges all these different resources into a single input stream, puts them
   * into a single JS memory file, and injects this into Selenium using the given
   * js element tag ID (jsTagID).
   * @param cpResourcePaths resource paths relative to resourceLocator or fully qualified
   * @param filePaths file system paths, these can be relative if used from a
   *                  running directory, but generally should be full
   * @param jsTagID the ID of the script element to inject into Selenium
   * @param se the Selenium instance to inject into
   */
  public static void injectJavaScriptResourcesTogether(final String[] cpResourcePaths, final String[] filePaths, final String jsTagID,
      final Selenium se) {
    final ArrayList<InputStream> ins = new ArrayList<InputStream>();
    try {
      for (final String cpResourcePath : cpResourcePaths) {

        final InputStream in = JavascriptInjection.class.getResourceAsStream(cpResourcePath);
        if (in != null) {
          ins.add(in);
        }
      }

      for (final String filePath : filePaths) {
        final File f = new File(filePath).getAbsoluteFile().getCanonicalFile();
        final InputStream in = new FileInputStream(f);
        ins.add(in);
      }

      final SequenceInputStream sin = new SequenceInputStream(Collections.enumeration(ins));

      final String js = inputStream2UTF8(sin);

      se.addScript(js, jsTagID);

    } catch (final Throwable e) {
      if (RuntimeException.class.isInstance(e)) {
        throw RuntimeException.class.cast(e);
      } else {
        throw new RuntimeException(e);
      }
    } finally {
      for (final Closeable closer : ins) {
        try {
          closer.close();
        } catch (final Throwable e) {
        }
      }
    }
  }

  public static void injectJavaScriptResource(final String cpResourcePath, final String jsTagID, final Selenium se) {
    final ArrayList<InputStream> closeables = new ArrayList<InputStream>();
    try {
      final InputStream in = JavascriptInjection.class.getResourceAsStream(cpResourcePath);
      if (in != null) {
        closeables.add(in);
      }
      final String js = inputStream2UTF8(in);

      //don't swallow here...let the caller do that if
      //they need to. API should not eat exceptions generally
      se.addScript(js, jsTagID);

    } catch (final Throwable e) {
      if (RuntimeException.class.isInstance(e)) {
        throw RuntimeException.class.cast(e);
      } else {
        throw new RuntimeException(e);
      }
    } finally {
      for (final Closeable closer : closeables) {
        try {
          closer.close();
        } catch (final Throwable e) {
        }
      }
    }
  }

}
