import java.util
import org.apache.hadoop.hbase.client._
import org.apache.hadoop.hbase.filter._
import org.apache.hadoop.hbase.{HColumnDescriptor, HTableDescriptor, HBaseConfiguration}
import org.apache.hadoop.hbase.util.Bytes

/**
 * Created by 10191032 on 2016/2/4.
 */
object TempTest {
  def main (args: Array[String]) {
    val conf = HBaseConfiguration.create()
    val admin = new HBaseAdmin(conf)

    //create
    if(!admin.tableExists("wjdtest")) {
      val tabDesc = new HTableDescriptor("wjdtest")
      val cfDesc = new HColumnDescriptor("cf")
      tabDesc.addFamily(cfDesc)
      admin.createTable(tabDesc)

      println(admin.tableExists("wjdtest"))
    }

    //drop


    val batch = new util.ArrayList[Row]()
    val table = new HTable(conf,"wjdtest")
//    //Put table
//    val putList = new util.ArrayList[Put]()
    val put1 = new Put(Bytes.toBytes("row1"))
    put1.add(Bytes.toBytes("cf"), Bytes.toBytes("qf1"), Bytes.toBytes("val1"))
//    val put2 = new Put(Bytes.toBytes("row2"))
//    put2.add(Bytes.toBytes("cf"), Bytes.toBytes("qf1"), Bytes.toBytes("val2"))
//    val put3 = new Put(Bytes.toBytes("row3"))
//    put3.add(Bytes.toBytes("cf"), Bytes.toBytes("qf1"), Bytes.toBytes("val3"))
//
//
//    putList.add(put1)
//    putList.add(put2)
//    putList.add(put3)
//
//    table.put(putList)

    batch.add(put1)

    //get
    val get =  new Get(Bytes.toBytes("row1"))
    get.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("qf"))
//    val resGet = table.get(get).getValue(Bytes.toBytes("cf"),Bytes.toBytes("qf"))
//    println(Bytes.toString(resGet))

    batch.add(get)

    //delete
    val del = new Delete(Bytes.toBytes("row1"))
    del.deleteColumns(Bytes.toBytes("cf"),Bytes.toBytes("qf"))
//    table.delete(del)
//    val resGet2 = table.get(get).getValue(Bytes.toBytes("cf"),Bytes.toBytes("qf"))
//    println(Bytes.toString(resGet2))

    batch.add(del)

    val a = table.batch(batch)

    //scan
//   val scanRes =table.getScanner(Bytes.toBytes("cf"))
//    for(next<-scanRes.next(10))
//    {
//      println("scanner:row="+Bytes.toString(next.getRow) + " value=" +Bytes.toString(next.getValue(Bytes.toBytes("cf"),Bytes.toBytes("qf"))))
//    }



//    table.close()

    //filter
//    val table = new HTable(conf,"wjdtest")
//    val scan = new Scan()
//    scan.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("qf"))
//    val filter1 = new RowFilter(CompareFilter.CompareOp.LESS_OR_EQUAL,new BinaryComparator(Bytes.toBytes("row2")))
//    scan.setFilter(filter1)
//    val resFilter1 = table.getScanner(scan)
//    for(next<-resFilter1.next(100))
//      println(next)
//    resFilter1.close()


//    val filter = new KeyOnlyFilter()
//    val scan = new Scan()
//    scan.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("qf"))
//    scan.setFilter(filter)
//    val resFilter = table.getScanner(scan)
//    for(next<-resFilter.next(100))
//      println(Bytes.toString(next.getValue(Bytes.toBytes("cf"),Bytes.toBytes("qf"))))
//    resFilter.close()

//    val filter = new FirstKeyOnlyFilter
//    val scan = new Scan()
//    scan.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("qf"))
//    scan.setFilter(filter)
//    val resFilter = table.getScanner(scan)
//    for(next<-resFilter.next(100))
//      println(next)
//    resFilter.close()


//        val scan = new Scan()
//        val filter = new ColumnCountGetFilter(1)
//        scan.setFilter(filter)
//        val resFilter = table.getScanner(scan)
//        for(next<-resFilter.next(100))
//          println(next)
//        resFilter.close()

//    val scan = new Scan()
//    val filter = new ColumnPaginationFilter(1,1)
//    scan.setFilter(filter)
//    val resFilter = table.getScanner(scan)
//    for(next<-resFilter.next(100))
//      println(next)
//    resFilter.close()

    val filter = new RowFilter(CompareFilter.CompareOp.NOT_EQUAL,new BinaryComparator(Bytes.toBytes("row3")))
    val filterSkip = new WhileMatchFilter(filter)
    val scan = new Scan()
    scan.setFilter(filterSkip)
    val resFilter = table.getScanner(scan)
    for(next<-resFilter.next(100))
      println(Bytes.toString(next.getRow)  + ":" + Bytes.toString(next.getValue(Bytes.toBytes("cf"),Bytes.toBytes("qf"))))
    resFilter.close()

//    val filter1 = new RowFilter(CompareFilter.CompareOp.NOT_EQUAL,new BinaryComparator(Bytes.toBytes("row1")))
//    val filter2 = new  ValueFilter(CompareFilter.CompareOp.NOT_EQUAL,new BinaryComparator(Bytes.toBytes("val5")))
//
//    val list = new util.ArrayList[Filter]()
//    list.add(filter1)
//    list.add(filter2)
//    val filteList = new FilterList(FilterList.Operator.MUST_PASS_ALL,list)
//    val scan = new Scan()
//    scan.setFilter(filteList)
//    val resFilter = table.getScanner(scan)
//    for(next<-resFilter.next(100))
//      println(next)
//    resFilter.close()

//    val a = new Delete(Bytes.toBytes("row6"))
//    a.deleteColumn(Bytes.toBytes("cf"),Bytes.toBytes("qf"))
//    table.delete(a)
















//    val filterPage = new PageFilter(1)
//    var lastRow = null
//    while(true){
//      val scan = new Scan()
//      scan.setFilter(filterPage)
//      if(lastRow!=null){
//        scan.setStartRow(Bytes.add(lastRow,POSTFIX))
//      }
//      val resPage = table.getScanner(scan)
//      for(res<-resPage.next(1000)){
//        println(res)
//        lastRow = res.getRow
//      }
//      resPage.close()
//
//    }












  }
}
