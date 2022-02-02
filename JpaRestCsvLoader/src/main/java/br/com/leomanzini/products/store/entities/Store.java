package br.com.leomanzini.products.store.entities;

import java.util.ArrayList;
import java.util.List;

import br.com.leomanzini.products.store.dto.StoreDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "stores")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Store {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nome")
	private String name;

	@Column(name = "document", unique = true, nullable = false)
	private Integer document;

	@Builder.Default
	@OneToMany(mappedBy = "store")
	private List<Inventory> inventory = new ArrayList<>();

	public Store(StoreDto storeToInsert) {
		this.id = storeToInsert.getStoreId();
		this.name = storeToInsert.getStoreName();
		this.document = storeToInsert.getStoreDocument();
	}
}
