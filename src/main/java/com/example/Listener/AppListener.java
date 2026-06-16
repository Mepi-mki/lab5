package com.example.Listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

@WebListener
public class AppListener implements HttpSessionListener, ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent e) {
        System.out.println("====== APP LISTENER ĐÃ ĐƯỢC KÍCH HOẠT ======");
        ServletContext application = e.getServletContext();
        Integer visitors = 0;
        try {
            String path = application.getRealPath("/visitors.txt"); 
            List<String> lines = Files.readAllLines(Paths.get(path)); 
            visitors = Integer.valueOf(lines.get(0)); 
        } catch (Exception ex) {
            visitors = 10000; 
        }
        application.setAttribute("visitors", visitors); 
    }

    @Override
    public void sessionCreated(HttpSessionEvent e) {
        HttpSession session = e.getSession();
        ServletContext application = session.getServletContext();
        Integer visitors = (Integer) application.getAttribute("visitors"); 
        application.setAttribute("visitors", visitors + 1); 
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent e) {
    }

    @Override
    public void contextDestroyed(ServletContextEvent e) {
        System.out.println("====== APP LISTENER ĐÃ ĐƯỢC HỦY ======");
        ServletContext application = e.getServletContext();
        Integer visitors = (Integer) application.getAttribute("visitors"); 
        try {
            String path = application.getRealPath("/visitors.txt"); 
            byte[] data = String.valueOf(visitors).getBytes(); 
            Files.write(Paths.get(path), data, StandardOpenOption.CREATE); 
        } catch (Exception ex) {
            ex.printStackTrace(); 
        }
    }
}
