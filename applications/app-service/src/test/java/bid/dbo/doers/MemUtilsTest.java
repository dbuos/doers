package bid.dbo.doers;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.mockito.junit.MockitoJUnitRunner;

public class MemUtilsTest {

    @Test
    public void formatInMB() {
        final String format = MemUtils.formatInMB(406515712L);
        assertThat(format).isEqualTo("387 MB");
    }
}