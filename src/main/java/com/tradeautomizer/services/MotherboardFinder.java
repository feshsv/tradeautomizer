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
                    oneLine.contains("интерфейс подключения накопителей") || // так же есть "интерфейс накопителей"
                    oneLine.contains("тип установленных накопителей") ||
                    oneLine.contains("количество установленных накопителей") ||
                    oneLine.contains("количество сетевых портов Ethernet") ||
                    oneLine.contains("скорость сетевого порта Ethernet") ||
                    oneLine.contains("количество слотов для модулей оперативной памяти") || //этого в одном ТЗ нет
                    oneLine.contains("наличие интегрированного видеоадаптера") ||
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
                if(oneLine.contains("≥")) {
                    filteredByProcessorsCount = motherboardEntities.stream()
                            .filter(motherboard -> motherboard.getProcessorsCount().equals("up") && getIntValue(oneLine) == 1)
                            .collect(Collectors.toList());
                } else {
                    filteredByProcessorsCount = motherboardEntities.stream()
                            .filter(motherboard -> motherboard.getProcessorsCount().equals("dp") && getIntValue(oneLine) > 1)
                            .collect(Collectors.toList());
                }
                motherboardEntities.clear();
                motherboardEntities.addAll(filteredByProcessorsCount);
                return;
            }
        }
    }

    private void selectMotherboardByRomInterfaces() {
        boolean isSata = false;
        boolean isUsb = false;
        for(String oneLine : motherboardRelatedLines) {
            if(oneLine.contains("интерфейс поддерживаемых накопителей")) {
                List<MotherboardEntity> filteredByRomInterfaces;
                if(oneLine.contains("sata")) {
                    isSata = true;
                } else if(oneLine.contains("usb")) {
                    isUsb = true;
                }
                //todo вощем isSas есть в таблице, а вот с SATA и USB вопрос
                filteredByRomInterfaces = motherboardEntities.stream()
                        .filter(motherboard -> motherboard.getIsSas() == oneLine.contains("sas"))
                        .filter(motherboard -> motherboard.getSataCount() > 0 && oneLine.contains("sata"))
                        //todo .filter(motherboard -> motherboard.getUsb //и тут с usb винтами, но их явно в таблице не увидел)
                        .collect(Collectors.toList());

                motherboardEntities.clear();
                motherboardEntities.addAll(filteredByRomInterfaces);
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




        return motherboardEntities;
    }
}
