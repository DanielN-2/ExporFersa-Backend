package br.edu.ufersa.ExporFersa.ExporFersaAPI.project;

enum ProjectStatus {
    PENDING,
    APPROVED,
    REJECTED;

    public boolean canTransitionTo(ProjectStatus nextStatus) {
        return switch (this) {
            case PENDING -> nextStatus == APPROVED || nextStatus == REJECTED;
            case APPROVED, REJECTED -> false;
        };
    }

    public boolean allowsEditing() {
        return this == PENDING;
    }
}
