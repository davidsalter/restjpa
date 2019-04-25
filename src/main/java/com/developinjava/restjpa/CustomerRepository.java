package com.developinjava.restjpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CustomerRepository {

  @PersistenceContext(name = "customer-unit")
  EntityManager em;

  Customer find(Long customerId) {
    return em.find(Customer.class, customerId);
  }

  List<Customer> findAll() {
    return em.createQuery("SELECT c FROM Customer c").getResultList();
  }

  public void save(Customer customer) {
    em.persist(customer);
  }
}
