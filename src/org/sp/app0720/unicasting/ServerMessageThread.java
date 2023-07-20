package org.sp.app0720.unicasting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.JTextArea;

//다수의 접속 클라이언트들이 각각 독립된 대화를 유지하기 위한 thread 정의.
//thread 란 하나의 프로세스 내에서 독립적으로 수행될 수 있는 또 하나의 세부 실행 단위
//실제적으로 대화를 전달하는 행위를 담당
public class ServerMessageThread extends Thread{
	Socket socket;
	BufferedReader buffr;
	BufferedWriter buffw;
	
	JTextArea area;
	
	public ServerMessageThread(Socket socket, JTextArea area) {
		this.area=area;
		//얻어진 소켓으로부터, 스트림을 얻어놓자.
		try {
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() { //대화를 나누는 행위가 실제로 이뤄지는 독립적인 실행 단위
		while(true) {
			String msg = null;
			try {
				// 클라이언트의 말을 듣고
				msg = buffr.readLine();
				area.append(msg + "\n"); // 로그 남기기
				
				// 말하기
				buffw.write(msg + "\n");
				buffw.flush(); // 출력 스트림 객체는 비우기, flush() 해야 함
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
