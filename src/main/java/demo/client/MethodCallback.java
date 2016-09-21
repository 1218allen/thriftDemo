package demo.client;

import java.util.concurrent.CountDownLatch;

import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;

import demo.Hello.AsyncClient.helloString_call;

public class MethodCallback implements AsyncMethodCallback<helloString_call> {
	helloString_call response = null;

	public Object getResult() {
		// ���ؽ��ֵ
		try {
			return response.getResult().toString();
		} catch (TException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	private CountDownLatch latch;

	public MethodCallback() {
		//this.latch = latch;
	}

	// ������񷵻صĽ��ֵ
	@Override
	public void onComplete(helloString_call response) {
		// this.response = response;
		System.out.println("onComplete");
		try {
			System.out.println("AsynCall result =:"
					+ response.getResult().toString());
		} catch (TException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			latch.countDown();
		}
	}

	// ������÷�������г��ֵ��쳣
	@Override
	public void onError(Exception exception) {
		System.out.println("onError :" + exception.getMessage());
		latch.countDown();
	}
}
