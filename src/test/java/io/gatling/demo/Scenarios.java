package io.gatling.demo;

import io.gatling.javaapi.core.ScenarioBuilder;

import static io.gatling.demo.Transactions.*;
import static io.gatling.javaapi.core.CoreDsl.*;

public class Scenarios {
    final static ScenarioBuilder scn1LoginLogout = scenario("UC1_loginLogout")
//            .during(120).on(
            .forever().on(
                    pace(100)
                            .exec(userDataPrepare)
                            .exec(welcome)
                            .pause(2)
                            .exec(login)
                            .pause(2)
                            .exec(flights)
                            .pause(2)
                            .exec(logout)
            );
    final static ScenarioBuilder scn2SearchTicketWithoutPayment = scenario("UC2_searchTicketWithoutPayment")
//            .during(120).on(
            .forever().on(
                    pace(73)
                            .exec(userDataPrepare)
                            .exec(flightDataPrepare)
                            .exec(welcome)
                            .pause(2)
                            .exec(login)
                            .pause(2)
                            .exec(flights)
                            .pause(2)
                            .exec(findFlight)
                            .pause(2)
                            .exec(chooseFlight)
            );
    final static ScenarioBuilder scn3BuyTicket = scenario("UC3_buyTicket")
//            .during(120).on(
            .forever().on(
                    pace(62)
                            .exec(userDataPrepare)
                            .exec(flightDataPrepare)
                            .exec(welcome)
                            .pause(2)
                            .exec(login)
                            .pause(2)
                            .exec(flights)
                            .pause(2)
                            .exec(findFlight)
                            .pause(2)
                            .exec(chooseFlight)
                            .pause(2)
                            .exec(paymentDetails)
                            .pause(2)
                            .exec(itineraryAfterBuying)
                            .pause(2)
                            .exec(logout)
            );
    final static ScenarioBuilder scn4ViewItinerary = scenario("UC4_viewItinerary")
//            .during(120).on(
            .forever().on(
                    pace(110)
                            .exec(userDataPrepare)
                            .exec(welcome)
                            .pause(2)
                            .exec(login)
                            .pause(2)
                            .exec(itinerarySimple)
                            .pause(2)
                            .exec(logout)
            );
    final static ScenarioBuilder scn5CancelTicket = scenario("UC5_cancelFirstTicket")
//            .during(120).on(
            .forever().on(
                    pace(49)
                            .exec(userDataPrepare)
                            .exec(welcome)
                            .pause(2)
                            .exec(login)
                            .pause(2)
                            .exec(itineraryBeforeCancel)
                            .pause(2)
                            .exec(cancelFirstTicket)
            );
    final static ScenarioBuilder scn6UserRegistration = scenario("UC6_userRegistration")
//            .during(120).on(
            .forever().on(
                    pace(74)
                            .exec(randomUserDataPrepare)
                            .exec(welcome)
                            .pause(2)
                            .exec(startRegistration)
                            .pause(2)
                            .exec(fillRegistrationFields)
                            .pause(2)
                            .exec(continueAfterRegistration)
                            .pause(2)
                            .exec(logout)
            );
    final static ScenarioBuilder scn7Buy10Tickets = scenario("UC7_buy10Tickets")
            .forever().on(
                    pace(62)
                            .exec(userDataPrepare)
                            .exec(welcome)
                            .pause(2)
                            .exec(login)
                            .pause(2)
                            .repeat(10).on(
                                    exec(flightDataPrepare)
                                            .exec(flights)
                                            .pause(2)
                                            .exec(findFlight)
                                            .pause(2)
                                            .exec(chooseFlight)
                                            .pause(2)
                                            .exec(paymentDetails)
                                            .pause(2)
                                            .exec(itineraryAfterBuying)
                                            .pause(2)
                            )
                            .exec(logout)
            );
    final static ScenarioBuilder scnSetupUserWith5Tickets = scenario("setup_UserWithTickets")
            .exec(userDataPrepare)
            .exec(welcome)
            .exec(startRegistration)
            .exec(fillRegistrationFields)
            .exec(continueAfterRegistration)
            .repeat(5).on(
                    exec(flightDataPrepare)
                            .exec(flights)
                            .exec(findFlight)
                            .exec(chooseFlight)
                            .exec(paymentDetails)
                            .exec(itineraryAfterBuying)
            )
            .exec(logout);
}
