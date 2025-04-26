package io.gatling.demo;

import java.time.Duration;
import java.util.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import io.gatling.javaapi.jdbc.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import static io.gatling.javaapi.jdbc.JdbcDsl.*;

public class RecordedSimulation extends Simulation {

    private HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:1080")
            .inferHtmlResources()
            .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .acceptEncodingHeader("gzip, deflate")
            .acceptLanguageHeader("ru-RU,ru;q=0.8,en-US;q=0.5,en;q=0.3")
            .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:137.0) Gecko/20100101 Firefox/137.0");

    private Map<CharSequence, String> headers_0 = Map.ofEntries(
            Map.entry("If-Modified-Since", "Fri, 15 Dec 2023 21:35:31 GMT"),
            Map.entry("If-None-Match", "\"16e-60c932df4aec0\""),
            Map.entry("Priority", "u=0, i"),
            Map.entry("Upgrade-Insecure-Requests", "1")
    );

    private Map<CharSequence, String> headers_1 = Map.ofEntries(
            Map.entry("If-Modified-Since", "Fri, 15 Dec 2023 21:35:31 GMT"),
            Map.entry("If-None-Match", "\"2c6-60c932df4aec0\""),
            Map.entry("Priority", "u=4"),
            Map.entry("Upgrade-Insecure-Requests", "1")
    );

    private Map<CharSequence, String> headers_2 = Map.ofEntries(
            Map.entry("Accept", "image/avif,image/webp,image/png,image/svg+xml,image/*;q=0.8,*/*;q=0.5"),
            Map.entry("If-Modified-Since", "Fri, 15 Dec 2023 21:35:31 GMT"),
            Map.entry("If-None-Match", "\"259-60c932df4aec0\""),
            Map.entry("Priority", "u=5, i")
    );

    private Map<CharSequence, String> headers_3 = Map.ofEntries(
            Map.entry("Accept", "image/avif,image/webp,image/png,image/svg+xml,image/*;q=0.8,*/*;q=0.5"),
            Map.entry("If-Modified-Since", "Fri, 15 Dec 2023 21:35:31 GMT"),
            Map.entry("If-None-Match", "\"105b-60c932df4aec0\""),
            Map.entry("Priority", "u=5, i")
    );

    private Map<CharSequence, String> headers_4 = Map.ofEntries(
            Map.entry("Priority", "u=4"),
            Map.entry("Upgrade-Insecure-Requests", "1")
    );

    private Map<CharSequence, String> headers_5 = Map.ofEntries(
            Map.entry("If-Modified-Since", "Fri, 15 Dec 2023 21:35:31 GMT"),
            Map.entry("If-None-Match", "\"551-60c932df4aec0\""),
            Map.entry("Priority", "u=4"),
            Map.entry("Upgrade-Insecure-Requests", "1")
    );

    private Map<CharSequence, String> headers_7 = Map.ofEntries(
            Map.entry("Accept", "image/avif,image/webp,image/png,image/svg+xml,image/*;q=0.8,*/*;q=0.5"),
            Map.entry("If-Modified-Since", "Fri, 15 Dec 2023 21:35:31 GMT"),
            Map.entry("If-None-Match", "\"2a7-60c932df4aec0\""),
            Map.entry("Priority", "u=5, i")
    );

    private Map<CharSequence, String> headers_8 = Map.ofEntries(
            Map.entry("Origin", "http://localhost:1080"),
            Map.entry("Priority", "u=4"),
            Map.entry("Upgrade-Insecure-Requests", "1")
    );

    private Map<CharSequence, String> headers_10 = Map.ofEntries(
            Map.entry("Accept", "image/avif,image/webp,image/png,image/svg+xml,image/*;q=0.8,*/*;q=0.5"),
            Map.entry("If-Modified-Since", "Fri, 15 Dec 2023 21:35:31 GMT"),
            Map.entry("If-None-Match", "\"2d6-60c932df4aec0\""),
            Map.entry("Priority", "u=5, i")
    );

    private Map<CharSequence, String> headers_12 = Map.ofEntries(
            Map.entry("Accept", "image/avif,image/webp,image/png,image/svg+xml,image/*;q=0.8,*/*;q=0.5"),
            Map.entry("If-Modified-Since", "Fri, 15 Dec 2023 21:35:31 GMT"),
            Map.entry("If-None-Match", "\"2b0-60c932df4aec0\""),
            Map.entry("Priority", "u=5, i")
    );

    private Map<CharSequence, String> headers_13 = Map.ofEntries(
            Map.entry("Accept", "image/avif,image/webp,image/png,image/svg+xml,image/*;q=0.8,*/*;q=0.5"),
            Map.entry("If-Modified-Since", "Fri, 15 Dec 2023 21:35:31 GMT"),
            Map.entry("If-None-Match", "\"2ef-60c932df4aec0\""),
            Map.entry("Priority", "u=5, i")
    );

    private Map<CharSequence, String> headers_14 = Map.ofEntries(
            Map.entry("Accept", "image/avif,image/webp,image/png,image/svg+xml,image/*;q=0.8,*/*;q=0.5"),
            Map.entry("If-Modified-Since", "Fri, 15 Dec 2023 21:35:31 GMT"),
            Map.entry("If-None-Match", "\"2dd-60c932df4aec0\""),
            Map.entry("Priority", "u=5, i")
    );

    final ChainBuilder welcome =
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
    final ChainBuilder login =
            exec(http("login")
                    .post("/cgi-bin/login.pl")
                    .headers(headers_8)
                    .formParam("userSession", "#{userSession}")
                    .formParam("username", "jojo")
                    .formParam("password", "bean")
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
    final ChainBuilder logout =
            exec(http("logout")
                    .get("/cgi-bin/welcome.pl?signOff=1")
                    .headers(headers_4)
                    .resources(
                            http("/cgi-bin/nav.pl?in=home")
                                    .get("/cgi-bin/nav.pl?in=home")
                                    .headers(headers_4)
                    ));
    private final ScenarioBuilder scn = scenario("LoginLogout")
            .exec(welcome)
            .pause(3)
            .exec(login)
            .pause(3)
            .exec(logout);

    {
        setUp(scn.injectOpen(atOnceUsers(1))).protocols(httpProtocol);
    }
}
