package demo.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.thrift.TException;

import demo.Hello;
import demo.Hello.Iface;

public class HelloServiceImpl implements Hello.Iface {
	public boolean helloBoolean(boolean para) throws TException {
		return para;
	}

	public int helloInt(int para) throws TException {
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return para;
	}

	public String helloNull() throws TException {
		return null;
	}

	public String helloString(String para) throws TException {
		return para;
	}

	public void helloVoid() throws TException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");// 设置日期格式

		try {
			Thread.sleep(1000);
			System.out.println("Hello World!" + df.format(new Date()));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}