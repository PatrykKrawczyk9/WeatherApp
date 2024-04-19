package pl.kurs.weatherapp.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public enum ObjectMapperHolder {
    INSTANCE;

    private final ObjectMapper objectMapper;

    ObjectMapperHolder() {
        this.objectMapper = create();
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    private ObjectMapper create() {
        ObjectMapper mapper = new ObjectMapper();

        return mapper;
    }
}
