package com.ubyy.service.impl;

import com.ubyy.pojo.Coordinate;
import com.ubyy.pojo.Position;
import com.ubyy.service.IDynamicService;
import org.springframework.stereotype.Service;
import org.apache.commons.collections4.ListUtils;
import sun.security.util.Length;

import java.io.*;
import java.util.*;

@Service
public class DynamicServiceImpl implements IDynamicService {

    /**
     * 传参调用python脚本
     * @param positions：向python脚本传入的参数
     * @return
     * @throws IOException
     */
    @Override
    public Coordinate TransferRecognition(List<Position> positions) throws IOException {
        //前面一半是本地环境下的python的启动文件地址，后面一半是要执行的python脚本地址
//        String[] arguments = new String[] {"C:\\Users\\hp-pc\\anaconda3\\envs\\tensorflow2-gpu\\python.exe", "F:\\test1\\transfer_parameters.py", String.valueOf(positions)};
        String[] arguments = new String[] {"C:\\Users\\hp-pc\\anaconda3\\envs\\tensorflow2-gpu\\python.exe", "F:\\VRP_DRL_MHA-master_v1_test\\TensorFlow2\\UAV_model.py", String.valueOf(positions)};

        Process proc;
        String str = "";
        try {
            proc = Runtime.getRuntime().exec(arguments);
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(), "GBK"));

            new InputStreamReader(proc.getInputStream());
            String line = null;
            while ((line = in.readLine()) != null) {
                str += line;
                System.out.println(line);
            }
            in.close();
            int re = proc.waitFor();
            System.out.println(re);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Coordinate coordinate = StringToList(str);

        return coordinate;
    }

    /**
     * 传参调用脚本（车辆信息）
     * @param positions
     * @return
     */
    @Override
    public Coordinate TransferRecognitionVehicle(List<Position> positions) throws IOException {
        //前面一半是本地环境下的python的启动文件地址，后面一半是要执行的python脚本地址
//        String[] arguments = new String[] {"C:\\Users\\hp-pc\\anaconda3\\envs\\tensorflow2-gpu\\python.exe", "F:\\test1\\transfer_parameters.py", String.valueOf(positions)};
        String[] arguments = new String[] {"C:\\Users\\hp-pc\\anaconda3\\envs\\tensorflow2-gpu\\python.exe", "F:\\VRP_DRL_MHA-master_v1_test_vehicle\\TensorFlow2\\UAV_model.py", String.valueOf(positions)};

        Process proc;
        String str = "";
        try {
            proc = Runtime.getRuntime().exec(arguments);
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(), "GBK"));

            new InputStreamReader(proc.getInputStream());
            String line = null;
            while ((line = in.readLine()) != null) {
                str += line;
                System.out.println(line);
            }
            in.close();
            int re = proc.waitFor();
            System.out.println(re);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Coordinate coordinate = stringToListVehicle(str);

        return coordinate;
    }

    private Coordinate stringToListVehicle(String str){
        List<List<List<Double>>> truck_lists = truck_string_to_list(str);
        Coordinate coordinate = new Coordinate();
        coordinate.setTruck_route_list(truck_lists);
        return coordinate;
    }

    private Coordinate StringToList(String str) {
//        String str = "[[[116.404, 39.915], [116.287445, 39.83103], [116.404, 39.915]], [[116.404, 39.915], [116.31012, 39.868103], [116.34451, 39.831596], [116.404, 39.915]], [[116.404, 39.915], [116.34524, 39.89842], [116.43487, 39.863213], [116.42187, 39.832153], [116.404, 39.915]], [[116.404, 39.915], [116.443275, 39.896736], [116.343864, 39.828407], [116.404, 39.915]], [[116.404, 39.915], [116.34597, 39.854626], [116.404, 39.915]]]|[[[116.34524, 39.89842], [116.44401, 39.872036], [116.43487, 39.863213]]]";
//        System.out.println(str);
        String truck_route = str.substring(0, str.indexOf('|'));
        String uav_route = str.substring(str.indexOf("|") + 1);

        List<List<List<Double>>> truck_lists = truck_string_to_list(truck_route);
        List<List<List<Double>>> uav_lists = uav_string_to_list(uav_route);

        Coordinate coordinate = new Coordinate();
        coordinate.setTruck_route_list(truck_lists);
        coordinate.setUav_route_list(uav_lists);
        return coordinate;
    }

    private List<List<List<Double>>> truck_string_to_list(String truck_route){
        // 消除字符串类型的数字之外的元素，并将字符串类型的数字放入到List<String>中
        truck_route = truck_route.replace("[", "");
        truck_route = truck_route.replace("]", "");
        List<String> truck_lists = Arrays.asList(truck_route.split(","));

        ArrayList<Double> list_0 = new ArrayList<>();
        // 将String类型的数字变成double类型的数字，并放入到ArrayList<Double>中
        for (String co: truck_lists){
            list_0.add(Double.valueOf(co));
        }
        ArrayList<Integer> indexs = new ArrayList<>();
        List<List<List<Double>>> listList = new ArrayList<>();

        // 首先分割成坐标形式
        List<List<Double>> lists = ListUtils.partition(list_0, 2);
        // 记录分割点索引，利用了起始点坐标相同这一特性
        indexs.add(0);
        for(int i = 0; i < lists.size() - 1; i++){
            if(lists.get(i).equals(lists.get(i + 1))){
                indexs.add(i + 1);
            }
        }
        indexs.add(lists.size());
        //进行分割
        for(int i = 0; i < indexs.size() - 1; i++){
            listList.add(lists.subList(indexs.get(i), indexs.get(i + 1)));
        }
        return listList;
    }

    private List<List<List<Double>>> uav_string_to_list(String uav_route){
        ArrayList<Double> list_0 = new ArrayList<>();


        uav_route = uav_route.replace("[", "");
        uav_route = uav_route.replace("]", "");

        List<String> uav_lists = Arrays.asList(uav_route.split(","));
        for (String co: uav_lists){
            list_0.add(Double.valueOf(co));
        }
        // 首先分割成坐标形式
        List<List<Double>> lists = ListUtils.partition(list_0, 2);
        // 然后再分割成3个一组
        List<List<List<Double>>> listList = ListUtils.partition(lists, 3);
//        ArrayList<List<Double>> lists2 = new ArrayList<>();
//        for(List<List<Double>> lists1: listList){
//            if(lists1.get(0) == lists1.get(1)){
//                listList.set(listList.indexOf(lists1), lists2);
//            }
//        }
        return listList;
    }
}
