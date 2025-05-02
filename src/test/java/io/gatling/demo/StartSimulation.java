package io.gatling.demo;

import io.gatling.javaapi.core.Simulation;

import static io.gatling.demo.Scenarios.*;
import static io.gatling.demo.Transactions.httpProtocol;
import static io.gatling.javaapi.core.CoreDsl.atOnceUsers;

public class StartSimulation extends Simulation {
    {
        setUp(viewItinerary.injectOpen(atOnceUsers(1))).protocols(httpProtocol);
    }
}
