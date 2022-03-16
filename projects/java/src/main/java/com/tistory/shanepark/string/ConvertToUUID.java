package com.tistory.shanepark.string;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;

/**
 * UUID 에서 구분자가 제거된 스트링을 클립보드에 복사하면 자동으로 클립보드에 텍스트를 UUID 형태로 변경한 텍스트를 넣어주는 프로그램.
 */
public class ConvertToUUID {

    public static void main(String[] args) throws IOException, UnsupportedFlavorException, InterruptedException {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        while (true) {
            Thread.sleep(100);
            Transferable contents = clipboard.getContents(clipboard);
            StringBuffer sb = new StringBuffer((String) contents.getTransferData(DataFlavor.stringFlavor));
            if (sb.charAt(8) == '-') {
                continue;
            }
            sb.insert(8, "-");
            sb.insert(13, "-");
            sb.insert(18, "-");
            sb.insert(23, "-");
            StringSelection stringSelection = new StringSelection(sb.toString());
            clipboard.setContents(stringSelection, null);
            System.out.println("uuid = " + sb);
        }
    }
}
