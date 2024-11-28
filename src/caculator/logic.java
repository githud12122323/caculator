package caculator;

public class logic {
    private double num1;// số 1
    private double num2 ; // số 2
    private char operator ; // toán tử (+ - * /)

    // tạo contractor khởi tạo gt ban đầu
    public logic(double num1, double num2, char operator) {
        this.num1 = 0;
        this.num2 = 0;
        this.operator = ' ';
    }

    public logic() {

    }

    public void setNum2(double num2) {
        this.num2 = num2;
    }

    // phương thức để đặt số
    public void setNum1(double num1) {
        this.num1 = num1;

    }

    public void setOperator(char operator) {
        this.operator = operator;
    }
    // phương thức tính toán dựa trên toán tử
    public double tinhtoan(){
        switch (operator){
            case '+' : // phép cộng
                return num1 + num2;
            case '-' : // phép trừ
                return num1 - num2;
            case '*' :
                return num1*num2;
            case '/' :
                if (num2 != 0 ){
                    return num1/num2;
                }else {
                    throw new ArithmeticException("ko chia đc cho 0");
                }
            default:
                return 0; // trả về 0 nếu không có toán tử hợp le
        }
    }
}

