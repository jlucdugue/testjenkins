package fr.imie.tpjdbc;

import java.sql.Connection;

public interface Itransactional {
	public abstract void setConnection(Connection connection);
	public abstract Connection getConnection();
}
