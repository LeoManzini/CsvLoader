package br.com.leomanzini.products.store.dao.impl;

import java.util.List;

import br.com.leomanzini.products.store.dao.JpaDaoImplementationClass;
import br.com.leomanzini.products.store.entities.Inventory;
import br.com.leomanzini.products.store.utils.Queries;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

public class InventoryDaoImpl extends JpaDaoImplementationClass<Inventory> {

	public InventoryDaoImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	@Transactional
	public List<Inventory> findAll() {
		TypedQuery<Inventory> findAllInventories = super.getEntityManager()
				.createQuery(Queries.INVENTORY_FIND_ALL.getQuery(), Inventory.class);
		return findAllInventories.getResultList();
	}

	@Override
	@Transactional
	public Inventory findById(Integer id) {
		TypedQuery<Inventory> findInventoriesById = super.getEntityManager()
				.createQuery(Queries.INVENTORY_FIND_BY_ID.getQuery(), Inventory.class);
		findInventoriesById.setParameter("inventoryId", id);
		return findInventoriesById.getSingleResult();
	}

	@Override
	@Transactional
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
	@Transactional
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
	@Transactional
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

	@Transactional
	public List<Inventory> findByDocument(Integer storeDocument) {
		TypedQuery<Inventory> inventoryQuery = super.getEntityManager()
				.createQuery(Queries.INVENTORY_FIND_BY_DOCUMENT.getQuery(), Inventory.class);
		inventoryQuery.setParameter("storeDocument", storeDocument);
		return inventoryQuery.getResultList();
	}

	@Transactional
	public Inventory findStoreProduct(Integer storeDocument, Integer productSerial) {
		try {
			TypedQuery<Inventory> inventoryQuery = super.getEntityManager()
					.createQuery(Queries.INVENTORY_FIND_PRODUCT_STORE.getQuery(), Inventory.class);
			inventoryQuery.setParameter("storeDocument", storeDocument);
			inventoryQuery.setParameter("serial", productSerial);
			
			return inventoryQuery.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
	@Transactional
	public boolean updateProduct(Inventory updatableObject) {
		Query updateInventory = super.getEntityManager().createNativeQuery(Queries.INVENTORY_UPDATE_SERIAL.getQuery());

		super.getEntityManager().getTransaction().begin();

		updateInventory.setParameter("amount", updatableObject.getAmount());
		updateInventory.setParameter("price", updatableObject.getPrice());
		updateInventory.setParameter("storeDocument", updatableObject.getStore().getDocument());
		updateInventory.setParameter("serial", updatableObject.getProduct().getSerial());

		if (updateInventory.executeUpdate() != 1) {
			return false;
		} else {
			super.getEntityManager().getTransaction().commit();
			return true;
		}
	}
}
