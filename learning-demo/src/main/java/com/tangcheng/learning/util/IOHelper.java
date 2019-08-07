package com.tangcheng.learning.util;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by tang.cheng on 2017/4/24.
 */
public class IOHelper {

    public static final Logger LOGGER = LoggerFactory.getLogger(IOHelper.class);

    public static void uncompressTarGZ(File tarFile, File dest) throws IOException {
        boolean mkdirs = dest.mkdirs();
        if (!mkdirs) {
            LOGGER.warn("Unable to create directory '{}'", dest.getAbsolutePath());
            return;
        }

        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(tarFile));
        GzipCompressorInputStream gcis = new GzipCompressorInputStream(inputStream);
        try (TarArchiveInputStream tais = new TarArchiveInputStream(gcis)) {
            TarArchiveEntry entry;
            while ((entry = tais.getNextTarEntry()) != null) {// create a file with the same name as the entry
                File desFile = new File(dest, entry.getName());
                if (entry.isDirectory()) {
                    boolean mkDirs = desFile.mkdirs();
                    if (!mkDirs) {
                        LOGGER.warn("Unable to create directory '{}'", desFile.getAbsolutePath());
                    }
                } else {
                    boolean createNewFile = desFile.createNewFile();
                    if (!createNewFile) {
                        LOGGER.warn("Unable to create file '{}'", desFile.getCanonicalPath());
                        continue;
                    }
                    try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(desFile));) {
//                        IOUtils.copy(tais, bos);
                        byte[] btoRead = new byte[1024];
                        int len;
                        while ((len = tais.read(btoRead)) != -1) {
                            bos.write(btoRead, 0, len);
                        }
                    }
                }
            }
            LOGGER.info("Untar completed successfully!");
        }
    }


    public static void printTarGzFile(File tarFile) throws IOException {
        BufferedInputStream bin = new BufferedInputStream(FileUtils.openInputStream(tarFile));
        CompressorInputStream cis = new GzipCompressorInputStream(bin);

        try (TarArchiveInputStream tais = new TarArchiveInputStream(cis)) {
            TarArchiveEntry entry;
            while ((entry = tais.getNextTarEntry()) != null) {
                if (entry.isDirectory()) {
                    LOGGER.warn("dir:{}", entry.getName());
                } else {
                    int size = (int) entry.getSize();
                    byte[] content = new byte[size];
                    int readCount = tais.read(content, 0, size);
                    LOGGER.info("fileName:{}", entry.getName());
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(content, 0, readCount);
                    try (LineIterator iterator = IOUtils.lineIterator(byteArrayInputStream, Charset.forName("utf-8"))) {
                        while (iterator.hasNext()) {
                            LOGGER.info("line:{}", iterator.nextLine());
                        }
                    }
                }
            }
            LOGGER.info("===============finish===============");
        }
    }
}
