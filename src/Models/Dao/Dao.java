/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.Dao;

import java.util.List;

public interface Dao<T> {
    public abstract void save(T t) throws DaoException;
    public abstract List<T> findAll() throws DaoException;
    public abstract T findById(Long id) throws DaoException;
    public abstract void update(T t) throws DaoException;
    public abstract void delete(Long id) throws DaoException;
}
