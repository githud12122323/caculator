package caculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class giaodien implements ActionListener {
    private JFrame frame; // tạo frame
    private JTextField textField; // mở màn hình hiển thị
    private JButton[] numberbuttons = new JButton[10];
    private JButton[] funtionbutton = new JButton[8];// mảng chức năg ( + - * / = dec ac .)
    private JButton congbutton, trubutton, nhanbutton, chiabutton; // nút phép toán
    private JButton decbutton, equbutton, delbutton, clrbutton;// nút chức năng
    private JPanel panel;// panel chức các nút


    private logic logic;//đối tượng xử lý logic
    private Font font = new Font("Arial", Font.BOLD, 20);
    private double currennumber = 0; // lưu số hiện tại


    // contructor xây dựng giao diện
    public giaodien() {
        logic = new logic(); // khởi tạo đối tượng logic


        // cửa sổ chính
        frame = new JFrame("máy tính của truyền"); // tên frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLayout(null);// ko sử dụng bố cục mặc định


        // tạo màn hình hiển thịư
        textField = new JTextField();
        textField.setBounds(50, 40, 300, 50); // vị trí và kích thước
        textField.setFont(font); //  đặt phông chữ
        textField.setEditable(false); // khoobg cho phép nhập tay
        frame.add(textField); // thêm vào cửa sổ


        // nút chức năng
        congbutton = new JButton("+");
        trubutton = new JButton("-");
        nhanbutton = new JButton("*");
        chiabutton = new JButton("/");
        decbutton = new JButton(".");
        delbutton = new JButton("DEL");
        clrbutton = new JButton("C");
        equbutton = new JButton("=");

        // đưa các nút vào mảng chức năng
        funtionbutton[0] = congbutton;
        funtionbutton[1] = trubutton;
        funtionbutton[2] = nhanbutton;
        funtionbutton[3] = chiabutton;
        funtionbutton[4] = decbutton;
        funtionbutton[5] = equbutton;
        funtionbutton[6] = delbutton;
        funtionbutton[7] = clrbutton;

        // thêm sự kiện định dạng cho các nút chức năng
        for (JButton button : funtionbutton) {
            button.addActionListener(this);// lắng nghe sự kiện
            button.setFont(font);// đặt phông chữ
            button.setForeground(Color.green); // màu viền

        }
        // tạo các nút số ( 0 - 9 )
        for (int i = 0; i < 10; i++) {
            numberbuttons[i] = new JButton(String.valueOf(i)); // hiển thị số
            numberbuttons[i].addActionListener(this);// lắng nghe sự kiện
            numberbuttons[i].setFont(font); // đặt phông chữ
            numberbuttons[i].setFocusable(false); // tắt đường viền khi chọn

        }


        // tạo pannel chứa các nứt
        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300); // vị trí và kích thước
        panel.setLayout(new GridLayout(4, 4, 10, 10));// bố cục dạng luới vs khoảng cách 10 px
        // Thêm nút vào panel
        panel.add(numberbuttons[1]);
        panel.add(numberbuttons[2]);
        panel.add(numberbuttons[3]);
        panel.add(congbutton);
        panel.add(numberbuttons[4]);
        panel.add(numberbuttons[5]);
        panel.add(numberbuttons[6]);
        panel.add(trubutton);
        panel.add(numberbuttons[7]);
        panel.add(numberbuttons[8]);
        panel.add(numberbuttons[9]);
        panel.add(nhanbutton);
        panel.add(decbutton);
        panel.add(numberbuttons[0]);
        panel.add(equbutton);
        panel.add(chiabutton);

        // tạo del và c bên ngoài pannel
        delbutton.setBounds(50, 430, 145, 50);// vị trí và kiích thước
        clrbutton.setBounds(205, 430, 145, 50); // vị trí và kích thước
        frame.add(delbutton);
        frame.add(clrbutton);


        // tạo icon
        ImageIcon image = new ImageIcon("img.png");// thêm annh logo
        frame.setIconImage(image.getImage()); // thay đổi icon của frame

        frame.add(panel);
        frame.setVisible(true);// hiển thị
    }

    // xử lý khi người dùng nhấn nút
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberbuttons[i]) {// nếu nhấn nút số
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource() == decbutton) { // nếu nhấn dấu thập phân
            textField.setText(textField.getText().concat("."));
        }
        if (e.getSource() == congbutton) { // nút cộng
            logic.setNum1(Double.parseDouble(textField.getText())); // lưu số đầu
            logic.setOperator('+');
            textField.setText(""); // xóa màn hình

        }
        if (e.getSource() == trubutton) { // nút cộng
            logic.setNum1(Double.parseDouble(textField.getText())); // lưu số đầu
            logic.setOperator('-');
            textField.setText(""); // xóa màn hình
        }
        if (e.getSource() == nhanbutton) { // nút cộng
            logic.setNum1(Double.parseDouble(textField.getText())); // lưu số đầu
            logic.setOperator('*');
            textField.setText(""); // xóa màn hình
        }
        if (e.getSource() == chiabutton) { // nút cộng
            logic.setNum1(Double.parseDouble(textField.getText())); // lưu số đầu
            logic.setOperator('/');
            textField.setText(""); // xóa màn hình
        }
        // các nút cộng trừ nhân chia
        if (e.getSource() == equbutton) { // nếu nhấn "="
            logic.setNum2(Double.parseDouble(textField.getText())); // lưu số thứ hai
            double result = logic.tinhtoan(); // tính toán kết quả
            textField.setText(String.valueOf(result)); // hiển thị kết quả
        }
        if(e.getSource()== clrbutton){ // nếu nhấn c
            textField.setText(""); // xóa màn hình

        }
        if (e.getSource() == delbutton) {// nếu nhấn del
            String text = textField.getText();
            textField.setText(text.isEmpty()? "" : text.substring(0,text.length()-1));// xóa kí tự cuối
        }
    }

}