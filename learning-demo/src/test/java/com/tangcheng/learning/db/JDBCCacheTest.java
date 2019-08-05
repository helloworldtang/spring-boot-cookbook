package com.tangcheng.learning.db;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.sql.*;
import java.util.concurrent.TimeUnit;

/**
 * @author: tangcheng
 * @description:
 * @since: Created in 2018/09/04 19:42
 */
@Slf4j
public class JDBCCacheTest {

    @Test
    public void testJdbcCache() throws ClassNotFoundException, SQLException, InterruptedException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1:3306/career-bank?autoReconnect=true&useCompression=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true";
        try (Connection conn = DriverManager.getConnection(url, "root", "")) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT * from job_detail")) {
//                ps.setFetchSize(100);
//                ps.setFetchDirection(ResultSet.FETCH_FORWARD);
                try (ResultSet rs = ps.executeQuery()) {
                    rs.setFetchSize(100);
                    ps.setFetchDirection(ResultSet.FETCH_FORWARD);
                    ResultSetMetaData rsm = rs.getMetaData();
                    long num = 0L;
                    while (rs.next()) {
                        num++;
//                        for (int i = 1, count = rsm.getColumnCount(); i <= count; i++) {
//                            log.info("{}->{}", rsm.getColumnName(i), rs.getString(i));
//                        }
                        log.info("num:{}", num);
//                        TimeUnit.MILLISECONDS.sleep(num);
                    }
                    log.info("begin to close ResultSet");
                    TimeUnit.MINUTES.sleep(5);
                }//resultSet
                log.info("begin to close PreparedStatement");
                TimeUnit.MINUTES.sleep(5);
            }//preparedStatement
            log.info("begin to close Connection");
            TimeUnit.MINUTES.sleep(5);
        }//Connection

        log.info("all success. sleep for watch heap GC(Garbage Collection)");
        TimeUnit.MINUTES.sleep(10);
    }

    // TODO: 2018/9/5 如果一下子查询大量数据时，如何让占用内存量降低到FetchSize的大小
    /**
     "main" #1 prio=5 os_prio=0 tid=0x0000000002d5e800 nid=0x8d2c runnable [0x0000000002cfd000]
     java.lang.Thread.State: RUNNABLE
     at java.net.SocketInputStream.socketRead0(Native Method)
     at java.net.SocketInputStream.socketRead(SocketInputStream.java:116)
     at java.net.SocketInputStream.read(SocketInputStream.java:171)
     at java.net.SocketInputStream.read(SocketInputStream.java:141)
     at com.mysql.jdbc.util.ReadAheadInputStream.fill(ReadAheadInputStream.java:101)
     at com.mysql.jdbc.util.ReadAheadInputStream.readFromUnderlyingStreamIfNecessary(ReadAheadInputStream.java:144)
     at com.mysql.jdbc.util.ReadAheadInputStream.read(ReadAheadInputStream.java:174)
     - locked <0x00000006c6c5c910> (a com.mysql.jdbc.util.ReadAheadInputStream)
     at com.mysql.jdbc.CompressedInputStream.readFully(CompressedInputStream.java:273)
     at com.mysql.jdbc.CompressedInputStream.getNextPacketFromServer(CompressedInputStream.java:117)
     at com.mysql.jdbc.CompressedInputStream.getNextPacketIfRequired(CompressedInputStream.java:209)
     at com.mysql.jdbc.CompressedInputStream.read(CompressedInputStream.java:251)
     at com.mysql.jdbc.MysqlIO.readFully(MysqlIO.java:3011)
     at com.mysql.jdbc.MysqlIO.reuseAndReadPacket(MysqlIO.java:3522)
     at com.mysql.jdbc.MysqlIO.reuseAndReadPacket(MysqlIO.java:3462)
     at com.mysql.jdbc.MysqlIO.checkErrorPacket(MysqlIO.java:3903)
     at com.mysql.jdbc.MysqlIO.checkErrorPacket(MysqlIO.java:871)
     at com.mysql.jdbc.MysqlIO.nextRow(MysqlIO.java:1999)
     at com.mysql.jdbc.MysqlIO.readSingleRowSet(MysqlIO.java:3413)
     at com.mysql.jdbc.MysqlIO.getResultSet(MysqlIO.java:471)
     at com.mysql.jdbc.MysqlIO.readResultsForQueryOrUpdate(MysqlIO.java:3115)
     at com.mysql.jdbc.MysqlIO.readAllResults(MysqlIO.java:2344)
     at com.mysql.jdbc.MysqlIO.sqlQueryDirect(MysqlIO.java:2739)
     at com.mysql.jdbc.ConnectionImpl.execSQL(ConnectionImpl.java:2486)
     - locked <0x00000006c6c019f0> (a com.mysql.jdbc.JDBC4Connection)
     at com.mysql.jdbc.PreparedStatement.executeInternal(PreparedStatement.java:1858)
     - locked <0x00000006c6c019f0> (a com.mysql.jdbc.JDBC4Connection)
     at com.mysql.jdbc.PreparedStatement.executeQuery(PreparedStatement.java:1966)
     - locked <0x00000006c6c019f0> (a com.mysql.jdbc.JDBC4Connection)
     at com.tangcheng.learning.db.JDBCCacheTest.testJdbcCache(JDBCCacheTest.java:25)
     at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
     at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
     at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     at java.lang.reflect.Method.invoke(Method.java:498)
     at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
     at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
     at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
     at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
     at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
     at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
     at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
     at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
     at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
     at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
     at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
     at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
     at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
     at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
     at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)
     at com.intellij.rt.execution.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:47)
     at com.intellij.rt.execution.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:242)
     at com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:70)
     */

}
