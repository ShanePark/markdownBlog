package com.shane.controller;

import com.shane.model.Board;
import com.shane.repositories.BoardRepository;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Controller
public class ExcelController {

    @Autowired
    BoardRepository repository;

    Logger logger = LoggerFactory.getLogger(ExcelController.class);

    @GetMapping("/excel")
    public ResponseEntity<InputStreamResource> downloadExcel(HttpServletResponse response) throws IOException {

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("게시판글들");
            int rowNo = 0;

            CellStyle headStyle = workbook.createCellStyle();
            headStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.LIGHT_BLUE.getIndex());
            headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            Font font = workbook.createFont();
            font.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
            font.setFontHeightInPoints((short) 13);
            headStyle.setFont(font);

            Row headerRow = sheet.createRow(rowNo++);
            headerRow.createCell(0).setCellValue("글 번호");
            headerRow.createCell(1).setCellValue("작성자");
            headerRow.createCell(2).setCellValue("제목");
            headerRow.createCell(3).setCellValue("내용");
            for(int i=0; i<=3; i++){
                headerRow.getCell(i).setCellStyle(headStyle);
            }

            List<Board> list = repository.findAll();
            for (Board board : list) {
                Row row = sheet.createRow(rowNo++);
                row.createCell(0).setCellValue(board.getBoardNo());
                row.createCell(1).setCellValue(board.getWriter());
                row.createCell(2).setCellValue(board.getTitle());
                row.createCell(3).setCellValue(board.getContent());
            }

            sheet.setColumnWidth(0, 3000);
            sheet.setColumnWidth(1, 3000);
            sheet.setColumnWidth(2, 4000);
            sheet.setColumnWidth(3, 8000);

            File tmpFile = File.createTempFile("TMP~", ".xlsx");
            try (OutputStream fos = new FileOutputStream(tmpFile);) {
                workbook.write(fos);
            }
            InputStream res = new FileInputStream(tmpFile) {
                @Override
                public void close() throws IOException {
                    super.close();
                    if (tmpFile.delete()) {
                        logger.info(() -> "임시 파일 삭제 완료");
                    }
                }
            };

            return ResponseEntity.ok() //
                    .contentLength(tmpFile.length()) //
                    .contentType(MediaType.APPLICATION_OCTET_STREAM) //
                    .header("Content-Disposition", "attachment;filename=boardlist.xlsx") //
                    .body(new InputStreamResource(res));

        }
    }
}
