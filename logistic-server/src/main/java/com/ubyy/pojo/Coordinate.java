package com.ubyy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * 返回给前端的坐标
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coordinate{

    private List<List<List<Double>>> truck_route_list;
    private List<List<List<Double>>> uav_route_list;
}
