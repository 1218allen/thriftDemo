package demo.client;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.thrift.TException;
import org.apache.thrift.async.TAsyncClientManager;
//import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TNonblockingTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.transport.TFramedTransport;

import demo.Hello;

class startThread extends Thread {
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");// 设置日期格式

	HelloServiceClient client = new HelloServiceClient();
	String str = "";

	public startThread() {

	}

	public startThread(String str) {
		this.str = str;
	}

	public void run() {
		System.out.println(str + "start:" + df.format(new Date()));
		client.startClient(str);
	}
}

public class HelloServiceClient {
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");// 设置日期格式

	/**
	 * 调用Hello服务
	 */
	public void startClient(String hello) {
		try {
			// 设置调用的服务器为本地，端口为7911
			TTransport transport = new TFramedTransport(new TSocket(
					"localhost", 7911));
			transport.open();
			// 设置传输协议为TBinaryProtocol
			TProtocol protocol = new TCompactProtocol(transport);
			Hello.Client client = new Hello.Client(protocol);
			client.helloVoid();
			transport.close();
			System.out.println(hello + "end:" + df.format(new Date()));
		} catch (TTransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void startClient1(String hello) {
		try {
			// 设置调用的服务器为本地，端口为7911
			TTransport transport = new TSocket("localhost", 7911);
			transport.open();
			// 设置传输协议为TBinaryProtocol
			TProtocol protocol = new TCompactProtocol(transport);
			Hello.Client client = new Hello.Client(protocol);
			client.helloVoid();
			transport.close();
			System.out.println(hello + "end11:" + df.format(new Date()));
		} catch (TTransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			startThread s = new startThread("yoo" + i);
			s.start();
		}
	}

}