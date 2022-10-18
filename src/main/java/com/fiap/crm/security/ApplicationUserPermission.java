package com.fiap.crm.security;

public enum ApplicationUserPermission {
	CLIENTE_READ("cliente:read"),
	CLIENTE_WRITE("cliente:write"),
	CLIENTE_DELETE("cliente:delete"),
	CLIENTE_READ_SELF("cliente:read_self");
	private final String permission;

	ApplicationUserPermission(String permission) {
		this.permission = permission;
	}

	public String getPermission() {
		return permission;
	}
}
