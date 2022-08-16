package com.tradeautomizer.services;

import com.tradeautomizer.entity.MotherboardEntity;
import com.tradeautomizer.repository.MotherboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MotherboardFinder {

    private final MotherboardRepository motherboardRepository;
    private final List<String> motherboardRelatedLines = new ArrayList<>();
    private final List<MotherboardEntity> motherboardEntities = new ArrayList<>();

    @Autowired
    public MotherboardFinder(MotherboardRepository motherboardRepository) {
        this.motherboardRepository = motherboardRepository;
    }

    private void getAllMotherboards() {
        motherboardEntities.clear();
        motherboardEntities.addAll(motherboardRepository.findAll());
    }

    private void findMotherboardsTzLines(String[] tzLines) {
        motherboardRelatedLines.clear();
        for(String oneLine : tzLines) {
            oneLine = oneLine.toLowerCase();
            if(oneLine.contains("количество установленных процессоров") ||
                    oneLine.contains("интерфейс поддерживаемых накопителей") ||
                    //oneLine.contains("интерфейс подключения накопителей") || // так же есть "интерфейс накопителей"
                    oneLine.contains("количество сетевых портов") ||
                    oneLine.contains("количество слотов для модулей оперативной памяти") || //этого в одном ТЗ нет
                    oneLine.contains("количество слотов для установки плат расширения PCIe x16") || //этого в одном ТЗ нет
                    oneLine.contains("количество слотов для установки плат расширения PCIe x8")){ //этого в одном ТЗ нет
                motherboardRelatedLines.add(oneLine);
            }
        }
    }

    private void selectMotherboardByProcessorsCount() {
        for(String oneLine : motherboardRelatedLines) {
            if(oneLine.contains("количество установленных процессоров")) {
                List<MotherboardEntity> filteredByProcessorsCount;
                if(oneLine.contains("≥") && getIntValue(oneLine) == 1) {
                    filteredByProcessorsCount = motherboardEntities.stream()
                            .filter(motherboard -> motherboard.getProcessorsCount().equalsIgnoreCase("up"))
                            .collect(Collectors.toList());
                } else {
                    filteredByProcessorsCount = motherboardEntities.stream()
                            .filter(motherboard -> motherboard.getProcessorsCount().equalsIgnoreCase("dp") && getIntValue(oneLine) > 1)
                            .collect(Collectors.toList());
                }
                motherboardEntities.clear();
                motherboardEntities.addAll(filteredByProcessorsCount);
                return;
            }
        }
    }

    private void selectMotherboardByRomInterfaces() {
        for(String oneLine : motherboardRelatedLines) {
            if(oneLine.contains("интерфейс поддерживаемых накопителей")) {
                List<MotherboardEntity> filteredByRomInterfaces;
                filteredByRomInterfaces = motherboardEntities.stream()
                        .filter(motherboard -> motherboard.getIsSas() == oneLine.contains("sas"))
                        .filter(motherboard -> motherboard.getSataCount() > 0 && oneLine.contains("sata"))
                        .filter(motherboard -> (motherboard.getUsb2external() > 0 || motherboard.getUsb2internal() > 0 ||
                                                motherboard.getUsb3external() > 0 || motherboard.getUsb3internal() > 0) &&
                                                oneLine.contains("usb"))
                        .collect(Collectors.toList());
                motherboardEntities.clear();
                motherboardEntities.addAll(filteredByRomInterfaces);
                return;
            }
        }
    }

    private void selectMotherboardByLanPortsCount() {
        for(String oneLine : motherboardRelatedLines) {
            if(oneLine.contains("количество сетевых портов")) {
                List<MotherboardEntity> filteredByLanPortsCount;
                if(oneLine.contains("≥")) {
                    filteredByLanPortsCount = motherboardEntities.stream()
                            .filter(motherboard -> motherboard.getLanCount() >= getIntValue(oneLine))
                            .collect(Collectors.toList());
                } else {
                    filteredByLanPortsCount = motherboardEntities.stream()
                            .filter(motherboard -> motherboard.getLanCount() <= getIntValue(oneLine))
                            .collect(Collectors.toList());
                }
                motherboardEntities.clear();
                motherboardEntities.addAll(filteredByLanPortsCount);
                return;
            }
        }
    }

    private void selectMotherboardByDimmCount() {
        for(String oneLine : motherboardRelatedLines) {
            if(oneLine.contains("количество слотов для модулей оперативной памяти")) {
                List<MotherboardEntity> filteredByDimmCount;
                if(oneLine.contains("≥")) {
                    filteredByDimmCount = motherboardEntities.stream()
                            .filter(motherboard -> motherboard.getDimmCount() >= getIntValue(oneLine))
                            .collect(Collectors.toList());
                } else {
                    filteredByDimmCount = motherboardEntities.stream()
                            .filter(motherboard -> motherboard.getDimmCount() <= getIntValue(oneLine))
                            .collect(Collectors.toList());
                }
                motherboardEntities.clear();
                motherboardEntities.addAll(filteredByDimmCount);
                return;
            }
        }
    }

    private void selectMotherboardByPCIe8Count() {
        for(String oneLine : motherboardRelatedLines) {
            if(oneLine.contains("количество слотов для установки плат расширения PCIe x8")) {
                List<MotherboardEntity> filteredByPCIe8Count;
                if(oneLine.contains("≥")) {
                    filteredByPCIe8Count = motherboardEntities.stream()
                            .filter(motherboard -> motherboard.getPciEx8() >= getIntValue(oneLine))
                            .collect(Collectors.toList());
                } else {
                    filteredByPCIe8Count = motherboardEntities.stream()
                            .filter(motherboard -> motherboard.getPciEx8() <= getIntValue(oneLine))
                            .collect(Collectors.toList());
                }
                motherboardEntities.clear();
                motherboardEntities.addAll(filteredByPCIe8Count);
                return;
            }
        }
    }

    private void selectMotherboardByPCIe16Count() {
        for(String oneLine : motherboardRelatedLines) {
            if(oneLine.contains("количество слотов для установки плат расширения PCIe x16")) {
                List<MotherboardEntity> filteredByPCIe16Count;
                if(oneLine.contains("≥")) {
                    filteredByPCIe16Count = motherboardEntities.stream()
                            .filter(motherboard -> motherboard.getPciEx16() >= getIntValue(oneLine))
                            .collect(Collectors.toList());
                } else {
                    filteredByPCIe16Count = motherboardEntities.stream()
                            .filter(motherboard -> motherboard.getPciEx16() <= getIntValue(oneLine))
                            .collect(Collectors.toList());
                }
                motherboardEntities.clear();
                motherboardEntities.addAll(filteredByPCIe16Count);
                return;
            }
        }
    }

    private Integer getIntValue(String line) {
        if(line.contains("≥")) {
            return Integer.parseInt(line.split("≥", 2)[1].replaceAll("[^0-9.]", ""));
        } else {
            return Integer.parseInt(line.split("≤", 2)[1].replaceAll("[^0-9.]", ""));
        }
    }

    public List<MotherboardEntity> get(String[] tzLines){
        getAllMotherboards();
        findMotherboardsTzLines(tzLines);
        selectMotherboardByProcessorsCount();
        selectMotherboardByRomInterfaces();
        selectMotherboardByLanPortsCount();
        selectMotherboardByDimmCount();
        selectMotherboardByPCIe8Count();
        selectMotherboardByPCIe16Count();
        return motherboardEntities;
    }
}
