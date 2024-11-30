package com.nhnacademy.minidooray2teamtaskapi.model.milestone;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum MilestoneState {
    WAITING(0),
    TODO(1),
    PROCEEDING(2),
    COMPLETED(3);

    private int id;

    MilestoneState(int id) {
        this.id = id;
    }

    @JsonValue
    public String toJson() {return this.name().toLowerCase();}

    @JsonCreator
    public static MilestoneState fromJson(String json) {
        for (MilestoneState state : MilestoneState.values()) {
            if (state.toJson().equals(json)) {
                return state;
            }
        }
        return WAITING;
    }


}
