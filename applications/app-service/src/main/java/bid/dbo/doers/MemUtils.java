package bid.dbo.doers;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.NumberFormat;
import java.util.logging.Logger;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemUtils {
    public static void printInfo() {
        final MemInfo memInfo = getMemInfo();
        final Logger log = Logger.getGlobal();
        log.info("========================== Memory Info ==========================");
        log.info("Free memory: " + formatInMB(memInfo.getFreeMemory()));
        log.info("Allocated memory: " + formatInMB(memInfo.allocatedMemory));
        log.info("Max memory: " + formatInMB(memInfo.getMaxMemory()));
        log.info("Total free memory: " + formatInMB(memInfo.getTotalFreeMemory()));
        log.info("=================================================================");
    }

    public static String formatInMB(long bytes) {
        final long mb = 1024 * 1024L;
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
