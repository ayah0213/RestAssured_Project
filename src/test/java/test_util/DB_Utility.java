package test_util;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DB_Utility {


    // gonna be visible for all static method inside of each static method, good for re-usability
    private static Connection con;
    private static Statement stm;
    private static ResultSet rs;
    private static ResultSetMetaData rsmd;


    // method for creating connection method just checking succesfull or not
    public static void createConnection() {
        String url = ConfigurationReader.getProperty("hr.database.url");
        String username = ConfigurationReader.getProperty("hr.database.username");
        String password = ConfigurationReader.getProperty("hr.database.password");

        // call method to reduce Try / Catch
        createConnection(url,username,password);
    }


    // create GENERAL  method connection to all JDBC  using PARAMetr.
    public static void createConnection(String url, String username, String password) {

        try {
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Connection successfully");
        } catch (SQLException e) {
            System.out.println("Conection failed " + e.getMessage());
        }
    }


    // create  3rd  Qiuery methods as Static/
    // why its sttaic cause we ant to create it without using an object so gonna be useful later on
    public static ResultSet runQuery(String sql) {
        // for doing connetion in ecah and evevrywhere by calling query using this method
        // we need to create a CONNNECTION inside in here
        try {
            stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql); //setting teh object of ResulSet for reuse
            rsmd = rs.getMetaData(); // setting teh value of ResultSetMetaData for reuse
        } catch (SQLException e) {
            System.out.println("Error occure while running query " + e.getMessage());
        }
        return rs;
        // returns data from ur reesult

    }


    // 4th- method for DESTROY  - CLOSE all resources after being used
    // but first of all if dont wnat o get errors during closing unmatching, didnt opened yet door/ connection
    // we have to make sure they re not open == not null then close, if yes then dont do anything just move on
    // we have to check if we have obj. before closing, cause w ant take action if that doesnt exist
    public static void destroy() {
        try {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            System.out.println("Error occurred while closing the Resources " + e.getMessage());

        }
    }

    // 5th - Method Find out ROW Count
    // return the row count of this resultSet
    public static int getRowCount() {
        int rowCount = 0;
        try {
            rs.last();
            rowCount = rs.getRow();
        } catch (SQLException e) {
            System.out.println("Error occurred while getting row count:" + e.getMessage());
        } finally {
            resetCursor();
        }
        return rowCount;
    }


    // 6th Method GEt columnCount
    // return columnCount of this ResultSet
    public static int getColumnCount() {
        int columnCount = 0;
        try {
            columnCount = rsmd.getColumnCount();
        } catch (SQLException e) {
            System.out.println("Error occurred while getting column  count:" + e.getMessage());
        }
        return columnCount;
    }


    // 7th - Method get all Column Names into LIst object
    public static List<String> getAllColumnNamesAsList() {

        List<String> columnNameList = new ArrayList<>();
        try {
            for (int colIndex = 1; colIndex <= getColumnCount(); colIndex++) {
                String columnNAme = rsmd.getColumnName(colIndex);
                columnNameList.add(columnNAme);
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while getting al column names as list " + e.getMessage());
        }

        return columnNameList;
    }


    // 8th - Method getting  entire Row of DATA according to Row Number
    public static List<String> getRowDataAsList(int rownUm) {

        List<String> rowDataAsList = new ArrayList<>();
        int colCount = getColumnCount();

        try {
            rs.absolute(rownUm);
            for (int colIndex = 1; colIndex <= colCount; colIndex++) {
                String cellValue = rs.getString(colIndex);
                rowDataAsList.add(cellValue);

            }
        } catch (SQLException e) {
            System.out.println("Error occurred while getting all data of entire row as list " + e.getMessage());
        } finally {
            resetCursor();
        }
        return rowDataAsList;
    }


    // 9th - method getting specific cell value according row num column index
    // gets only 1 cell value DATA
    // return value as String at thta location
    public static String getCEllValue(int rowNum, int columnIndex) {
        String cellVAlue = "";
        try {
            rs.absolute(rowNum);
            cellVAlue = rs.getString(columnIndex);

        } catch (SQLException e) {
            System.out.println("Error occurred while getting all data of entire row as list " + e.getMessage());
        } finally {
            resetCursor();
        }
        return cellVAlue;
    }

    // 10th - method getting specific cell value according row num column name
    // gets only 1 cell value DATA
    // return value as String at thta location
    public static String getCEllValue(int rowNum, String columnNAme) {
        String cellVAlue = "";
        try {
            rs.absolute(rowNum);
            cellVAlue = rs.getString(columnNAme);

        } catch (SQLException e) {
            System.out.println("Error occurred while getting all data of entire row as list " + e.getMessage());
        } finally {
            resetCursor();
        }
        return cellVAlue;
    }

    // 17th method get the CEll value at First Row and First Column
    public static String getFirstRowFirstColumn(){

        return getCEllValue(1,1);
    }

// 11th method getting entire columnDAta as List according to column number
    // return list objects that contains all rows in that column

    public static List<String> getColumnDataAsList(int columnNum) {

        List<String> columnDataLIst = new ArrayList<>();
        try {
            rs.beforeFirst(); //mak sure teh cursor is at before first location
            while (rs.next()) {
                String cellVAlue = rs.getString(columnNum);
                columnDataLIst.add(cellVAlue);
            }
            rs.beforeFirst();// make sure to reset the cursor to before first location
        } catch (SQLException e) {
            System.out.println("Error occurred while getting column data  as list " + e.getMessage());
        } finally {
            resetCursor();
        }

        return columnDataLIst;
    }


    // 12th method getting entire columnDAta as List according to column Name
    // return list objects that contains all rows in that column
    public static List<String> getColumnDataAsList(String columnNAme) {

        List<String> columnDataLIst = new ArrayList<>();
        try {
            while (rs.next()) {
                String cellVAlue = rs.getString(columnNAme);
                columnDataLIst.add(cellVAlue);
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while getting column data  as list " + e.getMessage());
        } finally {
            resetCursor();
        }

        return columnDataLIst;
    }


    // 13 Display all DATA from ResultSet object
    public static void displayAllData() {

        int columnCount = getColumnCount();
        try {

            while (rs.next()) {
                for (int colIndex = 1; colIndex <= columnCount; colIndex++) {

                    //System.out.print(rs.getString(colIndex) + "\t");
                    System.out.printf("%-25s", rs.getString(colIndex) );
                }
                System.out.println();
            }

        } catch (SQLException e) {
            System.out.println("Error occurred while getting column data  as list " + e.getMessage());
        } finally {
            resetCursor();
        }
    }


    // 14 method Reset cursor move the cursor where it was beginning, to before First location
    private static void resetCursor() {
        try {
            rs.beforeFirst();
        } catch (SQLException e) {
            System.out.println("Error occurred while getting column data  as list " + e.getMessage());
        }
    }


    // 15th MEthod Save entire row data as MAp<String, STring>
    // paramet. RowNum
    // return MAp object hat contains key value pair
    // key : column name
    // value : cell value
    public static Map<String, String> getRowMap(int rowNum) {


        Map<String, String> rowMAp = new LinkedHashMap<>();
        int columnCount = getColumnCount();

        try {
            rs.absolute(rowNum);

            for (int colIndex = 1; colIndex <= columnCount; colIndex++) {
                String columnNanme = rsmd.getColumnName(colIndex);
                String cellValue = rs.getString(colIndex);
                rowMAp.put(columnNanme, cellValue);
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while getting rowMAp as key & value " + e.getMessage());
        } finally {
            resetCursor();
        }

        return rowMAp;
    }

    // 16 method for storing All Rows as List of Map object
    // return List of Map object that contain each row data as Map<String,String>
    public static List<Map<String, String>> getRowMapAsListOfMap() {
        List<Map<String, String>> allRowMApListOfMap = new ArrayList<>();
        int rowCount = getRowCount();
        // move from 1st row f=to last row
        /// get each row as map object and add it in the list
        for (int rowindex = 1; rowindex <= rowCount; rowindex++) {
            Map<String, String> rowMap = getRowMap(rowindex);
            allRowMApListOfMap.add(rowMap);
        }
        resetCursor();
        return allRowMApListOfMap;
    }




}
