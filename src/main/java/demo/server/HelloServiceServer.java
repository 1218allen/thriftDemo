package demo.server;

import org.apache.thrift.TProcessor;
import org.apache.thrift.server.THsHaServer;
//import org.apache.thrift.protocol.TBinaryProtocol;
//import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadPoolServer.Args;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TCompactProtocol.Factory;
import org.apache.thrift.server.TNonblockingServer;

import demo.Hello;
import demo.impl.HelloServiceImpl;

public class HelloServiceServer {
	/**
	 * 启动thrift服务器
	 */
	public void startServer(String serverType) {
		try {
			TServer server = null;
			// 设置协议工厂为TBinaryProtocol.Factory
			Factory proFactory = new TCompactProtocol.Factory();
			// 关联处理器与Hello服务的实现
			TProcessor processor = new Hello.Processor<Hello.Iface>(
					new HelloServiceImpl());

			if (serverType.equals("nonblocking")) {
				TNonblockingServerSocket serverTransport = new TNonblockingServerSocket(
						7911);
				TNonblockingServer.Args tArgs = new TNonblockingServer.Args(
						serverTransport);
				tArgs.processor(processor);
				tArgs.protocolFactory(proFactory);
				tArgs.transportFactory(new TFramedTransport.Factory());
				server = new TNonblockingServer(tArgs);
			} else if (serverType.equals("hsha")) {
				TNonblockingServerSocket serverTransport = new TNonblockingServerSocket(
						7911);
				THsHaServer.Args tArgs = new THsHaServer.Args(serverTransport);
				tArgs.processor(processor);
				tArgs.protocolFactory(proFactory);
				tArgs.transportFactory(new TFramedTransport.Factory());
				server = new THsHaServer(tArgs);
			} else if (serverType.equals("selector")) {
				TNonblockingServerSocket serverTransport = new TNonblockingServerSocket(
						7911);
				TThreadedSelectorServer.Args tArgs = new TThreadedSelectorServer.Args(
						serverTransport);
				tArgs.processor(processor);
				tArgs.protocolFactory(proFactory);
				tArgs.transportFactory(new TFramedTransport.Factory());
				server = new TThreadedSelectorServer(tArgs);
			} else if (serverType.equals("threadpool")) {
				TServerSocket serverTransport = new TServerSocket(7911);
				TThreadPoolServer.Args tArgs = new TThreadPoolServer.Args(
						serverTransport);
				tArgs.processor(processor);
				tArgs.protocolFactory(proFactory);
//				tArgs.maxWorkerThreads = 2;
//				tArgs.minWorkerThreads = 1;
				server = new TThreadPoolServer(tArgs);
			}

			System.out.println("Start server on port 7911....");
			server.serve();
		} catch (TTransportException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HelloServiceServer server = new HelloServiceServer();
		server.startServer("selector");
	}

}
