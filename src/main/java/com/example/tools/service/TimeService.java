package com.example.tools.service;

import java.time.LocalDateTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

public class TimeService {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Duration calculateDifference(String startTime, String endTime) throws Exception {
        LocalDateTime start = LocalDateTime.parse(startTime, formatter);
        LocalDateTime end = LocalDateTime.parse(endTime, formatter);
        return Duration.between(start, end);
    }

    public String formatDuration(Duration duration) {
        long days = duration.toDays();
        long hours = duration.toHoursPart();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();
        
        StringBuilder result = new StringBuilder();
        if (days > 0) result.append(days).append("天 ");
        if (hours > 0) result.append(hours).append("小时 ");
        if (minutes > 0) result.append(minutes).append("分钟 ");
        if (seconds > 0) result.append(seconds).append("秒");
        
        return result.toString();
    }
} 