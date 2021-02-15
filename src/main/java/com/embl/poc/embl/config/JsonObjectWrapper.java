package com.embl.poc.embl.config;

import java.util.Collections;
import java.util.Map;

public final class JsonObjectWrapper {
    private JsonObjectWrapper() {}

    public static <E> Map<String, E> withLabel(String label, E wrappedObject) {
        return Collections.singletonMap(label, wrappedObject);
    }
}