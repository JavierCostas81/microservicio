package com.formacionbdi.springboot.app.item.controllers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.springboot.app.item.models.Item;
import com.formacionbdi.springboot.app.item.models.Producto;
import com.formacionbdi.springboot.app.item.models.service.ItemService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@RestController
public class ItemController {
	private Logger log = LoggerFactory.getLogger(ItemController.class);
	@Autowired
	private CircuitBreakerFactory<?, ?> cbFactory;
	
	@Autowired
	@Qualifier("serviceFeign")
	ItemService itemService;
	
	@GetMapping("/listar")
	public List<Item> listar(){
		return itemService.findAll();
	}
	
	//@HystrixCommand(fallbackMethod="metodoAlternativo")
	//usando factory
	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
		return cbFactory.create("items")
				.run(()->itemService.findById(id, cantidad), e -> metodoAlternativo(id, cantidad,e)); 
	}
	
	//usando anotations
	@CircuitBreaker(name = "items", fallbackMethod = "metodoAlternativo")
	@GetMapping("/ver2/{id}/cantidad/{cantidad}")
	public Item detalle2(@PathVariable Long id, @PathVariable Integer cantidad) {
		return itemService.findById(id, cantidad); 
	}
	//usando anotations
	@CircuitBreaker(name = "items", fallbackMethod = "metodoAlternativo2")
	@TimeLimiter(name = "items")//, fallbackMethod = "metodoAlternativo2")
	@GetMapping("/ver3/{id}/cantidad/{cantidad}")
	public CompletableFuture<Item> detalle3(@PathVariable Long id, @PathVariable Integer cantidad) {
		return CompletableFuture.supplyAsync(()->itemService.findById(id, cantidad)); 
	}
	

	public Item metodoAlternativo(@PathVariable Long id, @PathVariable Integer cantidad, Throwable e) {
		log.info(e.getMessage());
		Item item = new Item();
		Producto producto = new Producto();
		item.setCantidad(cantidad);
		producto.setId(id);
		producto.setNombre("Cámara Sony");
		producto.setPrecio(500.00);
		item.setProducto(producto);
		return item;

	}
	public CompletableFuture<Item>  metodoAlternativo2(@PathVariable Long id, @PathVariable Integer cantidad, Throwable e) {
		log.info(e.getMessage());
		Item item = new Item();
		Producto producto = new Producto();
		item.setCantidad(cantidad);
		producto.setId(id);
		producto.setNombre("Cámara Sony");
		producto.setPrecio(500.00);
		item.setProducto(producto);
		return CompletableFuture.supplyAsync(()-> item);

	}
}
