package com.world.fucking.listener;

import com.world.fucking.utils.PDFUtil;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class NewPdfPageEventHelper extends PdfPageEventHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(NewPdfPageEventHelper.class);

    private boolean water = false;
    private String textWater = null;
    private boolean textWaterFull = true;
    private String imageWaterPath = null;

    private boolean logo = false;
    private String logoImagePath = null;
    private float logoWidth = 0f;
    private float logoHeight = 0f;

    private boolean header = false;
    private String headerText = null;

    private boolean pageNum = false;

    /**
     * @param textWater 水印文本
     * @param textWaterFull 是否平铺
     */
    public void setTextWaterWater(String textWater, boolean textWaterFull) {
        this.water = true;
        this.textWaterFull = textWaterFull;
        this.textWater = textWater;
    }

    /**
     * @param imageWaterPath 图片水印路径
     */
    public void setImageWaterWater(String imageWaterPath) {
        this.water = true;
        this.imageWaterPath = imageWaterPath;
    }

    /**
     * @param logoImagePath logo图片路径
     * @param logoWidth 宽
     * @param logoHeight 高
     */
    public void setLogo(String logoImagePath, float logoWidth, float logoHeight) {
        this.logo = true;
        this.logoImagePath = logoImagePath;
        this.logoWidth = logoWidth;
        this.logoHeight = logoHeight;
    }


    public void setHeader(String headerText) {
        this.header = true;
        this.headerText = headerText;
    }

    /**
     * 打印页码
     */
    public void setPageNum() {
        this.pageNum = true;
    }

    /**
     * 每开始一页pdf时回调
     *
     * @param pdfWriter pdf
     * @param document doc
     */
    @Override
    public void onStartPage(PdfWriter pdfWriter, Document document) {
        try {
            if (water) {
                // 设置水印(图片、文字水印)
                PdfContentByte waterMar = pdfWriter.getDirectContentUnder();
                // 水印
                if (textWater != null) {
                    PDFUtil.drawWater(waterMar, textWater, textWaterFull);
                }
                if (imageWaterPath != null) {
                    PDFUtil.drawWater(waterMar, imageWaterPath);
                }
            }
            if (header) {
                // 生成页眉
                PDFUtil.addHeader(pdfWriter, headerText);

            }
            if (logo) {
                // 设置logo
                PDFUtil.addImg(document, logoImagePath, logoWidth, logoHeight);

            }
            if (pageNum) {
                // 生成页码
                PDFUtil.addPageNum(pdfWriter, document);
            }
        } catch (Exception e) {
            LOGGER.error("", e);
        }
    }
}

