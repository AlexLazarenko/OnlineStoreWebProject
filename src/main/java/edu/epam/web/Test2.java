package edu.epam.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

public class Test2 {

    public static String MostFreeTime(String[] strArr) {
        // code goes here
        SimpleDateFormat format = new SimpleDateFormat("h:mm a");
        Map<Date, Date> treeMap = new TreeMap<>();
        //validation
        if (strArr != null && strArr.length != 3) {
            System.out.println("Wrong input!!!");
        }
        for (int i = 0; i < strArr.length; i++) {
            int index = strArr[i].indexOf("-");
            if (index == -1) {
                System.out.println("Wrong input!!!");
            }
            //split to parse string to date
            String[] array = strArr[i].split("-");
            //add space symbol to make parse correct
            for (int x = 0; x < array.length; x = x + 2) {
                StringBuilder stringBuilder = new StringBuilder(array[x]);
                stringBuilder.insert(5, " ");
                String date1 = stringBuilder.toString();
                StringBuilder stringBuilder2 = new StringBuilder(array[x + 1]);
                stringBuilder2.insert(5, " ");
                String date2 = stringBuilder2.toString();
                try {
                    //parsing date and save to map
                    treeMap.put(format.parse(date1), format.parse(date2));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        int counter = 1;
        Date startFreeTime1 = null;
        Date endFreeTime1 = null;
        Date startFreeTime2 = null;
        Date endFreeTime2 = null;
        //select free time periods
        for (Map.Entry<Date, Date> entry : treeMap.entrySet()) {
            if (counter == 1) {
                startFreeTime1 = entry.getValue();
            }
            if (counter == 2) {
                endFreeTime1 = entry.getKey();
                startFreeTime2 = entry.getValue();
            }
            if (counter == 3) {
                endFreeTime2 = entry.getKey();
            }
            counter++;
        }
        //calculate free time periods
        long freeTimePeriod1 = endFreeTime1.getTime() - startFreeTime1.getTime();
        long freeTimePeriod2 = endFreeTime2.getTime() - startFreeTime2.getTime();
        long longestFreeTimePeriod = Math.max(freeTimePeriod1, freeTimePeriod2);
        //converting free time periods into hours and minutes
        long diffInMinutes = longestFreeTimePeriod / (60 * 1000) % 60;
        long diffInHours = longestFreeTimePeriod / (60 * 60 * 1000);
        //building answer
        StringBuilder builder = new StringBuilder();
        if (diffInHours < 10) {
            builder.append("0");
        }
        builder.append(diffInHours);
        builder.append(":");
        if (diffInMinutes < 10) {
            builder.append("0");
        }
        builder.append(diffInMinutes);
        String output=builder.toString();
        return output;
    }

    public static void main(String[] args) {
        // keep this function call here
        System.out.println(MostFreeTime(new String[]{"12:00PM-12:01PM", "12:02PM-12:03PM", "12:04PM-12:05PM"}));
        //   Scanner s = new Scanner(System.in);
        //  System.out.print(MostFreeTime(s.nextLine()));
    }

}

