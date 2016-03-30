import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.coprocessor.AggregationClient;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * Created by 10191665 on 2016/3/9.
 */
public class MyAggregationClient {

    private static final byte[] TABLE_NAME = Bytes.toBytes("cun_incr");
    private static final byte[] CF = Bytes.toBytes("vent");

    public static void main(String[] args) throws Throwable {
        //Configuration customConf = new Configuration();
//        customConf.setStrings("hbase.zookeeper.quorum",                "node0,node1,node2");
//提高RPC通信时长
//        customConf.setLong("hbase.rpc.timeout", 600000);
//设置Scan缓存
//        customConf.setLong("hbase.client.scanner.caching", 1000);
        Configuration configuration = HBaseConfiguration.create();
        AggregationClient aggregationClient = new AggregationClient(
                configuration);
        Scan scan = new Scan();
//指定扫描列族，唯一值
        scan.addFamily(CF);
        long rowCount = aggregationClient.rowCount(new HTable(HBaseConfiguration.create(), Bytes.toBytes("cun_incr")), null, scan);
        System.out.println("row count is " + rowCount);

    }
}