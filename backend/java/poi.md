# Java) POI 활용해 DB데이터 엑셀 파일로 저장하기

​	

사용자에게 요구 데이터를 보여 주는 방법은 여러가지가 있습니다. 보통은 웹 페이지를 통해 화면을 보여주는데요, 사용자가 해당 내용을 확인하기 위해서는 항상 해당 웹 페이지에 방문 해야만 하며, 인터넷에 연결되어있어야만 한다는 제약이 생깁니다.

그래서 파일로 조회 데이터를 저장하고자 하는 수요는 반드시 생기고, 그 수요를 충족시키기 위한 여러가지 라이브러리들이 나와 있습니다. 그 대표적으로 Apache POI가 있습니다.

​		

## Apache POI (Poor Obfuscation Implementation)

> https://poi.apache.org

Apache POI는 아파치 소프트웨어 재단에 의해 운영되는 오픈소스 프로젝트 입니다. 순수 자바 라이브러리로서 Microsoft Office의 Word, PowerPoint, Excel 형식의 파일을 읽고 쓸 수 있게 해주며 최근의 오피스 포맷인 Office Open XML File Format도 지원해줍니다.

​			

## 데이터베이스 조회

Excel파일로 만들기에 앞서서 지금 가지고 있는 데이터베이스에서 특정 리스트를 조회 해 보도록 하겠습니다. 각자 사용하시는 데이터베이스와 기존에 있는 테이블을 그대로 활용 하시면 됩니다.

​			

**ExcelController.java**

```java
package com.shane.controller;

import com.shane.model.Board;
import com.shane.repositories.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ExcelController {

    @Autowired
    BoardRepository repository;

    @GetMapping("/excel")
    public void downloadExcel(HttpServletResponse resp) {
        List<Board> list = repository.findAll();
        for (Board board : list) {
            System.out.println(board);
        }
    }
}

```

저는 간단하게 게시판 자료를 불러오는 메서드를 활용 해 불러온 후, System.out.println을 이용해 내용들을 출력 하도록 해 보았습니다.

​					

![image-20211015143646765](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/poi.assets/image-20211015143646765.webp)

게시판의 Board 객체가 차례대로 출력 됩니다. boardNo, title, content, writer 총 4개의 프로퍼티가 있습니다.

​		

## POI dependency 추가

mvnrepository에 방문 해서 poi를 검색 해 보았습니다.

> https://mvnrepository.com/artifact/org.apache.poi/poi

![image-20211015145031788](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/poi.assets/image-20211015145031788.webp)

​			

여러가지 버전이 나오는데, 최신 버전인 5.0.0 버전을 활용 해 보겠습니다.

​			

pom.xml 에 아래의 내용을 추가 한 뒤에 Maven Reload Project 해줍니다.

```xml
		<dependency>
			<groupId>org.apache.poi</groupId>
			<artifactId>poi</artifactId>
			<version>5.0.0</version>
		</dependency>

```

​		

의존성이 잘 추가 되었다면, org.apache.poi 에서 Workbook 클래스를 정상적으로 불러옵니다.

![image-20211015145344870](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/poi.assets/image-20211015145344870.webp)

​	

## POI 활용해 xls 파일 만들기

Workbook의 생성자를 보니 HSSFWorkbook이 보입니다. 이건 만들 엑셀 파일의 확장자를 xls로 할지 아니면 xlsx로 할지에 따라 달라집니다. xlsx가 최신 버전이지만 xls 파일을 먼저 만들어 보기 위해 HSSFWorkbook 생성자를 활용 해 보겠습니다. .xlsx 파일을 생성하려면 XSSFWorkbook을 생성해야 합니다.

![image-20211015145517624](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/poi.assets/image-20211015145517624.webp)

​		

우선 설명을 위해 최대한 간단한 코드로 작성 해 보았습니다. 보기엔 쉽지만 좋은 코드는 아닙니다.

**ExcelController.java**

```java
package com.shane.controller;

import com.shane.model.Board;
import com.shane.repositories.BoardRepository;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class ExcelController {

    @Autowired
    BoardRepository repository;

    @GetMapping("/excel")
    public void downloadExcel(HttpServletResponse response) throws IOException {

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("게시판글들");
        int rowNo = 0;

        Row headerRow = sheet.createRow(rowNo++);
        headerRow.createCell(0).setCellValue("글 번호");
        headerRow.createCell(1).setCellValue("작성자");
        headerRow.createCell(2).setCellValue("제목");
        headerRow.createCell(3).setCellValue("내용");

        List<Board> list = repository.findAll();
        for (Board board : list) {
            Row row = sheet.createRow(rowNo++);
            row.createCell(0).setCellValue(board.getBoardNo());
            row.createCell(1).setCellValue(board.getWriter());
            row.createCell(2).setCellValue(board.getTitle());
            row.createCell(3).setCellValue(board.getContent());
        }

        response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment;filename=boardlist.xls");

        workbook.write(response.getOutputStream());
        workbook.close();
    }
}

```

​		

**코드를 간단하게 설명 드리자면,**

​		

```java
Workbook workbook = new HSSFWorkbook();
```

Workbook을 생성합니다. 엑셀 파일을 말합니다.

​	

```java
Sheet sheet = workbook.createSheet("게시판글들");
```

하나의 sheet를 만듭니다.

​		

`int rowNo = 0;` 는 row number를 카운팅 하기 위한 변수 입니다.

​	

```java
        Row headerRow = sheet.createRow(rowNo++);
        headerRow.createCell(0).setCellValue("글 번호");
        headerRow.createCell(1).setCellValue("작성자");
        headerRow.createCell(2).setCellValue("제목");
        headerRow.createCell(3).setCellValue("내용");
```

제일 위에 입력할 row 인데요, 따로 headerRow 라고, 변수명을 굳이 구분하는 이유는 나중에 헤더부분만 따로 스타일링 한다던가 하는 일이 있을때 분류할 수 있습니다.

​			

```java
        List<Board> list = repository.findAll();
        for (Board board : list) {
            Row row = sheet.createRow(rowNo++);
            row.createCell(0).setCellValue(board.getBoardNo());
            row.createCell(1).setCellValue(board.getWriter());
            row.createCell(2).setCellValue(board.getTitle());
            row.createCell(3).setCellValue(board.getContent());
        }
```

이제 DB에서 받아온 데이터들을 반복문을 돌려 꺼내 쓰며 각각의 row를 생성 합니다.

​	

```java
        response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment;filename=boardlist.xls");

        workbook.write(response.getOutputStream());
        workbook.close();
```

이제 마지막으로 응답 컨텐츠와 헤더를 정해 줍니다. 그러고나서는 response의 OutputStream에 workbook을 write 해줍니다. response에 바로 응답을 하면 에러가 생겼을 경우 대응하기가 어렵기 때문에 좋은 코드의 방식은 아니지만 일단 이해를 위해 최대한 간단한 코드로 작성 해 보았습니다.

헤더에는 한글이 들어갈 수 없기 때문에 header를 통해 파일명을 지정해주는 방식으로는 한글 파일명을 사용할 수 없습니다.

​					

이제 컨트롤러에 적힌 해당 주소로 요청을 보내면 다운로드 받는 화면이 나옵니다.

![image-20211015150526526](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/poi.assets/image-20211015150526526.webp)

​		

​	

다운을 받고, Downloads 폴더에 가면 boardlist.xls 라는 파일명으로 파일 다운로드가 되어 있습니다.

![	](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/poi.assets/image-20211015150742740.webp)

해당 파일을 열어보겠습니다.

​	

![image-20211015150654757](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/poi.assets/image-20211015150654757.webp)

게시판의 모든 내용이 엑셀 파일로 잘 정리되어 있습니다. 아주 간단하게 넣어 보았는데 이것 만으로도 왠만한 상황에서는 훌륭하게 사용 할 수 있습니다.

​		

## 개선하기

마지막으로 몇가지 스타일링 도 넣어보고, 응답 방식도 개선한 코드를 공유 해 드리겠습니다. 

참, .xls 확장자도 최신의 .xlsx 를 사용하게끔 변경 하였습니다. 그걸 위해서는 Workbook을 생성 할 때 HSSF 대신에 XSSF를 생성해야하는데요,

![image-20211015154342734](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/poi.assets/image-20211015154342734.webp)

그냥은 추가가 되지 않습니다.

​		

아래의 의존성을 추가 해 줘야 합니다.

```xml
		<dependency>
			<groupId>org.apache.poi</groupId>
			<artifactId>poi-ooxml</artifactId>
			<version>5.0.0</version>
		</dependency>

```

​	

이제 XSSFWorkbook을 사용 할 수 있습니다.	

![image-20211015154924342](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/poi.assets/image-20211015154924342.webp)

​			

**개선한 코드 입니다.**

```java
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

```

row에 style을 넣으면 원래 됐었는데 5.0.0 버전에서는 뭔가 달라졌는지 스타일이 원하는 대로 안되어서 반복문을 돌리며 cell에 스타일링을 했습니다.

배경 색도 넣고, 글자 크기와 글자 색상도 변경 했습니다.

​		

실행 해 보았습니다.

![image-20211015161227350](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/poi.assets/image-20211015161227350.webp)

다운 로드 받아보니 .xlsx 파일을 받는게 확인 됩니다.

​			

![image-20211015160745877](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/poi.assets/image-20211015160745877.webp)

또한 임시파일도 잘 삭제되는 게 확인 됩니다.

​	

새로 받은 파일을 확인 해 봅니다.

![image-20211015162002849](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/java/poi.assets/image-20211015162002849.webp)

Header 부분에는 스타일링이 원하는 대로 적용 되었습니다. 글자가 살짝 더 크고 배경이 파랑색이며 글은 흰색으로 작성 되었습니다.

이걸로도 엑셀 파일로 만들어 내기에는 충분 하지만 원한다면 여러가지 옵션을 추가로 줄 수 있습니다.

​	

위에서 만든 프로젝트의 전체 코드는 아래의 링크에서 확인하실 수 있습니다. pom.xml이나 컨트롤러의 설정 혹은 Repository를 확인 하실 수 있습니다.

https://github.com/Shane-Park/markdownBlog/tree/master/projects/vuejs





