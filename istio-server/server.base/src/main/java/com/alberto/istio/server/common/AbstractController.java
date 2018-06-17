package com.alberto.istio.server.common;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractController {
	private Information information;
	private Metrics metrics;

	public AbstractController(String environment, String service, String version) {
		information = Information.builder()//
				.environment(System.getProperty("environment", environment))//
				.service(System.getProperty("service", service))//
				.version(System.getProperty("version", version))//
				.mode(EFailureMode.get(System.getProperty("mode", "never")))//
				.delay(Long.parseLong(System.getProperty("delay", "0")))//
				.build();
		metrics = new Metrics();
	}

	@RequestMapping(path = "/seterror/{when}")
	public void seterror(@PathVariable("when") String when) {
		EFailureMode mode = EFailureMode.get(when);
		if (mode != null) {
			information.setMode(mode);
		}
		log.info("SetError:{}", information.getMode());
	}

	@RequestMapping(path = "/setdelay/{seconds}")
	public void setdelay(@PathVariable("seconds") long seconds) {
		information.setDelay(seconds);
		log.info("SetDelay:{}", information.getDelay());
	}

	protected ResponseEntity<Object> process(IPayload<?> payload) {
		metrics.total();
		ResponseEntity<Object> response;
		HttpHeaders headers = new HttpHeaders();
		headers.addAll(information.asHeaders());
		if (information.fail(metrics)) {
			metrics.failure();
			response = ResponseEntity.notFound().headers(setup(headers)).build();
		} else {
			if (information.getDelay() > 0) {
				metrics.delayed();
				try {
					Thread.sleep(information.getDelay() * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			metrics.success();
			response = ResponseEntity.ok().headers(setup(headers)).body(payload.get());
		}
		log.info("process:{}", response);
		return response;
	}

	public HttpHeaders setup(HttpHeaders headers) {
		headers.addAll(metrics.asHeaders());
		StringBuilder sb = new StringBuilder();
		headers.forEach((k, v) -> {
			sb.append(k + ",");
		});
		headers.add("Access-Control-Expose-Headers", sb.toString());
		return headers;
	}
}