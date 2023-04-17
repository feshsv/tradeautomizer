package com.tradeautomizer.entities;

import com.tradeautomizer.dto.MotherboarDto;
import com.tradeautomizer.dto.ProcessorDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EntityType {
    PROCESSOR(ProcessorDto.class),
    MOTHERBOARD(MotherboarDto.class);

    private final Class<?> type;
}
