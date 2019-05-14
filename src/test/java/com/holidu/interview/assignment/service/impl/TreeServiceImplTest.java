package com.holidu.interview.assignment.service.impl;


import com.holidu.interview.assignment.client.SocrataClient;
import com.holidu.interview.assignment.model.Coordinates;
import com.holidu.interview.assignment.model.client.TreeData;
import com.holidu.interview.assignment.service.TreeService;
import org.junit.Assert;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TreeServiceImplTest {

    @Test
    public void testSearch() {
        SocrataClient client = mock(SocrataClient.class);
        TreeService service = new TreeServiceImpl(client);

        List<TreeData> trees = new ArrayList<>();
        trees.add(new TreeData(null, 40d, 70d));
        trees.add(new TreeData("cherry", 40d, 70d));
        trees.add(new TreeData("cherry", 40d, 71d));
        when(client.searchForTrees(any(Coordinates.class), any(Coordinates.class))).thenReturn(trees);

        Map<String, Long> result = service.search(40d, 70d, 500d);

        Assert.assertEquals(1, result.size());
        Assert.assertEquals(1, result.get("cherry").intValue());
    }
}