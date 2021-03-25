package com.lightbend.lambdas;

import java.time.Instant;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

class MessageTransformers {
    static MessageTransformer<String> toString = message -> message.toString();

    static MessageTransformer<Instant> toTimestamp = message -> message.getTimestamp();

    static MessageTransformer<Message> toUpperCase = message -> new Message(message.getId(), message.getTimestamp(), message.getContent().toUpperCase());;

    static <T> List<T> mapMessages(List<Message> messages, MessageTransformer<T> transformer) {
        return messages
                .stream()
                .map(transformFunction(transformer))
                .collect(Collectors.toList());
    }

    private static <T> Function<Message, T> transformFunction(MessageTransformer<T> transformer) {
        return new Function<Message, T>() {
            @Override
            public T apply(Message message) {
                return transformer.apply(message);
            }
        };
    }
}
