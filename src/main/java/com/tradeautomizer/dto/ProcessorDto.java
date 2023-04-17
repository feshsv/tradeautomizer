package com.tradeautomizer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessorDto {
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
