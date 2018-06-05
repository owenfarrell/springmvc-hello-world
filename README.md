# SpringMVC Hello World Application

[![Build Status](https://travis-ci.org/owenfarrell/springmvc-hello-world.svg?branch=master)](https://travis-ci.org/owenfarrell/springmvc-hello-world)
[![Heroku](https://heroku-badge.herokuapp.com/?app=springmvc-hello-world&svg=1)](https://springmvc-hello-world.herokuapp.com/)
[![SonarCloud](https://sonarcloud.io/api/project_badges/measure?project=com.github.owenfarrell%3Aspringmvc-hello-world&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.github.owenfarrell%3Aspringmvc-hello-world)

This application is for illustrative purposes only and demonstrates the ability to sanitizing a (potentially) dangerous through a dispatcher pattern implementation.

## Vulnerable Dispatcher Pattern

The default landing page (/hello) provides a simple form. When submitting the form, the raw contents are included in the response body, making the page vulnerable to an injection attack. For example, the following request will generate an alert dialog.

```
curl -d 'name=foo&value=<script>alert("bar");</script>' http://localhost:8080/hello
```

## Sanitizing Dispatcher Pattern

An alternative page (/sanitizing) provides a similar form. In this case, the controller responsible for processing the request checks for unsafe content. If unsafe content is detected, an error page is displayed. Otherwise, the content is displayed on screen. Althought the request data is not modified, the following request is made safe through conditional dispatching.

```
curl -d 'name=foo&value=<script>alert("bar");</script>' http://localhost:8080/sanitizing
```

## Running the Application

This application leverages Spring Boot as web application platform and can be built and run with the Gradle wrapper.

```
./gradlew bootRun
```
