package com.turkcell.library_management_cqrs.application.features.penalty.command.delete;

public record DeletePenaltyResponse(String message) {
    public static DeletePenaltyResponse create() {
        return new DeletePenaltyResponse("Penalty deleted successfully");
    }
}
