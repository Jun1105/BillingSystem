package com.jun.billing.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class DateUtil {

    /**
     * 获取n周 周一日期 - 周日日期
     * @return
     */
    public static List<LocalDate> getLastDate(Integer n){
        LocalDate localDate = LocalDate.now();
        LocalDate todayOfLastWeek = localDate.minusDays(7*n);
        LocalDate monday = todayOfLastWeek.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
        LocalDate sunday = todayOfLastWeek.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        List<LocalDate> localDates = new ArrayList<>();
        localDates.add(monday);
        localDates.add(sunday);
        return localDates;
    }

    public static void main(String[] args) {
        List<LocalDate> lastDate = getLastDate(4);
        List<LocalDate> lastDate1 = getLastDate(4);
        System.out.println(lastDate.get(0).isBefore(lastDate1.get(0)));
        LocalDate day0 = LocalDate.of(2022, 8, 29);
        System.out.println(day0.toString());

        LocalDate day1 = LocalDate.of(2022, 9, 4);
        System.out.println(day1.toString());

        System.out.println(DAYS.between(day0, day1));
    }
}
