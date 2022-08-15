package com.tradeautomizer.entity;

import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.Entity;

@Data
@Entity
@Jacksonized
public class ProcessorEntity extends AbstractEntity {

    private String model;
    private Integer core;
    private Integer flow;
    private Double baseFrequency;
    private String unitOfClockFrequency;
    private Double maxBoostFrequency;
    private String unitOfMaxBoostFrequency;
    private Double cache;
    private String unitOfCache;
    private Integer tdp;
    private String unitOfTdp;
    private String socket;
    private String memoryType;
    private Integer memoryCount;
    private String cpuScale;
}
