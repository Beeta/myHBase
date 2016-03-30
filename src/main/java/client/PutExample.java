package client;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * Created by 10191032 on 2016/2/17.
 */
public class PutExample {

    public static void main(String[] args)throws IOException{
        Configuration conf = HBaseConfiguration.create();
        HBaseAdmin admin = new HBaseAdmin(conf);


        HTable table =  new HTable(conf,"hbasetest");
        Put put = new Put(Bytes.toBytes("row1"));
        put.add(Bytes.toBytes("cf"),Bytes.toBytes("qf"),Bytes.toBytes("val2"));
        table.put(put);

    }



//    public static void main(String[] args) throws IOException{
//        Configuration conf = HBaseConfiguration.create();
//
//        HBaseHelper helper = HBaseHelper.getHelper(conf);
//
//        helper.dropTable("wjdtest");
//        helper.createTable("wjdtest","cf");
//
//        HConnection connection = helper.getConnection();
//        HTable table = (HTable) connection.getTable("wjdtest");
//
//        Put put = new Put(Bytes.toBytes("row2"));
//
//        put.add(Bytes.toBytes("cf"),Bytes.toBytes("qf"),Bytes.toBytes("val2"));
//
//        table.put(put);
//    }



}
