package clinica.dao;

public interface GenericDao<T> {
    public int insert(T obj);
    public java.util.List<T> listAll();
    public T findByID(Integer id);
}
