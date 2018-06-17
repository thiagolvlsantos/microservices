package com.alberto.istio.server.common;

import org.springframework.http.HttpHeaders;

import lombok.Data;

@Data
public class Metrics implements IHeaders {
	public static final String TOTAL = "Sm-Metrics-Total";
	public static final String SUCCESS = "Sm-Metrics-Success";
	public static final String FAILURE = "Sm-Metrics-Failure";
	public static final String DELAYED = "Sm-Metrics-Delayed";

	private int total;
	private int success;
	private int failure;
	private int delayed;

	public int total() {
		return ++this.total;
	}

	public int success() {
		return ++this.success;
	}

	public int failure() {
		return ++this.failure;
	}

	public int delayed() {
		return ++this.delayed;
	}

	@Override
	public HttpHeaders asHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add(TOTAL, String.valueOf(total));
		headers.add(SUCCESS, String.valueOf(success));
		headers.add(FAILURE, String.valueOf(failure));
		headers.add(DELAYED, String.valueOf(delayed));
		return headers;
	}
}