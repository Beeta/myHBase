import org.apache.hadoop.hbase.client.{Increment, HTable}
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.util.Bytes

/**
 * Created by 10191665 on 2016/3/9.
 */
object Counter extends App{
  val conf = HBaseConfiguration.create()
//  //单计数器
//  val cnt1=cun_incr.incrementColumnValue(Bytes.toBytes("20110101"),Bytes.toBytes("daily"),Bytes.toBytes("hits"),1)
//  val cnt2=cun_incr.incrementColumnValue(Bytes.toBytes("20110101"),Bytes.toBytes("daily"),Bytes.toBytes("hits"),1)
//  println(cnt1)

  //多计数器
  val cun_incr=new HTable(conf,"cun_incr")
  val increment1 = new  Increment(Bytes.toBytes("20110101"))
  increment1.addColumn(Bytes.toBytes("daily"),Bytes.toBytes("clicks"),1)
  increment1.addColumn(Bytes.toBytes("daily"),Bytes.toBytes("hits"),1)
  increment1.addColumn(Bytes.toBytes("weekly"),Bytes.toBytes("clicks"),10)
  increment1.addColumn(Bytes.toBytes("weekly"),Bytes.toBytes("hits"),-10)
//  increment1.addColumn(Bytes.toBytes("weekly"),Bytes.toBytes("hits"),-10)
  val cnt3=cun_incr.increment(increment1)
  for (kv <-cnt3.raw() )
    println("kv:"+ kv + " ,value:"+ Bytes.toLong(kv.getValue))

  //println(cnt3)
}
