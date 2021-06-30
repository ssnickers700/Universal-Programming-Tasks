package zad1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

public class Database {

    String url;
    TravelData travelData;
    Connection connection;

    public Database(String url, TravelData travelData) {
        this.url = url;
        this.travelData = travelData;
    }

    public void create() {

        try {

            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();
            statement.execute("drop table oferta");
            statement.execute("create table oferta(" +
                                    "id integer," +
                                    "kraj varchar(50)," +
                                    "data_wyjazdu varchar(50)," +
                                    "data_powrotu varchar(50)," +
                                    "miejsce varchar(50)," +
                                    "cena varchar(50)," +
                                    "waluta varchar(10))");

            int id = 1;
            PreparedStatement preparedStatement = connection.prepareStatement("insert into oferta values(?,?,?,?,?,?,?)");

            for (String s : travelData.dbList) {

                String[] line = s.split("\t");

                preparedStatement.setInt(1, id++);
                preparedStatement.setString(2, line[0]);
                preparedStatement.setString(3, line[1]);
                preparedStatement.setString(4, line[2]);
                preparedStatement.setString(5, line[3]);
                preparedStatement.setString(6, line[4]);
                preparedStatement.setString(7, line[5]);
                preparedStatement.executeUpdate();
            }
            statement.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void showGui() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Oferta");

            JFrame jf = new JFrame("Oferty");
            jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            jf.setPreferredSize(new Dimension(1000, 400));
            jf.setLocationRelativeTo(null);

            JTable table = new JTable(createTableModel(resultSet));
            JScrollPane scrollPane = new JScrollPane(table);
            jf.add(scrollPane, BorderLayout.CENTER);

            jf.pack();
            jf.setVisible(true);
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        /*Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Oferta");
            ResultSetMetaData resultMeta = resultSet.getMetaData();
            int columnCount = resultMeta.getColumnCount();

            for (int i = 1; i <= columnCount; i++)
                System.out.format("%20s", resultMeta.getColumnName(i) + " | ");

            while(resultSet.next()) {
                System.out.println("");
                for (int i = 1; i <= columnCount; i++)
                    System.out.format("%20s", resultSet.getString(i) + " | ");
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    public DefaultTableModel createTableModel(ResultSet rs) throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        Vector<String> columnNames = new Vector<>();
        int columnCount = metaData.getColumnCount();

        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        Vector<Vector<Object>> data = new Vector<>();

        while (rs.next()) {
            Vector<Object> vector = new Vector<>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }
        return new DefaultTableModel(data, columnNames);
    }
}
