package com.example.test_socked;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button js;
	EditText shuru;
	TextView jieshou;
	Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			String s=msg.obj.toString();
			//Log.i("TAG", s);
			jieshou.setText(s);
		}
		
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		js=(Button) findViewById(R.id.button2);
		jieshou=(TextView) findViewById(R.id.textView1);
		js.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				new Thread(new UseThread()).start();
			}
		});
	}
		class UseThread implements Runnable{
			public void run() {
				Socket socket;
				try {
					//Log.i("TGA", "before socket");
					socket = new Socket("10.12.75.215",8888);
					
					InputStream in=socket.getInputStream();
					//Log.i("TGA", "" + in.available());
					byte[] buffer=new byte[in.available()];
					in.read(buffer);
					String str=new String(buffer);
					Message message=new Message();
					message.obj=str;
					handler.sendMessage(message);
					//Log.i("TGA", ""+str);
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
}
