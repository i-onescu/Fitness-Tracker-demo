package org.fittrack.fitnesstrackerdemo.models.dtos;

import lombok.Builder;

@Builder
public record ResponsePayload(Object response, String statusCode) { }