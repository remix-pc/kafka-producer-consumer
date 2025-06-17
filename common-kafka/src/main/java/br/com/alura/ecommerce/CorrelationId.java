package br.com.alura.ecommerce;

import java.util.UUID;

public class CorrelationId {

    private final String id;

    public CorrelationId(String title) {
        id = title + "(" + UUID.randomUUID().toString() + ")";
    }

    public CorrelationId continueWith(CorrelationId title) {
        return new CorrelationId(id + '-' + title);
    }
}
