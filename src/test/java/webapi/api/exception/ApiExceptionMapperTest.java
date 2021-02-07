package webapi.api.exception;

import static org.assertj.core.api.Assertions.assertThat;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;

public class ApiExceptionMapperTest {
    private ApiExceptionMapper exceptionMapper;
    
    @Before
    public void setup() {
        exceptionMapper = new ApiExceptionMapper();
    }

    @Test
    public void toResponse_otherException() {
        Response response = exceptionMapper.toResponse(new IllegalArgumentException());
        assertThat(response.getStatus()).isEqualTo(500);
        assertThat(response.getMediaType()).isEqualTo(MediaType.APPLICATION_JSON_TYPE);
    }
}
