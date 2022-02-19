package br.com.leomanzini.products.store.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.leomanzini.products.store.dto.StoreDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@SuppressWarnings("serial")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Store implements Serializable {

	@Id
	@EqualsAndHashCode.Include
	private Integer document;

	@Column(name = "nome")
	private String name;

	@Builder.Default
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "store")
	private List<Inventory> inventory = new ArrayList<>();

	public Store(StoreDto storeToInsert) {
		this.document = storeToInsert.getStoreDocument();
		this.name = storeToInsert.getStoreName();
	}
}
