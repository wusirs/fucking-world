package com.world.fucking.utils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.IOException;
import java.util.Objects;


public class PDFUtil {

    private PDFUtil() {
        throw new IllegalStateException("Utility class");
    }

    private static final Rectangle RECTANGLE = PageSize.A4;


    /**
     * 给pdf添加图片水印
     *
     * @param contentByte pdf
     * @param path        图片路径
     */
    public static void drawWater(PdfContentByte contentByte, String path, boolean textWaterFull) throws DocumentException, IOException {
        if (textWaterFull) {
            File file = new File(path);
            if (!file.exists()) {
                throw new IOException("文件不存在！");
            }
            contentByte.beginText();
            PdfGState pdfGState = new PdfGState();

            // 设置不透明度
            pdfGState.setFillOpacity(0.2F);
            // 设置透明度
            contentByte.setGState(pdfGState);

            // 设置对齐方式 内容，坐标 旋转角度
            Image image = Image.getInstance(path);
            // 设置大小为200，居中对齐
            float weight = 200F;
            float height = 200F;
            image.scaleAbsolute(weight, height);

            image.setAbsolutePosition((RECTANGLE.getWidth() - weight) / 2, (RECTANGLE.getHeight() - height) / 2);
            contentByte.addImage(image);
            // 水印颜色
            contentByte.setColorFill(BaseColor.GRAY);
            // 关闭
            contentByte.endText();
            contentByte.stroke();
        }
    }

    public static void drawWater(PdfContentByte contentByte, String path) throws DocumentException, IOException {
        drawWater(contentByte, path, false);
    }

    /**
     * @param contentByte content
     * @param width       Width of the rectangle
     * @param height      Height of the rectangle
     * @param positionX   X-coordinate of the lower-left corner
     * @param positionY   Y-coordinate of the lower-left corner
     * @param color       Color of teh rectangle
     */
    public static void drawRectangle(PdfContentByte contentByte, float width, float height,
                                     float positionX, float positionY, BaseColor color) {
        if (Objects.isNull(color)) {
            contentByte.setColorFill(BaseColor.BLACK);
        } else {
            contentByte.setColorFill(color);
        }
        // Draw the rectangle
        contentByte.rectangle(positionX, positionY, width, height);
        contentByte.stroke();
    }

    /**
     * 添加logo
     *
     * @param document  pdf
     * @param imgPath   图片路径
     * @param newWidth  宽
     * @param newHeight 高
     * @throws IOException       io异常
     * @throws DocumentException 异常
     */
    public static void addImg(Document document, String imgPath, float newWidth, float newHeight) throws IOException, DocumentException {
        Image img1 = Image.getInstance(imgPath);
        img1.setAbsolutePosition(50, RECTANGLE.getHeight() - 60);
        img1.scaleAbsolute(newWidth, newHeight);
        document.add(img1);
    }

    public static void addHeader(PdfWriter pdfWriter, String headerText) {
        throw new UnsupportedOperationException("notImplemented() cannot be performed because ...");
    }

    public static void addPageNum(PdfWriter pdfWriter, Document document) {
        throw new UnsupportedOperationException("notImplemented() cannot be performed because ...");
    }
}
