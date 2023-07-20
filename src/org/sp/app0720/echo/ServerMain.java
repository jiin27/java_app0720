package org.sp.app0720.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
	//클라이언트의 접속을 받을 수 있는 객체를 서버소켓이라 한다
	ServerSocket server;
	
	public ServerMain() {
		try {
			//소켓 서버 생성
			server = new ServerSocket(7777);
			System.out.println("서버 생성");
			
			//접속자가 감지되면, Socket이 반환된다. 접속한 이유가 대화를 나누기 위한 것이므로, 접속이 감지되었을 때 소켓이 반환 됨.
			Socket socket = server.accept();
			System.out.println("접속자 감지");
			
			InetAddress add=socket.getInetAddress();
			String ip=add.getHostAddress();
			System.out.println("접속한 자의 IP "+ip);
			
			//대화를 나누기 위한 스트림 얻기
			//접속한 자와 연관된 스트림을 얻을 수 있다
			InputStream is=socket.getInputStream();
			InputStreamReader reader=new InputStreamReader(is);
			BufferedReader buffr=new BufferedReader(reader); //문장 단위로 
			
			String msg=null;
			while(true) {
				msg=buffr.readLine(); //데이터가 읽히기 전까지 대기상태에 빠짐. read() 메서드가 실행부를 대기상태에 빠지게 한다. 메인 실행부 대신.
				System.out.println("받은 메시지: "+msg);				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new ServerMain();
	}
}
