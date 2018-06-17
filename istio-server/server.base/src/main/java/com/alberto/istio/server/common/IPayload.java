package com.alberto.istio.server.common;

public interface IPayload<T> {

	T get();

	static <P> IPayload<P> of(P payload) {
		return new IPayload<P>() {

			@Override
			public P get() {
				return payload;
			}
		};
	}
}
