package io.gatling.demo;

import io.gatling.javaapi.core.Simulation;

import static io.gatling.demo.Scenarios.*;
import static io.gatling.demo.Transactions.httpProtocol;
import static io.gatling.javaapi.core.CoreDsl.atOnceUsers;
import static io.gatling.javaapi.core.CoreDsl.constantConcurrentUsers;

public class StartSimulation extends Simulation {
    {
//        setUp(scnSetupUserWith5Tickets.injectOpen(atOnceUsers(10))).protocols(httpProtocol);
         setUp(scn6UserRegistration.injectOpen(atOnceUsers(1))).protocols(httpProtocol).maxDuration(10);
//        setUp(
//                scn1LoginLogout.injectClosed(constantConcurrentUsers(1).during(1200)),
//                scn2SearchTicketWithoutPayment.injectClosed(constantConcurrentUsers(2).during(1200)),
//                scn3BuyTicket.injectClosed(constantConcurrentUsers(3).during(1200)),
//                scn4ViewItinerary.injectClosed(constantConcurrentUsers(1).during(1200)),
//                scn5CancelTicket.injectClosed(constantConcurrentUsers(1).during(1200)),
//                scn6UserRegistration.injectClosed(constantConcurrentUsers(2).during(1200))
//        ).protocols(httpProtocol);
    }
}
