package com.effective.tms.security.mapper;

public interface Mapper<D, S> {
    D map(S source);
}
