package top.moma.example.memos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.*;

public class StreamMemos {

  public static void main(String[] args) {
    StreamMemos streamMemos = new StreamMemos();
    // streamMemos.collectEnd(streamMemos.vehicleStream());
    // streamMemos.mapOp(streamMemos.vehicleStream());
    // List<Vehicle> s = streamMemos.vehicleStream();
    List<List<Integer>> lists = new ArrayList<>();
    List<Integer> list1 = new ArrayList<>();
    list1.add(2);
    List<Integer> list2 = new ArrayList<>();
    list2.add(3);
    lists.add(list1);
    lists.add(list2);

    int rel =
        lists.stream()
            .reduce(
                1,
                (a, b) -> {
                  System.out.println(a);
                  System.out.println(b.get(0));
                  return a + b.get(0);
                },
                Integer::sum);
    System.out.println(rel); // return 5 instead of 4
  }

  public Stream<String> listStream() {
    /* From List */
    List<String> list = new ArrayList<>();
    list.add("abc");
    list.add("def");
    list.add("ghi");
    Stream<String> listStream = list.stream();
    return listStream;
  }

  public Stream<String> arraysStream() {
    /* From Array*/
    String[] stringArray = "abc,def,ghi".split(",");
    Stream<String> arrayStream = Arrays.stream(stringArray);
    return arrayStream;
  }

  public Stream<String> objsStream() {
    /* From Objects */
    Stream<String> objStream = Stream.of("abc", "def", "ghi");
    return objStream;
  }

  public Stream<Integer> intStream() {
    /* From int(s) */
    Stream<Integer> intStream = IntStream.of(1, 2, 3, 4).boxed();
    return intStream;
  }

  public Stream<Long> longStream() {
    /* From long(s) */
    Stream<Long> longStream = LongStream.of(1L, 2L, 3L, 4L).boxed();
    return longStream;
  }

  public Stream<Double> doubleStream() {
    /* From double(s)*/
    Stream<Double> doubleStream = DoubleStream.of(1.1, 1.2, 1.3).boxed();
    return doubleStream;
  }

  public Stream<String> lineStream() {
    try {
      BufferedReader bufferedReader = new BufferedReader(new FileReader("text/lineStream.txt"));
      Stream<String> lineStream = bufferedReader.lines();
      return lineStream;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 模拟数据 vehicleStream
   *
   * @return java.util.stream.Stream<top.moma.example.memos.StreamMemos.Vehicle>
   * @author Created by ivan
   * @since 2022/9/6 16:35
   */
  public List<Vehicle> vehicleStream() {
    List<Vehicle> vehicles = new ArrayList<>();
    vehicles.add(
        Vehicle.builder()
            .id(1L)
            .brand("Mercedes-Benz")
            .wheels(4)
            .type("G63")
            .price(new BigDecimal("1000000"))
            .years("2010")
            .build());
    vehicles.add(
        Vehicle.builder()
            .id(2L)
            .brand("Mercedes-Benz")
            .wheels(4)
            .type("CLK320")
            .price(new BigDecimal("800000"))
            .years("2021")
            .build());
    vehicles.add(
        Vehicle.builder()
            .id(3L)
            .brand("BMW")
            .wheels(4)
            .type("M4")
            .price(new BigDecimal("400000"))
            .years("2020")
            .build());
    vehicles.add(
        Vehicle.builder()
            .id(4L)
            .brand("BMW")
            .wheels(4)
            .type("i8")
            .price(new BigDecimal("1200000"))
            .years("2022")
            .build());
    vehicles.add(
        Vehicle.builder()
            .id(5L)
            .brand("Audi")
            .wheels(4)
            .type("rs7")
            .price(new BigDecimal("1500000"))
            .years("2022")
            .build());
    vehicles.add(
        Vehicle.builder()
            .id(6L)
            .brand("Audi")
            .wheels(4)
            .type("a4")
            .price(new BigDecimal("300000"))
            .years("2000")
            .build());

    return vehicles;
  }

  /**
   * 模拟两个Dealer dealerStream
   *
   * @return java.util.List<top.moma.example.memos.StreamMemos.Dealer>
   * @author Created by ivan
   * @since 2022/9/7 11:00
   */
  public List<Dealer> dealerStream() {
    List<Dealer> dealers = new ArrayList<>();
    // All
    dealers.add(Dealer.builder().id(1L).name("DDKY").inStock(vehicleStream()).build());
    // Only Audi
    dealers.add(
        Dealer.builder()
            .id(2L)
            .name("YMCP")
            .inStock(
                vehicleStream().stream()
                    .filter(vehicle -> vehicle.getBrand().equals("Audi"))
                    .collect(Collectors.toList()))
            .build());
    return dealers;
  }

  /**
   * Match End OPs
   *
   * @param vehicleStream vehicleStream
   * @return void
   * @author Created by ivan
   * @since 2022/11/17 15:20
   */
  public void matchEnd(List<Vehicle> vehicleStream) {
    Supplier<Stream<Vehicle>> supplier = vehicleStream::stream;
    // Match条件，是否wheels =4
    boolean ifAllMatch = supplier.get().allMatch(vehicle -> vehicle.getWheels() == 4);
    boolean ifAnyMatch = supplier.get().anyMatch(vehicle -> vehicle.getWheels() == 4);
    boolean ifNoneMatch = supplier.get().noneMatch(vehicle -> vehicle.getWheels() == 4);
    System.out.println("All 4 wheels: " + ifAllMatch);
    System.out.println("Any 4 wheels: " + ifAnyMatch);
    System.out.println("None 4 wheels: " + ifNoneMatch);
    // Match条件，是否brand equals Audi
    ifAllMatch = supplier.get().allMatch(vehicle -> vehicle.getBrand().equals("Audi"));
    ifAnyMatch = supplier.get().anyMatch(vehicle -> vehicle.getBrand().equals("Audi"));
    ifNoneMatch = supplier.get().noneMatch(vehicle -> vehicle.getBrand().equals("Audi"));
    System.out.println("All Audi: " + ifAllMatch);
    System.out.println("Any Audi: " + ifAnyMatch);
    System.out.println("None Audi: " + ifNoneMatch);
  }

  /**
   * Collect end OPs
   *
   * @param vehicleStream vehicleStream
   * @return void
   * @author Created by ivan
   * @since 2022/11/17 15:20
   */
  public void collectEnd(List<Vehicle> vehicleStream) {
    Supplier<Stream<Vehicle>> supplier = vehicleStream::stream;
    // To List
    List<Vehicle> vehicles =
        supplier
            .get()
            .filter(vehicle -> vehicle.getBrand().equals("Audi"))
            .collect(Collectors.toList());
    System.out.println(" Audi List");
    System.out.println(vehicles);

    // To Set
    Set<String> vehicleSet =
        supplier.get().map(vehicle -> vehicle.getBrand()).collect(Collectors.toSet());
    System.out.println(" Brand Set");
    System.out.println(vehicleSet);

    // To Map
    Map<Long, Vehicle> vehicleMap =
        supplier
            .get()
            .filter(vehicle -> vehicle.getBrand().equals("Audi"))
            .collect(Collectors.toMap(Vehicle::getId, vehicle -> vehicle));
    System.out.println("Audi Map");
    System.out.println(vehicleMap);

    // Group by
    Map<String, List<Vehicle>> vehicleMap1 =
        supplier
            .get()
            .filter(vehicle -> vehicle.getBrand().equals("Audi"))
            .collect(Collectors.groupingBy(Vehicle::getYears));
    System.out.println(" Group Map");
    System.out.println(vehicleMap1);

    // Join Strings
    List<String> list = new ArrayList<>();
    list.add("abc");
    list.add("abc");
    list.add("edf");
    Stream<String> listStream = list.stream();
    String newString = listStream.collect(Collectors.joining("-"));
    System.out.println(" New String ");
    System.out.println(newString);
  }

  /**
   * forEach end Ops
   *
   * @param vehicleStream vehicleStream
   * @return void
   * @author Created by ivan
   * @since 2022/11/17 15:20
   */
  public void foreachEnd(List<Vehicle> vehicleStream) {
    Supplier<Stream<Vehicle>> supplier = vehicleStream::stream;
    // print all
    supplier
        .get()
        .filter(vehicle -> vehicle.getBrand().equals("Audi"))
        .forEach(vehicle -> System.out.println(vehicle));
    // add 1000 to price
    supplier
        .get()
        .filter(vehicle -> vehicle.getBrand().equals("Audi"))
        .forEach(vehicle -> vehicle.setPrice(vehicle.getPrice().add(new BigDecimal("1000"))));
    // print again
    supplier
        .get()
        .filter(vehicle -> vehicle.getBrand().equals("Audi"))
        .forEach(vehicle -> System.out.println(vehicle));
  }

  /**
   * Count end ops
   *
   * @param vehicleStream vehicleStream
   * @return void
   * @author Created by ivan
   * @since 2022/11/17 16:37
   */
  public void countEnd(List<Vehicle> vehicleStream) {
    Supplier<Stream<Vehicle>> supplier = vehicleStream::stream;

    // Count
    Long size = supplier.get().count();
    System.out.println(size);

    // Cheapest one
    Vehicle vehicleMin =
        supplier
            .get()
            .min((vehicle1, vehicle2) -> vehicle1.getPrice().compareTo(vehicle2.getPrice()))
            .get();
    System.out.println(vehicleMin);

    // Dearest One, with Comparator
    Vehicle vehicleMax = supplier.get().max(Comparator.comparing(Vehicle::getPrice)).get();
    System.out.println(vehicleMax);
  }

  /**
   * Filter ops
   *
   * @param vehicleStream vehicleStream
   * @return void
   * @author Created by ivan
   * @since 2022/11/17 15:21
   */
  public void filterOps(List<Vehicle> vehicleStream) {
    Supplier<Stream<Vehicle>> supplier = vehicleStream::stream;
    // 获取4个轮子的Audi
    supplier
        .get()
        .filter(vehicle -> vehicle.getBrand().equals("Audi") && vehicle.getWheels() == 4)
        .forEach(System.out::println);
    System.out.println();
    // 获取价格 >= 100w，可替换lambda
    supplier
        .get()
        .filter(vehicle -> vehicle.getPrice().compareTo(new BigDecimal("1000000")) >= 0)
        .forEach(System.out::println);
  }

  /**
   * Map op
   *
   * @param vehicleStream vehicleStream
   * @return void
   * @author Created by ivan
   * @since 2022/11/17 15:21
   */
  public void mapOp(List<Vehicle> vehicleStream) {
    Supplier<Stream<Vehicle>> supplier = vehicleStream::stream;
    // 出售所有的Audi
    supplier
        .get()
        .map(
            vehicle -> {
              if (vehicle.getBrand().equals("Audi")) {
                vehicle.setSale(true);
              }
              return vehicle;
            })
        .collect(Collectors.toList())
        .forEach(System.out::println);
    System.out.println();
    // 下调价格10%
    supplier
        .get()
        .map(
            vehicle -> {
              vehicle.setPrice(vehicle.getPrice().multiply(new BigDecimal("0.9")));
              return vehicle;
            })
        .collect(Collectors.toList())
        .forEach(System.out::println);
    System.out.println();
    // 获取id列表
    supplier.get().map(Vehicle::getId).collect(Collectors.toList()).forEach(System.out::println);
  }

  /**
   * Flat map op
   *
   * @param dealers dealers
   * @return void
   * @author Created by ivan
   * @since 2022/11/17 15:21
   */
  public void flatMapOp(List<Dealer> dealers) {
    Supplier<Stream<Dealer>> supplier = dealers::stream;
    // 获取全部车型
    supplier
        .get()
        .map(Dealer::getInStock)
        .collect(Collectors.toList())
        .forEach(System.out::println);
    System.out.println();
    supplier
        .get()
        .flatMap(dealer -> dealer.getInStock().stream())
        .collect(Collectors.toList())
        .forEach(System.out::println);
  }

  /**
   * Stateful ops
   *
   * @param vehicles vehicles
   * @return void
   * @author Created by ivan
   * @since 2022/11/17 15:21
   */
  public void stateOp(List<Vehicle> vehicles) {
    Supplier<Stream<Vehicle>> supplier = vehicles::stream;
    // 取价格排序的前三
    supplier
        .get()
        .sorted(Comparator.comparing(Vehicle::getPrice).reversed())
        .limit(3)
        .collect(Collectors.toList())
        .forEach(System.out::println);
    System.out.println();
    // 取价格排序除前三之外的
    supplier
        .get()
        .sorted(Comparator.comparing(Vehicle::getPrice).reversed())
        .skip(3)
        .collect(Collectors.toList())
        .forEach(System.out::println);
    System.out.println();
    // 输出所有Brand
    supplier
        .get()
        .map(Vehicle::getBrand)
        .distinct()
        .collect(Collectors.toList())
        .forEach(System.out::println);
  }

  /**
   * Vehicle
   *
   * <p>定义车辆对象
   *
   * @version 1.0
   * @author Created by ivan at 16:30.
   */
  @Data
  @Builder
  @AllArgsConstructor
  static class Vehicle {
    private Long id;
    private String brand;
    private Integer wheels;
    private String type;
    private BigDecimal price;
    private String years;
    private Boolean sale;

    public Vehicle(Vehicle v) {
      this.id = v.id;
      this.brand = v.brand;
      this.wheels = v.wheels;
      this.type = v.type;
      this.price = v.price;
      this.years = v.years;
      this.sale = v.sale;
    }
  }

  /**
   * StreamMemos
   *
   * <p>Dealer对象，一个Dealer可以销售多种Vehicle
   *
   * @version 1.0
   * @author Created by ivan at 10:59.
   */
  @Data
  @Builder
  @AllArgsConstructor
  static class Dealer {
    private Long id;
    private String name;
    private List<Vehicle> inStock;
  }
}
