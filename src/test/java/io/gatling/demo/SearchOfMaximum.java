package io.gatling.demo;

import io.gatling.javaapi.core.Simulation;

import static io.gatling.demo.Scenarios.*;
import static io.gatling.demo.Transactions.httpProtocol;
import static io.gatling.javaapi.core.CoreDsl.*;

public class SearchOfMaximum extends Simulation {
    {
        setUp(
                scn1LoginLogout.injectClosed(
                        incrementConcurrentUsers(1)
                                .times(7)
                                .eachLevelLasting(1320)
                                .separatedByRampsLasting(60)
                                .startingFrom(4)
                ),
                scn2SearchTicketWithoutPayment.injectClosed(
                        incrementConcurrentUsers(2)
                                .times(7)
                                .eachLevelLasting(1320)
                                .separatedByRampsLasting(60)
                                .startingFrom(8)
                ),
                scn3BuyTicket.injectClosed(
                        incrementConcurrentUsers(3)
                                .times(7)
                                .eachLevelLasting(1320)
                                .separatedByRampsLasting(60)
                                .startingFrom(12)
                ),
                scn4ViewItinerary.injectClosed(
                        incrementConcurrentUsers(1)
                                .times(7)
                                .eachLevelLasting(1320)
                                .separatedByRampsLasting(60)
                                .startingFrom(4)
                ),
                scn5CancelTicket.injectClosed(
                        incrementConcurrentUsers(1)
                                .times(7)
                                .eachLevelLasting(1320)
                                .separatedByRampsLasting(60)
                                .startingFrom(4)
                ),
                scn6UserRegistration.injectClosed(
                        incrementConcurrentUsers(2)
                                .times(7)
                                .eachLevelLasting(1320)
                                .separatedByRampsLasting(60)
                                .startingFrom(8)
                )
        ).protocols(httpProtocol).maxDuration(9600);
    }
}
