package com.world.fucking.utils;

import cn.hutool.core.text.CharSequenceUtil;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.world.fucking.domain.User;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * pdf 工具类
 *
 * @author heisenberg
 * @since 1.0.0
 */
@Slf4j
public class PdfUtils {

    /**
     * 字体存放的跟路径，默认为'C:\Windows\Fonts\'
     */
    private static final String FONT_PATH = "C:\\Windows\\Fonts\\";

    /**
     * 纸张大小
     */
    private static Rectangle rectangle = PageSize.A4;

    private PdfUtils() {
        throw new IllegalStateException();
    }

    /**
     * 设置字体默认值
     *
     * @param fontName 字体名称
     * @return {@link BaseFont
     * @throws DocumentException
     * @throws IOException
     */
    private static BaseFont createBaseFont(String fontName) throws DocumentException, IOException {
        // 默认为宋体
        if (fontName == null) {
            fontName = "simsun.ttc";
        }
        String fontPre = fontName.substring(fontName.lastIndexOf(".") + 1);
        if (fontPre.equals("ttc")) {
            // ttc格式的字体需要加上后缀
            fontName = fontName + ",0";
        }
        String font = FONT_PATH + fontName;
        return BaseFont.createFont(font, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
    }

    /**
     * 设置字体
     *
     * @return {@link Font}
     */
    public static Font setFont() {
        return setFont(null, null, null, null);
    }

    public static Font setFont(Integer fontSize) {
        return setFont(null, fontSize, null, null);
    }

    public static Font setFont(Integer fontSize, BaseColor fontColor) {
        return setFont(null, fontSize, null, fontColor);
    }

    /**
     * @param fontName  字体名称 默认宋体
     * @param fontSize  字体大小 默认12号
     * @param fontStyle 字体样式
     * @param fontColor 字体颜色 默认黑色
     * @return {@link Font}
     */
    public static Font setFont(String fontName, Integer fontSize, Integer fontStyle, BaseColor fontColor) {
        try {
            BaseFont baseFont = createBaseFont(fontName);
            Font font = new Font(baseFont);
            if (fontSize != null) {
                font.setSize(fontSize);
            }
            if (fontStyle != null) {
                font.setStyle(fontStyle);
            }
            if (fontColor != null) {
                font.setColor(fontColor);
            }
            return font;
        } catch (Exception e) {
            throw new UnsupportedOperationException("设置字体失败！");
        }
    }

    /**
     * 创建pdf文档
     *
     * @param response 响应
     * @param fileName 文件名称
     * @return {@link Document}
     */
    public static Document createDocument(HttpServletResponse response, String fileName) {
        try {
            response.reset();
            response.setHeader("Content-Type", "application/pdf-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        // 设置大小
        rectangle = PageSize.A4;
        return new Document(rectangle, 50, 50, 80, 50);
    }

    /**
     * 绘制标题
     *
     * @param font      字体
     * @param titleName 标题名称
     * @return {@link Paragraph
     */
    public static Paragraph setParagraph(Font font, String titleName) {
        Paragraph paragraph = new Paragraph(titleName, font);
        //设置文字居中
        paragraph.setAlignment(Element.ALIGN_CENTER);
        //行间距
        paragraph.setLeading(5f);
        //设置段落上空白
        paragraph.setSpacingBefore(-20f);
        //设置段落下空白
        paragraph.setSpacingAfter(15f);
        return paragraph;
    }

    /**
     * 设置表格内容
     *
     * @param headFont 表头字体
     * @param textFont 正文字体
     * @param title    表头
     * @param list     数据
     * @return {@link PdfPTable}
     */
    public static PdfPTable setTable(Font headFont, Font textFont, String[] title, List<User> list) {
        //四列
        PdfPTable table = createTable(new float[]{120, 120, 120, 120});

        for (String head : title) {
            table.addCell(createCell(head, headFont));
        }
        for (User user : list) {
            table.addCell(createCell(user.getUsername(), textFont, false, true));
            table.addCell(createCell(user.getPassword(), textFont, true, true));
            table.addCell(createCell("11", textFont, true, true));
            table.addCell(createCell("11", textFont, true, true));
        }
        return table;
    }

    private static PdfPTable createTable(float[] widths) {
        PdfPTable table = new PdfPTable(widths);
        try {
            // 设置表格大小
            table.setTotalWidth(rectangle.getWidth() - 100);

            table.setLockedWidth(true);
            // 居中
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            // 边框
            table.getDefaultCell().setBorder(1);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return table;
    }

    private static PdfPCell createCell(String value, Font font, boolean... args) {
        PdfPCell cell = new PdfPCell();
        // 水平、垂直居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPhrase(new Phrase(value, font));

        if (args.length == 0) {
            return cell;
        }

        cell.setBorderWidthLeft(0);
        cell.setBorderWidthRight(0);
        return cell;
    }

    /**
     * 给pdf添加图片水印
     *
     * @param waterMar pdf
     * @param path     图片路径
     * @throws Exception 异常
     */
    public static void addWater(PdfContentByte waterMar, String path) throws IOException, DocumentException {
        File file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException("水印图片不存在！");
        }
        waterMar.beginText();

        PdfGState gs = new PdfGState();
        // 不透明度
        gs.setFillOpacity(0.2f);
        // 设置透明度
        waterMar.setGState(gs);
        // 设置对齐方式 内容 X坐标 Y坐标 旋转角度
        Image img = Image.getInstance(path);
        // 设置大小为200，并且居中
        float newWidth = 200f;
        float newHeight = 200f;
        img.scaleAbsolute(newWidth, newHeight);
        img.setAbsolutePosition((rectangle.getWidth() - newWidth) / 2, (rectangle.getHeight() - newHeight) / 2);

        waterMar.addImage(img);
        // 水印颜色
        waterMar.setColorFill(BaseColor.GRAY);

        // 关闭
        waterMar.endText();
        waterMar.stroke();
    }

    /**
     * 给pdf添加文字水印
     *
     * @param waterMar pdf
     * @param text     水印文本
     * @param full     是否平铺
     * @throws Exception 异常
     */
    public static void addWater(PdfContentByte waterMar, String text, boolean full) throws DocumentException, IOException {
        if (CharSequenceUtil.isEmpty(text)) {
            return;
        }
        waterMar.beginText();
        PdfGState gs = new PdfGState();
        // 不透明度
        gs.setFillOpacity(0.2f);
        int fontSize = 14;
        waterMar.setFontAndSize(createBaseFont(null), fontSize);
        // 设置透明度
        waterMar.setGState(gs);
        if (full) {
            // 设置对齐方式 内容 X坐标 Y坐标 旋转角度 '/6'目的是为了让宽存在6个水印
            for (int x = 50; x <= rectangle.getWidth(); x += rectangle.getWidth() / 4) {
                for (int y = 50; y <= rectangle.getHeight(); y += rectangle.getHeight() / 4) {
                    waterMar.showTextAligned(Element.ALIGN_RIGHT, text, x, y, 45);
                }
            }
        } else {
            waterMar.showTextAligned(Element.ALIGN_RIGHT, text, (rectangle.getWidth()) / 2, (rectangle.getHeight()) / 2, 45);
        }
        // 水印颜色
        waterMar.setColorFill(BaseColor.GRAY);

        // 关闭
        waterMar.endText();
        waterMar.stroke();
    }

    /**
     * 添加图片
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
        img1.setAbsolutePosition(50, rectangle.getHeight() - 60);
        img1.scaleAbsolute(newWidth, newHeight);
        document.add(img1);
    }


    /**
     * 生成页眉
     *
     * @param pdfWriter writer
     * @param text      文本
     */
    public static void addHeader(PdfWriter pdfWriter, String text) {
        ColumnText.showTextAligned(pdfWriter.getDirectContent(),
                Element.ALIGN_LEFT, new Paragraph(text, PdfUtils.setFont(10, BaseColor.GRAY)),
                50, rectangle.getHeight() - 12, 0);
    }

    /**
     * 生成页码
     *
     * @param pdfWriter writer
     * @param document  document
     */
    public static void addPageNum(PdfWriter pdfWriter, Document document) {
        try {
            //创建一个两列的表格
            PdfPTable table = new PdfPTable(1);
            table.setTotalWidth(rectangle.getWidth());
            //锁定列宽
            table.setLockedWidth(true);
            //设置每列宽度
            table.setWidths(new int[]{20});
            // 设置页码，字体大小为12号，灰色
            PdfPCell cell = new PdfPCell(new Phrase(document.getPageNumber() + "", PdfUtils.setFont(10, BaseColor.GRAY)));

            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.disableBorderSide(-1);
            table.addCell(cell);

            // 将页码写到距离pdf底部30px的位置
            table.writeSelectedRows(0, -1, 0, 30, pdfWriter.getDirectContent());
        } catch (DocumentException e) {
            throw new IllegalStateException("PDF文档操作失败", e);
        }
    }
}
