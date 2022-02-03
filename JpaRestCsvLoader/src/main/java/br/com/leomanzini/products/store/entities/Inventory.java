package br.com.leomanzini.products.store.entities;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Inventory {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "serial", referencedColumnName = "serial")
	private Product product;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "store_document")
	private Store store;
	
	private Integer amount;
	private BigDecimal price;
}
