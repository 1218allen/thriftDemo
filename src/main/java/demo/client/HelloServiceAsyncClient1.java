package demo.client;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TNonblockingTransport;

import demo.Hello;

public class HelloServiceAsyncClient1 {
	/**
	 * 调用 Hello 服务
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
//		try {
//			TAsyncClientManager clientManager = new TAsyncClientManager();
//			TNonblockingTransport transport = new TNonblockingSocket(
//					"localhost", 7911);
//			TProtocolFactory protocol = new TCompactProtocol.Factory();
//			Hello.AsyncClient asyncClient = new Hello.AsyncClient(protocol,
//					clientManager, transport);
//			System.out.println("Client calls .....");
//			CountDownLatch latch = new CountDownLatch(1);
//			MethodCallback callBack = new MethodCallback(latch);
//			asyncClient.helloString("Hello", callBack);
//			System.out.println("end");
////			Object res = callBack.getResult();
////			// 等待服务调用后的返回结果
////			while (res == null) {
////				res = callBack.getResult();
////			}
////			boolean wait = latch.await(30, TimeUnit.SECONDS);
////			System.out.println("latch.await =:" + wait);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
}
