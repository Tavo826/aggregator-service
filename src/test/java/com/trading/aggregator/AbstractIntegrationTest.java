package com.trading.aggregator;

import org.mockserver.client.MockServerClient;
import org.mockserver.springtest.MockServerTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@MockServerTest
@AutoConfigureWebTestClient
@SpringBootTest(properties = {
        "customer.service.url=http://localhost:{mockServerPort}",
        "stock.service.url=http://localhost:{mockServerPort}"
})
abstract class AbstractIntegrationTest {

    protected MockServerClient mockServer;

    @Autowired
    protected WebTestClient webTestClient;
}
