package com.sale_vehicule.order_service.repository;

import com.sale_vehicule.order_service.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
