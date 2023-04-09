package com.example.cv1.controller;

import com.example.cv1.Services.AppUserService;
import com.example.cv1.domain.AppUser;
import com.example.cv1.dto.AppUserInputQLDTO;
import com.example.cv1.repository.AppUserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/*
The communication between client and server in GraphQL subscriptions typically involves the following steps:
The client sends a subscription request to the server over a WebSocket connection. The subscription request includes the GraphQL subscription query and any variables needed for the query.
The server receives the subscription request and processes the query. It determines which data the client is interested in and starts listening for changes to that data.
When the data changes, the server sends a message to the client over the WebSocket connection. The message includes the updated data.
The client receives the message from the server and processes it. It can use the updated data to update its UI or perform other actions.
The client can continue to receive updates from the server as long as the subscription is active. When the client is no longer interested in updates, it can send an unsubscribe request to the server to terminate the subscription.

@BatchMapping is an annotation that efficiently retrieves data from the database using a single query.
This reduces the number of database queries and leads to more efficient data processing.

@SchemaMapping maps the results of a custom SQL query to entity objects, while @BatchMapping retrieves data
from multiple entities with a single query, reducing the number of database queries and increasing performance.

Real-time chat application:
Real-time stock market tracker:

A WebSocket is a protocol that provides a bi-directional, full-duplex communication channel over a single TCP connection
between a client and a server. It allows real-time communication between a web browser and a server, enabling data to be sent and received by both parties simultaneously.

*/


@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level= AccessLevel.PRIVATE)
@RestController
public class AppUserQLController {
    private final AppUserRepository userRepository;
    private final AppUserService userService;

    @QueryMapping
    Iterable<AppUser> users() {
        return userRepository.findAll();
    }

    @QueryMapping
    public Iterable<AppUser> appUsers() {
        return userRepository.findAll();
    }

    @QueryMapping
    public Optional<AppUser> appUserById(@Argument long id) {
        return userRepository.findById(id);
    }

    @MutationMapping
    public AppUser createAppUser(@Argument AppUserInputQLDTO appUser) {
        return userService.create(appUser.toEntity());

    }
}
