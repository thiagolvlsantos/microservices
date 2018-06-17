package com.alberto.istio.server.common;

public enum EFailureMode {

	ALWAYS, EVEN, ODD, NEVER;

	public static EFailureMode get(String value) {
		for (EFailureMode e : EFailureMode.values()) {
			if (e.name().equalsIgnoreCase(value)) {
				return e;
			}
		}
		return null;
	}

	public boolean fail(int request) {
		switch (this) {
		case ALWAYS:
			return true;
		case EVEN:
			return request % 2 == 0;
		case ODD:
			return request % 2 == 1;
		case NEVER:
			return false;
		}
		return false;
	}
}