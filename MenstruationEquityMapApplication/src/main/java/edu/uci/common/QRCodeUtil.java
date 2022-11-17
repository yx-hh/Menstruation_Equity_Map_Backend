package edu.uci.common;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * QR code util class.
 *
 * @author ruiyan ma
 */
public class QRCodeUtil {
    private static final int WIDTH = 400;

    private static final int HEIGHT = 400;

    private static final String FORMAT = "PNG";

    /**
     * Write qrcode to the given output stream.
     *
     * @param content:      the content of qrcode
     * @param outputStream: the given output stream.
     */
    public static void writeQRCode(String content, OutputStream outputStream) {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MARGIN, 1);

        // EncodeHintType.ERROR_CORRECTION: default level L
        // ErrorCorrectionLevel: L = ~7%, M = ~15%, Q = ~25%, H = ~30%
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);

        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hints);
            MatrixToImageWriter.writeToStream(bitMatrix, FORMAT, outputStream);
        } catch (IOException | WriterException e) {
            e.printStackTrace();
        }
    }
}
