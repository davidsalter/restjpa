package com.developinjava.restjpa;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/customer")
@Stateless
public class CustomerController {

  @Inject CustomerRepository customerRepository;

  @GET
  @Path("/all")
  @Produces("application/json")
  public Response customers() {
    return Response.ok(customerRepository.findAll()).build();
  }

  @GET
  @Path("/{id}")
  @Produces("application/json")
  public Response find(@PathParam("id") Long customerId) {
    System.out.println("Find customerId = " + customerId);

    Customer customer = customerRepository.find(customerId);

    if (customer != null) {
      return Response.ok(customer).build();
    } else return Response.status(Response.Status.NOT_FOUND).build();
  }

  @POST
  @Produces("application/json")
  public Response create(Customer customer) throws Exception {
    System.out.println("customer = " + customer);

    customerRepository.save(customer);

    URI location = new URI("http://localhost:8080/customer/" + customer.getId());
    return Response.created(location).build();
  }
}
