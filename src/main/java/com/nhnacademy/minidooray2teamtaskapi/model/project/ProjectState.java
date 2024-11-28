package com.nhnacademy.minidooray2teamtaskapi.model.project;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;


public enum ProjectState {
    ACTIVE,
    DORMANT,
    CLOSED;


    public long getOrdinal() {
        return this.ordinal() + 1;
    }




    @JsonValue
    public String toJson() {
        return this.name().toLowerCase();
    }

    @JsonCreator
    public ProjectState fromString(String string) {
        for (ProjectState value : ProjectState.values()) {
            if (value.name().equalsIgnoreCase(string)) {
                return value;
            }
        }
        return ACTIVE;
    }
}
