package com.tradeautomizer.services;

import com.tradeautomizer.entity.ProcessorEntity;
import com.tradeautomizer.repository.ProcessorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProcessorsFinder {

    private final ProcessorsRepository processorsRepository;
    private final List<String> processorRelatedLines = new ArrayList<>();
    private final List<ProcessorEntity> processorEntities = new ArrayList<>();

    @Autowired
    public ProcessorsFinder(ProcessorsRepository processorsRepository) {
        this.processorsRepository = processorsRepository;
    }

    private void getAllProcessors() {
        processorEntities.clear();
        processorEntities.addAll(processorsRepository.findAll());
    }

    private void findProcessorsTzLines(String[] tzLines) {
        processorRelatedLines.clear();
        for(String oneLine : tzLines) {
            oneLine = oneLine.toLowerCase();
            if(oneLine.contains("количество ядер") ||
                    oneLine.contains("количество потоков") ||
                    oneLine.contains("базовая частота") ||
                    oneLine.contains("объем кэш") ||
                    oneLine.contains("количество слотов для модулей оперативной памяти")){
                processorRelatedLines.add(oneLine);
            }
        }
    }

    private void selectProcessorsByCore() {
        for(String oneLine : processorRelatedLines) {
            if(oneLine.contains("количество ядер")) {
                List<ProcessorEntity> filteredByCore;
                if(oneLine.contains("≥")) {
                    filteredByCore = processorEntities.stream()
                            .filter(proc -> proc.getCore() >= getIntValue(oneLine))
                            .collect(Collectors.toList());
                } else {
                    filteredByCore = processorEntities.stream()
                            .filter(proc -> proc.getCore() <= getIntValue(oneLine))
                            .collect(Collectors.toList());
                }
                processorEntities.clear();
                processorEntities.addAll(filteredByCore);
                return;
            }
        }
    }

    private void selectProcessorsByFlow() {
        for(String oneLine : processorRelatedLines) {
            if(oneLine.contains("количество потоков")) {
                List<ProcessorEntity> filteredByFlow;
                if(oneLine.contains("≥")) {
                    filteredByFlow = processorEntities.stream()
                            .filter(proc -> proc.getFlow() >= getIntValue(oneLine))
                            .collect(Collectors.toList());
                } else {
                    filteredByFlow = processorEntities.stream()
                            .filter(proc -> proc.getFlow() <= getIntValue(oneLine))
                            .collect(Collectors.toList());
                }
                processorEntities.clear();
                processorEntities.addAll(filteredByFlow);
                return;
            }
        }
    }

    private void selectProcessorsByBaseFrequency() {
        for(String oneLine : processorRelatedLines) {
            if(oneLine.contains("базовая частота")) {
                List<ProcessorEntity> filteredByBaseFrequency;
                if(oneLine.contains("≥")) {
                    filteredByBaseFrequency = processorEntities.stream()
                            .filter(proc -> proc.getBaseFrequency() >= getDoubleValue(oneLine))
                            .collect(Collectors.toList());
                } else {
                    filteredByBaseFrequency = processorEntities.stream()
                            .filter(proc -> proc.getBaseFrequency() <= getDoubleValue(oneLine))
                            .collect(Collectors.toList());
                }
                processorEntities.clear();
                processorEntities.addAll(filteredByBaseFrequency);
                return;
            }
        }
    }

    private void selectProcessorsByCache() {
        for(String oneLine : processorRelatedLines) {
            if(oneLine.contains("объем кэш")) {
                List<ProcessorEntity> filteredByCache;
                if(oneLine.contains("≥")) {
                    filteredByCache = processorEntities.stream()
                            .filter(proc -> proc.getCache() >= getDoubleValue(oneLine))
                            .collect(Collectors.toList());
                } else {
                    filteredByCache = processorEntities.stream()
                            .filter(proc -> proc.getCache() <= getDoubleValue(oneLine))
                            .collect(Collectors.toList());
                }
                processorEntities.clear();
                processorEntities.addAll(filteredByCache);
                return;
            }
        }
    }

    private void selectProcessorsByMaxMemoryModules() {
        for(String oneLine : processorRelatedLines) {
            if(oneLine.contains("количество слотов для модулей оперативной памяти")) {
                List<ProcessorEntity> filteredByMaxMemoryModules;
                if(oneLine.contains("≥")) {
                    filteredByMaxMemoryModules = processorEntities.stream()
                            .filter(proc -> proc.getMemoryCount() >= getIntValue(oneLine))
                            .collect(Collectors.toList());
                } else {
                    filteredByMaxMemoryModules = processorEntities.stream()
                            .filter(proc -> proc.getMemoryCount() <= getIntValue(oneLine))
                            .collect(Collectors.toList());
                }
                processorEntities.clear();
                processorEntities.addAll(filteredByMaxMemoryModules);
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

    private Double getDoubleValue(String line) {
        if(line.contains("≥")) {
            return Double.parseDouble(line.split("≥", 2)[1].replaceAll("[^0-9.]", ""));
        } else {
            return Double.parseDouble(line.split("≤", 2)[1].replaceAll("[^0-9.]", ""));
        }
    }

    public List<ProcessorEntity> get(String[] tzLines){
        getAllProcessors();
        findProcessorsTzLines(tzLines);
        selectProcessorsByCore();
        selectProcessorsByFlow();
        selectProcessorsByBaseFrequency();
        selectProcessorsByCache();
        selectProcessorsByMaxMemoryModules();
        return processorEntities;
    }
}
