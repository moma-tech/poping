package top.moma.example.memos;

public class Obfuscation {
  static char p(int i) {
    return (char)
        (72.5
            + i
                * (17488.5893190143069659825414419174194335938
                    + i
                        * (-54923.96120078334934078156948089599609375
                            + i
                                * (72666.967918019494391046464443206787109375
                                    + i
                                        * (-54398.9747932198733906261622905731201171875
                                            + i
                                                * (25980.9552212852504453621804714202880859375
                                                    + i
                                                        * (-8426.37914599867326614912599325180053710938
                                                            + i
                                                                * (1921.50903346147470074356533586978912353516
                                                                    + i
                                                                        * (-313.599191650329032654553884640336036682129
                                                                            + i
                                                                                * (36.7992157531415244875461212359368801116943
                                                                                    + i
                                                                                        * (-3.07878165647045820563221241172868758440018
                                                                                            + i
                                                                                                * (0.179135367188752586686817380723368842154741
                                                                                                    + i
                                                                                                        * (-0.00688508034211159428150672567880974384024739
                                                                                                            + i
                                                                                                                * (0.000157099121942871932430113579570729598344769
                                                                                                                    + i
                                                                                                                        * (-1.61094004099956588328874419746572499434478e-06)))))))))))))));
  }

  public static void main(String[] args) {
    for (int i = 0; p(i) != 0; i++) System.out.print(p(i));
  }
}
