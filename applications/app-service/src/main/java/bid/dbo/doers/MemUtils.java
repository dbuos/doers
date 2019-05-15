package bid.dbo.doers;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.NumberFormat;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemUtils {
    public static void printInfo() {
        final MemInfo memInfo = getMemInfo();
        System.out.println("========================== Memory Info ==========================");
        System.out.println("Free memory: " + formatInMB(memInfo.getFreeMemory()));
        System.out.println("Allocated memory: " + formatInMB(memInfo.allocatedMemory));
        System.out.println("Max memory: " + formatInMB(memInfo.getMaxMemory()));
        System.out.println("Total free memory: " + formatInMB(memInfo.getTotalFreeMemory()));
        System.out.println("=================================================================");
    }

    public static String formatInMB(long bytes) {
        final long mb = 1024 * 1024;
        return NumberFormat.getInstance().format(bytes / mb) + " MB";
    }

    public static MemInfo getMemInfo() {
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
