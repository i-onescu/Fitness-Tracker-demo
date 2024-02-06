package org.fittrack.fitnesstrackerdemo.enums;

import lombok.Getter;

@Getter
public enum Intensity {

    LOW("Low", 1),
    MEDIUM("Medium", 2),
    HIGH("High", 3);

    private final String name;
    private final int value;

    Intensity(String name, int value) {
        this.name = name;
        this.value = value;
    }
}
