package com.alberto.istio.server.common;

import org.springframework.http.HttpHeaders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Information implements IHeaders, IFailure<Metrics> {
	public static final String ENVIRONMENT = "Sm-Info-Env";
	public static final String SERVICE = "Sm-Info-Service";
	public static final String VERSION = "Sm-Info-Version";
	public static final String MODE = "Sm-Info-Mode";
	public static final String DELAY = "Sm-Info-Delay";

	private String environment;
	private String service;
	private String version;
	private EFailureMode mode;
	private long delay;

	@Override
	public HttpHeaders asHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add(ENVIRONMENT, environment);
		headers.add(SERVICE, service);
		headers.add(VERSION, version);
		headers.add(MODE, mode.name().toLowerCase());
		headers.add(DELAY, String.valueOf(delay));
		return headers;
	}

	public boolean fail(Metrics metrics) {
		return mode.fail(metrics.getTotal());
	}
}