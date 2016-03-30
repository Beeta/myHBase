
import java.nio.file.FileSystem
import java.util
import org.apache.hadoop.hbase.client._
import org.apache.hadoop.hbase.client.coprocessor.AggregationClient
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.hbase.{HColumnDescriptor, HTableDescriptor, HBaseConfiguration}

/**
 * Created by 10191032 on 2016/3/1.
 */
object MyCRUDExample {

  def main (args: Array[String]) {

    val conf = HBaseConfiguration.create()
    val admin = new HBaseAdmin(conf)
    val table = new HTable(conf,"wjdtest")


//计数器
    val table2=new HTable(conf,"counters")
    val cnt1=table2.incrementColumnValue(Bytes.toBytes("20110101"),Bytes.toBytes("daily"),Bytes.toBytes("hits"),1)
    val cnt2=table2.incrementColumnValue(Bytes.toBytes("20110101"),Bytes.toBytes("daily"),Bytes.toBytes("hits"),1)
    println(cnt1)

//协处理器 TODO http://blog.itpub.net/29754888/viewspace-1249972/
   val  aggregationClient = new AggregationClient(conf)
    val ss=new Scan()



//    //create
//    if(!admin.tableExists("wjdtest1")) {
//      val tabDesc = new HTableDescriptor("wjdtest1")
//      val cfDesc = new HColumnDescriptor("cf")
//      tabDesc.addFamily(cfDesc)
//      admin.createTable(tabDesc)
//      println(admin.tableExists("wjdtest1"))
//    }
//
//    //drop
//    if (admin.tableExists("wjdtest1")) {
//      if (admin.isTableEnabled("wjdtest1")) admin.disableTable("wjdtest1")
//      admin.deleteTable("wjdtest1")
//      println(admin.tableExists("wjdtest1"))
//    }

//    //put
    val put = new Put(Bytes.toBytes("row10"))
    put.add(Bytes.toBytes("cf"),Bytes.toBytes("qf1"),Bytes.toBytes("val11"))
//    put.add(Bytes.toBytes("cf"),Bytes.toBytes("qf2"),Bytes.toBytes("val11"))
//    put.add(Bytes.toBytes("cf"),Bytes.toBytes("qf3"),10,Bytes.toBytes("val11"))
    table.put(put)
//
//    //delete
//    val delete = new Delete(Bytes.toBytes("row10"))
//    delete.deleteColumn(Bytes.toBytes("cf"),Bytes.toBytes("qf1"))
//    delete.deleteColumn(Bytes.toBytes("cf"),Bytes.toBytes("qf2"))
//    delete.deleteColumn(Bytes.toBytes("cf"),Bytes.toBytes("qf3"))
//    table.delete(delete)

    //多行操作
//    val putList = new util.ArrayList[Put]()
//    val put1 = new Put(Bytes.toBytes("row11"))
//    put1.add(Bytes.toBytes("cf"), Bytes.toBytes("qf1"), Bytes.toBytes("val12"))
//    val put2 = new Put(Bytes.toBytes("row12"))
//    put2.add(Bytes.toBytes("cf"), Bytes.toBytes("qf2"), Bytes.toBytes("val12"))
//    val put3 = new Put(Bytes.toBytes("row13"))
//    put3.add(Bytes.toBytes("cf"), Bytes.toBytes("qf3"), Bytes.toBytes("val12"))
//    putList.add(put1)
//    putList.add(put2)
//    putList.add(put3)
//    table.put(putList)

    //get
//    val get =  new Get(Bytes.toBytes("row1"))
//    //    get.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("qf"))
//    get.addFamily(Bytes.toBytes("cf"))
//    val resGet = table.get(get)
//    for(kv <- resGet.raw())
//      println("kv:" + kv + " ,Value:" + Bytes.toString(kv.getValue))

    //scan
    val scan = new Scan(Bytes.toBytes("row2"))
    scan.addFamily(Bytes.toBytes("cf"))
    val resScan = table.getScanner(scan)
    var singleScanner = resScan.next()
    while(singleScanner!=null)
    {
      for (kv <-singleScanner.raw() )
        println("kv:"+ kv + " ,value:"+ Bytes.toString(kv.getValue))
      singleScanner = resScan.next
    }
    resScan.close()


    //batch
//batch    val batch = new util.ArrayList[Row]()
//    val put = new Put(Bytes.toBytes("row999"))
//    put.add(Bytes.toBytes("cf"),Bytes.toBytes("qf"),20,Bytes.toBytes("val999"))
//    batch.add(put)
//    val del = new Delete(Bytes.toBytes("row999"))
//    del.deleteColumn(Bytes.toBytes("cf"),Bytes.toBytes("qf1"))
//    batch.add(del)
//    table.batch(batch)




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

