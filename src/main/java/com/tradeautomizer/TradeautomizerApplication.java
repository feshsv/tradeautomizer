package com.tradeautomizer;

import com.tradeautomizer.populatedb.CommonFillerDb;
import com.tradeautomizer.services.MotherboardFinder;
import com.tradeautomizer.services.ProcessorsFinder;
import com.tradeautomizer.utils.DocxHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class TradeautomizerApplication implements CommandLineRunner {

    private final ProcessorsFinder processorsFinder;
    private final MotherboardFinder motherboardFinder;
    private final CommonFillerDb commonFillerDb;


    @Autowired
    public TradeautomizerApplication(ProcessorsFinder processorsFinder,
                                     MotherboardFinder motherboardFinder,
                                     CommonFillerDb commonFillerDb) {
        this.processorsFinder = processorsFinder;
        this.motherboardFinder = motherboardFinder;
        this.commonFillerDb = commonFillerDb;
    }


    public static void main(String[] args) {
        SpringApplication.run(TradeautomizerApplication.class, args);

    }

    @Override
    public void run(String... args) {
        commonFillerDb.fillDb();
        final var tzLines = DocxHelper.getLineByLineTextFromDocx(new File("C:\\Users\\Sergey\\Downloads\\Закупки\\simple.docx"));
        final var processors = processorsFinder.get(tzLines);
        final var motherboards = motherboardFinder.get(tzLines);


    }
}
