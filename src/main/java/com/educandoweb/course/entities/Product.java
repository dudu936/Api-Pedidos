package com.educandoweb.course.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable, Iterable<Category> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String name;
	@NotEmpty
	private String description;
	@NotNull
	private Double price;
	private String imgUrl;
	
	@ManyToMany
	@JoinTable(name = "tb_product_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories = new HashSet<>();
	
	@OneToMany(mappedBy = "id.product")
	private Set<OrderItem> orders = new HashSet<>();

	public Product() {
	}

	public Product(String name, String description, Double price, String imgUrl, Category... categories) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
		this.addCategory(categories);
	}
	
	private Product(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.description = builder.description;
		this.price = builder.price;
		this.imgUrl = builder.imgUrl;
		this.categories = builder.categories;
		this.orders = builder.orders;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Set<Category> getCategories() {
		return categories;
	}
	
	@JsonIgnore
	public Set<Order> getOrders(){
		Set<Order> orders = new HashSet<>();
		for(OrderItem order : this.orders) {
			orders.add(order.getOrder());
		}
		return orders;
	}
	
	public void addCategory(Category... categories) {
		if(categories.length == 1) {
			this.categories.add(categories[0]);
		}
		for(Category category: categories) {
			this.categories.add(category);
		}
	}
	
	@Override
	public Iterator<Category> iterator() {
		return categories.iterator();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}
	
	public static class Builder {
		private Long id;
		private String name;
		private String description;
		private Double price;
		private String imgUrl;
		private Set<Category> categories = new HashSet<>();
		private Set<OrderItem> orders = new HashSet<>();
		
		public Builder id(Long id) {
			this.id = id;
			return this;
		}
		
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		
		public Builder description(String description) {
			this.description = description;
			return this;
		}
		
		public Builder price(Double price) {
			this.price = price;
			return this;
		}
		
		public Builder imgUrl(String imgUrl) {
			this.imgUrl = imgUrl;
			return this;
		}
		
		public Builder categories(Set<Category> categories) {
			this.categories = categories;
			return this;
		}
		
		public Builder orders(Set<OrderItem> orders) {
			this.orders = orders;
			return this;
		}
		
		public Product Build() {
			return new Product(this);
		}

	}
}
