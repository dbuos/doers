package bid.dbo.doers.domain.ex;

import org.junit.Test;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

public class BusinessExceptionTest {

    @Test
    public void testException() {
        final BusinessException ex = BusinessException.Type.EX1.build();
        assertThat(ex).hasMessage("Msg");
        assertThat(ex.getCode()).isEqualTo("EX1");
    }

    @Test
    public void testExceptionDefer() {
        final Supplier<Throwable> defer = BusinessException.Type.EX1.defer();
        assertThat(defer.get()).hasMessage("Msg");
        assertThat(((BusinessException)defer.get()).getCode()).isEqualTo("EX1");
    }
}