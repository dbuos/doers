package bid.dbo.doers;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MemUtilsTest {

    @Test
    public void formatInMB() {
        final String format = MemUtils.formatInMB(406515712L);
        assertThat(format).isEqualTo("387 MB");
    }

    @Test
    public void print() {
        MemUtils.printInfo();
    }
}