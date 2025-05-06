package io.gatling.demo;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.FeederBuilder;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import static io.gatling.demo.Headers.*;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.Proxy;
import static io.gatling.javaapi.http.HttpDsl.http;

public class Transactions {
    final static HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:1080")
            .inferHtmlResources()
            .proxy(Proxy("localhost", 8888)) // Прокси через Fiddler
            .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .acceptEncodingHeader("gzip, deflate")
            .acceptLanguageHeader("ru-RU,ru;q=0.8,en-US;q=0.5,en;q=0.3")
            .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:137.0) Gecko/20100101 Firefox/137.0");
    private static final FeederBuilder<String> citiesDataFeeder = csv("cities.csv").circular();
    private static final FeederBuilder<String> usersDataFeeder = csv("userData.csv").circular();
    final static ChainBuilder dataPrepare =
            exec(
                    feed(citiesDataFeeder)
                            .feed(usersDataFeeder)
                            .exec(session -> {
                                LocalDate departDate = LocalDate.now().plusDays(20);
                                LocalDate returnDate = departDate.plusDays(3);
                                List<String> seatPrefs = List.of("Aisle", "Window", "None");
                                List<String> seatTypes = List.of("First", "Business", "Coach");
                                Random random = new Random();
                                String seatPref = seatPrefs.get(random.nextInt(seatPrefs.size()));
                                String seatType = seatTypes.get(random.nextInt(seatPrefs.size()));
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                                return session
                                        .set("departDate", departDate.format(formatter))
                                        .set("returnDate", returnDate.format(formatter))
                                        .set("seatPref", seatPref)
                                        .set("seatType", seatType);
                            })
            );

    final static ChainBuilder welcome =
            exec(http("home_page")
                            .get("/WebTours/")
                            .headers(headers_0)
                            .resources(
                                    http("/WebTours/header.html")
                                            .get("/WebTours/header.html")
                                            .headers(headers_1),
                                    http("/WebTours/images/hp_logo.png")
                                            .get("/WebTours/images/hp_logo.png")
                                            .headers(headers_2),
                                    http("/WebTours/images/webtours.png")
                                            .get("/WebTours/images/webtours.png")
                                            .headers(headers_3),
                                    http("/cgi-bin/welcome.pl?signOff=true")
                                            .get("/cgi-bin/welcome.pl?signOff=true")
                                            .headers(headers_4),
                                    http("/WebTours/home.html")
                                            .get("/WebTours/home.html")
                                            .headers(headers_5),
                                    http("/WebTours/images/mer_login.gif")
                                            .get("/WebTours/images/mer_login.gif")
                                            .headers(headers_7)
                            ),
                    http("home_page_userSession")
                            .get("/cgi-bin/nav.pl?in=home")
                            .headers(headers_4)
                            .check(regex("name=\"userSession\" value=\"(.+?)\"").saveAs("userSession")));
    final static ChainBuilder login =
            exec(http("login")
                    .post("/cgi-bin/login.pl")
                    .headers(headers_8)
                    .formParam("userSession", "#{userSession}")
                    .formParam("username", "#{login}")
                    .formParam("password", "#{password}")
                    .formParam("login.x", "77")
                    .formParam("login.y", "4")
                    .formParam("JSFormSubmit", "off")
                    .check(substring("User password was correct"))
                    .resources(
                            http("/cgi-bin/nav.pl?page=menu&in=home")
                                    .get("/cgi-bin/nav.pl?page=menu&in=home")
                                    .headers(headers_4),
                            http("/WebTours/images/flights.gif")
                                    .get("/WebTours/images/flights.gif")
                                    .headers(headers_10),
                            http("/cgi-bin/login.pl?intro=true")
                                    .get("/cgi-bin/login.pl?intro=true")
                                    .headers(headers_4),
                            http("/WebTours/images/in_home.gif")
                                    .get("/WebTours/images/in_home.gif")
                                    .headers(headers_12),
                            http("/WebTours/images/signoff.gif")
                                    .get("/WebTours/images/signoff.gif")
                                    .headers(headers_13),
                            http("/WebTours/images/itinerary.gif")
                                    .get("/WebTours/images/itinerary.gif")
                                    .headers(headers_14)
                    ));
    final static ChainBuilder logout =
            exec(http("logout")
                    .get("/cgi-bin/welcome.pl?signOff=1")
                    .headers(headers_4)
                    .resources(
                            http("/cgi-bin/nav.pl?in=home")
                                    .get("/cgi-bin/nav.pl?in=home")
                                    .headers(headers_4)
                    ));
    final static ChainBuilder flights =
            exec(
                    http("flights")
                            .get("/cgi-bin/welcome.pl?page=search")
                            .headers(headers_4)
                            .resources(
                                    http("/cgi-bin/nav.pl?page=menu&in=flights")
                                            .get("/cgi-bin/nav.pl?page=menu&in=flights")
                                            .headers(headers_4),
                                    http("/WebTours/images/in_flights.gif")
                                            .get("/WebTours/images/in_flights.gif")
                                            .headers(headers2_2),
                                    http("/WebTours/images/home.gif")
                                            .get("/WebTours/images/home.gif")
                                            .headers(headers_12),
                                    http("/cgi-bin/reservations.pl?page=welcome")
                                            .get("/cgi-bin/reservations.pl?page=welcome")
                                            .headers(headers_4)
                                            .check(substring("Find Flight")),
                                    http("/WebTours/images/button_next.gif")
                                            .get("/WebTours/images/button_next.gif")
                                            .headers(headers2_2)
                            )
                            .check(substring("Since user has already logged on"))
            );
    final static ChainBuilder findFlight =
            exec(
                    http("findFlight")
                            .post("/cgi-bin/reservations.pl")
                            .headers(headers2_6)
                            .body(ElFileBody("findFlight.html"))
                            .check(bodyString().saveAs("findFlight"))
                            .check(substring("Flight departing from <B>#{depart}</B> to <B>#{arrive}</B> on <B>#{departDate}</B>"))
                            .check(regex("name=\"outboundFlight\" value=\"(.+?)\"").findRandom().saveAs("outboundFlight"))
            );
    final static ChainBuilder chooseFlight =
            exec(
                    http("chooseFlight")
                            .post("/cgi-bin/reservations.pl")
                            .headers(headers2_7)
                            .body(ElFileBody("chooseFlight.html"))
                            .check(substring("Payment Details"))
            );
    final static ChainBuilder paymentDetails =
            exec(
                    http("paymentDetails")
                            .post("/cgi-bin/reservations.pl")
                            .headers(headers2_8)
                            .body(ElFileBody("paymentDetails.html"))
                            .check(substring("A #{seatType} Class ticket\n" +
                                             " from #{depart} to #{arrive}."))
                            .check(regex(":\\s{2}(\\d{1,2}(?:am|pm))\\s:").saveAs("time"))
                            .check(regex("Flight\\s+(\\d+)\\s+leaves").saveAs("flightNumber"))
            );
    private final static ChainBuilder itineraryPage =
            exec(
                    http("/cgi-bin/welcome.pl?page=itinerary")
                            .get("/cgi-bin/welcome.pl?page=itinerary")
                            .headers(headers_4)
                            .resources(
                                    http("/WebTours/images/cancelreservation.gif")
                                            .get("/WebTours/images/cancelreservation.gif")
                                            .headers(headers2_11),
                                    http("/WebTours/images/cancelallreservations.gif")
                                            .get("/WebTours/images/cancelallreservations.gif")
                                            .headers(headers2_12),
                                    http("/cgi-bin/nav.pl?page=menu&in=itinerary")
                                            .get("/cgi-bin/nav.pl?page=menu&in=itinerary")
                                            .headers(headers_4),
                                    http("/WebTours/images/in_itinerary.gif")
                                            .get("/WebTours/images/in_itinerary.gif")
                                            .headers(headers_14)
                            )
                            .check(substring("Since user has already logged on"))
            );
    final static ChainBuilder itinerarySimple =
            exec(
                    itineraryPage,
                    http("itinerary")
                            .get("/cgi-bin/itinerary.pl")
                            .headers(headers_4)
            );
    final static ChainBuilder itineraryAfterBuying =
            exec(
                    itineraryPage,
                    http("itinerary")
                            .get("/cgi-bin/itinerary.pl")
                            .headers(headers_4)
                            .check(substring("<center>\n  #{departDate}\n :  #{time} : Flight #{flightNumber} leaves #{depart}  for #{arrive}.<br>  </center>"))
            );
    final static ChainBuilder itineraryBeforeCancel =
            exec(
                    itineraryPage,
                    http("itinerary")
                            .get("/cgi-bin/itinerary.pl")
                            .headers(headers_4)
                            .check(regex("name=\"flightID\" value=\"(.+?)\"").findAll().saveAs("flightIDs"))
            );
    final static ChainBuilder startRegistration =
            exec(
                    http("startRegistration")
                            .get("/cgi-bin/login.pl?username=&password=&getInfo=true")
                            .headers(headers_4)
                            .check(substring("First time registering?"))
            );
    final static ChainBuilder prepareRandomUserData =
            exec(session -> session
                    .set("login", "#{randomAlphanumeric(6)}")
                    .set("password", "#{randomAlphanumeric(8)}")
                    .set("firstName", "#{randomAlphanumeric(5)}")
                    .set("lastName", "#{randomAlphanumeric(6)}")
                    .set("streetAddress", "#{randomAlphanumeric(6)} St #{randomInt(1, 100)}")
                    .set("city", "#{randomAlphanumeric(5)}"));
    final static ChainBuilder fillRegistrationFields =
            exec(
                    http("fillRegistrationFields")
                            .post("/cgi-bin/login.pl")
                            .headers(headers3_1)
                            .body(ElFileBody("fillRegistrationFields.html"))
                            .check(substring("Thank you, <b>#{login}</b>, for registering"))
            );
    final static ChainBuilder continueAfterRegistration =
            exec(
                    http("continueAfterRegistration")
                            .get("/cgi-bin/welcome.pl?page=menus")
                            .headers(headers_4)
                            .resources(
                                    http("/cgi-bin/nav.pl?page=menu&in=home")
                                            .get("/cgi-bin/nav.pl?page=menu&in=home")
                                            .headers(headers_4),
                                    http("/cgi-bin/login.pl?intro=true")
                                            .get("/cgi-bin/login.pl?intro=true")
                                            .headers(headers_4)
                                            .check(substring("Welcome, <b>#{login}</b>, to the Web Tours reservation pages.")),
                                    http("/WebTours/images/flights.gif")
                                            .get("/WebTours/images/flights.gif")
                                            .headers(headers_10),
                                    http("/WebTours/images/itinerary.gif")
                                            .get("/WebTours/images/itinerary.gif")
                                            .headers(headers3_6),
                                    http("/WebTours/images/signoff.gif")
                                            .get("/WebTours/images/signoff.gif")
                                            .headers(headers_13),
                                    http("/WebTours/images/in_home.gif")
                                            .get("/WebTours/images/in_home.gif")
                                            .headers(headers3_8)
                            )
            );
    final static ChainBuilder cancelFirstTicket =
            exec(session -> {
                List<String> flightIDs = session.getList("flightIDs");
                System.out.println(flightIDs);
                System.out.println(flightIDs.get(0));
                String boundary = "------geckoformboundary243bbf98a166ef94ea7ad0e59ff55ac1";

                StringBuilder cancelRequestBody = new StringBuilder(boundary);
                cancelRequestBody.append("\r\nContent-Disposition: form-data; name=\"1\"\r\n\r\non\r\n");

                for (String flightId : flightIDs) {
                    cancelRequestBody.append(boundary);
                    cancelRequestBody.append("\r\nContent-Disposition: form-data; name=\"flightID\"\r\n\r\n");
                    cancelRequestBody.append(flightId);
                    cancelRequestBody.append("\r\n");
                }
                cancelRequestBody.append(boundary);
                cancelRequestBody.append("\r\nContent-Disposition: form-data; name=\"removeFlights.x\"\r\n\r\n22\r\n");
                cancelRequestBody.append(boundary);
                cancelRequestBody.append("\r\nContent-Disposition: form-data; name=\"removeFlights.y\"\r\n\r\n33\r\n");

                for (int i = 1; i <= flightIDs.size(); i++) {
                    cancelRequestBody.append(boundary);
                    cancelRequestBody.append("\r\nContent-Disposition: form-data; name=\".cgifields\"\r\n\r\n");
                    cancelRequestBody.append(i);
                    cancelRequestBody.append("\r\n");
                }
                cancelRequestBody.append(boundary);
                cancelRequestBody.append("--\r\n");
                return session
                        .set("firstFlightId", flightIDs.get(0))
                        .set("cancelRequestBody", cancelRequestBody.toString());
            })
                    .exec(
                            http("cancelFirstTicket")
                                    .post("/cgi-bin/itinerary.pl")
                                    .headers(headers3_1)
                                    .body(StringBody("#{cancelRequestBody}"))
                                    .check(substring("#{firstFlightId}").notExists())
                                    .check(substring("Unfortunately, we could not delete your entire itinerary because of a database synchronization error").notExists())
                                    .check(substring("The server encountered an internal error").notExists())
                    );
    final static ChainBuilder buyFiveTickets =
            exec(dataPrepare)
                    .exec(welcome)
                    .pause(3)
                    .exec(login)
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
