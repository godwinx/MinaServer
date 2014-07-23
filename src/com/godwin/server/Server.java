package com.godwin.server;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.godwin.handler.ServerHandler;
import com.godwin.tools.LogUtils;
import com.godwin.tools.PropertiesConfig;

/**
 * Mina server
 * @author xieyj
 * @date 2014-7-23 下午04:34:30
 *
 */
public class Server {
	public Server(){
		// Init log file
		LogUtils.initialLog();
		// Init properties file
		PropertiesConfig.initialPropFile();
	}
	
	/**
	 * init server
	 * 
	 * @author xieyj
	 * @date 2014-7-23 下午04:36:21
	 */
	private void initServer(){
		// 服务器端的主要对象
		IoAcceptor acceptor = new NioSocketAcceptor();

		// 设置Filter链
		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		// 协议解析，采用mina现成的UTF-8字符串处理方式
		// 这里不添加字符编码过滤器
		//acceptor.getFilterChain().addLast("codec",new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
		//acceptor.getFilterChain().addLast("codec",new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		
		acceptor.getFilterChain().addLast("threadPool", new ExecutorFilter(Executors.newCachedThreadPool()));  
		// 设置消息处理类（创建、关闭Session，可读可写等等，继承自接口IoHandler）
		acceptor.setHandler(new ServerHandler(acceptor));
		// 设置接收缓存区大小
		acceptor.getSessionConfig().setReadBufferSize(2048);
		// 设置连接超时时间
		// 这个方法设置关联在通道上的读、写或者是读写事件在指定时间内未发生，该通道就进入空闲状态。一旦调用这个方法，则每隔idleTime 都会回调过滤器、IoHandler 中的sessionIdle()方法。
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 180);
		try {
			// 服务器开始监听
			acceptor.bind(new InetSocketAddress(PropertiesConfig.PORT_RECEIVE_VALUE));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new Server().initServer();
	}
}
