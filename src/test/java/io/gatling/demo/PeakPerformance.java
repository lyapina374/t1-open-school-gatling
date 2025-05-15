package io.gatling.demo;

import io.gatling.javaapi.core.Simulation;

import java.time.Duration;

import static io.gatling.demo.Scenarios.*;
import static io.gatling.demo.Transactions.httpProtocol;
import static io.gatling.javaapi.core.CoreDsl.*;

public class PeakPerformance extends Simulation {
    {
        setUp(
                scn1LoginLogout.injectClosed(
                        constantConcurrentUsers(9).during(Duration.ofMinutes(2)),
                        constantConcurrentUsers(7).during(Duration.ofMinutes(2)),
                        constantConcurrentUsers(5).during(Duration.ofMinutes(2)),
                        constantConcurrentUsers(3).during(Duration.ofMinutes(2)),
                        constantConcurrentUsers(1).during(Duration.ofMinutes(2))),
                scn2SearchTicketWithoutPayment.injectClosed(
                        constantConcurrentUsers(18).during(Duration.ofMinutes(2)),
                        constantConcurrentUsers(14).during(Duration.ofMinutes(2)),
                        constantConcurrentUsers(10).during(Duration.ofMinutes(2)),
                        constantConcurrentUsers(6).during(Duration.ofMinutes(2)),
                        constantConcurrentUsers(2).during(Duration.ofMinutes(2))),
                scn3BuyTicket.injectClosed(
                        constantConcurrentUsers(27).during(Duration.ofMinutes(2)),
                        constantConcurrentUsers(21).during(Duration.ofMinutes(2)),
                        constantConcurrentUsers(15).during(Duration.ofMinutes(2)),
                        constantConcurrentUsers(9).during(Duration.ofMinutes(2)),
                        constantConcurrentUsers(3).during(Duration.ofMinutes(2))),
                scn4ViewItinerary.injectClosed(
                        constantConcurrentUsers(9).during(Duration.ofMinutes(2)),
                        constantConcurrentUsers(7).during(Duration.ofMinutes(2)),
                        constantConcurrentUsers(5).during(Duration.ofMinutes(2)),
                        constantConcurrentUsers(6).during(Duration.ofMinutes(2)),
                        constantConcurrentUsers(1).during(Duration.ofMinutes(2))),
                scn5CancelTicket.injectClosed(
                        constantConcurrentUsers(9).during(Duration.ofMinutes(2)),
                        constantConcurrentUsers(7).during(Duration.ofMinutes(2)),
                        constantConcurrentUsers(5).during(Duration.ofMinutes(2)),
                        constantConcurrentUsers(6).during(Duration.ofMinutes(2)),
                        constantConcurrentUsers(1).during(Duration.ofMinutes(2))),
                scn6UserRegistration.injectClosed(
                        constantConcurrentUsers(18).during(Duration.ofMinutes(2)),
                        constantConcurrentUsers(14).during(Duration.ofMinutes(2)),
                        constantConcurrentUsers(10).during(Duration.ofMinutes(2)),
                        constantConcurrentUsers(6).during(Duration.ofMinutes(2)),
                        constantConcurrentUsers(2).during(Duration.ofMinutes(2)))
        ).protocols(httpProtocol).maxDuration(Duration.ofMinutes(10));
    }
}
