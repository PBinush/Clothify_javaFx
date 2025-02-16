package edu.icet.Repository;

import javafx.collections.ObservableList;

public interface CrudDao <T> extends SuperDao{
    boolean save(T t);
    boolean delete(String id);
    boolean update(T t);
    T search(String id);
}
