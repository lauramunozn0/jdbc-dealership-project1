package com.pluralsight.db;

import com.pluralsight.models.LeaseContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LeaseDao {
    private DataSource dataSource;

    public LeaseDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addLeaseContract(LeaseContract leaseContract) {
        String sql = "INSERT INTO lease_contracts (vin, lease_start, lease_end, monthly_payment) VALUES (?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, leaseContract.getVin());
            statement.setDate(2, java.sql.Date.valueOf(leaseContract.getLeaseStart()));
            statement.setDate(3, java.sql.Date.valueOf(leaseContract.getLeaseEnd()));
            statement.setDouble(4, leaseContract.getMonthlyPayment());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding lease contract", e);

        }

    }
}
