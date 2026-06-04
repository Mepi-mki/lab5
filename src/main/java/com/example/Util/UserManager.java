package com.example.Util;

import java.util.List;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.example.Entity.User;

public class UserManager {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("PolyOE");
    EntityManager em = factory.createEntityManager();

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String PHONE_REGEX = "^(0|\\+84)(3|5|7|8|9)[0-9]{8}$";

    public boolean isValidEmail(String email) {
        return email != null && Pattern.matches(EMAIL_REGEX, email);
    }

    public boolean isValidPhone(String phone) {
        return phone != null && Pattern.matches(PHONE_REGEX, phone);
    }

    public void findAll() {
        String jpql = "SELECT o FROM User o";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        List<User> list = query.getResultList();
        list.forEach(user -> {
            String fullname = user.getFullname();
            boolean admin = user.getAdmin();
            System.out.println(fullname + ": " + admin);
        });
    }

    public void findById() {
        // Thay "<<user-id>>" bằng một ID cụ thể có trong CSDL, ví dụ "user01"
        User user = em.find(User.class, "user01"); 
        if (user != null) {
            String fullname = user.getFullname();
            boolean admin = user.getAdmin();
            System.out.println(fullname + ": " + admin);
        } else {
            System.out.println("Không tìm thấy user!");
        }
    }

    public void create() {
        User user = new User("U01", "123", "Nguyễn Văn Tèo", "teo@gmail.com", false);
        
        // Tích hợp AI: Kiểm tra email trước khi thêm mới
        if (!isValidEmail(user.getEmail())) {
            System.out.println("Lỗi: Email không đúng định dạng!");
            return; // Dừng lại, không lưu vào CSDL
        }

        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            System.out.println("Thêm mới thành công!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Lỗi thêm mới: " + e.getMessage());
        }
    }

    public void update() {
        User user = em.find(User.class, "U01");
        if (user != null) {
            user.setFullname("Nguyễn Văn Tèo");
            
            String newEmail = "teonv@gmail.com";
            // Tích hợp AI: Kiểm tra email trước khi cập nhật
            if (!isValidEmail(newEmail)) {
                System.out.println("Lỗi: Email cập nhật không đúng định dạng!");
                return;
            }
            user.setEmail(newEmail);

            try {
                em.getTransaction().begin();
                // em.merge(user); // JPA tự động cập nhật đối tượng đang được quản lý
                em.getTransaction().commit();
                System.out.println("Cập nhật thành công!");
            } catch (Exception e) {
                em.getTransaction().rollback();
                System.out.println("Lỗi cập nhật: " + e.getMessage());
            }
        } else {
            System.out.println("Không tìm thấy User U01 để cập nhật.");
        }
    }

    public void deleteById() {
        User user = em.find(User.class, "U01");
        if (user != null) {
            try {
                em.getTransaction().begin();
                em.remove(user);
                em.getTransaction().commit();
                System.out.println("Xóa thành công!");
            } catch (Exception e) {
                em.getTransaction().rollback();
                System.out.println("Lỗi xóa: " + e.getMessage());
            }
        } else {
            System.out.println("Không tìm thấy User U01 để xóa.");
        }
    }
    public void findStudents() {
        // Đoạn code viết theo đúng hướng dẫn cơ bản của Bài 3
        String jpql = "SELECT o FROM User o WHERE o.email LIKE :search AND o.admin = :role";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        
        // Cung cấp giá trị cho tham số
        query.setParameter("search", "%@fpt.edu.vn");
        query.setParameter("role", false);
        
        List<User> list = query.getResultList();
        
        System.out.println("--- Danh sách sinh viên FPT (Không phải Admin) ---");
        for (User user : list) {
            System.out.println("Họ tên: " + user.getFullname() + " | Email: " + user.getEmail());
        }
    }
    // Phần code tối ưu hóa cho phương thức findFptStudents() do AI yêu cầu bài 3
    public void findStudentsOptimized() {
        String jpql = "SELECT o FROM User o WHERE o.email LIKE :search AND o.admin = :role";
        
        // Tối ưu 1: Gộp chuỗi khởi tạo và truyền tham số (Method Chaining)
        List<User> list = em.createQuery(jpql, User.class)
                            .setParameter("search", "%@fpt.edu.vn")
                            .setParameter("role", false)
                            .getResultList();
        
        System.out.println("--- Danh sách sinh viên FPT (Không phải Admin) ---");
        
        // Tối ưu 2: Kiểm tra rỗng để cải thiện trải nghiệm người dùng
        if (list.isEmpty()) {
            System.out.println("Không tìm thấy người dùng nào phù hợp!");
            return;
        }

        // Tối ưu 3: Sử dụng cú pháp forEach kết hợp Lambda Expression cho ngắn gọn
        list.forEach(user -> System.out.println("Họ tên: " + user.getFullname() + " | Email: " + user.getEmail()));
    }
}
