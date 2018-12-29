# Manage_Students_in_Domitories_Application_Using_Java_Swing
# Ứng dụng java swing quản lý sinh viên trong các nhà trọ, ký túc xá
## Yêu cầu
- Chương trình **java** viết trên IDE **eclise** và dữ liệu truy xuất từ **SQLServer**

## Cài đặt
- Đầu tiên, khởi chạy file `QuanLyNhaTro.SQL` trên **SQLServer** nhằm khởi tạo các quan hệ và dữ liệu
- Import Project vào eclipse và thay đổi QUAN TRỌNG TRÊN `src/com.mixicoding.manageapp.database/Database.java`:
  - Phương thức connect : `jdbc:sqlserver://localhost:1433;databaseName=QuanLyNhaTro;user=sa;password=sa`
  - Trong đó `1433` là **port** của SQLServer có lẽ bạn cần thay đổi 
  - *databaseName* nên giữ nguyên vì đã khởi tạo trên SQLServer
  - user là `sa` hoặc quản trị khác 
  - password là mật khẩu tài khoản khai báo ở trên
- Cấu hình thư viện / **configure build path** thêm file `sqljdbc4.jar` để có thư viện kết nối database

## Triển khai chương trình
- Run `Main.java` để thưởng thức ☺
___
## Prerequisites
- This is a **Java** program was writed on **eclise** IDE and data is stored on **SQLServer**

## Installing
- Firstly, excute `QuanLyNhaTro.SQL` to create relation and values
- Import my project into eclise and make ==IMPORTANT== changes on `src/com.mixicoding.manageapp.database/Database.java`:
  - connect method: `jdbc:sqlserver://localhost:1433;databaseName=QuanLyNhaTro;user=sa;password=sa`
  - You can change the **port** of SQLServer after 'localhost:' (`1433` is mine)
  - databaseName is `QuanLyNhaTro`
  - user is `sa` or another administrator account on our database
  - and you should have the password of this account ☺
- **configure build path** add file sqljdbc4.jar to have access database library

  ## Deployment
- Run "Main.java" and enjoy it☻
 ___
 # Authors
- Pham Xuan Anh
- Vu Hoang Minh Huy
- Vo Phi Khanh
