package giaodien;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import CuaHang.CuaHangGUI;
import HoaDon.HoaDonGUI;
import KhachHang.KhachHangGUI;
import NhaCungCap.NhaCungCapGUI;
import NhanVien.NhanVienGUI;
import NhapHang.NhapHangGUI;
import SanPham.SanPhamGUI;
import TaiKhoan.TaiKhoanGUI;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.StackWalker.Option;
import java.util.concurrent.CancellationException;



public class giaodienchinh extends JFrame {
private int height = 700;
private int width = 1300;
private JList<LeftMenuItem> list;
private BorderLayout mainLayout = new BorderLayout(0,0);

private JPanel content;
private JPanel top;
private JPanel left;

private JLabel luachon;
private JLabel donhangOut=new JLabel();
private JLabel donhangIn =new JLabel();

private JLabel logout;

private JPanel cuahang;
private JPanel nhaphang;
private JPanel nhanvien;
private JPanel sanpham;
private JPanel nhacungcap;
private JPanel khachhang;
private JPanel hoadon;
private JPanel taikhoan;


public giaodienchinh() {
	
	//setLayout(mainLayout);
	setSize(width,height);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	top =CreateTopPanel();
	
	left = CreateLeftPanel();
	
	content = new JPanel();
	content.setLayout(new BorderLayout());
	
	
	
	JPanel container = new JPanel();
	container.setLayout(mainLayout);
	container.add(top,BorderLayout.NORTH);
	container.add(left,BorderLayout.WEST);
	container.add(content,BorderLayout.CENTER);
	container.setBorder(new EmptyBorder(0,10,10,10));
	add(container);
	setVisible(true);
	/*
	//thiet lap giua man hin
	//setLocationRelativeTo(null);
	setVisible(true);
	
	//khoa thay doi kich thuoc
	//setResizable(false);
	//full man hinh
	//setExtendedState(JFrame.MAXIMIZED_BOTH);
	*/
}

	public JPanel CreateTopPanel() {
		JPanel panel = new JPanel(); //panel chinh	
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 18);
		panel.setLayout(new BorderLayout());
		
		ImageIcon iconOut = new ImageIcon();// icon dangxuat
		
		

		iconOut=new ImageIcon(getClass().getResource("/images/logout.png"));
		logout = new JLabel("Tri???u",iconOut,JLabel.CENTER);
		logout.setFont(font);
		
		//tennguoidung = new JLabel("")
		//dangxuat.add(logout);
		//dangxuat.add(tennguoidung);
		logout.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
                
                if(JOptionPane.showConfirmDialog(content, "B???n c?? mu???n ????ng xu???t?","Ch?? ??",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
                	System.out.println("????ng xu???t");
                }
            }
		});
		
		luachon = new JLabel("", SwingConstants.CENTER);
		
		luachon.setFont(font);
		panel.add(logout,BorderLayout.WEST);
		panel.add(luachon,BorderLayout.CENTER);
		
		
		panel.setBorder(new EmptyBorder(10, 10, 10, 30));
		return panel;
	}
	private void addDonHangOut(JPanel top) {
		ImageIcon iconGH = new ImageIcon();
		iconGH = new ImageIcon(getClass().getResource("/images/shopping.png"));
		donhangOut = new JLabel("",iconGH,JLabel.CENTER);
		donhangOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				//????n h??ng
				JOptionPane.showMessageDialog(content, "Gio hang");
			}
		});
		top.add(donhangOut,BorderLayout.EAST);
	}
	private void addDonHangIn(JPanel top) {
		ImageIcon iconIn = new ImageIcon();
		iconIn = new ImageIcon(getClass().getResource("/images/import.png"));
		donhangIn = new JLabel ("",iconIn,JLabel.CENTER);
		donhangIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				JOptionPane.showMessageDialog(content, "don hang in");
			}
		});
		top.add(donhangIn,BorderLayout.EAST);
	}
	public JPanel CreateLeftPanel() {
		
		JPanel panel = new JPanel(new BorderLayout());
		JScrollPane sp = new JScrollPane(list = createList());
		panel.add(sp,BorderLayout.CENTER);
		return panel;
	}
	
	private JList<LeftMenuItem> createList() {
		DefaultListModel<LeftMenuItem> model = new DefaultListModel<>();
		model.addElement(new LeftMenuItem("C???a h??ng","shop"));
		model.addElement(new LeftMenuItem("Nh???p h??ng","checklist"));
		model.addElement(new LeftMenuItem("S???n ph???m","watch"));
		model.addElement(new LeftMenuItem("H??a ????n","bill"));
		model.addElement(new LeftMenuItem("Nh??n vi??n","employee"));
		model.addElement(new LeftMenuItem("Kh??ch h??ng","user"));
		model.addElement(new LeftMenuItem("Nh?? cung c???p","company"));
		model.addElement(new LeftMenuItem("T??i kho???n","account"));
		
		list = new JList<LeftMenuItem>(model);
		list.setCellRenderer(new LeftMenuItemRenderer());
		
		
		
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				LeftSelec();
			}
		});
		return list;
	}
	private void LeftSelec() {
		String nameAction=String.valueOf(list.getSelectedValue());
		//JOptionPane.showConfirmDialog(this, nameAction);
		content.removeAll();
		top.remove(donhangIn);
		top.remove(donhangOut);
		
		switch(nameAction){
			case "C???a h??ng":{
				luachon.setText("C???A H??NG");
				addDonHangOut(top);		//add logo gio hang
				
				cuahang = new CuaHangGUI();
				content.add(cuahang);
				break;
			}
			case "H??a ????n":{
				luachon.setText("H??A ????N");
				hoadon = new HoaDonGUI();
				content.add(hoadon);
				break;
			}
			case "S???n ph???m":{
				luachon.setText("S???N PH???M");
				sanpham= new SanPhamGUI();
				content.add(sanpham);
				break;
			}
			case "Nh???p h??ng":{
				addDonHangIn(top);		//add logo nhap hang
				luachon.setText("NH???P H??NG");
				
				nhaphang = new NhapHangGUI();
				content.add(nhaphang);
				break;
			}
			case "Nh??n vi??n":{
				luachon.setText("NH??N VI??N");
				
				nhanvien = new NhanVienGUI();
				content.add(nhanvien);
				break;
			}
			case "Kh??ch h??ng":{
				luachon.setText("KH??CH H??NG");
				khachhang = new KhachHangGUI();
				content.add(khachhang);
				break;
			}
			case "Nh?? cung c???p":{
				luachon.setText("NH?? CUNG C???P");
				nhacungcap = new NhaCungCapGUI();
				content.add(nhacungcap);
				break;
			}
			case "T??i kho???n":{
				luachon.setText("T??I KHO???N");
				taikhoan = new TaiKhoanGUI();
				content.add(taikhoan);
				break;
			}
		}
		
		revalidate();
		repaint();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 new giaodienchinh() ;
		 
		
	}

}
