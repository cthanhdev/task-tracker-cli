# Task Tracker CLI

Ứng dụng dòng lệnh (CLI - Command Line Interface) quản lý và theo dõi công việc (Tasks), được xây dựng bằng **Java 21** và **Maven**. Dự án được thiết kế theo kiến trúc phân tầng rõ ràng, áp dụng **Builder Pattern** và đảm bảo tính bất biến (immutability) của dữ liệu.

---

## 📌 Tính năng chính

- ➕ **Thêm công việc mới** (`add`)
- ✏️ **Cập nhật mô tả công việc** (`update`)
- ❌ **Xóa công việc** (`delete`)
- 🔄 **Cập nhật trạng thái**:
  - Đang thực hiện (`mark-in-progress`)
  - Đã hoàn thành (`mark-done`)
- 📋 **Xem danh sách công việc (`list`)**:
  - Xem tất cả các công việc
  - Lọc theo trạng thái: `done`, `todo`, `in-progress`
- 🕒 **Tự động theo dõi thời gian**:
  - Ghi nhận thời điểm tạo (`createdAt`)
  - Ghi nhận thời điểm cập nhật lần cuối (`updatedAt`)
- 🚪 **Thoát ứng dụng** (`\exit`)

---

## 🛠️ Công nghệ sử dụng

- **Ngôn ngữ**: Java 21
- **Quản lý dự án & Build tool**: Apache Maven
- **Thư viện hỗ trợ**:
  - [Project Lombok](https://projectlombok.org/): Tự động sinh boilerplate code.
  - [JUnit 4](https://junit.org/junit4/): Kiểm thử đơn vị (Unit Testing).

---

## 📂 Cấu trúc thư mục

```text
task-tracker-cli/
├── src/
│   ├── main/
│   │   └── java/cli/tasktracker/
│   │       ├── App.java             # Entry point, xử lý vòng lặp CLI
│   │       ├── RequestHandler.java  # Tiếp nhận, phân tích cú pháp (parse) và điều hướng lệnh
│   │       ├── TaskManager.java     # Xử lý nghiệp vụ CRUD và quản lý danh sách Task
│   │       ├── Task.java            # Model/Entity đại diện cho một công việc (Builder Pattern)
│   │       └── TaskStatus.java      # Enum trạng thái (TODO, IN_PROGRESS, DONE)
│   └── test/
│       └── java/cli/tasktracker/
│           └── AppTest.java         # Unit tests
├── pom.xml                          # Cấu hình Maven dependencies & build plugins
└── README.md
```

---

## 💻 Hướng dẫn cài đặt & Chạy ứng dụng

### 1. Yêu cầu môi trường
- **Java Development Kit (JDK)**: Phiên bản 21 trở lên.
- **Apache Maven**: Phiên bản 3.8 trở lên.

Kiểm tra phiên bản cài đặt trên máy bằng lệnh:
```bash
java -version
mvn -version
```

### 2. Biên dịch dự án
Mở terminal tại thư mục gốc của dự án và chạy:
```bash
mvn clean compile
```

### 3. Chạy kiểm thử (Tests)
```bash
mvn test
```

### 4. Khởi chạy ứng dụng
Chạy trực tiếp từ Maven:
```bash
mvn compile exec:java -Dexec.mainClass="cli.tasktracker.App"
```

Hoặc đóng gói thành file `.jar` và chạy:
```bash
mvn clean package
java -cp target/task-tracker-cli-1.0-SNAPSHOT.jar cli.tasktracker.App
```

---

## 📖 Bảng hướng dẫn câu lệnh (CLI Usage)

Khi ứng dụng chạy, giao diện CLI hiển thị dấu nhắc lệnh `<Task-Track> `:

| Lệnh | Cú pháp | Ví dụ | Mô tả |
| :--- | :--- | :--- | :--- |
| **Thêm task** | `add <mô_tả>` | `add "Học Java 21"` | Thêm một công việc mới với trạng thái mặc định là `TODO`. |
| **Cập nhật** | `update <id> <mô_tả_mới>` | `update 1 "Học Java nâng cao"` | Cập nhật nội dung cho task có ID tương ứng và tự động ghi nhận thời gian `updatedAt`. |
| **Xóa task** | `delete <id>` | `delete 1` | Xóa công việc có ID tương ứng khỏi danh sách. |
| **Đang làm** | `mark-in-progress <id>` | `mark-in-progress 1` | Chuyển trạng thái công việc sang `IN_PROGRESS`. |
| **Hoàn thành** | `mark-done <id>` | `mark-done 1` | Chuyển trạng thái công việc sang `DONE`. |
| **Xem tất cả** | `list` | `list` | Hiển thị toàn bộ công việc hiện có. |
| **Lọc: Đã xong** | `list done` | `list done` | Chỉ hiển thị các công việc có trạng thái `DONE`. |
| **Lọc: Cần làm** | `list todo` | `list todo` | Chỉ hiển thị các công việc có trạng thái `TODO`. |
| **Lọc: Đang làm** | `list in-progress` | `list in-progress` | Chỉ hiển thị các công việc có trạng thái `IN_PROGRESS`. |
| **Thoát** | `\exit` | `\exit` | Thoát khỏi chương trình. |

---

## 📝 Định dạng hiển thị Task

Mỗi công việc khi hiển thị qua lệnh `list` sẽ có định dạng trực quan:

```text
ID: 1
Description: Học Java 21
Status: IN_PROGRESS
Created At: 2026-10-17T13:10:00.123456
Updated At: 2026-10-17T13:20:00.654321
__________________________________________________________
```

---

## 🎯 Định hướng phát triển tiếp theo (Roadmap)
- [ ] Lưu trữ dữ liệu ra file `tasks.json` (Persistence Storage).
- [ ] Xử lý ngoại lệ (`try-catch`) tập trung tại tầng giao diện để tránh crash khi người dùng nhập sai tham số.
- [ ] Tích hợp cơ sở dữ liệu PostgreSQL.
