package com.tradeautomizer.dao;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProcessorDao implements Daos {
    String model;
    Integer core;
    Integer flow;
    Double baseFrequency;
    String unitOfClockFrequency;
    Double maxBoostFrequency;
    String unitOfMaxBoostFrequency;
    Double cache;
    String unitOfCache;
    Integer tdp;
    String unitOfTdp;
    String socket;
    String memoryType;
    Integer memoryCount;
    String cpuScale;
}
