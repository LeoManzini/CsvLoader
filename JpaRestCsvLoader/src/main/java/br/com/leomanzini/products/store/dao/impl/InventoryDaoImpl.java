package br.com.leomanzini.products.store.dao.impl;

import java.util.List;

import br.com.leomanzini.products.store.dao.JpaDaoImplementationClass;
import br.com.leomanzini.products.store.model.entities.Inventory;
import br.com.leomanzini.products.store.utils.Queries;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

public class InventoryDaoImpl extends JpaDaoImplementationClass<Inventory> {

	@Override
	public List<Inventory> findAll() {
		TypedQuery<Inventory> findAllInventories = super.getEntityManager()
				.createQuery(Queries.INVENTORY_FIND_ALL.getQuery(), Inventory.class);
		return findAllInventories.getResultList();
	}

	@Override
	public Inventory findById(Integer id) {
		TypedQuery<Inventory> findInventoriesById = super.getEntityManager()
				.createQuery(Queries.INVENTORY_FIND_BY_ID.getQuery(), Inventory.class);
		findInventoriesById.setParameter("productId", id);
		return findInventoriesById.getSingleResult();
	}

	@Override
	public boolean insert(Inventory insertObject) {
		Query insertInventory = super.getEntityManager().createNativeQuery(Queries.INVENTORY_INSERT.getQuery());

		super.getEntityManager().getTransaction().begin();

		insertInventory.setParameter("amount", insertObject.getAmount());
		insertInventory.setParameter("price", insertObject.getPrice());
		insertInventory.setParameter("serial", insertObject.getProduct().getSerial());
		insertInventory.setParameter("storeDocument", insertObject.getStore().getDocument());

		if (insertInventory.executeUpdate() != 1) {
			return false;
		} else {
			super.getEntityManager().getTransaction().commit();
			return true;
		}
	}

	@Override
	public boolean update(Inventory updatableObject) {
		Query updateInventory = super.getEntityManager().createNativeQuery(Queries.INVENTORY_UPDATE.getQuery());

		super.getEntityManager().getTransaction().begin();

		updateInventory.setParameter("amount", updatableObject.getAmount());
		updateInventory.setParameter("price", updatableObject.getPrice());
		updateInventory.setParameter("id", updatableObject.getId());

		if (updateInventory.executeUpdate() != 1) {
			return false;
		} else {
			super.getEntityManager().getTransaction().commit();
			return true;
		}
	}

	@Override
	public boolean delete(Integer id) {
		Query deleteInventory = super.getEntityManager().createQuery(Queries.INVENTORY_DELETE.getQuery());

		super.getEntityManager().getTransaction().begin();

		deleteInventory.setParameter("id", id);

		if (deleteInventory.executeUpdate() != 1) {
			return false;
		} else {
			super.getEntityManager().getTransaction().commit();
			return true;
		}
	}

	public List<Inventory> findByDocument(Integer storeDocument) {
		TypedQuery<Inventory> inventoryQuery = super.getEntityManager()
				.createQuery(Queries.INVENTORY_FIND_BY_DOCUMENT.getQuery(), Inventory.class);
		inventoryQuery.setParameter("storeDocument", storeDocument);
		return inventoryQuery.getResultList();
	}

	@Transactional
	public Inventory findStoreProduct(Integer storeDocument, Integer productSerial) {
		TypedQuery<Inventory> inventoryQuery = super.getEntityManager()
				.createQuery(Queries.INVENTORY_FIND_BY_DOCUMENT.getQuery(), Inventory.class);
		inventoryQuery.setParameter("storeDocument", storeDocument);

		List<Inventory> productList = inventoryQuery.getResultList();

		for (Inventory inventoryReturn : productList) {
			if (inventoryReturn.getProduct().getSerial().intValue() == productSerial) {
				return inventoryReturn;
			}
		}
		return null;
	}
}
