package io.gatling.demo;

import io.gatling.javaapi.core.Simulation;

import static io.gatling.demo.Scenarios.*;
import static io.gatling.demo.Transactions.httpProtocol;
import static io.gatling.javaapi.core.CoreDsl.constantConcurrentUsers;

public class VolumeTest extends Simulation {
    {
        setUp(
                scn1LoginLogout.injectClosed(constantConcurrentUsers(4).during(3600)),
                scn2SearchTicketWithoutPayment.injectClosed(constantConcurrentUsers(8).during(3600)),
                scn3BuyTicket.injectClosed(constantConcurrentUsers(8).during(3600)),
                scn7Buy10Tickets.injectClosed(constantConcurrentUsers(4).during(3600)),
                scn4ViewItinerary.injectClosed(constantConcurrentUsers(4).during(3600)),
                scn5CancelTicket.injectClosed(constantConcurrentUsers(4).during(3600)),
                scn6UserRegistration.injectClosed(constantConcurrentUsers(8).during(3600))
        ).protocols(httpProtocol).maxDuration(3600);
    }
}
