package sample;

import java.io.Serializable;
import java.sql.*;
import java.util.*;

public class Account implements Serializable {
    private Connection conn;
    Account(Client client, double initialDeponent, Connection conn) throws SQLException {
        this.conn = conn;
        PreparedStatement st1 = conn.prepareStatement("" +
                "INSERT INTO public.\"Accounts\" (balance, last_name, first_name, middle_name) VALUES (?, ?, ?, ?)");
        st1.setDouble(1, initialDeponent);
        st1.setString(2, client.getSurname());
        st1.setString(3, client.getName());
        st1.setObject(4, client.getMiddle_name());
        st1.executeUpdate();
        st1.close();
    }

    public int getSenderId() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT (id) FROM public.\"Accounts\"");
        rs.next();
        int senderId = rs.getInt(1);
        stmt.close();
        return senderId;
    }

}