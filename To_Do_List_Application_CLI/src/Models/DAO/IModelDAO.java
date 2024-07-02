package Models.DAO;

public interface IModelDAO<T> {
    void add(T o);
    T get(int id);

    Boolean delete(T o);

    Boolean update(T o1, T o2);
}
