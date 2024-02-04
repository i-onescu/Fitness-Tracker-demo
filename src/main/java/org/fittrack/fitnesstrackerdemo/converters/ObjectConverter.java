package org.fittrack.fitnesstrackerdemo.converters;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface ObjectConverter<T, U> {

    T convertSecondToFirst(U u);
    U convertFirstToSecond(T t);
}
