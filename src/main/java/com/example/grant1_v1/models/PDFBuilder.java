package com.example.grant1_v1.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;
import java.io.IOException;

public class PDFBuilder {
        public static void createPDFAsList(ObservableList<Task> list){
            Stage s = new Stage();
            try {
                PDDocument document = new PDDocument();
                PDPage page = new PDPage(PDRectangle.A4);
                document.addPage(page);
                PDPageContentStream contentStream = new PDPageContentStream(document, page);
                PDType0Font font = PDType0Font.load(document, new File("src/main/java/com/example/grant1_v1/models/TNR.ttf"));
                contentStream.setFont(font, 18);
                contentStream.beginText();
                contentStream.newLineAtOffset(50, 700);

                // Создание нумерованного списка
                contentStream.setLeading(14.5f); // Межстрочный интервал
                contentStream.showText("Активности из KANBAN");
                contentStream.newLine();
                contentStream.setFont(font, 14);
                int numeration = 1;
                for (Task t:list) {
                    contentStream.showText(numeration++ +")  "+t.text);
                    contentStream.newLine();
                    contentStream.showText("СТАТУС ВЫПОЛНЕНИЯ: "+t.getStatus());
                    contentStream.newLine();
                }
                contentStream.endText();

                contentStream.close();
                FileChooser fileChooser = new FileChooser();

                // Установка фильтра для сохранения только текстовых файлов
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF-файлы (*.pdf)", "*.pdf");
                fileChooser.getExtensionFilters().add(extFilter);

                // Показать диалоговое окно для выбора пути и имени файла
                File file = fileChooser.showSaveDialog(s);

                document.save(file.getAbsolutePath());
                document.close();

                System.out.println("PDF-файл успешно создан.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
