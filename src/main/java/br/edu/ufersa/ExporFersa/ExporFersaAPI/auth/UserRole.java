package br.edu.ufersa.ExporFersa.ExporFersaAPI.auth;

public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER"),
    GUEST("ROLE_GUEST");

    private final String roleName;
    UserRole(String roleName) {
        this.roleName = roleName;
    }
    public String getRoleName() {
        return roleName;
    }
}
