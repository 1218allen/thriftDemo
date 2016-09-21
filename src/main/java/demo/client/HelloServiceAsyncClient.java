package demo.client;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.thrift.TException;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TNonblockingTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import demo.Hello;

public class HelloServiceAsyncClient {
	/**
	 * 调用 Hello 服务
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		try {
			TAsyncClientManager clientManager = new TAsyncClientManager();
			TNonblockingTransport transport = new TNonblockingSocket(
					"localhost", 7911);
			TProtocolFactory protocol = new TCompactProtocol.Factory();
			Hello.AsyncClient asyncClient = new Hello.AsyncClient(protocol,
					clientManager, transport);
			System.out.println("Client calls .....");
			CountDownLatch latch = new CountDownLatch(1);
			MethodCallback callBack = new MethodCallback();
//			asyncClient.helloString("Hello World", callBack);
			asyncClient.helloVoid(callBack);
			System.out.println("end");
//			Object res = callBack.getResult();
//			// 等待服务调用后的返回结果
//			while (res == null) {
//				res = callBack.getResult();
//			}
			//boolean wait = latch.await(3, TimeUnit.SECONDS);
			//System.out.println("latch.await =:" + wait);
			
			while(true){
				Thread.sleep(100);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
