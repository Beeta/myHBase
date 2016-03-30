package client;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * Created by 10191032 on 2016/3/1.
 */
public class FilterExample {

    public static void main(String[] args)throws IOException {
        Configuration conf = HBaseConfiguration.create();
//        HBaseAdmin admin = new HBaseAdmin(conf);
        HTable table =  new HTable(conf,"wjdtest");

        Filter filter1 = new CustomFilter(Bytes.toBytes("val100"));

        Scan scan = new Scan();
        scan.setFilter(filter1);
        ResultScanner scanner = table.getScanner(scan);
        Result singleScanner = scanner.next();
        while (singleScanner!=null){
            for(KeyValue kv:singleScanner.raw()){
                System.out.println("kv:" + kv + ",Value:" + Bytes.toString(kv.getValue()));
            }
            singleScanner = scanner.next();
        }
        scanner.close();

    }

}
