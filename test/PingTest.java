import ge.edu.freeuni.sdp.service.scheduler.temperature.PingService;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

/**
 * Created by vato on 6/11/16.
 */
public class PingTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(PingService.class);
    }

    @Test
    public void TestPingReturns200() throws Exception {

        Response ping = target("ping")
                .request().get();


        int status = ping.getStatus();
        assertEquals(200, status);
    }
}
