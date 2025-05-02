package io.gatling.demo;

import io.gatling.javaapi.core.ScenarioBuilder;

import static io.gatling.demo.Transactions.*;
import static io.gatling.javaapi.core.CoreDsl.scenario;

public class Scenarios {
    final static ScenarioBuilder loginLogout = scenario("loginLogout")
            .exec(dataPrepare)
            .exec(welcome)
            .pause(3)
            .exec(login)
            .pause(3)
            .exec(itinerarySimple)
            .pause(3)
            .exec(logout);
    final static ScenarioBuilder buyTicket = scenario("buyTicket")
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
            .exec(logout);
    final static ScenarioBuilder registration = scenario("registration")
            .exec(dataPrepare)
            .exec(welcome)
            .pause(3)
            .exec(startRegistration)
            .pause(3)
            .exec(fillRegistrationFields)
            .pause(3)
            .exec(continueAfterRegistration)
            .pause(3)
            .exec(logout);
    final static ScenarioBuilder cancelTicket = scenario("cancelFirstTicket")
            .exec(dataPrepare)
            .exec(welcome)
            .pause(3)
            .exec(login)
            .pause(3)
            .exec(itineraryBeforeCancel)
            .pause(3)
            .exec(cancelFirstTicket);
    final static ScenarioBuilder searchTicketWithoutPayment = scenario("searchTicketWithoutPayment")
            .exec(dataPrepare)
            .exec(welcome)
            .pause(3)
            .exec(login)
            .pause(3)
            .exec(flights)
            .pause(3)
            .exec(findFlight)
            .pause(3)
            .exec(chooseFlight);
    final static ScenarioBuilder viewItinerary = scenario("viewItinerary")
            .exec(dataPrepare)
            .exec(welcome)
            .pause(3)
            .exec(login)
            .pause(3)
            .exec(itinerarySimple)
            .pause(3)
            .exec(logout);
}
