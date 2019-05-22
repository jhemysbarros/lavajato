package br.unitins.lavajato.dao;

import java.util.List;

public interface DAO<T> {

	boolean create(T obj);

	boolean update(T obj);

	boolean delete(T obj);

	T findById(int obj);

	List<T> findAll(T obj);
}
