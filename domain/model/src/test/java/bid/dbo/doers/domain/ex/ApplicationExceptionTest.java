package bid.dbo.doers.domain.ex;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationExceptionTest {

    @Test
    public void testExceptionStructure() {
        ApplicationException exception = new ApplicationException("msj", "code001");
        assertThat(exception).hasMessage("msj");
        assertThat(exception.getCode()).isEqualTo("code001");
    }
}