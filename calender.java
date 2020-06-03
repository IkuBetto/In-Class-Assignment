package kai;


import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class calender extends JFrame implements ActionListener,Runnable {

	Container contentpane;

	JTextArea infotextArea,freeArea;

	JButton touroku,check,ok1,connectBut,UPDATEBut,ok2,ok3,delete,ok4;

	JButton[] day = new JButton[42];

	JLabel month,date,sunday,monday,tuesday,wednesday,thursday,friday,saturday;

	JTextField hostField, portField,text1,text2;

	JFrame checkgui,finish,sch,deletegui,schtouroku;

	JRadioButton[] time= new JRadioButton[3];

	int i,lastDayOfMonth ,t,count,count2,count3,count4,c;



	String index,choice,choice_index,id,o1,o2,o3;

	String host = "localhost";

	int port = 9000;

	Thread thread = null;

	Client client = null;



    public boolean clientOpen() {
		try {
	    	if(client == null){
	        	host = hostField.getText();
	        	port = Integer.valueOf(portField.getText()).intValue();;
	    		client = new Client();  // Clientクラスの呼び出し
	        	client.connectServer(host, port);

	        	return true;
	    	}
		} catch (Exception e) {
	    	System.out.println("接続時に何らかの例外が発生しました");
	    	e.printStackTrace(System.err);
	    	return false;
		}


		return false;
    }

    public void start() {
        if (thread == null) {

            thread = new Thread(this);
            thread.start();
        }

    }

	public void actionPerformed(ActionEvent e){
		for(i = 1; i < lastDayOfMonth+1 ; i++){
			if (e.getSource() == day[i]) {
				index = String.valueOf(i);
				writeMes("delete_judge");
				writeMes(index);
				writeMes(String.valueOf(time.length));

				writeMes("check_judge");
				writeMes(index);
				writeMes(String.valueOf(time.length));

				sch=new JFrame();
				sch.setSize(300,200);
				sch.setVisible(true);
				setLayout(new FlowLayout());
				sch.setLayout(null);



				JLabel ans=new JLabel("操作を選んでください");
				ans.setOpaque(true);

				ans.setBounds(50, 5, 150, 30);
				sch.add(ans);

				touroku = new JButton("予約");
				touroku.setBounds(20, 60, 80, 30);
				touroku.addActionListener(this);
				sch.add(touroku);

				check = new JButton("確認");
				check.setBounds(100, 60, 80, 30);
				check.addActionListener(this);
				sch.add(check);

				delete = new JButton("消去");
				delete.setBounds(180, 60, 80, 30);
				delete.addActionListener(this);
				sch.add(delete);


			}

		}if(e.getSource() == touroku){
			writeMes("yoyaku");



			  writeMes(String.valueOf(time.length));
			  writeMes(index);




			schtouroku=new JFrame();
			schtouroku.setSize(300,400);
			schtouroku.setVisible(true);
			setLayout(new FlowLayout());
			schtouroku.setLayout(null);

			JLabel name=new JLabel("御名前を入力してください");
			name.setOpaque(true);
			name.setBounds(50, 5, 150, 30);
			schtouroku.add(name);

			text2=new JTextField(10);
			text2.setBounds(80, 40, 100, 30);
			schtouroku.add(text2);

			JLabel l=new JLabel("何名様ですか？");
			l.setOpaque(true);
			l.setBounds(50, 105, 150, 30);
			schtouroku.add(l);

			JLabel l2=new JLabel("人");
			l2.setOpaque(true);
			l2.setBounds(110, 140, 150, 30);
			schtouroku.add(l2);

			JLabel l3=new JLabel("予約時間を選んでください");
			l3.setOpaque(true);
			l3.setBounds(50, 180, 200, 30);
			schtouroku.add(l3);



		    time[0] = new JRadioButton("17:00～19:00");
		    time[1] = new JRadioButton("19:00～21:00");
		    time[2] = new JRadioButton("21:00～23:00");

		    ButtonGroup group = new ButtonGroup();
		    group.add(time[0]);
		    group.add(time[1]);
		    group.add(time[2]);

		    time[0].setBounds(80, 210, 150, 30);
		    time[1].setBounds(80, 240, 150, 30);
		    time[2].setBounds(80, 270, 150, 30);


		    schtouroku.add(time[0]);
		    schtouroku.add(time[1]);
		    schtouroku.add(time[2]);

			text1=new JTextField(10);
			text1.setBounds(80, 140, 30, 30);
			schtouroku.add(text1);

			ok1 = new JButton("OK");
			ok1.setBounds(100, 300, 80, 30);
			ok1.addActionListener(this);
			schtouroku.add(ok1);






		}if(e.getSource() == ok1){
			writeMes("touroku");
			writeMes(index);
		    for (int i = 0 ; i < time.length; i++){
		      if (time[i].isSelected()){
		        choice = time[i].getText();
		        choice_index = String.valueOf(i);

		      }
		    }
		    c=0;
		    for(i=0;i<time.length;i++){

		    	if((!time[i].isSelected())){
		    		c+=1;
		    	}
		    }
		    System.out.println(c);
		    if(text2.getText().equals("")||text1.getText().equals("")||c==time.length){
		    	JFrame frame = new JFrame();
		    	JOptionPane.showMessageDialog(frame, "記入欄が未入力です");

		    }else{
			    writeMes(text2.getText());//名前
				writeMes(text1.getText());//人数
				writeMes(choice_index);//時間
				writeMes(id);//channelの登録番号


				finish=new JFrame();
				finish.setSize(300,200);
				finish.setVisible(true);
				setLayout(new FlowLayout());
				finish.setLayout(null);

				JLabel label2=new JLabel("予約をしました");
				label2.setOpaque(true);
				label2.setBounds(50, 5, 150, 30);
				finish.add(label2);

				ok2 = new JButton("戻る");
				ok2.setBounds(100, 100, 80, 30);
				ok2.addActionListener(this);
				finish.add(ok2);

		    }


		}if(e.getSource() == connectBut){
			if(clientOpen()) start();
			try {
			      InetAddress addr = InetAddress.getLocalHost();
			      System.out.println("IP Address     : " + addr.getHostAddress());
			      String addr_str=String.valueOf(addr.getHostAddress());
					writeMes("IPAddress");
					writeMes(addr_str);

			} catch (UnknownHostException e2) {
			      e2.printStackTrace();
			}
			writeMes("UPDATE");
			String strlastDayOfMonth=String.valueOf(lastDayOfMonth);
			writeMes(strlastDayOfMonth);
			writeMes(String.valueOf(time.length));
			writeMes(id);






		}if(e.getSource()==check){
			writeMes("kakunin");
			writeMes(index);
			writeMes(String.valueOf(time.length));
			writeMes(id);//channelの登録番号
			checkgui=new JFrame();
			checkgui.setSize(300,200);
			checkgui.setVisible(true);
			setLayout(new FlowLayout());
			checkgui.setLayout(null);

			freeArea = new JTextArea();
			freeArea.setRows(4);
			freeArea.setBounds(0, 0, 300, 200);
			checkgui.add(freeArea);
			freeArea.append((String.format("  %s日の予約",index)));
			freeArea.append("\n");


			ok3 = new JButton("戻る");
			ok3.setBounds(100, 100, 80, 30);
			ok3.addActionListener(this);
			freeArea.add(ok3);


		}if(e.getSource()==UPDATEBut){
			writeMes("UPDATE");
			String strlastDayOfMonth=String.valueOf(lastDayOfMonth);
			writeMes(strlastDayOfMonth);
			writeMes(String.valueOf(time.length));
			for(i = 1; i < lastDayOfMonth+1 ; i++){
				day[i].setBackground(Color.WHITE);
			}

		}if(e.getSource()==ok2){
			finish.dispose();
			schtouroku.dispose();
			sch.dispose();

		}if(e.getSource()==ok3){
			checkgui.dispose();
			sch.dispose();

		}if(e.getSource()==ok4){
			deletegui.dispose();
			sch.dispose();

		}if(e.getSource()==delete){
			writeMes("DELETE");
			writeMes(index);
			writeMes(String.valueOf(time.length));
			writeMes(id);
			deletegui=new JFrame();
			deletegui.setSize(300,200);
			deletegui.setVisible(true);
			setLayout(new FlowLayout());
			deletegui.setLayout(null);

			JLabel label3=new JLabel("予定を削除しました");
			label3.setOpaque(true);
			label3.setBounds(50, 5, 150, 30);
			deletegui.add(label3);

			ok4 = new JButton("戻る");
			ok4.setBounds(100, 100, 80, 30);
			ok4.addActionListener(this);
			deletegui.add(ok4);



		}







	}





	public calender(String title) {
		// コンストラクタ



		setTitle(title);


		setSize(500, 500);

		setLayout(new FlowLayout());
		JPanel Panel = new JPanel();

		Panel.setPreferredSize(new Dimension(500, 500));
		Panel.setBackground(Color.WHITE);
		Panel.setLayout(null);
		contentpane = getContentPane();
		contentpane.add(Panel);

		JLabel month=new JLabel("1月");
		month.setOpaque(true);
		month.setBackground(Color.WHITE);
		month.setBounds(250, 10, 80, 30);
		Panel.add(month);

		JLabel sunday  =new JLabel("日");
		sunday.setOpaque(true);
		sunday.setBackground(Color.WHITE);
		sunday.setHorizontalAlignment(JLabel.CENTER);
		sunday.setVerticalAlignment(JLabel.CENTER);
		sunday.setBounds(100, 30, 30, 30);
		Panel.add(sunday);

		JLabel monday  =new JLabel("月");
		monday.setOpaque(true);
		monday.setBackground(Color.WHITE);
		monday.setHorizontalAlignment(JLabel.CENTER);
		monday.setVerticalAlignment(JLabel.CENTER);
		monday.setBounds(150, 30, 30, 30);
		Panel.add(monday);

		JLabel tuesday  =new JLabel("火");
		tuesday.setOpaque(true);
		tuesday.setBackground(Color.WHITE);
		tuesday.setBounds(200, 30, 30, 30);
		tuesday.setHorizontalAlignment(JLabel.CENTER);
		tuesday.setVerticalAlignment(JLabel.CENTER);
		Panel.add(tuesday);


		JLabel wednesday  =new JLabel("水");
		wednesday.setOpaque(true);
		wednesday.setBackground(Color.WHITE);
		wednesday.setHorizontalAlignment(JLabel.CENTER);
		wednesday.setVerticalAlignment(JLabel.CENTER);
		wednesday.setBounds(250, 30, 30, 30);
		Panel.add(wednesday);

		JLabel thursday  =new JLabel("木");
		thursday.setOpaque(true);
		thursday.setBackground(Color.WHITE);
		thursday.setHorizontalAlignment(JLabel.CENTER);
		thursday.setVerticalAlignment(JLabel.CENTER);
		thursday.setBounds(300, 30, 30, 30);
		Panel.add(thursday);

		JLabel friday  =new JLabel("金");
		friday.setOpaque(true);
		friday.setBackground(Color.WHITE);
		friday.setHorizontalAlignment(JLabel.CENTER);
		friday.setVerticalAlignment(JLabel.CENTER);
		friday.setBounds(350, 30, 30, 30);
		Panel.add(friday);

		JLabel saturday  =new JLabel("土");
		saturday.setOpaque(true);
		saturday.setBackground(Color.WHITE);
		saturday.setHorizontalAlignment(JLabel.CENTER);
		saturday.setVerticalAlignment(JLabel.CENTER);
		saturday.setBounds(400, 30, 30, 30);
		Panel.add(saturday);

		Calendar cal = Calendar.getInstance();


		cal.set(Calendar.YEAR, 2019);
		cal.set(Calendar.MONTH, 0);
		lastDayOfMonth = cal.getActualMaximum(Calendar.DATE);


		cal.set(2019, 0,1);

		switch (cal.get(Calendar.DAY_OF_WEEK)) {
	    case Calendar.SUNDAY:
	        t=90;
	        break;
	    case Calendar.MONDAY:
	        t=140;
	        break;
	    case Calendar.TUESDAY:
	        t=190;
	        break;
	    case Calendar.WEDNESDAY:
	        t=240;
	        break;
	    case Calendar.THURSDAY:
	        t=290;
	        break;
	    case Calendar.FRIDAY:
	        t=340;
	        break;
	    case Calendar.SATURDAY:
	        t=390;
	        break;
	}


		int w=0;
		int y=0;
		for (i = 1; i < lastDayOfMonth+1 ; i++){

			day[i] = new JButton(String.valueOf(i));
			day[i].setBounds(t+w, 60+y, 50, 30);
			day[i].addActionListener(this);
			day[i].setBackground(Color.WHITE);
			Panel.add(day[i]);
			w+=50;
			if(t+w==440){
				t=90;
				w=0;
				y+=30;

			}


		}


		hostField = new JTextField(host);
		hostField.setBounds(120, 350, 100, 30);
		Panel.add(hostField);

		portField = new JTextField(""+port);
		portField.setBounds(350, 350, 100, 30);
		Panel.add(portField);

		JLabel host_label=new JLabel("Host = ", Label.RIGHT);
		host_label.setBounds(80, 350, 100, 30);
		Panel.add(host_label);

		JLabel port_label=new JLabel("Port = ", JLabel.RIGHT);
		port_label.setBounds(250, 350, 100, 30);
		Panel.add(port_label);

		connectBut = new JButton("Connect");
		connectBut.setBounds(200, 390, 100, 30);
		connectBut.addActionListener(this);
		Panel.add(connectBut);

		UPDATEBut = new JButton("UPDATE");
		UPDATEBut.setBounds(200, 250, 100, 30);
		UPDATEBut.addActionListener(this);
		Panel.add(UPDATEBut);














	}

	/**
	 * mainメソッド
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		calender frame=new calender("a");
		frame.setVisible(true);

	}

	@Override
	public void run() {
		try {
	    	String s ;
	    	String u,in,k,in2,t;
	    	int int_i,int_i2,int_t,id_int;
	    	while(thread != null) {
	        	s = client.read();       // メッセージの読みとり
	        	//System.out.println(s);
	        	if(s == null){
	        		clientClose();
	        	}else if(s.equals("UPDATE_result")){

	        		count=1;

	        		while(count<lastDayOfMonth+1){
	        			count2=0;
	        			count3=0;
	        			count4=0;
	        			int_i=0;
	        			while(count2<time.length){
		        			u=client.read();//j[][]
		        			in=client.read();//i
		        			System.out.println(u);

		        			int_i=Integer.parseInt(in);

		        			if(!u.equals("")){
		        				count3+=1;

		        			}if(u.equals(id)){
		        				count4=1;
		        			}

		        			count2+=1;

	        			}
	        			if(count3<3&&count3>0){
	        				day[int_i].setBackground(Color.YELLOW);
	        			}else if(count3==3){
	        				day[int_i].setBackground(Color.RED);

	        			}if(count4==1){
	        				day[int_i].setBackground(Color.CYAN);
	        			}
	        			count+=1;
	        		}

	        	}else if(s.equals("yoyakuOK")){
	        		System.out.println("a");
	        		k=client.read();
	        		System.out.println(k);
	        		int int_k=Integer.parseInt(k);//falseの数
	        		for (i = 0; i < int_k ; i++){
	        			in2=client.read();
	        			int_i2=Integer.parseInt(in2);
	        			time[int_i2].setEnabled(false);
	        		}

	        	}else if(s.equals("ipaddress")){
	        		id=client.read();
	        		id_int=Integer.parseInt(id);
	        		System.out.println(id_int);

	        	}else if(s.equals("judgeOK")){
	        		count=0;
	        		k=client.read();
	        		int int_k=Integer.parseInt(k);//falseの数 MAX3
	        		for (i = 0; i < int_k ; i++){
	        			in2=client.read();
	        			if(in2.equals(id)){
	        				count+=1;//idと一緒だったらプラス1

	        			}
	        		}
	        		if(count==0){
	        			delete.setEnabled(false);
	        			check.setEnabled(false);
	        		}if(int_k==3){
	        			touroku.setEnabled(false);
	        		}

	        	}else if(s.equals("OUTPUT")){

	        		o1=client.read();//名前
	        		o2=client.read();//人数
	        		o3=client.read();//時間
	        		Thread.sleep(1000);
	        		//freeArea.insert(o1, freeArea.getLineEndOffset(1));
	        		//freeArea.insert(o2, freeArea.getLineStartOffset(2));
	        		//freeArea.insert(o3, freeArea.getLineEndOffset(3));

	        		freeArea.append((String.format("名前:%s様",o1)));
	    			freeArea.append("\n");
	    			freeArea.append((String.format("人数:%s名様",o2)));
	    			freeArea.append("\n");
	    			freeArea.append((String.format("時間:%s",o3)));

	        	}else {
	        		//Thread.sleep(1000);
	        		//freeArea.append("  "+s);

	        	}

	    	}
		} catch(Exception e) {
	    	if(thread != null) {
	        	System.out.println("受信中に例外が発生しました");
	        	e.printStackTrace(System.err);

	    	}
		}
	}

    public void clientClose() {
		try {
	    	if(client != null){
	    		client.close();
	    		client = null;
	    		thread = null;
	    	}
		} catch(Exception e) {
	    	System.out.println("切断時に何らかの例外が発生しました");
	    	e.printStackTrace(System.err);
		}
    }

    public void writeMes(String mes) {
		try {
	    	client.write(mes);
		} catch(Exception e) {
	    	System.out.println("送信中に何らかの例外が発生しました");
	    	e.printStackTrace(System.err);
		}
    }




}


