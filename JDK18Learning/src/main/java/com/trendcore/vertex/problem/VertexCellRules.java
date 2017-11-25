package com.trendcore.vertex.problem;

import java.util.List;

/**
 * Created by Anurag
 */
public interface VertexCellRules {
    boolean apply(Integer cell, List<Integer> neighbourList);
}
