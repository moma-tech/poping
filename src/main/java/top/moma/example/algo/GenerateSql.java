package top.moma.example.algo;

public class GenerateSql {

  public static void main(String[] args) {
    String type = "Airtime,MobileData,Betting,TV,Electricity";
    String template =
        "select count(*) from contact_save_record_0009 where service_type='AATransfer' and update_time>='%s' and update_time < '%s'";
    String[] years = new String[] {"2023"};
    String[] months = new String[] {"07", "08", "09"};
    String days = "01";
    String midDay05 = "05";
    String midDay = "07";
    String midDay15 = "11";
    String midDay2 = "15";
    String midDay25 = "19";
    String midDay3 = "23";
    String midDay35 = "27";
    String time = "T00:00:00";
    String link = "-";

    for (int i = 0; i < years.length; i++) {
      String start = years[i] + link;
      String end = years[i] + link;
      for (int m = 0; m < months.length - 1; m++) {
        /*month*/
        //        System.out.printf(
        //            template,
        //            start + months[m] + link + days + time,
        //            end + months[m + 1] + link + days + time);
        //        System.out.println();
        /*15*/
        //        System.out.printf(
        //            template,
        //            start + months[m] + link + days + time,
        //            start + months[m] + link + midDay2 + time);
        //        System.out.println();
        //        System.out.printf(
        //            template,
        //            start + months[m] + link + midDay2 + time,
        //            end + months[m + 1] + link + days + time);
        //        System.out.println();
        /*7*/
        //        System.out.printf(
        //            template,
        //            start + months[m] + link + days + time,
        //            start + months[m] + link + midDay + time);
        //        System.out.println();
        //
        //        System.out.printf(
        //            template,
        //            start + months[m] + link + midDay + time,
        //            start + months[m] + link + midDay2 + time);
        //        System.out.println();
        //
        //        System.out.printf(
        //            template,
        //            start + months[m] + link + midDay2 + time,
        //            start + months[m] + link + midDay3 + time);
        //        System.out.println();
        //
        //        System.out.printf(
        //            template,
        //            start + months[m] + link + midDay3 + time,
        //            end + months[m + 1] + link + days + time);
        //        System.out.println();
        /*4*/
        //        System.out.printf(
        //            template,
        //            start + months[m] + link + days + time,
        //            start + months[m] + link + midDay05 + time);
        //        System.out.println();
        //        System.out.printf(
        //            template,
        //            start + months[m] + link + midDay05 + time,
        //            start + months[m] + link + midDay + time);
        //        System.out.println();
        //
        //        System.out.printf(
        //            template,
        //            start + months[m] + link + midDay + time,
        //            start + months[m] + link + midDay15 + time);
        //        System.out.println();
        //        System.out.printf(
        //            template,
        //            start + months[m] + link + midDay15 + time,
        //            start + months[m] + link + midDay2 + time);
        //        System.out.println();
        //
        //        System.out.printf(
        //            template,
        //            start + months[m] + link + midDay2 + time,
        //            start + months[m] + link + midDay25 + time);
        //        System.out.println();
        //        System.out.printf(
        //            template,
        //            start + months[m] + link + midDay25 + time,
        //            start + months[m] + link + midDay3 + time);
        //        System.out.println();
        //
        //        System.out.printf(
        //            template,
        //            start + months[m] + link + midDay3 + time,
        //            end + months[m + 1] + link + midDay35 + time);
        //        System.out.println();
        //        System.out.printf(
        //            template,
        //            start + months[m] + link + midDay35 + time,
        //            end + months[m + 1] + link + days + time);
        //        System.out.println();
        /*1*/
        StringBuffer header = new StringBuffer();
        header.append("select * from (");
        for (int j = 1; j < 31; j++) {
          int t = j + 1;
          header.append(
              String.format(
                  template,
                  start + months[m] + link + j + time,
                  end + months[m] + link + (j + 1) + time));

          header.append(") t").append(j).append(" JOIN (");
        }
        //        header
        //            .append(
        //                String.format(
        //                    template,
        //                    start + months[m] + link + 30 + time,
        //                    end + months[m + 1] + link + days + time))
        //            .append(") 30 ;");
        header
            .append(
                String.format(
                    template,
                    start + months[m] + link + 31 + time,
                    end + months[m + 1] + link + days + time))
            .append(") a31 ;");
        System.out.println(header.toString());
      }
      System.out.println();
    }
  }
}
