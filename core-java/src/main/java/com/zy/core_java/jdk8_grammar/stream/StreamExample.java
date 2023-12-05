package com.zy.core_java.jdk8_grammar.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/***
 * @author ZhangYu
 * @date 2021/8/31
 */
public class StreamExample {


    /**
     * 获取 String 集合中最长的元素
     */
    @Test
    public void test3() {
        List<String> list = Arrays.asList("111", "222", "333", "4234224324", "5435435");
        Optional<String> max = list.stream().max(Comparator.comparing(String::length));
        System.out.println(max);
    }

    /**
     * 获取 Integer 集合中的最大值
     **/
    @Test
    public  void test4(){
        List<Integer> list = Arrays.asList(2, 22,3443,666,111,-2);
        Optional<Integer> max = list.stream().max(Comparator.comparingInt(Integer::intValue));
        System.out.println(max);
    }

    /**
     * 对象类型的比较
     * 【示例】
     * 1：首先根据工资进行比较，返回最高的那个
     * 2：如果工资相同，在根据职级比较，俩个条件都是返回最高那个
     *
     */
    @Test
    public  void test5(){
        List<Emp> empList=new ArrayList<>(3);
        empList.add(new Emp("张三",6500d,3));
        empList.add(new Emp("李斯特",7200d,1));
        empList.add(new Emp("李四",6000d,3));
        empList.add(new Emp("王五",7200d,3));
        //多个条件可以使用thenComparingXXX并列使用
        Optional<Emp> max = empList.stream().max(Comparator.comparingDouble(Emp::getSalary).thenComparingDouble(Emp::getLevel));
        System.out.println(max);
    }
    @Data
    @AllArgsConstructor
    class  Emp{
        String name;
        Double salary;
        int level;
    }


    /**
     *
     * 统计集合中符合特定条件的数量
     * 1：过滤条件
     * 2：完成统计计数
     *
     */
    @Test
    public  void test6(){
       //计算 Integer 集合中大于 6 的元素的个数。
        List<Integer> list = Arrays.asList(7, 6, 4, 8, 2, 11, 9);
        long count = list.stream().filter(p -> p > 6).count();
        System.out.println(count);
    }


    /**
     * 将字符串集合中的字符转换为大写字符
     */
    @Test
    public  void test7(){
        List<String> list = Arrays.asList("aaa", "bbb", "rrer", "ewew", "sdsds");
        List<String> collect = list.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public  void test8(){
        List<String> list = Arrays.asList("a,b,c,d", "1,2,3,4");
        List<String> listNew = list.stream().flatMap(s -> {
            // 将每个元素转换成一个stream
             String[] split = s.split(",");
            return Arrays.stream(split);
        }).collect(Collectors.toList());
        System.out.println(listNew);
    }


    /**
     * reduce进行求和、求乘积的方式
     */
    @Test
    public  void test9(){
        List<Integer> list = Arrays.asList(1, 3, 5, 7, 9, 15);
        // 求和方式1
        Optional<Integer> sum = list.stream().reduce((x, y) -> x + y);
        // 求和方式2
        Optional<Integer> sum2 = list.stream().reduce(Integer::sum);
        // 求和方式
        Integer sum3 = list.stream().reduce(0, Integer::sum);
        // 求乘积
        Optional<Integer> product = list.stream().reduce((x, y) -> x * y);
        // 求最大值方式1
        Optional<Integer> max = list.stream().reduce((x, y) -> x > y ? x : y);
        // 求最大值写法2
        Integer max2 = list.stream().reduce(1, Integer::max);
    }


    @Test
    public  void test10(){
        List<Emp> empList=new ArrayList<>(3);
        empList.add(new Emp("张三",6500d,3));
        empList.add(new Emp("李斯特",7200d,1));
        empList.add(new Emp("李四",6000d,3));
        empList.add(new Emp("王五",7200d,3));
        // 求总数
        long count1 = empList.stream().collect(Collectors.counting());
        //简写
        long count2 = empList.stream().count();


        // 求平均工资
        Double average = empList.stream().collect(Collectors.averagingDouble(Emp::getSalary));


        // 求最高工资
        Optional<Double> max = empList.stream().map(Emp::getSalary).collect(Collectors.maxBy(Double::compare));

        // 求工资之和
        Double sum1 = empList.stream().collect(Collectors.summingDouble(Emp::getSalary));
        //简写
        Double sum2 = empList.stream().mapToDouble(Emp::getSalary).sum();

        // 一次性统计所有信息
        DoubleSummaryStatistics collect = empList.stream().collect(Collectors.summarizingDouble(Emp::getSalary));
    }


    /**
     * 分组(partitioningBy/groupingBy)
     * 根据员工的level进行分组
     */
    @Test
    public  void test11(){
        List<Emp> empList=new ArrayList<>(3);
        empList.add(new Emp("张三",6500d,3));
        empList.add(new Emp("李斯特",7200d,1));
        empList.add(new Emp("李四",6000d,1));
        empList.add(new Emp("王五",7200d,3));
        Map<Integer, List<Emp>> collect = empList.stream().collect(Collectors.groupingBy(Emp::getLevel));
        for (Map.Entry<Integer, List<Emp>> integerListEntry : collect.entrySet()) {
            System.out.println(integerListEntry);
        }

    }





    @Test
    public  void test12(){
        List<Emp> empList=new ArrayList<>(3);
        empList.add(new Emp("张三",6500d,3));
        empList.add(new Emp("李斯特",7300d,1));
        empList.add(new Emp("李四",6000d,1));
        empList.add(new Emp("王五",7200d,3));
        //根据工资倒序排列
        List<Emp> collect = empList.stream().sorted((o1, o2) -> {
            //逆序只需要Double.compare翻转顺序即可
            return Double.compare(o2.getSalary(), o1.getSalary());
        }).collect(Collectors.toList());
        System.out.println(collect);
    }


    /**
     * 测试IntStream
     */
    @Test
    public  void  test13(){
        IntStream range = IntStream.range(1, 10);
        //打印出：123456789
        range.forEach(System.out::print);
    }


    /**
     * 测试IntStream
     */
    @Test
    public  void  test14(){
        IntStream range = IntStream.iterate(0,p->{
            return      p+1;
        } ).limit(5);
        //打印出：01234
        range.forEach(System.out::print);
    }




}
