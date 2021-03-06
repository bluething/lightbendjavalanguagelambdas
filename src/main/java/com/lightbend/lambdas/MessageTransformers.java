package com.lightbend.lambdas;

import java.time.Instant;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

class MessageTransformers {
    static Function<Message, String> toString = Message::toString;

    static Function<Message, Instant> toTimestamp = Message::getTimestamp;

    static Function<Message, Message> toUpperCase = message -> new Message(message.getId(), message.getTimestamp(), message.getContent().toUpperCase());;

    static <T> List<T> mapMessages(List<Message> messages, Function<Message, T> transformer) {
        return messages
                .stream()
                .map(transformer)
                .collect(Collectors.toList());
    }
}
