import org.apache.hadoop.hbase.client.coprocessor.AggregationClient
import org.apache.hadoop.hbase.client.Scan
import org.apache.hadoop.hbase.util.Bytes

/**
 * Created by 10191665 on 2016/3/9.
 */
object Coprocessor extends App {
  val aggregationClient = new AggregationClient(Counter.conf)
  val scan = new Scan()
  var rowCount:Long=0
  try {
    scan.addColumn(Bytes.toBytes("daily"), Bytes.toBytes("hits"))
    rowCount = aggregationClient.rowCount(Counter.cun_incr, null, scan)
  }
  catch {
    case e:Throwable=> e.printStackTrace()
  }
//  return rowCount
  println(rowCount)
}
