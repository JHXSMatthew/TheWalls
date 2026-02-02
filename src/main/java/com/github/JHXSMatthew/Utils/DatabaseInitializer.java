package com.github.JHXSMatthew.Utils;

import com.github.JHXSMatthew.Controllers.MySQLController;
import com.github.JHXSMatthew.Main;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据库初始化工具类
 * 用于自动创建所需的数据库表
 */
public class DatabaseInitializer {

    private final MySQLController controller;
    private final Connection connection;

    public DatabaseInitializer(MySQLController controller, Connection connection) {
        this.controller = controller;
        this.connection = connection;
    }

    /**
     * 初始化数据库表
     */
    public void initializeTables() {
        try {
            // 创建主要统计表
            createStatsTable();
            
            // 创建套件表
            createKitsTable();
            
            // 创建经验控制表
            createExpControlTable();
            
            // 创建打地鼠分数表
            createWackAmoleTable();
            
            System.out.println("[TheWalls] 数据库表初始化完成");
        } catch (SQLException e) {
            System.err.println("[TheWalls] 数据库表初始化失败: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 创建主要统计数据表
     */
    private void createStatsTable() throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS `TheWalls` (" +
                "`id` INT AUTO_INCREMENT PRIMARY KEY," +
                "`Name` VARCHAR(64) NOT NULL UNIQUE," +
                "`Games` INT DEFAULT 0," +
                "`Wins` INT DEFAULT 0," +
                "`Kills` INT DEFAULT 0," +
                "`Deaths` INT DEFAULT 0," +
                "`Coins` INT DEFAULT 0," +
                "`LastLogin` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSQL);
        }
    }

    /**
     * 创建套件表
     */
    private void createKitsTable() throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS `TheWallsKits` (" +
                "`id` INT AUTO_INCREMENT PRIMARY KEY," +
                "`player_id` INT NOT NULL," +
                "`current` VARCHAR(64) DEFAULT NULL," +
                "`warrior` INT DEFAULT 0," +
                "`archer` INT DEFAULT 0," +
                "`tank` INT DEFAULT 0," +
                "`healer` INT DEFAULT 0," +
                "`miner` INT DEFAULT 0," +
                "`builder` INT DEFAULT 0," +
                "`ninja` INT DEFAULT 0," +
                "`assassin` INT DEFAULT 0," +
                "FOREIGN KEY (player_id) REFERENCES TheWalls(id) ON DELETE CASCADE" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSQL);
        }
    }

    /**
     * 创建经验控制表
     */
    private void createExpControlTable() throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS `expControl` (" +
                "`id` INT AUTO_INCREMENT PRIMARY KEY," +
                "`Name` VARCHAR(64) NOT NULL UNIQUE," +
                "`Amount` FLOAT DEFAULT 0.0," +
                "`LastUpdated` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSQL);
        }
    }

    /**
     * 创建打地鼠分数表
     */
    private void createWackAmoleTable() throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS `WackAmole` (" +
                "`id` INT AUTO_INCREMENT PRIMARY KEY," +
                "`name` VARCHAR(64) NOT NULL UNIQUE," +
                "`score` INT DEFAULT 0," +
                "`best_score` INT DEFAULT 0," +
                "`games_played` INT DEFAULT 0," +
                "`last_played` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSQL);
        }
    }

    /**
     * 检查表是否存在
     */
    public boolean tableExists(String tableName) throws SQLException {
        String query = "SELECT COUNT(*) FROM information_schema.tables " +
                      "WHERE table_schema = DATABASE() AND table_name = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, tableName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
}