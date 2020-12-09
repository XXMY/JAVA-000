package com.cfw.geektime.java000.sharding;

import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringKeysShardingAlgorithm implements ComplexKeysShardingAlgorithm<String> {

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<String> shardingValue) {
        List<String> result = new ArrayList<>();
        int i = shardingValue.getColumnNameAndShardingValuesMap().hashCode() % availableTargetNames.size();
        result.add(availableTargetNames.toArray(new String[]{})[i]);
        return result;
    }
}
