package com.aurel.carlib.repository;
 
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
 
import com.aurel.carlib.model.Product;
 
@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    public Iterable<Product> findByName(String name);
    public Iterable<Product> findByNameLike(String name);

    public Iterable<Product> findByCategoriesName(String name);
    public Iterable<Product> findByCategoriesNameLike(String name);

    @Query("FROM Product WHERE name = ?1")
    public Iterable<Product> findByNameJPQL(String name);
	
	@Query(value = "SELECT * FROM produit WHERE cout > :cout", nativeQuery = true)
	public Iterable<Product> findByCostNative(@Param("cout") Integer cost);
 
}
