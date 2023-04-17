package com.tradeautomizer.populatedb;

import com.tradeautomizer.entities.MotherboardEntity;
import com.tradeautomizer.entities.ProcessorEntity;

import java.util.UUID;

public class ObjectFactory {

    private ObjectFactory(){}

    public static ProcessorEntity createProcessorEntity(String s) {
        final var processorParamsArray = s.split(";");
        ProcessorEntity processorEntity = new ProcessorEntity();
        processorEntity.setId(UUID.randomUUID().toString());
        processorEntity.setModel(processorParamsArray[0]);
        processorEntity.setCore(Integer.parseInt(processorParamsArray[1]));
        processorEntity.setFlow(Integer.parseInt(processorParamsArray[2]));
        processorEntity.setBaseFrequency(Double.valueOf(processorParamsArray[3]));
        processorEntity.setUnitOfClockFrequency(processorParamsArray[4]);
        processorEntity.setMaxBoostFrequency(Double.valueOf(processorParamsArray[5]));
        processorEntity.setUnitOfMaxBoostFrequency(processorParamsArray[6]);
        processorEntity.setCache(Double.valueOf(processorParamsArray[7]));
        processorEntity.setUnitOfCache(processorParamsArray[8]);
        processorEntity.setTdp(Integer.parseInt(processorParamsArray[9]));
        processorEntity.setUnitOfTdp(processorParamsArray[10]);
        processorEntity.setSocket(processorParamsArray[11]);
        processorEntity.setMemoryType(processorParamsArray[12]);
        processorEntity.setMemoryCount(Integer.parseInt(processorParamsArray[13]));
        processorEntity.setCpuScale(processorParamsArray[14]);
        return processorEntity;
    }

    public static MotherboardEntity createMotherboarEntity(String s) {
        final var motherboradParamsArray = s.split(";");
        MotherboardEntity motherboardEntity = new MotherboardEntity();
        motherboardEntity.setId(UUID.randomUUID().toString());
        motherboardEntity.setModel(motherboradParamsArray[0]);
        motherboardEntity.setSocket(motherboradParamsArray[1]);
        motherboardEntity.setProcessorsCount(motherboradParamsArray[2]);
        motherboardEntity.setDimmCount(Integer.parseInt(motherboradParamsArray[3]));
        motherboardEntity.setChipset(motherboradParamsArray[4]);
        motherboardEntity.setSize(motherboradParamsArray[5]);
        motherboardEntity.setPciEx40(Integer.parseInt(motherboradParamsArray[6]));
        motherboardEntity.setPciEx32(Integer.parseInt(motherboradParamsArray[7]));
        motherboardEntity.setPciEx16(Integer.parseInt(motherboradParamsArray[8]));
        motherboardEntity.setPciEx8(Integer.parseInt(motherboradParamsArray[9]));
        motherboardEntity.setPciEx4(Integer.parseInt(motherboradParamsArray[10]));
        motherboardEntity.setPciEx1(Integer.parseInt(motherboradParamsArray[11]));
        motherboardEntity.setM2sata(Integer.parseInt(motherboradParamsArray[12]));
        motherboardEntity.setM2pciEx4(Integer.parseInt(motherboradParamsArray[13]));
        motherboardEntity.setUsb2internal(Integer.parseInt(motherboradParamsArray[14]));
        motherboardEntity.setUsb2external(Integer.parseInt(motherboradParamsArray[15]));
        motherboardEntity.setUsb3internal(Integer.parseInt(motherboradParamsArray[16]));
        motherboardEntity.setUsb3external(Integer.parseInt(motherboradParamsArray[17]));
        motherboardEntity.setSuperDom(Integer.parseInt(motherboradParamsArray[18]));
        motherboardEntity.setComExternal(Integer.parseInt(motherboradParamsArray[19]));
        motherboardEntity.setComInternal(Integer.parseInt(motherboradParamsArray[20]));
        motherboardEntity.setLanCount(Integer.parseInt(motherboradParamsArray[21]));
        motherboardEntity.setLanSpeed(Integer.parseInt(motherboradParamsArray[22]));
        motherboardEntity.setLanSpeedUnit(motherboradParamsArray[23]);
        motherboardEntity.setSataCount(Integer.parseInt(motherboradParamsArray[24]));
        motherboardEntity.setIsSas(Boolean.valueOf(motherboradParamsArray[25]));
        return motherboardEntity;
    }
}
