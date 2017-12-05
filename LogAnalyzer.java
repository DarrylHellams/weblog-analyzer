/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    private int[] dayCounts;
    private int[] monthCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer(String web)
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        dayCounts = new int[31];
        monthCounts = new int[12];
        // Create the reader to obtain the data.
        reader = new LogfileReader(web);
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            int day = entry.getDay();
            int month = entry.getMonth();
            hourCounts[hour]++;
            dayCounts[day]++;
            monthCounts[month]++;
        }
    }
    
    public int numberofAccesses()
    {
        int total = 0;
        
        for (int hour = 0; hour < hourCounts.length; hour++)
        {
            total = total + hourCounts[hour];
        }
        return total;
    }
    
    public int busiestHour()
    {
        int busiest = 0;
        int busiestHour = 0;
        
        for (int hour = 0; hour < hourCounts.length; hour++)
        {
            if (busiest < hourCounts[hour])
            {
                busiest = hourCounts[hour];
                busiestHour = hour;
            }
        }
        return busiestHour;
    }
    
    public int quietestHour()
    {
        int quietest = hourCounts[0];
        int quietestHour = 0;
        
        for (int hour = 0; hour < hourCounts.length; hour++)
        {
            if (quietest > hourCounts[hour])
            {
                quietest = hourCounts[hour];
                quietestHour = hour;
            }
        }
        return quietestHour;
    }
    
    public int busiestTwoHourPeriod()
    {
        int busiestPeriod = 0;
        int busiestPeriodHour = 0;
        
        for (int hour = 0; hour < hourCounts.length - 1; hour++)
        {
            int periodCount = hourCounts[hour] + hourCounts[hour + 1];
            if (periodCount > busiestPeriodHour)
            {
                busiestPeriod = hour;
                busiestPeriodHour = periodCount;
            }
        }
        return busiestPeriodHour;
    }
    
    public int busiestDay()
     {
         int busiest = 0;
        int busiestday = 0;
         
         for (int day = 0; day < dayCounts.length; day++)
         {
             if (busiest < dayCounts[day])
             {
                 busiest = dayCounts[day];
                 busiestday = day;
             }
         }
         return busiestday;
     }
     
    public int quietestDay()
     {
        int quietest = dayCounts[1];
        int quietestday = 0;
         
        for (int day = 0; day < dayCounts.length; day++)
        {
            if (quietest < dayCounts[day])
            {
                 quietest = dayCounts[day];
                 quietestday = day;
            }
        }
        return quietestday;
     }
     
    public int busiestMonth()
    {
        int busiest = 0;
        int busiestMonth = 0;
         
        for (int month = 0; month < monthCounts.length; month++)
        {
            if (busiest < monthCounts[month])
            {
                busiest = monthCounts[month];
                busiestMonth = month;
            }
        }
         return busiestMonth;
    }
    
    public int quietestMonth()
    {
        int quietest = monthCounts[1];
        int quietestMonth = 0;
        
        for (int month = 1; month < monthCounts.length; month++)
        {
            if (quietest < monthCounts[month])
            {
                quietest = monthCounts[month];
                quietestMonth = month;
            }
        }
        return quietestMonth;
    }
        
    public int averageAccessesPerMonth()
     {
         int average = 0;
         
         for(int month = 0; month < monthCounts.length; month++)
         {
             average = average + monthCounts[month];
         }
         
         average = average / 12;
         return average;
     }
    
    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
}
