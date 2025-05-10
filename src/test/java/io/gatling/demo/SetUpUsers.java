package io.gatling.demo;

import io.gatling.javaapi.core.Simulation;

import static io.gatling.demo.Scenarios.*;
import static io.gatling.demo.Transactions.httpProtocol;
import static io.gatling.javaapi.core.CoreDsl.atOnceUsers;
import static io.gatling.javaapi.core.CoreDsl.incrementConcurrentUsers;

public class SetUpUsers extends Simulation {
    {
        setUp(
                scnSetupUserWith5Tickets.injectOpen(atOnceUsers(10))
        ).protocols(httpProtocol);
    }
}
