package com.tangcheng.learning.awt;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.extern.slf4j.Slf4j;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ZXingFactory {

    public static BufferedImage createQRImage(String inviteUrl) throws WriterException {
        BitMatrix bitMatrix = getBitMatrix(inviteUrl);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    /**
     * 生成一个二维码图片
     *
     * @param inviteUrl
     * @return
     * @throws WriterException
     * @throws IOException
     */
    public static File createQRFile(String inviteUrl) throws WriterException, IOException {
        BitMatrix bitMatrix = getBitMatrix(inviteUrl);
        String tmpDir = System.getProperty("java.io.tmpdir");
        Path path = FileSystems.getDefault().getPath(tmpDir, "qr_" + RandomUtils.generateRandomString(6) + ".png");
        MatrixToImageWriter.writeToPath(bitMatrix, "png", path);// 输出图像
        return path.toFile();
    }

    private static BitMatrix getBitMatrix(String inviteUrl) throws WriterException {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        // 指定纠错等级,纠错级别（L 7%、M 15%、Q 25%、H 30%）
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 内容所使用字符集编码
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        //hints.put(EncodeHintType.MAX_SIZE, 350);//设置图片的最大值
        //hints.put(EncodeHintType.MIN_SIZE, 100);//设置图片的最小值
        hints.put(EncodeHintType.MARGIN, 1);//设置二维码边的空度，非负数
        return new MultiFormatWriter().encode(inviteUrl,//要编码的内容
                //编码类型，目前zxing支持：Aztec 2D,CODABAR 1D format,Code 39 1D,Code 93 1D ,Code 128 1D,
                //Data Matrix 2D , EAN-8 1D,EAN-13 1D,ITF (Interleaved Two of Five) 1D,
                //MaxiCode 2D barcode,PDF417,QR Code 2D,RSS 14,RSS EXPANDED,UPC-A 1D,UPC-E 1D,UPC/EAN extension,UPC_EAN_EXTENSION
                BarcodeFormat.QR_CODE,
                300, //二维码宽度
                300, //二维码高度
                hints);
    }
}
