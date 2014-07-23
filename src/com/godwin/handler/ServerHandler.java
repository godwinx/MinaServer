package com.godwin.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.godwin.tools.DateUtils;
import com.godwin.tools.LogUtils;
import com.godwin.tools.Toolkit;


public class ServerHandler extends IoHandlerAdapter {
	
	
	/**
	 * 所有sessionId列表
	 */
	public static List<Long> sesstionIdList=new ArrayList<Long>();
	private static IoAcceptor acceptor;
	
	public ServerHandler(IoAcceptor acceptor){
		this.acceptor=acceptor;
	}
	public static IoAcceptor getIoAcceptor(){
		return acceptor;
	}
	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		super.exceptionCaught(session, cause);
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		IoBuffer ioBuffer = (IoBuffer) message;
		//用来校验数据的CRC
		byte[] backupData = new byte[ioBuffer.limit()];
		ioBuffer.get(backupData);
		StringBuilder sensorData = new StringBuilder();
		sensorData.append(Toolkit.byte2HexStr(backupData, backupData.length));
		System.out.println("收到客户端"+session.getRemoteAddress()+"发来的消息为" + "  " + sensorData.toString()+"   "+DateUtils.getCurrentTime());
		LogUtils.getLoger().info("\r\nDTU DATA：" + sensorData.toString());	
		
	}
	
	@Override
	public void sessionOpened(IoSession session) throws Exception {
		super.sessionOpened(session);
		String address=session.getRemoteAddress().toString();
		System.out.println("客户端"+address+"连接服务器.....");
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		super.sessionClosed(session);
		
		/***************************************************************
		 *在配置文件配置了executorFilter多线程后，session就获取不到信息了，
		 *去掉多线程配置就可以获取，这里要用多线程，没办法，只能曲线救国，
		 *sesstionIdList保存开始连接时的所有sessionId，
		 *acceptor.getManagedSessions().keySet()获取当前存在的所有sessionId
		 *sesstionIdList减去keySet()得到刚close的sessionId列表了。
		 *
		 ***************************************************************/
		List<Long> tempList=new ArrayList<Long>();
		tempList.addAll(sesstionIdList);
		//找出离线的session
		tempList.removeAll(acceptor.getManagedSessions().keySet());
		sesstionIdList.removeAll(tempList);
		
		
		/*String address=session.getRemoteAddress().toString();
		System.out.println(address);*/
		for (int i = 0; i < tempList.size(); i++) {
			long id=tempList.get(i);
			
		}
		System.out.println("客户端与服务端断开连接.....");
	}
	
	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		if(status.equals(IdleStatus.BOTH_IDLE)){
			//空闲时关闭session
			session.close(true);
		}
	}
	
}
