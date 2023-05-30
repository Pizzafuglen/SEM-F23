package org.example;

import com.sun.net.httpserver.HttpServer;
import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ASServerTest extends TestCase {
    private HttpServer server;

    @BeforeEach
    public void setUp() throws Exception {
        server = HttpServer.create(new InetSocketAddress("localhost", 8002), 0);
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

        server.createContext("/3/", new ASHandler());
        server.setExecutor(threadPoolExecutor);

        server.start();
    }

    @AfterEach
    public void tearDown() {
        server.stop(0);
    }

    @Test
    public void testServerCreation() {
        assertNotNull(server);
        assertEquals("localhost", server.getAddress().getHostName());
        assertEquals(8002, server.getAddress().getPort());
    }


}