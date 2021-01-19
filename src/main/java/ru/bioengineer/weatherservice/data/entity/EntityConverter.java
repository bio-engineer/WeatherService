package ru.bioengineer.weatherservice.data.entity;

@FunctionalInterface
public interface EntityConverter<O> {

    O convert();
}
