# Manage_Students_in_Domitories_Application_Using_Java

♠Chương trình java viết trên IDE eclise và dữ liệu truy xuất từ SQLServer
♠Đầu tiên, khởi chạy file "QuanLyNhaTro.SQL" trên SQLServer nhằm khởi tạo các quan hệ và dữ liệu
♠Import Project vào eclipse và thay đổi QUAN TRỌNG TRÊN "src/com.mixicoding.manageapp.database/Database.java":
  - Phương thức connect : jdbc:sqlserver://localhost:1433;databaseName=QuanLyNhaTro;user=sa;password=sa
  - Trong đó 1433 là port của SQLServer có lẽ bạn cần thay đổi 
  - databaseName nên giữ nguyên vì đã khởi tạo trên SQLServer
  - user là sa hoặc quản trị khác 
  - password là mật khẩu tài khoản khai báo ở trên
♠Khởi chạy "Main.java" để thưởng thức ☺


♥This is a Java program write on eclise IDE and data is stored on SQLServer
♥Firstly, excute "QuanLyNhaTro.SQL" to create relation and values
♥Import my project into eclise and make IMPORTANT changes on "src/com.mixicoding.manageapp.database/Database.java":
  - connect method: jdbc:sqlserver://localhost:1433;databaseName=QuanLyNhaTro;user=sa;password=sa
  - You can change the port of SQLServer after 'localhost:' (1433 is mine)
  - databaseName is QuanLyNhaTro
  - user is sa or another administrator account on our database
  - and you should have the password of this account ☺
 ♥Run "Main.java" and enjoy it☻
