# HƯỚNG DẪN CHẠY DỰ ÁN

I. YÊU CẦU MÔI TRƯỜNG - Maven - JDK 1.8 trở lên - IDE Eclipse - MySQL Workbench
II. CÁCH CHẠY DỰ ÁN
    BƯỚC 1: clone source code từ repository này về
    BƯỚC 2: mở dự án bằng Eclipse hoặc Intellij, sau đó mở cửa sổ Terminal của dự án lên
    BƯỚC 3: click chuột phải vào dự án >> Show in Local Terminal >> Terminal
    BƯỚC 4: click chuột phải vào dự án >> Open in Terminal
    BƯỚC 5: tại cửa sổ Terminal gõ lệnh git checkout dev để chuyển sang nhánh dev (nhánh đang chứa source code tổng hợp của dự án)
    BƯỚC 6: click chuột phải vào file pom.xml >> Maven >> Update project
    BƯỚC 7: vào thư mục \cineverse_backend\src\main\resources\application.properties để cấu hình thông tin ứng dụng
    #spring.datasource.username=${username MySQL của bạn} 
	#spring.datasource.password=${password MySQL của bạn} 
    - ví dụ:#spring.datasource.username=root
            #spring.datasource.password=12345678
    BƯỚC 8: click chuột phải vào file src/main/java/com/fsoft/happflight/AirlineTicketSpringApiApplication.java >> Run as >> Java Application
    BƯỚC 9: mở file SQL cinever_database.sql (ở cùng cấp ở thư mục READ_ME này) và chạy bằng MySQL Workbench để insert dữ liệu vào database
