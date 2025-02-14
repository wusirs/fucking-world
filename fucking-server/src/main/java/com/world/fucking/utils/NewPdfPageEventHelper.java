package com.world.fucking.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NewPdfPageEventHelper extends PdfPageEventHelper {

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

    public void setTextWaterWater(String textWater, boolean textWaterFull) {
        this.water = true;
        this.textWaterFull = textWaterFull;
        this.textWater = textWater;
    }

    public void setImageWaterWater(String imageWaterPath) {
        this.water = true;
        this.imageWaterPath = imageWaterPath;
    }

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

    public void setPageNum() {
        this.pageNum = true;
    }

    /**
     * 每开始一页pdf时回调
     *
     * @param pdfWriter writer
     * @param document document
     */
    @Override
    public void onStartPage(PdfWriter pdfWriter, Document document) {
        try {
            if (water) {
                // 设置水印(图片、文字水印)
                PdfContentByte waterMar = pdfWriter.getDirectContentUnder();
                // 水印
                if (textWater != null) {
                    PdfUtils.addWater(waterMar, textWater, textWaterFull);
                }
                if (imageWaterPath != null) {
                    PdfUtils.addWater(waterMar, imageWaterPath);
                }
            }
            if (header) {
                // 生成页眉
                PdfUtils.addHeader(pdfWriter, headerText);

            }
            if (logo) {
                // 设置logo
                PdfUtils.addImg(document, logoImagePath, logoWidth, logoHeight);

            }
            if (pageNum) {
                // 生成页码
                PdfUtils.addPageNum(pdfWriter, document);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
