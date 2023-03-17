import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Font;

public class WinFile2DB extends JDialog {

   private final JPanel contentPanel = new JPanel();
   private JTextArea textArea;
   
   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      try {
         WinFile2DB dialog = new WinFile2DB();
         dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
         dialog.setVisible(true);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   /**
    * Create the dialog.
    */
   public WinFile2DB() {
      setTitle("address.csv -> addressTBL table로");
      setBounds(100, 100, 450, 300);
      getContentPane().setLayout(new BorderLayout());
      contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
      getContentPane().add(contentPanel, BorderLayout.CENTER);
      contentPanel.setLayout(new BorderLayout(0, 0));
      {
         JScrollPane scrollPane = new JScrollPane();
         contentPanel.add(scrollPane, BorderLayout.CENTER);
         {
            textArea = new JTextArea();
            textArea.setFont(new Font("굴림", Font.PLAIN, 15));
            scrollPane.setViewportView(textArea);
            {
               JPopupMenu popupMenu = new JPopupMenu();
               addPopup(textArea, popupMenu);
               {
                  JMenuItem mnuFontSize10 = new JMenuItem("10");
                  mnuFontSize10.addActionListener(new ActionListener() {
                     public void actionPerformed(ActionEvent e) {
                        textArea.setFont(new Font(textArea.getFont().getFamily(), Font.PLAIN, 10));
                     }
                  });
                  popupMenu.add(mnuFontSize10);
               }
               {
                  JMenuItem mnuFontSize20 = new JMenuItem("20");
                  mnuFontSize20.addActionListener(new ActionListener() {
                     public void actionPerformed(ActionEvent e) {
                        textArea.setFont(new Font(textArea.getFont().getFamily(), Font.PLAIN, 20));
                     }
                  });
                  popupMenu.add(mnuFontSize20);
               }
               {
                  JMenuItem mnuFontSize30 = new JMenuItem("30");
                  mnuFontSize30.addActionListener(new ActionListener() {
                     public void actionPerformed(ActionEvent e) {
                        textArea.setFont(new Font(textArea.getFont().getFamily(), Font.PLAIN, 30));
                     }
                  });
                  popupMenu.add(mnuFontSize30);
               }
            }
         }
      }
      {
         JPanel buttonPane = new JPanel();
         {
            JPopupMenu popupMenu = new JPopupMenu();
            addPopup(buttonPane, popupMenu);
            {
               JMenuItem mnuFontNameNanum = new JMenuItem("나눔");
               mnuFontNameNanum.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                     textArea.setFont(new Font("나눔고딕", Font.PLAIN, textArea.getFont().getSize()));
                  }
               });
               popupMenu.add(mnuFontNameNanum);
            }
            {
               JMenuItem mnuFontNameGulim = new JMenuItem("굴림");
               mnuFontNameGulim.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                     textArea.setFont(new Font("굴림", Font.PLAIN, textArea.getFont().getSize()));
                  }
               });
               popupMenu.add(mnuFontNameGulim);
            }
            {
               JMenuItem mnuFontNameGungseo = new JMenuItem("궁서");
               mnuFontNameGungseo.addActionListener(new ActionListener() {
                  
                  @Override
                  public void actionPerformed(ActionEvent e) {
                     textArea.setFont(new Font("궁서", Font.PLAIN, textArea.getFont().getSize()));
                  }
               });
               popupMenu.add(mnuFontNameGungseo);
            }
         }
         buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
         getContentPane().add(buttonPane, BorderLayout.SOUTH);
         {
            JButton okButton = new JButton("Read");
            okButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                  // D:\\FileIO\\address.csv 를 읽어온다.
                  
               }
            });
            okButton.setActionCommand("OK");
            buttonPane.add(okButton);
            getRootPane().setDefaultButton(okButton);
            {
               JPopupMenu popupMenu = new JPopupMenu();
               addPopup(okButton, popupMenu);
               {
                  JMenuItem mnuRed = new JMenuItem("Red");
                  mnuRed.addActionListener(new ActionListener() {
                     public void actionPerformed(ActionEvent e) {
                        textArea.setBackground(Color.RED);
                     }
                  });
                  popupMenu.add(mnuRed);
               }
               {
                  JMenuItem mnuGreen = new JMenuItem("Green");
                  mnuGreen.addActionListener(new ActionListener() {
                     public void actionPerformed(ActionEvent e) {
                        textArea.setBackground(Color.GREEN);
                     }
                  });
                  popupMenu.add(mnuGreen);
               }
               {
                  JMenuItem mnuBlue = new JMenuItem("Blue");
                  mnuBlue.addActionListener(new ActionListener() {
                     
                     @Override
                     public void actionPerformed(ActionEvent e) {
                        textArea.setBackground(Color.BLUE);
                     }
                  });
                  popupMenu.add(mnuBlue);
               }
            }
         }
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
               showMenu(e);
            }
         }
         private void showMenu(MouseEvent e) {
            popup.show(e.getComponent(), e.getX(), e.getY());
         }
      });
   }
}