# Dự án Website Bán Laptop với Spring Boot

## Giới thiệu

Dự án này là một website bán laptop được phát triển bằng **Spring Boot**, áp dụng mô hình **MVC (Model-View-Controller)** và tích hợp nhiều công nghệ hiện đại trong hệ sinh thái Spring như **Spring Data JPA**, **Spring Security**, và **JSP** để xây dựng giao diện. Mục tiêu của dự án là cung cấp một nền tảng cho phép người dùng xem danh sách sản phẩm, chi tiết sản phẩm, tìm kiếm, quản lý giỏ hàng, và thực hiện thanh toán. Dự án cũng hỗ trợ phân quyền người dùng (guest, user, admin) và tích hợp các tính năng bảo mật, phân trang, lọc dữ liệu.

Dự án được thiết kế để học tập và thực hành các khái niệm cốt lõi của Spring Framework, đồng thời mô phỏng một website thương mại điện tử thực tế như [FPT Shop](https://fptshop.com.vn/may-tinh-xach-tay).

## Các tính năng chính

### Đối với người dùng chưa đăng nhập (Guest)

-   Xem danh sách sản phẩm trên trang chủ.
-   Xem chi tiết sản phẩm.
-   Tìm kiếm sản phẩm theo tiêu chí (giá, hãng sản xuất, ...).

### Đối với người dùng đã đăng nhập (User)

-   Kế thừa các tính năng của Guest.
-   Quản lý giỏ hàng.
-   Đặt hàng và xem lịch sử mua hàng.

### Đối với quản trị viên (Admin)

-   Kế thừa các tính năng của User.
-   Quản lý toàn bộ dữ liệu hệ thống (thêm, sửa, xóa sản phẩm, người dùng, đơn hàng, ...).

### Các tính năng kỹ thuật

-   **Phân trang (Pagination):** Tối ưu hóa truy vấn dữ liệu với phân trang sử dụng `Pageable` của Spring Data.
-   **Lọc dữ liệu (Filtering):** Sử dụng JPA Specifications để tìm kiếm sản phẩm theo nhiều tiêu chí (tên, giá, hãng sản xuất, ...).
-   **Bảo mật:** Tích hợp **Spring Security** để quản lý đăng nhập, phân quyền (ROLE_USER, ROLE_ADMIN), và bảo vệ chống tấn công CSRF, CORS.
-   **Session và Remember Me:** Quản lý phiên làm việc với **Spring Session** và hỗ trợ tính năng "Ghi nhớ đăng nhập".
-   **Giao diện:** Sử dụng **JSP** kết hợp **JSTL**, **Bootstrap**, và **jQuery** để xây dựng giao diện động.
-   **Cơ sở dữ liệu:** Sử dụng **MySQL** với **Spring Data JPA** và **Hibernate** để quản lý dữ liệu.
-   **Xử lý lỗi:** Tùy chỉnh trang lỗi 404, 403, và các lỗi khác.

## Công nghệ sử dụng

-   **Spring Boot**: Framework chính để xây dựng ứng dụng.
-   **Spring MVC**: Tổ chức code theo mô hình MVC.
-   **Spring Data JPA/Hibernate**: Quản lý dữ liệu và ánh xạ đối tượng-thực thể.
-   **Spring Security**: Quản lý xác thực (Authentication) và phân quyền (Authorization).
-   **Spring Session**: Quản lý phiên làm việc và hỗ trợ Remember Me.
-   **MySQL**: Cơ sở dữ liệu quan hệ.
-   **JSP/JSTL**: Công nghệ giao diện phía server.
-   **Bootstrap/jQuery**: Tạo giao diện responsive và xử lý tương tác phía client.
-   **Maven**: Quản lý thư viện và xây dựng dự án.

## Cấu trúc thư mục

```
src/
├── main/
│   ├── java/
│   │   ├── domain/           # Chứa các Entity (Model)
│   │   ├── controller/       # Chứa các Controller
│   │   ├── service/          # Chứa logic nghiệp vụ
│   │   ├── repository/       # Chứa các Repository để truy vấn database
│   │   └── config/           # Chứa các cấu hình (Security, MVC, ...)
│   ├── resources/
│   │   ├── static/           # Chứa tài nguyên tĩnh (CSS, JS, images)
│   │   ├── WEB-INF/view/     # Chứa các file JSP
│   │   └── application.properties # Cấu hình ứng dụng
├── test/                     # Chứa code kiểm thử
└── pom.xml                   # File cấu hình Maven
```

## Yêu cầu cài đặt

1. **Java**: JDK 17 trở lên.
2. **Maven**: Để quản lý thư viện và xây dựng dự án.
3. **MySQL**: Cơ sở dữ liệu để lưu trữ dữ liệu.
4. **IDE**: IntelliJ IDEA, Spring Tool Suite, hoặc VSCode (khuyến nghị sử dụng Spring Tool Suite để tránh lỗi build).
5. **Trình duyệt**: Chrome, Firefox, hoặc bất kỳ trình duyệt hiện đại nào.

## Hướng dẫn cài đặt và chạy dự án

1. **Clone dự án**:

    ```bash
    git clone https://github.com/tranvanhailam/laptopshop-java-spring-mvc.git
    ```

2. **Cấu hình cơ sở dữ liệu**:

    - Tạo database trong MySQL, ví dụ: `db_example`.
    - Cập nhật thông tin kết nối trong file `application.properties`:
        ```properties
        spring.datasource.url=jdbc:mysql://localhost:3306/db_example
        spring.datasource.username=<your-username>
        spring.datasource.password=<your-password>
        spring.jpa.hibernate.ddl-auto=update
        ```

3. **Cài đặt thư viện**:

    ```bash
    mvn clean install
    ```

4. **Chạy ứng dụng**:

    ```bash
    mvn spring-boot:run
    ```

5. **Truy cập ứng dụng**:
    - Mở trình duyệt và truy cập: `http://localhost:8080`.
    - Trang đăng nhập: `http://localhost:8080/login`.
    - Trang quản trị (yêu cầu ROLE_ADMIN): `http://localhost:8080/admin`.

## Tài liệu tham khảo

-   [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
-   [Spring Security Documentation](https://docs.spring.io/spring-security/reference/index.html)
-   [Spring Data JPA Documentation](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
-   [Thymeleaf vs JSP](https://www.thymeleaf.org/doc/articles/thvsjsp.html)
-   [Bootstrap Documentation](https://getbootstrap.com/docs/5.0/getting-started/introduction/)
-   [JPA Specifications](https://docs.spring.io/spring-data/jpa/reference/jpa/specifications.html)

## Tác giả

-   **TrầnVăn Hải Lâm**
-   Thời gian thực hiện: 6/2/2025 - 18/4/2025
