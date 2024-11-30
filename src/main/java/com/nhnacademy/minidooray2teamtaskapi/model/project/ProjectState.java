package com.nhnacademy.minidooray2teamtaskapi.model.project;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;


@Getter
public enum ProjectState {
    ACTIVE(0),
    DORMANT(1),
    CLOSED(2);


    private int id;

    ProjectState(int id) {
        this.id = id;
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
