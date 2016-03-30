
import java.util
import org.apache.hadoop.hbase.client.{ResultScanner, Scan, HTable, HBaseAdmin}
import org.apache.hadoop.hbase.filter._
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.util.Bytes

/**
 * Created by 10191032 on 2016/3/1.
 */
object MyFilterExample {

  def main (args: Array[String]) {
    val conf = HBaseConfiguration.create()
//    val admin = new HBaseAdmin(conf)
    val table = new HTable(conf,"wjdtest")

    //比较过滤器
    val scan = new Scan()
    scan.addFamily(Bytes.toBytes("cf"))
    val filter1 = new RowFilter(CompareFilter.CompareOp.LESS_OR_EQUAL,new BinaryComparator(Bytes.toBytes("row2")))
    val filter2 = new FamilyFilter(CompareFilter.CompareOp.LESS_OR_EQUAL,new BinaryComparator(Bytes.toBytes("cf")))
    val filter3 = new QualifierFilter(CompareFilter.CompareOp.LESS_OR_EQUAL,new BinaryComparator(Bytes.toBytes("qf1")))
    val filter4 = new ValueFilter(CompareFilter.CompareOp.LESS_OR_EQUAL,new SubstringComparator("val2"))
    scan.setFilter(filter4)
    val resFilter1 = table.getScanner(scan)
    printScan(resFilter1)
    resFilter1.close()

    //专用过滤器
//    val scan = new Scan(Bytes.toBytes("row1"))
//    val filter1 = new SingleColumnValueFilter(Bytes.toBytes("cf"),Bytes.toBytes("qf"),CompareFilter.CompareOp.EQUAL,new BinaryComparator(Bytes.toBytes("val100")))
//    val filter2 = new SingleColumnValueExcludeFilter(Bytes.toBytes("cf"),Bytes.toBytes("qf"),CompareFilter.CompareOp.EQUAL,new BinaryComparator(Bytes.toBytes("val100")))
//    val filter3 = new PrefixFilter(Bytes.toBytes("row1"))
//    val filter4 = new PageFilter(4)
//    val filter5 = new KeyOnlyFilter()
//    val filter6 = new FirstKeyOnlyFilter
//    val filter7 = new InclusiveStopFilter(Bytes.toBytes("row7"))
//    val filter8 = new ColumnCountGetFilter(10)
//    val filter9 = new ColumnPaginationFilter(1,1)
//    val filter10 = new ColumnPrefixFilter(Bytes.toBytes("qf"))
//    val filter11 = new RandomRowFilter(0.2.toFloat)
//    scan.setFilter(filter4)
//    val resScan = table.getScanner(scan)
//    printScan(resScan)
//    resScan.close()

    //附加过滤器
//    val filter = new ValueFilter(CompareFilter.CompareOp.NOT_EQUAL,new BinaryComparator(Bytes.toBytes("val200")))
//    val filter1 = new SkipFilter(filter)
//    val filter2 = new WhileMatchFilter(filter)
//    val scan = new Scan()
//    scan.setFilter(filter1)
//    val resScan = table.getScanner(scan)
//    printScan(resScan)
//    resScan.close()

    //filterList
//    val filter1 = new RowFilter(CompareFilter.CompareOp.NOT_EQUAL,new BinaryComparator(Bytes.toBytes("row1")))
//    val filter2 = new  ValueFilter(CompareFilter.CompareOp.NOT_EQUAL,new BinaryComparator(Bytes.toBytes("val5")))
//
//    val list = new util.ArrayList[Filter]()
//    list.add(filter1)
//    list.add(filter2)
//    val filteList = new FilterList(FilterList.Operator.MUST_PASS_ONE,list)
//    val scan = new Scan()
//    scan.setFilter(filteList)
//    val resScan = table.getScanner(scan)
//    printScan(resScan)
//    resScan.close()

    //自定义过滤器




  }

  def printScan(resScan: ResultScanner){
    var singleScanner = resScan.next
    while(singleScanner!=null)
    {
      for (kv <-singleScanner.raw() )
        println("kv:" + kv + " ,key:"+ Bytes.toString(kv.getRow) + " ,value:"+ Bytes.toString(kv.getValue))
      singleScanner = resScan.next
    }
  }
}
