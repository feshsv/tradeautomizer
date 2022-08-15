package com.tradeautomizer.entity;

import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.Entity;

@Data
@Entity
@Jacksonized
public class MotherboardEntity extends AbstractEntity {

    private String model;
    private String socket;
    private String processorsCount;
    private Integer dimmCount;
    private String chipset;
    private String size;
    private Integer pciEx40;
    private Integer pciEx32;
    private Integer pciEx16;
    private Integer pciEx8;
    private Integer pciEx4;
    private Integer pciEx1;
    private Integer m2sata;
    private Integer m2pciEx4;
    private Integer usb2internal;
    private Integer usb2external;
    private Integer usb3internal;
    private Integer usb3external;
    private Integer superDom;
    private Integer comExternal;
    private Integer comInternal;
    private Integer lanCount;
    private Integer lanSpeed;
    private String lanSpeedUnit;
    private Integer sataCount;
    private Boolean isSas;
}
