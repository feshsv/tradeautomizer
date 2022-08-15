package com.tradeautomizer.utils;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;


public class DocxHelper {

    private DocxHelper() {
    }

    public static String[] getLineByLineTextFromDocx(File docx) {
        String[] lines = new String[]{};
        try(var wordExtractor = new XWPFWordExtractor(new XWPFDocument(OPCPackage.open(new FileInputStream(docx))))) {
            var docxText = wordExtractor.getText().toLowerCase();

            //TODO иногда бывают переносы строк в конце длинной строки, тогда сплитит в два индекса или три. Надо обдумать
            lines = docxText.split("\n");
        } catch(Exception ex) {
            //log
        }
        return lines;
    }
}
