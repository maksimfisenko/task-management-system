package com.effective.tms.common.mapper;

public interface Mapper<D, S> {
    D map(S source);
}
