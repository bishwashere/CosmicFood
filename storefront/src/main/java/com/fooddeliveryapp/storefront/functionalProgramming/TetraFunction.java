package com.fooddeliveryapp.storefront.functionalProgramming;

@FunctionalInterface
public interface TetraFunction<N,O,P,Q,R> {
    R apply(N n, O o, P p, Q q);
}
