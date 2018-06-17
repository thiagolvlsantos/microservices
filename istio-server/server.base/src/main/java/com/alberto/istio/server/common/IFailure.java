package com.alberto.istio.server.common;

public interface IFailure<T> {

	boolean fail(T o);
}
