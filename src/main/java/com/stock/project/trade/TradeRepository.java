package com.stock.project.trade;

import com.stock.project.constants.Stock;
import com.stock.project.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<Trade,Long> {
    List<Trade> findAllByUserAndStock(User user, Stock stock);
}
