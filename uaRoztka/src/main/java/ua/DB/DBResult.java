package ua.DB;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;


public class DBResult {
    private ArrayList<String> column_name;
    private ArrayList<Row> row;
    private int columns;
    private boolean result;

    public DBResult(ResultSet rs){
        row = new ArrayList<Row>();
        column_name = new ArrayList<String>();
        columns = 0;
        result = false;
        try{
            ResultSetMetaData rsmd = rs.getMetaData();
            this.columns = rsmd.getColumnCount();
            for (int i=1; i<=this.columns; i++)
                column_name.add(rsmd.getColumnName(i));
            while (rs.next()) {
                Row row_tmp = new Row();
                for (int i=1; i<=this.columns; i++)
                    row_tmp.add(rs.getString(i));
                row.add(row_tmp);
            }
        } catch (Exception e){
            this.result = false;
        }
    }

    public String get (int row_number, int column_id){
        String res;
        try{
            res = row.get(row_number).get(column_id);
        } catch(Exception e){
            return null;
        }
        return res;
    }

    public ArrayList<String> getRow (int row_number){
        return row.get(row_number).param;
    }

    public String getColumnName (int id){return column_name.get(id);}

    public int row_count (){return row.size();}

    public int col_count (){return columns;}

    private class Row {
        public ArrayList<String> param;

        public Row(){
            param = new ArrayList<String>();
        }

        public Row(ArrayList<String> par){
            param = new ArrayList<String>();
            param = par;
        }

        public void add (String p){
            param.add(p);
        }

        public String get(int id){
            return param.get(id);
        }

        @Override
        public String toString(){
            String res = "|";
            for (int i=0;i<param.size();i++)
                res += param.get(i)+"|";
            return res;
        }
    }

    @Override
    public String toString(){
        String res="|";
        for (int i=0; i<columns; i++)
            res += column_name.get(i)+"|";
        res += "\n";
        for (int i=0; i<row_count(); i++)
            res += row.get(i).toString() + "\n";
        return res;
    }

}