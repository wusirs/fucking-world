package com.world.fucking.controller;

import com.world.fucking.domain.User;
import com.world.fucking.utils.NewPdfPageEventHelper;
import com.world.fucking.utils.PdfUtils;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/pdf")
@Slf4j
public class ExcelController {

    @PostMapping("/downloadPDF")
    public void downloadPDF(HttpServletResponse response) {
        try (BufferedOutputStream os = new BufferedOutputStream(response.getOutputStream())) {
            // 1.设置输出的文件名称
            String fileName = "考勤报表.pdf";

            // 2.创建pdf文档，并且设置纸张大小为A4
            Document document = PdfUtils.createDocument(response, fileName);
            PdfWriter writer = PdfWriter.getInstance(document, os);

            // 3.设置事件(水印、页眉、logo)等
            NewPdfPageEventHelper helper = new NewPdfPageEventHelper();
            // 水印
            helper.setImageWaterWater("D:\\out\\a.png");
            helper.setTextWaterWater("这 是 文 字 水 印", true);
            // logo
            helper.setLogo("D:\\out\\a.png", 80, 40);
            // 页眉
            helper.setHeader("xxxxx科技股份有限公司");
            // 页码
            helper.setPageNum();
            // 开启回调事件
            writer.setPageEvent(helper);

            // 4.打开文档
            document.open();

            // 5.设置标题
            String titleName = "这 个 是 标 题";
            // 设置字体样式：黑体 20号 加粗 红色
            Font titleFont = PdfUtils.setFont("simhei.ttf", 20, Font.BOLD, BaseColor.RED);
            Paragraph paragraph = PdfUtils.setParagraph(titleFont, titleName);

            // 6.设置表格
            // 定义列名
            String[] title = {"姓名", "性别", "年龄", "地址"};
            // 获取列表数据
            // 设置表头字体样式：黑体 14号 加粗 黑色
            // 设置正文字体样式：12号
            Font headFont = PdfUtils.setFont("simhei.ttf", 12, Font.BOLD, BaseColor.BLACK);
            Font textFont = PdfUtils.setFont(12);
            List<User> dataList = getData();
            PdfPTable table = PdfUtils.setTable(headFont, textFont, title, dataList);
            // 7.填充内容
            document.add(paragraph);
            document.add(table);

            // 关闭资源
            document.close();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 模拟从数据库获取数据
     *
     * @return {@link List<User>
     */
    private List<User> getData() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setUsername("名称-" + i);
            user.setPassword("password-" + i);
            list.add(user);
        }
        return list;
    }
}

