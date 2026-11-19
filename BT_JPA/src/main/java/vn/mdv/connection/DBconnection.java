
package vn.mdv.connection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DBconnection {

    // Tạo EntityManagerFactory
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("BaiTapLTWPU");

    // Tạo EntityManager để thao tác với database
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Đóng EntityManagerFactory khi ứng dụng kết thúc
    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
