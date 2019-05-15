package bid.dbo.doers;

import lombok.Builder;
import lombok.Data;

import java.text.NumberFormat;

public class MemUtils {
    public static void printInfo() {
        Runtime runtime = Runtime.getRuntime();
        final long maxMemory = runtime.maxMemory();
        final long allocatedMemory = runtime.totalMemory();
        final long freeMemory = runtime.freeMemory();
        System.out.println("========================== Memory Info ==========================");
        System.out.println("Free memory: " + formatInMB(freeMemory));
        System.out.println("Allocated memory: " + formatInMB(allocatedMemory));
        System.out.println("Max memory: " + formatInMB(maxMemory));
        System.out.println("Total free memory: " + formatInMB(freeMemory + (maxMemory - allocatedMemory)));
        System.out.println("=================================================================");
    }

    public static String formatInMB(long bytes) {
        final long mb = 1024 * 1024;
        return NumberFormat.getInstance().format(bytes / mb) + " MB";
    }

    public MemInfo getMemInfo() {
        final Runtime runtime = Runtime.getRuntime();
        return MemInfo.builder()
            .allocatedMemory(runtime.totalMemory())
            .freeMemory(runtime.freeMemory())
            .maxMemory(runtime.maxMemory())
            .build();
    }

    @Data
    @Builder
    public static class MemInfo {
        final long maxMemory;
        final long allocatedMemory;
        final long freeMemory;

        public long getTotalFreeMemory() {
            return freeMemory + maxMemory - allocatedMemory;
        }
    }

}
