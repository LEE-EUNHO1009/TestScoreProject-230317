import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class WinStudentUpdate extends JDialog {
	private JTextField tfID;
	private JTextField tfName;
	private JTextField tfPhoneNum2;
	private JTextField tfJumin1;
	private JTextField tfEmail;
	private JTextField tfAddress;
	private JTextField tfPhoneNum1;

	private JLabel lblDept;
	private JButton btnSelect;
	private JComboBox cbLocal;
	private JComboBox cbEmailCompany;
	protected String fileName;
	private JPasswordField tfJumin2;
	private JLabel lblPic;
	private JButton btnUpdate;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinStudentUpdate dialog = new WinStudentUpdate();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public WinStudentUpdate() {
		setTitle("학생 정보 변경창");
		setBounds(100, 100, 662, 296);
		getContentPane().setLayout(null);
		
		lblPic = new JLabel("");
		lblPic.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) { // 더블클릭하면...
					JFileChooser chooser = new JFileChooser();
					FileNameExtensionFilter filter = 
							new FileNameExtensionFilter("그림파일","png","bmp","gif","jpg");
					chooser.setFileFilter(filter);					
					int ret = chooser.showOpenDialog(null);
					if( ret == JFileChooser.APPROVE_OPTION ) {
						fileName = chooser.getSelectedFile().getPath();
						ImageIcon icon = new ImageIcon(fileName);
						Image img = icon.getImage();
						img = img.getScaledInstance(150, 180, Image.SCALE_SMOOTH);
						ImageIcon image = new ImageIcon(img);
						lblPic.setIcon(image);
					}	
				}
			}
		});
		lblPic.setBackground(new Color(255, 255, 0));
		lblPic.setOpaque(true);
		lblPic.setToolTipText("더블클릭하여 선택하시오.");
		lblPic.setBounds(12, 25, 150, 180);
		getContentPane().add(lblPic);
		
		JLabel lblID = new JLabel("학번(ID):");
		lblID.setBounds(217, 31, 57, 15);
		getContentPane().add(lblID);
		
		tfID = new JTextField();
		tfID.setEnabled(false);
		tfID.setBackground(new Color(0, 255, 255));		
		tfID.setBounds(305, 25, 116, 21);
		getContentPane().add(tfID);
		tfID.setColumns(10);
		
		JLabel lblName = new JLabel("이름:");
		lblName.setBounds(217, 65, 57, 15);
		getContentPane().add(lblName);
		
		tfName = new JTextField();
		tfName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tfName.setSelectionStart(0);
				tfName.setSelectionEnd(tfName.getText().length());
			}
		});
		tfName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					tfPhoneNum1.requestFocus();
				}
			}
		});
		tfName.setColumns(10);
		tfName.setBounds(305, 59, 116, 21);
		getContentPane().add(tfName);
		
		JLabel lblPhone = new JLabel("전화번호:");
		lblPhone.setBounds(217, 96, 57, 15);
		getContentPane().add(lblPhone);
		
		tfPhoneNum2 = new JTextField();
		tfPhoneNum2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tfPhoneNum2.setSelectionStart(0);
				tfPhoneNum2.setSelectionEnd(tfPhoneNum2.getText().length());
			}
		});
		tfPhoneNum2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					String temp = tfPhoneNum2.getText();
					if(temp.length() == 4 && isInteger(temp))
						tfJumin1.requestFocus();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				String temp = tfPhoneNum2.getText();
				if(temp.length() == 4 && isInteger(temp))
					tfJumin1.requestFocus();
			}
		});
		tfPhoneNum2.setBackground(new Color(0, 255, 255));
		tfPhoneNum2.setColumns(10);
		tfPhoneNum2.setBounds(515, 93, 93, 21);
		getContentPane().add(tfPhoneNum2);
		
		JLabel lblJumin = new JLabel("주민번호:");
		lblJumin.setBounds(217, 130, 57, 15);
		getContentPane().add(lblJumin);
		
		tfJumin1 = new JTextField();
		tfJumin1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tfJumin1.setSelectionStart(0);
				tfJumin1.setSelectionEnd(tfJumin1.getText().length());
			}
		});
		tfJumin1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					String temp = tfJumin1.getText();
					if(temp.length() == 6 && isInteger(temp))
						tfJumin2.requestFocus();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				String temp = tfJumin1.getText();
				if(temp.length() == 6 && isInteger(temp))
					tfJumin2.requestFocus();
			}
		});
		tfJumin1.setBackground(new Color(0, 255, 255));
		tfJumin1.setColumns(10);
		tfJumin1.setBounds(305, 124, 116, 21);
		getContentPane().add(tfJumin1);
		
		JLabel lblEmail = new JLabel("이메일:");
		lblEmail.setBounds(217, 166, 57, 15);
		getContentPane().add(lblEmail);
		
		tfEmail = new JTextField();
		tfEmail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tfEmail.setSelectionStart(0);
				tfEmail.setSelectionEnd(tfEmail.getText().length());
			}
		});
		tfEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {					
					cbEmailCompany.requestFocus();
				}
			}
		});
		tfEmail.setColumns(10);
		tfEmail.setBounds(305, 160, 116, 21);
		getContentPane().add(tfEmail);
		
		JLabel lblAddress = new JLabel("주소:");
		lblAddress.setBounds(12, 222, 39, 15);
		getContentPane().add(lblAddress);
		
		tfAddress = new JTextField();
		tfAddress.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tfAddress.setSelectionStart(0);
				tfAddress.setSelectionEnd(tfAddress.getText().length());
			}
		});
		tfAddress.setColumns(10);
		tfAddress.setBounds(47, 219, 374, 21);
		getContentPane().add(tfAddress);
		
		tfPhoneNum1 = new JTextField();
		tfPhoneNum1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tfPhoneNum1.setSelectionStart(0);
				tfPhoneNum1.setSelectionEnd(tfPhoneNum1.getText().length());
			}
		});
		tfPhoneNum1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					String temp = tfPhoneNum1.getText();
					if(temp.length() >=3 && temp.length() <= 4 && isInteger(temp))
						tfPhoneNum2.requestFocus();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				String temp = tfPhoneNum1.getText();
				if(temp.length() == 4 && isInteger(temp))
					tfPhoneNum2.requestFocus();
			}
		});
		tfPhoneNum1.setBackground(new Color(0, 255, 255));
		tfPhoneNum1.setColumns(10);
		tfPhoneNum1.setBounds(410, 93, 93, 21);
		getContentPane().add(tfPhoneNum1);
		
		cbLocal = new JComboBox();
		cbLocal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfPhoneNum1.requestFocus();
			}
		});
		cbLocal.setModel(new DefaultComboBoxModel(new String[] {"010", "02", "032", "031"}));
		cbLocal.setBounds(305, 92, 93, 23);
		getContentPane().add(cbLocal);
		
		JLabel lblAt = new JLabel("@");
		lblAt.setBounds(424, 166, 12, 15);
		getContentPane().add(lblAt);
		
		cbEmailCompany = new JComboBox();
		cbEmailCompany.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					tfAddress.requestFocus();
			}
		});
		cbEmailCompany.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!cbEmailCompany.getSelectedItem().equals("직접 입력"))
					tfAddress.requestFocus();
				else {
					cbEmailCompany.setSelectedItem("");
					cbEmailCompany.requestFocus();
				}
					
			}
		});
		cbEmailCompany.setEditable(true);
		cbEmailCompany.setModel(new DefaultComboBoxModel(new String[] {"직접 입력", "gmail.com", "daum.net", "naver.com", "ici.or.kr"}));
		cbEmailCompany.setBounds(443, 159, 175, 23);
		getContentPane().add(cbEmailCompany);
		
		JLabel lblLine = new JLabel("-");
		lblLine.setBounds(426, 129, 12, 15);
		getContentPane().add(lblLine);
		
		JLabel lblLine_1 = new JLabel("-");
		lblLine_1.setBounds(400, 96, 12, 15);
		getContentPane().add(lblLine_1);
		
		JLabel lblLine_1_1 = new JLabel("-");
		lblLine_1_1.setBounds(504, 96, 12, 15);
		getContentPane().add(lblLine_1_1);
		
		btnUpdate = new JButton("학생 정보 변경");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateStudentInfomation();
			}
		});
		btnUpdate.setBounds(443, 194, 175, 46);
		getContentPane().add(btnUpdate);
		
		btnSelect = new JButton("조회...");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfID.setText(JOptionPane.showInputDialog("학번(ID) 입력"));
				showStudentInfomation(); // 해당 학번의 정보를 조회한다.
			}
		});
		btnSelect.setBounds(443, 24, 97, 23);
		getContentPane().add(btnSelect);
		
		lblDept = new JLabel("학과:");
		lblDept.setForeground(new Color(255, 0, 0));
		lblDept.setFont(new Font("굴림", Font.BOLD, 14));
		lblDept.setBounds(446, 59, 162, 21);
		getContentPane().add(lblDept);
		
		tfJumin2 = new JPasswordField();
		tfJumin2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tfJumin2.setSelectionStart(0);
				tfJumin2.setSelectionEnd(new String(tfJumin2.getPassword()).length());
			}
		});
		tfJumin2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String temp = new String(tfJumin2.getPassword()); // String class 생성자를 확인할 것
				if(temp.length() == 7 && isInteger(temp))
					tfEmail.requestFocus();				
			}
		});
		tfJumin2.setBackground(new Color(0, 255, 255));
		tfJumin2.setBounds(443, 124, 136, 21);
		getContentPane().add(tfJumin2);

		
		disableComponent();
	}
	public WinStudentUpdate(String sID) {
		// TODO Auto-generated constructor stub
		this();
		tfID.setText(sID);
		showStudentInfomation(); //해당 학번의 정보를 조회한다.
	}

	private void disableComponent() {
		lblPic.setEnabled(false);
		tfName.setEnabled(false);
		cbLocal.setEnabled(false);
		tfPhoneNum1.setEnabled(false);	
		tfPhoneNum2.setEnabled(false);
		tfJumin1.setEnabled(false);
		tfJumin2.setEnabled(false);
		tfEmail.setEnabled(false);
		cbEmailCompany.setEnabled(false);
		tfAddress.setEnabled(false);
		btnUpdate.setEnabled(false);
	}

	protected void showStudentInfomation() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB", "root","1234");
			//=============================================		
			Statement stmt = con.createStatement();
			String sql = "select * from studentTBL where id='" + tfID.getText() + "'";
			System.out.println(sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			String record[] = new String[6];
			if(rs.next()) {
				enableComponent();
				
				for(int i=0;i<record.length;i++) {
					record[i] = rs.getString(i+2); // name부터 가져온다
					System.out.println(record[i]);
				}
				tfName.setText(record[0]);
				
				String sPhone = record[1];
				if(sPhone.substring(0,2).equals("02")) {
					cbLocal.setSelectedItem(sPhone.substring(0,2));
					tfPhoneNum1.setText(sPhone.substring(2,sPhone.length()-4));
				}else {
					cbLocal.setSelectedItem(sPhone.substring(0,3));
					tfPhoneNum1.setText(sPhone.substring(3,sPhone.length()-4));
				}
				tfPhoneNum2.setText(record[1].substring(record[1].length()-4));	
				
				tfJumin1.setText(record[2].substring(0,6)); // 주민번호 앞글자는 6글자
				tfJumin2.setText(record[2].substring(6));  // 주민번호 뒷글자의 시작은 6번부터
				
				tfEmail.setText(record[3].substring(0, record[3].indexOf("@")));
				cbEmailCompany.setSelectedItem(record[3].substring(record[3].indexOf("@")+1));	
				
				tfAddress.setText(record[5]);
				
				fileName = record[4];
				
				ImageIcon icon = new ImageIcon(fileName);
				Image img = icon.getImage();
				img = img.getScaledInstance(150, 180, Image.SCALE_SMOOTH);
				ImageIcon image = new ImageIcon(img);
				lblPic.setIcon(image);
				
			}
			rs.close();
			stmt.close();
			con.close();
			//==============================================
		} catch (ClassNotFoundException e1) {
			System.out.println("JDBC 드라이버 로드 에러");
		} catch (SQLException e1) {
			System.out.println("DB 연결 오류");
		}		
	}

	private void enableComponent() {
		lblPic.setEnabled(true);
		tfName.setEnabled(true);
		cbLocal.setEnabled(true);
		tfPhoneNum1.setEnabled(true);	
		tfPhoneNum2.setEnabled(true);
		tfJumin1.setEnabled(true);
		tfJumin2.setEnabled(true);
		tfEmail.setEnabled(true);
		cbEmailCompany.setEnabled(true);
		tfAddress.setEnabled(true);
		btnUpdate.setEnabled(true);
	}

	protected void UpdateStudentInfomation() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB", "root","1234");
			//=============================================		
			String sql = "update studentTBL set name=?,phone=?,jumin=?,email=?,pic=?,address=?";				
			sql = sql + " where id=?";
			PreparedStatement pstmt=con.prepareStatement(sql);			
			
			pstmt.setString(1, tfName.getText());
			pstmt.setString(2, cbLocal.getSelectedItem() + tfPhoneNum1.getText() + tfPhoneNum2.getText());
			pstmt.setString(3, tfJumin1.getText() + new String(tfJumin2.getPassword()));
			pstmt.setString(4, tfEmail.getText()+"@"+cbEmailCompany.getSelectedItem());
			pstmt.setString(5, fileName); // 이미지
			pstmt.setString(6, tfAddress.getText());	
			pstmt.setString(7, tfID.getText());
			
			pstmt.executeUpdate();								
			pstmt.close();
			con.close();
			
			// 텍스트필드만 공백으로 변경하기
			Component [] componentList = getContentPane().getComponents();
			for(Component c : componentList) {
				if(c instanceof JTextField)
					((JTextField) c).setText("");
			}
			cbLocal.setSelectedIndex(0);  // 010
			cbEmailCompany.setSelectedIndex(1); //직접 입력
			
			disableComponent();
			
			// 비활성화시키기 때문에 포커스를 줄 필요가 없다. 그래서 버튼으로 포커스 변경
			btnSelect.requestFocus();
			//==============================================
		} catch (ClassNotFoundException e1) {
			System.out.println("JDBC 드라이버 로드 에러");
		} catch (SQLException e1) {
			System.out.println("DB 연결 오류");
		}			
	}

	public static boolean isInteger(String strValue) {
	    try {
	      Integer.parseInt(strValue);
	      return true;
	    } catch (NumberFormatException ex) {
	      return false;
	    }
	}
}
