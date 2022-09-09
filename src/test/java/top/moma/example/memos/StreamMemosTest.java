package top.moma.example.memos;

import org.junit.jupiter.api.Test;

class StreamMemosTest {

  @Test
  public void createStream() {
    StreamMemos streamMemos = new StreamMemos();
    //    assertLinesMatch(
    //        streamMemos.listStream().collect(Collectors.toList()),
    //        streamMemos.arraysStream().collect(Collectors.toList()));
    //    assertLinesMatch(
    //        streamMemos.listStream().collect(Collectors.toList()),
    //        streamMemos.objsStream().collect(Collectors.toList()));
    //
    //    streamMemos.intStream().forEach(System.out::println);
    //    streamMemos.doubleStream().forEach(System.out::println);
    //    streamMemos.longStream().forEach(System.out::println);
    //
    //    streamMemos.filterOps(streamMemos.vehicleStream());
    //    streamMemos.mapOp(streamMemos.vehicleStream());
    //    streamMemos.flatMapOp(streamMemos.dealerStream());

    streamMemos.stateOp(streamMemos.vehicleStream());
  }
}
