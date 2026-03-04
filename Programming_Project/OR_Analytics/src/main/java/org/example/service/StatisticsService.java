package org.example.service;

import org.example.model.Surgery;

import java.util.*;
import java.util.stream.Collectors;

public class StatisticsService {

    public static double averageDuration(List<Surgery> list) {
        return list.stream().mapToInt(s -> s.duration).average().orElse(0);
    }

    public static String mostFrequentProcedure(List<Surgery> list) {
        return list.stream()
                .collect(Collectors.groupingBy(s -> s.procedure, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("N/A");
    }

    public static int totalORHours(List<Surgery> list) {
        int totalMinutes = list.stream().mapToInt(s -> s.duration).sum();
        return totalMinutes / 60;
    }

    public static Map<String, Integer> usageBySpecialty(List<Surgery> list) {
        Map<String, Integer> map = new TreeMap<>();
        for (Surgery s : list) {
            map.put(s.specialty, map.getOrDefault(s.specialty, 0) + s.duration);
        }
        return map;
    }

    public static Map<String, Integer> surgeriesBySurgeon(List<Surgery> list) {
        Map<String, Integer> map = new TreeMap<>();
        for (Surgery s : list) {
            map.put(s.surgeon, map.getOrDefault(s.surgeon, 0) + 1);
        }
        return map;
    }

    public static Map<String, Double> procedureVsRecovery(List<Surgery> list) {
        Map<String, List<Integer>> temp = new HashMap<>();
        for (Surgery s : list) {
            temp.computeIfAbsent(s.procedure, k -> new ArrayList<>()).add(s.recovery);
        }
        Map<String, Double> result = new TreeMap<>();
        for (String proc : temp.keySet()) {
            List<Integer> recs = temp.get(proc);
            double avg = recs.stream().mapToInt(i -> i).average().orElse(0);
            result.put(proc, avg);
        }
        return result;
    }

    public static Map<Integer, Integer> durationVsOccupancy(List<Surgery> list) {
        // Simple: group by duration buckets (e.g. 0–60, 61–120, etc.)
        Map<Integer, Integer> map = new TreeMap<>();
        for (Surgery s : list) {
            int bucket = (s.duration / 30) * 30; // 0, 30, 60, 90, ...
            map.put(bucket, map.getOrDefault(bucket, 0) + 1);
        }
        return map;
    }

    public static Map<Integer, Integer> hourlyOccupancy(List<Surgery> list) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (Surgery s : list) {
            map.put(s.startHour, map.getOrDefault(s.startHour, 0) + 1);
        }
        return map;
    }

    public static Map<String, Integer> dailyVolume(List<Surgery> list) {
        Map<String, Integer> map = new TreeMap<>();
        for (Surgery s : list) {
            map.put(s.date, map.getOrDefault(s.date, 0) + 1);
        }
        return map;
    }

    public static Map<String, Double> movingAverageDailyVolume(List<Surgery> list, int window) {
        Map<String, Integer> daily = dailyVolume(list);
        List<String> days = new ArrayList<>(daily.keySet());
        Collections.sort(days);

        Map<String, Double> result = new LinkedHashMap<>();
        for (int i = 0; i < days.size(); i++) {
            int start = Math.max(0, i - window + 1);
            int sum = 0;
            int count = 0;
            for (int j = start; j <= i; j++) {
                sum += daily.get(days.get(j));
                count++;
            }
            result.put(days.get(i), sum / (double) count);
        }
        return result;
    }
}
