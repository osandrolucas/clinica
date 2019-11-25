package clinica.dao;

public interface GenericDao<T> {
    int insert(T obj);
    java.util.List<T> listAll();
    T findByID(Integer id);
}
