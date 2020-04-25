
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        Parser parser1 = new Parser();
        while (true) {
            try {
                Thread.sleep(100);
                System.out.println(parser1.generateSentence());
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}







//        Connection connection = null;
//        String url = "jdbc:mysql://192.168.43.77:3306/first_lesson?serverTimezone=UTC";
//        String name = "admin";
//        String password = "admin";
//
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            connection = DriverManager.getConnection(url, name, password);
//            System.out.println("Соединение установлено");
//
//            Statement statement = null;
//            PreparedStatement statementCommon = null;
//
//            statement = connection.createStatement();
//            statementCommon = connection.prepareStatement(
//                    "INSERT INTO common_words SELECT * FROM words WHERE code_parent = ?");
//            ResultSet result1 = statement.executeQuery(
//                    "SELECT * FROM common_words");
//            System.out.println("запрос сформирован");
//
//            long start = System.currentTimeMillis();
//            int counter = 0;
//            while (result1.next()) {
//                counter++;
//                int code = result1.getInt("code");
//                System.err.println("совпад " + code + " (" + counter + ")");
//                statementCommon.setInt(1, code);
//                statementCommon.executeUpdate();
//                }
//            connection.close();
//            System.err.println((System.currentTimeMillis() - start) / 600 + " секунд");
//        } catch (SQLException e) {
//            System.out.println(e);
//

////        Parser parser2 = new Parser("rusAdverbs.txt");
////        System.out.println(parser2.getRandomNoun());
//
//           ArrayList<String> output = new ArrayList<>();
////        for (String i : parser2.parsed) {
////            for (String j : parser1.parsed) {
////                if (i.equals(j)) {
////                    output.add(i);
////                }
////            }
////        }


