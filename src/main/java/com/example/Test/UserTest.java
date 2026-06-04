package com.example.Test;

import com.example.Util.UserManager;

public class UserTest {
    public static void main(String[] args) {
        UserManager manager = new UserManager();

        System.out.println("--- 1. Kiểm tra create() ---");
        manager.create();

        System.out.println("\n--- 2. Kiểm tra findAll() ---");
        manager.findAll();

        System.out.println("\n--- 3. Kiểm tra findById() ---");
        manager.findById();

        System.out.println("\n--- 4. Kiểm tra update() ---");
        manager.update();

        System.out.println("\n--- 5. Kiểm tra deleteById() ---");
        manager.deleteById();

        // --- PHẦN CODE BỔ SUNG CHO BÀI 3 ---
        System.out.println("\n--- 6. Kiểm tra findStudents() (Bài 3) ---");
        manager.findStudents();

        System.out.println("\n--- 7. Kiểm tra findStudentsOptimized() (Bài 3 Tối ưu AI) ---");
        manager.findStudentsOptimized();
    }
}