import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JToolBar;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.KeyAdapter;

public class WinMain extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel dtm;
	private JTextField tfPhone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			WinMain dialog = new WinMain();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public WinMain() {
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				ShowTable();
			}
			public void windowLostFocus(WindowEvent e) {
			}
		});		
		setTitle("학생 관리 프로그램");
		setBounds(100, 100, 914, 472);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				String header[] = {"학번","이름","전화번호","주민번호","이메일","파일경로","주소"};
				dtm = new DefaultTableModel(header, 0);
				
				JPopupMenu popupMenu = new JPopupMenu();
					
				
				table = new JTable(dtm);
				scrollPane.setViewportView(table);
				
				
				addPopup(table, popupMenu);
				{
					JMenuItem mnuStudentInsert = new JMenuItem("추가...");
					mnuStudentInsert.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							WinStudentInsert winStudentInsert = new WinStudentInsert();
							winStudentInsert.setModal(true);
							winStudentInsert.setVisible(true);
						}
					});
					popupMenu.add(mnuStudentInsert);
				}
				{
					JMenuItem mnuStudentSelect = new JMenuItem("조회...");
					mnuStudentSelect.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							WinSearchWord winSearchWord = new WinSearchWord();
							winSearchWord.setModal(true);
							winSearchWord.setVisible(true);
						}
					});
					popupMenu.add(mnuStudentSelect);
				}
				{
					JMenuItem mnuStudentDelete = new JMenuItem("삭제");
					mnuStudentDelete.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							int row = table.getSelectedRow();
							System.out.println("날라갈 값=>" + table.getValueAt(row, 0)); //ID
							WinStudentDelete winStudentDelete = 
									new WinStudentDelete(table.getValueAt(row, 0).toString(), false);
							winStudentDelete.setModal(true);
							winStudentDelete.setVisible(true);
						}
					});
					popupMenu.add(mnuStudentDelete);
				}
				{
					JMenuItem mnuStudentUpdate = new JMenuItem("변경");
					mnuStudentUpdate.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							int row = table.getSelectedRow();
							String sID = table.getValueAt(row, 0).toString();
							WinStudentUpdate winStudentUpdate = new WinStudentUpdate(sID);
							winStudentUpdate.setModal(true);
							winStudentUpdate.setVisible(true);
						}
					});
					popupMenu.add(mnuStudentUpdate);
				}
			}
		}
		{
			JToolBar toolBar = new JToolBar();
			toolBar.setFloatable(false);
			getContentPane().add(toolBar, BorderLayout.NORTH);
			{
				JButton btnStudentInsert = new JButton("");
				btnStudentInsert.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						WinStudentInsert winStudentInsert = new WinStudentInsert();
						winStudentInsert.setModal(true);
						winStudentInsert.setVisible(true);
					}					
				});
				btnStudentInsert.setIcon(new ImageIcon(WinMain.class.getResource("/images/memberAdd.png")));
				toolBar.add(btnStudentInsert);
			}
			{
				JButton btnStudentDelete = new JButton("");
				btnStudentDelete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(table.getSelectedRow() == -1) {
							String sName = JOptionPane.showInputDialog("삭제할 이름 입력하시오");
							WinLookup winLookup = new WinLookup(sName);
							winLookup.setModal(true);
							winLookup.setVisible(true);
						}else {
							int row = table.getSelectedRow();
							WinStudentDelete winStudentDelete = 
									new WinStudentDelete(table.getValueAt(row, 0).toString(), false);
							winStudentDelete.setModal(true);
							winStudentDelete.setVisible(true);
						}
					}
				});
				btnStudentDelete.setIcon(new ImageIcon(WinMain.class.getResource("/images/memberRemove.png")));
				toolBar.add(btnStudentDelete);
			}
			{
				JButton btnStudentUpdate = new JButton("");
				btnStudentUpdate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(table.getSelectedRow() == -1) {
							WinStudentUpdate winStudentUpdate = new WinStudentUpdate();
							winStudentUpdate.setModal(true);
							winStudentUpdate.setVisible(true);
						}else {
							int row = table.getSelectedRow();
							String sID = table.getValueAt(row, 0).toString();
							WinStudentUpdate winStudentUpdate = new WinStudentUpdate(sID);
							winStudentUpdate.setModal(true);
							winStudentUpdate.setVisible(true);
						}
					}
				});
				btnStudentUpdate.setIcon(new ImageIcon(WinMain.class.getResource("/images/memberUpdate.png")));
				toolBar.add(btnStudentUpdate);
			}
			{
				JButton btnStudentSelect = new JButton("");
				btnStudentSelect.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						WinSearchWord winSearchWord = new WinSearchWord();
						winSearchWord.setModal(true);
						winSearchWord.setVisible(true);
					}
				});
				btnStudentSelect.setIcon(new ImageIcon(WinMain.class.getResource("/images/memberSearch.png")));
				toolBar.add(btnStudentSelect);
			}
			{
				tfPhone = new JTextField();
				tfPhone.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) {
						if(e.getKeyCode() == KeyEvent.VK_ENTER) {
							String sNumber = tfPhone.getText();
							if(isInteger(sNumber)) { // 숫자인지 판별하시오.
								showLookupNumber(sNumber);
							}else {
								JOptionPane.showMessageDialog(null, sNumber + "는 숫자가 아니다");
							}
						}
					}
				});
				tfPhone.setFont(new Font("굴림", Font.BOLD, 30));
				toolBar.add(tfPhone);
				tfPhone.setColumns(10);
			}
		}
		{
			JMenuBar menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			{
				JMenu mnFile = new JMenu("파일(F)");
				mnFile.setMnemonic('F');
				menuBar.add(mnFile);
				{
					JMenuItem mnExit = new JMenuItem("종료");
					mnExit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							System.exit(DISPOSE_ON_CLOSE);
						}
					});
					mnExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.ALT_DOWN_MASK));
					mnFile.add(mnExit);
				}
			}
			{
				JMenu mnStudentInfo = new JMenu("학생정보(I)");
				mnStudentInfo.setMnemonic('I');
				menuBar.add(mnStudentInfo);
				{
					JMenuItem mnStudentInsert = new JMenuItem("학생정보 추가...");
					mnStudentInsert.setIcon(new ImageIcon(WinMain.class.getResource("/images/memberAdd_icon.png")));
					mnStudentInsert.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							WinStudentInsert winStudentInsert = new WinStudentInsert();
							winStudentInsert.setModal(true);
							winStudentInsert.setVisible(true);
						}
					});
					mnStudentInfo.add(mnStudentInsert);
				}
				{
					JMenuItem mnStudentDelete = new JMenuItem("학생정보 삭제...");
					mnStudentDelete.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String sName = JOptionPane.showInputDialog("삭제할 이름 입력하시오");
							WinLookup winLookup = new WinLookup(sName);
							winLookup.setModal(true);
							winLookup.setVisible(true);
						}
					});
					mnStudentInfo.add(mnStudentDelete);
				}
				{
					JMenuItem mnStudentUpdate = new JMenuItem("학생정보 변경...");
					mnStudentUpdate.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							WinStudentUpdate winStudentUpdate = new WinStudentUpdate();
							winStudentUpdate.setModal(true);
							winStudentUpdate.setVisible(true);
						}
					});
					mnStudentInfo.add(mnStudentUpdate);
				}
				{
					JMenuItem mnStudentSelect = new JMenuItem("학생정보 조회...");
					mnStudentSelect.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							WinSearchWord winSearchWord = new WinSearchWord();
							winSearchWord.setModal(true);
							winSearchWord.setVisible(true);
						}
					});
					mnStudentInfo.add(mnStudentSelect);
				}
			}
			{
				JMenu mnNewMenu = new JMenu("학생성적(S)");
				mnNewMenu.setMnemonic('S');
				menuBar.add(mnNewMenu);
				{
					JMenuItem mnScoreInsert = new JMenuItem("성적입력...");
					mnScoreInsert.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							WinInsertScore winInsertScore = new WinInsertScore();
							winInsertScore.setModal(true);
							winInsertScore.setVisible(true);
						}
					});
					mnNewMenu.add(mnScoreInsert);
				}
			}
		}
		//ShowTable(); // 컴포넌트를 모두 배치한 후에 테이블에서 레코드를 가져와 표에 넣는다.
	}
	
	protected void showLookupNumber(String sNumber) {
		dtm.setRowCount(0); // reset
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB", "root","1234");
			
			//=============================================	
			String sql="";
			PreparedStatement pstmt=null;
			
				sql = "select * from studentTBL where phone like ?";			
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, "%" + sNumber + "%");
			
			ResultSet rs = pstmt.executeQuery();			
			while(rs.next()) {  // 각 레코드의 필드들을 읽어서 벡터에 저장한 후, DefaultTableModel 에 추가한다.
				Vector<String> vector = new Vector<>();
				for(int i=1; i<=dtm.getColumnCount(); i++) {
					String temp = rs.getString(i);
					if(i == 4) {	// 주민번호 -으로 나눔
						String sJumin = temp.substring(0, 6);
						sJumin = sJumin + "-" + temp.substring(6);
						vector.add(sJumin);
					}else if(i == 3) { // 전화번호 010-1111-1234 표현되게 변경
						String sPhone = "";
						if(temp.substring(0,2).equals("02")) {							
							sPhone = temp.substring(0,2) + "-";
							sPhone = sPhone + temp.substring(2,temp.length()-4) + "-";
							sPhone = sPhone + temp.substring(temp.length()-4);
						}else {
							sPhone = temp.substring(0,3) + "-";
							sPhone = sPhone + temp.substring(3,temp.length()-4) + "-";
							sPhone = sPhone + temp.substring(temp.length()-4);
						}
						vector.add(sPhone);
					}
					else
						vector.add(temp);
				}
				dtm.addRow(vector);		
			}
			rs.close();		
			pstmt.close();
			con.close();
			//==============================================
		} catch (ClassNotFoundException e1) {
			System.out.println("JDBC 드라이버 로드 에러");
		} catch (SQLException e1) {
			System.out.println("DB 연결 오류");
		}	
		
	}

	protected boolean isInteger(String sNumber) {// 숫자면 true, 숫자가 아니면 false 반환하는 함수  isInteger		
		boolean bInt = true;
		for(int i=0;i<sNumber.length();i++) {
			if(sNumber.charAt(i) >= '0' && sNumber.charAt(i) <= '9')
				continue;
			else {
				bInt = false;
				break;
			}
		}
		return bInt;
	}

	protected void ShowTable() { // 실행이 되면 모든 레코드를 보여준다.
		dtm.setRowCount(0); // reset
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB", "root","1234");
			
			//=============================================		
			String sql = "select * from studentTBL";			
			PreparedStatement pstmt=con.prepareStatement(sql);			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {  // 각 레코드의 필드들을 읽어서 벡터에 저장한 후, DefaultTableModel 에 추가한다.
				Vector<String> vector = new Vector<>();
				
				for(int i=1; i<=dtm.getColumnCount(); i++) {
					String temp = rs.getString(i);
					if(i == 4) {	// 전화번호 010-1111-1234 표현되게 변경
						String sJumin = temp.substring(0, 6);
						sJumin = sJumin + "-" + temp.substring(6);
						vector.add(sJumin);
					}else if(i == 3) {
						String sPhone = "";
						if(temp.substring(0,2).equals("02")) {							
							sPhone = temp.substring(0,2) + "-";
							sPhone = sPhone + temp.substring(2,temp.length()-4) + "-";
							sPhone = sPhone + temp.substring(temp.length()-4);
						}else {
							sPhone = temp.substring(0,3) + "-";
							sPhone = sPhone + temp.substring(3,temp.length()-4) + "-";
							sPhone = sPhone + temp.substring(temp.length()-4);
						}
						vector.add(sPhone);
					}
					else
						vector.add(temp);
				}
				dtm.addRow(vector);				
			}		
			System.out.println(dtm.getRowCount());
			rs.close();		
			pstmt.close();
			con.close();
			//==============================================
		} catch (ClassNotFoundException e1) {
			System.out.println("JDBC 드라이버 로드 에러");
		} catch (SQLException e1) {
			System.out.println("DB 연결 오류");
		}	
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					JTable source = (JTable)e.getSource();
					int row = source.rowAtPoint(e.getPoint());
					int col = source.columnAtPoint(e.getPoint());
					if(!source.isRowSelected(row))
						source.changeSelection(row, col, false,false);					
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
