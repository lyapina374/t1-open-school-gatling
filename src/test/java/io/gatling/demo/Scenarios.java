package io.gatling.demo;

import io.gatling.javaapi.core.ScenarioBuilder;

import static io.gatling.demo.Transactions.*;
import static io.gatling.javaapi.core.CoreDsl.*;

public class Scenarios {
    final static ScenarioBuilder scn1LoginLogout = scenario("UC1_loginLogout")
            .forever().on(
                    pace(100)
                            .exec(dataPrepare)
                            .exec(welcome)
                            .pause(3)
                            .exec(login)
                            .pause(3)
                            .exec(itinerarySimple)
                            .pause(3)
                            .exec(logout)
            );
    final static ScenarioBuilder scn2SearchTicketWithoutPayment = scenario("UC2_searchTicketWithoutPayment")
            .forever().on(
                    pace(73)
                            .exec(dataPrepare)
                            .exec(welcome)
                            .pause(3)
                            .exec(login)
                            .pause(3)
                            .exec(flights)
                            .pause(3)
                            .exec(findFlight)
                            .pause(3)
                            .exec(chooseFlight)
            );
    final static ScenarioBuilder scn3BuyTicket = scenario("UC3_buyTicket")
            .forever().on(
                    pace(62)
                            .exec(dataPrepare)
                            .exec(welcome)
                            .pause(3)
                            .exec(login)
                            .pause(3)
                            .exec(flights)
                            .pause(3)
                            .exec(findFlight)
                            .pause(3)
                            .exec(chooseFlight)
                            .pause(3)
                            .exec(paymentDetails)
                            .pause(3)
                            .exec(itineraryAfterBuying)
                            .pause(3)
                            .exec(logout)
            );
    final static ScenarioBuilder scn4ViewItinerary = scenario("UC4_viewItinerary")
            .forever().on(
                    pace(110)
                            .exec(dataPrepare)
                            .exec(welcome)
                            .pause(3)
                            .exec(login)
                            .pause(3)
                            .exec(itinerarySimple)
                            .pause(3)
                            .exec(logout)
            );
    final static ScenarioBuilder scn5CancelTicket = scenario("UC5_cancelFirstTicket")
            .forever().on(
                    pace(49)
                            .exec(dataPrepare)
                            .exec(welcome)
                            .pause(3)
                            .exec(login)
                            .pause(3)
                            .exec(itineraryBeforeCancel)
                            .pause(3)
                            .exec(cancelFirstTicket)
            );
    final static ScenarioBuilder scn6UserRegistration = scenario("UC6_userRegistration")
            .forever().on(
                    pace(74)
                            .exec(prepareRandomUserData)
                            .exec(welcome)
                            .pause(3)
                            .exec(startRegistration)
                            .pause(3)
                            .exec(fillRegistrationFields)
                            .pause(3)
                            .exec(continueAfterRegistration)
                            .pause(3)
                            .exec(logout)
            );
    final static ScenarioBuilder scnSetupUserWith5Tickets = scenario("setup_UserWithTickets")
            .exec(dataPrepare)
            .exec(welcome)
            .pause(3)
            .exec(startRegistration)
            .pause(3)
            .exec(fillRegistrationFields)
            .pause(3)
            .exec(continueAfterRegistration)
            .pause(3)
            .repeat(5).on(
                    exec(flights)
                            .pause(3)
                            .exec(findFlight)
                            .pause(3)
                            .exec(chooseFlight)
                            .pause(3)
                            .exec(paymentDetails)
                            .pause(3)
                            .exec(itineraryAfterBuying)
                            .pause(3)
            )
            .exec(logout);
}
