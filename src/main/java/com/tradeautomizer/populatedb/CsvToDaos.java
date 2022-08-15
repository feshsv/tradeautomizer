package com.tradeautomizer.populatedb;

import com.tradeautomizer.dao.MotherboardDao;
import com.tradeautomizer.dao.ProcessorDao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class CsvToDaos {

    private static List<String> readCsv(String filePath) {
        List<String> lines = new ArrayList<>();
        try(var streamOfString = Files.lines(Paths.get(filePath))) {
            lines = streamOfString.filter(str -> !str.isBlank()).collect(Collectors.toList());
        } catch (IOException e) {
            //log
        }
        return lines;
    }

    public static List<ProcessorDao> getDaos(ProcessorDao pd, String filePath) {
        final List<String> lines = readCsv(filePath);
        boolean isFirstLineLeft = false;
        final List<ProcessorDao> processorDaos = new ArrayList<>();

        for (String procParams : lines) {
            if (!isFirstLineLeft) {
                isFirstLineLeft = true;
                continue;
            }
            final var processorParamsArray = procParams.split(";");
            try {
                final var processorDao = ProcessorDao.builder()
                        .model(processorParamsArray[0])
                        .core(Integer.parseInt(processorParamsArray[1]))
                        .flow(Integer.parseInt(processorParamsArray[2]))
                        .baseFrequency(Double.valueOf(processorParamsArray[3]))
                        .unitOfClockFrequency(processorParamsArray[4])
                        .maxBoostFrequency(Double.valueOf(processorParamsArray[5]))
                        .unitOfMaxBoostFrequency(processorParamsArray[6])
                        .cache(Double.valueOf(processorParamsArray[7]))
                        .unitOfCache(processorParamsArray[8])
                        .tdp(Integer.parseInt(processorParamsArray[9]))
                        .unitOfTdp(processorParamsArray[10])
                        .socket(processorParamsArray[11])
                        .memoryType(processorParamsArray[12])
                        .memoryCount(Integer.parseInt(processorParamsArray[13]))
                        .cpuScale(processorParamsArray[14])
                        .build();

                processorDaos.add(processorDao);
            } catch (Exception e) {
                //log
            }
        }
        return processorDaos;
    }

    public static List<MotherboardDao> getDaos(MotherboardDao mb, String filePath) {
        final List<String> lines = readCsv(filePath);
        boolean isFirstLineLeft = false;
        final List<MotherboardDao> motherboardDaos = new ArrayList<>();

        for (String motherboradParams : lines) {
            if (!isFirstLineLeft) {
                isFirstLineLeft = true;
                continue;
            }
            final var motherboradParamsArray = motherboradParams.split(";");
            try {
                final var motherboardDao = MotherboardDao.builder()
                        .model(motherboradParamsArray[0])
                        .socket(motherboradParamsArray[1])
                        .processorsCount(motherboradParamsArray[2])
                        .dimmCount(Integer.parseInt(motherboradParamsArray[3]))
                        .chipset(motherboradParamsArray[4])
                        .size(motherboradParamsArray[5])
                        .pciEx40(Integer.parseInt(motherboradParamsArray[6]))
                        .pciEx32(Integer.parseInt(motherboradParamsArray[7]))
                        .pciEx16(Integer.parseInt(motherboradParamsArray[8]))
                        .pciEx8(Integer.parseInt(motherboradParamsArray[9]))
                        .pciEx4(Integer.parseInt(motherboradParamsArray[10]))
                        .pciEx1(Integer.parseInt(motherboradParamsArray[11]))
                        .m2sata(Integer.parseInt(motherboradParamsArray[12]))
                        .m2pciEx4(Integer.parseInt(motherboradParamsArray[13]))
                        .usb2internal(Integer.parseInt(motherboradParamsArray[14]))
                        .usb2external(Integer.parseInt(motherboradParamsArray[15]))
                        .usb3internal(Integer.parseInt(motherboradParamsArray[16]))
                        .usb3external(Integer.parseInt(motherboradParamsArray[17]))
                        .superDom(Integer.parseInt(motherboradParamsArray[18]))
                        .comExternal(Integer.parseInt(motherboradParamsArray[19]))
                        .comInternal(Integer.parseInt(motherboradParamsArray[20]))
                        .lanCount(Integer.parseInt(motherboradParamsArray[21]))
                        .lanSpeed(Integer.parseInt(motherboradParamsArray[22]))
                        .lanSpeedUnit(motherboradParamsArray[23])
                        .sataCount(Integer.parseInt(motherboradParamsArray[24]))
                        .isSas(Boolean.valueOf(motherboradParamsArray[25]))
                        .build();

                motherboardDaos.add(motherboardDao);
            } catch (Exception e) {
                //log
            }
        }
        return motherboardDaos;
    }
}
