package com.tradeautomizer.dto;

import lombok.*;

@Value
@Builder
public class MotherboarDto {
    static MotherboarDto EMPTY = MotherboarDto.builder().build();
    String model;
    String socket;
    String processorsCount;
    Integer dimmCount;
    String chipset;
    String size;
    Integer pciEx40;
    Integer pciEx32;
    Integer pciEx16;
    Integer pciEx8;
    Integer pciEx4;
    Integer pciEx1;
    Integer m2sata;
    Integer m2pciEx4;
    Integer usb2internal;
    Integer usb2external;
    Integer usb3internal;
    Integer usb3external;
    Integer superDom;
    Integer comExternal;
    Integer comInternal;
    Integer lanCount;
    Integer lanSpeed;
    String lanSpeedUnit;
    Integer sataCount;
    Boolean isSas;
}
