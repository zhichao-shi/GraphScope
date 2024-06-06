/*
 * Copyright 2020 Alibaba Group Holding Limited.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.graphscope.common.ir.meta.schema;

import com.alibaba.graphscope.groot.common.schema.api.GraphStatistics;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * Maintain Graph statistics meta for IR
 */
public class IrGraphStatistics implements GraphStatistics {
    private final GraphStatistics graphStatistics;

    public IrGraphStatistics(InputStream statisticsStream) throws IOException {
        String content = new String(statisticsStream.readAllBytes(), StandardCharsets.UTF_8);
        statisticsStream.close();
        this.graphStatistics = Utils.buildStatisticsFromJson(content);
    }

    public IrGraphStatistics(GraphStatistics statistics) {
        this.graphStatistics = statistics;
    }

    @Override
    public Long getVertexCount() {
        return this.graphStatistics.getVertexCount();
    }

    @Override
    public Long getEdgeCount() {
        return this.graphStatistics.getEdgeCount();
    }

    @Override
    public Long getVertexTypeCount(Integer vertexTypeId) {
        return this.graphStatistics.getVertexTypeCount(vertexTypeId);
    }

    @Override
    public Long getEdgeTypeCount(
            Optional<Integer> sourceTypeId,
            Optional<Integer> edgeTypeId,
            Optional<Integer> targetTypeId) {
        return this.graphStatistics.getEdgeTypeCount(sourceTypeId, edgeTypeId, targetTypeId);
    }

    @Override
    public String getVersion() {
        return this.graphStatistics.getVersion();
    }
}
