package com.tradeautomizer.utils;

import com.tradeautomizer.populatedb.CsvToDaos;
import com.tradeautomizer.repository.ProcessorsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CsvToDaosTest {

    CsvToDaos csvToDaos;
    ProcessorsRepository processorsRepository;

    @Autowired
    public CsvToDaosTest(CsvToDaos csvToDaos, ProcessorsRepository processorsRepository) {
        this.csvToDaos = csvToDaos;
        this.processorsRepository = processorsRepository;
    }

    @Test
    void isDataInDbTest() {
//        dataPusher.readCsv("C:\\Users\\Sergey\\Downloads\\Закупки\\processors1.csv");
//        List<ProcessorEntity> processorEntity = processorsRepository.findAll();
//        assertThat(processorEntity.get(0)).isNotNull();
    }
}