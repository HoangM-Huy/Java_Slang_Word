# Thông tin sinh viên:
1. Họ và tên: Hoàng Minh Huy
2. MSSV: 24127278
3. Lớp học phần: Lập trình ứng dụng Java 24KTPM1
4. Email: hmhuy2428@clc.fitus.edu.vn

# Java_Slang_Word
Một ứng dụng Java-Swing giúp người dùng tìm định nghĩa của 1 slang word hoặc ngược lại.

# Cách compile 
- Tạo 1 folder bin ngay kế bên folder src (Nếu chưa có).
- Compile chương trình (Các file .class sẽ được sinh ra trong folder bin):

javac -d bin src/Main.java src/model/*.java src/view/components/*.java src/controller/*.java src/view/panels/*.java src/view/*.java

# Cách chạy code trực tiếp (Sau khi đã compile):

java -cp bin Main

# Chạy trực tiếp bằng file jar:
java -jar Slang_Dictionary_App.jar

# Phần chấm điểm cho từng chức năng:
1. Chức năng tìm kiếm theo slang word: 10
2. Chức năng tìm kiếm theo definition, hiển thị ra tất cả các slang words mà trong defintion có chứa keyword gõ vào: 10
3. Chức năng hiển thị history, danh sách các slang word đã tìm kiếm: 10
4. Chức năng add 1 slang words mới. Nếu slang words trùng thì thông báo cho người dùng, confirm có overwrite hay duplicate ra 1 slang word mới: 10
5. Chức năng edit 1 slang word: 10
6. Chức năng delete 1 slang word. Confirm trước khi xoá: 10
7. Chức năng reset danh sách slang words gốc: 10
8. Chức năng random 1 slang word (On this day slang word): 10
9. Chức năng đố vui, chương trình hiển thị 1 random slang word, với 4 đáp án cho người dùng chọn: 10
10. Chức năng đố vui, chương trình hiển thị 1 definition, với 4 slang words đáp án cho người dùng chọn: 10

=> Điểm tổng: 100

# Link video youtube:
https://www.youtube.com/watch?v=vwg_xtr-lMc

# Link gitHub:
https://github.com/HoangM-Huy/Java_Slang_Word


