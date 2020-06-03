package kai;


/*各チャネル用のサーバプログラム*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Channel extends Thread {
	newserver server; // チャットサーバ本体
	Socket socket = null; // ソケット
	BufferedReader input; // 入力用ストリーム
	OutputStreamWriter output; // 出力用ストリーム
	String handle; // クライアントのハンドル
	String[] addr_arr=new String[256];

	final char controlChar = (char)05;   // change
	final char separateChar = (char)06;  // change

	// 引数はチャネル番号、ソケット、Server.
	Channel(Socket s, newserver cs) {
		server = cs;
		socket = s;
		start();
	}

	// クライアントへ文字列を出力する
	synchronized void write(String s) {
		try {
			output.write(s + "\r\n");
			output.flush();
		} catch (IOException e) {
			System.out.println("Write Err");
			close(); // エラーを起こしたら、接続を切断する
		}
	}

	/*
	 *  チャネルのメインルーチン。
	 *  クライアントからの入力を受け付ける
	 */
	public void run() {
		String s;
		try {
			// ソケットから入出力ストリームを得る
			input = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			output = new OutputStreamWriter(socket.getOutputStream());
			excel excel=null;
			excel_yomikomi excel_yomikomi=null;
			excel_yomikomi2 excel_yomikomi2=null;
			excel_yomikomi_UPDATE excel_yomikomi_UPDATE =null;
			excel_delete excel_delete=null;

			String from = null;
			String from2=null;
			String from3=null;
			String from4=null;
			String from5=null;
			String from6=null;
			String from7=null;
			String from8=null;
			String from9=null;
			String d=null;
			String d_time=null;
			String time=null;
			String addr=null;
			String id=null;
			int lastDayOfMonth,i,t,count_ip=1;

			String[][] j;
			String[] k,k_copy;
			while (true) { // 入力待ちのループ
				s = input.readLine(); // 文字列入力を待つ

				if (s.equals("touroku")){
					System.out.println("tourokuOK");
					from=input.readLine();//index
					from7=input.readLine();//名前
					from2=input.readLine();//人数
					from6=input.readLine();//時間
					id=input.readLine();//id
					excel=new excel(from,from7,from2,from6,id);
				}else if(s.equals("kakunin")) {

					System.out.println("kakuninOK");
					from3=input.readLine();
					time=input.readLine();
					id=input.readLine();
					k=new String[3];
					k_copy=new String[3];
					excel_yomikomi=new excel_yomikomi();
					for(i=0;i<Integer.parseInt(time);i++){

						k=excel_yomikomi.yomikomi(from3,String.valueOf(i),id);

						if(k[0]!=null&&k[1]!=null&&k[2]!=null){
							k_copy=k;
						}
					write("OUTPUT");
					for(i=0;i<k_copy.length;i++){
						write(k[i]);
					}

					}






				}else if(s.equals("UPDATE")){
					System.out.println("a");
					from4=input.readLine();//月末日
					lastDayOfMonth=Integer.parseInt(from4);
					time=input.readLine();
					int time_int=Integer.parseInt(time);
					j=new String[lastDayOfMonth+1][time_int];
					for (i = 1; i < lastDayOfMonth+1 ; i++){
						for(t=0;t<time_int;t++){
							excel_yomikomi_UPDATE=new excel_yomikomi_UPDATE();
							String stri=String.valueOf(i);
							String strt=String.valueOf(t);
							j[i][t]=excel_yomikomi_UPDATE.yomikomi(stri,strt);
							System.out.println(j[i][t]);
						}
					}
					write("UPDATE_result");

					for (i = 1; i < lastDayOfMonth+1 ; i++){
						for(t=0;t<time_int;t++){
							String stri=String.valueOf(i);

							write(j[i][t]);
							write(stri);

						}

					}

				}else if(s.equals("DELETE")){
					System.out.println("DELETEOK");
					from5=input.readLine();//index
					time=input.readLine();//time.length
					id=input.readLine();

					for(i=0;i<Integer.parseInt(time);i++){
						excel_delete=new excel_delete(from5,String.valueOf(i),id);
					}




				}else if(s.equals("yoyaku")){
					System.out.println("yoyakuOK");
					int count;
					boolean judge[];
					excel_yomikomi2=new excel_yomikomi2();
					from8=input.readLine();//time.length
					System.out.println(from8);
					int indexmax = Integer.parseInt(from8);

					judge=new boolean[indexmax];
					from9=input.readLine();//index
					System.out.println(from9);
					count=0;
					  for (i = 0 ; i < indexmax; i++){
						 judge[i]=excel_yomikomi2.yomikomi(i,from9);
						 System.out.println(judge[i]);
						 if(!judge[i]){
							 count+=1;
						 }

					  }
					  write("yoyakuOK");
					  write(String.valueOf(count));

					  for (i = 0 ; i < indexmax; i++){
						  if(!judge[i]){//値がある場合
								 write(String.valueOf(i));
						  }
					  }
				}else if(s.equals("IPAddress")){
					addr=input.readLine();//IPアドレス


					System.out.println(addr_arr.length);
					write("ipaddress");
					addr_arr[server.numberofchannel()]=addr;
					System.out.println(server.numberofchannel());
					write(String.valueOf(server.numberofchannel()));
			   }else if(s.equals("delete_judge")){
				   d=input.readLine();//index
				   d_time=input.readLine();//time.length

				int count;
				boolean judge[];
				excel_yomikomi2=new excel_yomikomi2();


				int indexmax = Integer.parseInt(d_time);

				judge=new boolean[indexmax];


				count=0;
				  for (i = 0 ; i < indexmax; i++){
					 judge[i]=excel_yomikomi2.yomikomi(i,d);
					 System.out.println(judge[i]);
					 if(!judge[i]){
						 count+=1;//falseの数を数える 値あり
					 }

				  }
				  write("judgeOK");
				  write(String.valueOf(count));

				  for (i = 0 ; i < indexmax; i++){
					  if(!judge[i]){//値がある場合
							 write(String.valueOf(i));
					  }
				  }

			 }
			}
		} catch (IOException e) {
			System.out.println("Exception occurs in Channel: " + handle);
			close(); // エラーを起こしたら、接続を切断する
		}
	}

	// 接続を切断する
	public void close() {
		try {
			input.close(); // ストリームを閉じる
			output.close();
			socket.close(); // ソケットを閉じる
			socket = null;
			server.broadcast("# 回線切断 :" + handle);
			stop();
		} catch (IOException e) {
			System.out.println("Close Err");
		}
	}
}
