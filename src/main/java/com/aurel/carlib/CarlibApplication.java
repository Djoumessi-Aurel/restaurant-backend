package com.aurel.carlib;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.aurel.carlib.helper.TypeMenu;
import com.aurel.carlib.model.Menu;
import com.aurel.carlib.model.Serveur;
import com.aurel.carlib.repository.MenuRepository;
import com.aurel.carlib.repository.ServeurRepository;

import jakarta.transaction.Transactional;

@SpringBootApplication
public class CarlibApplication implements CommandLineRunner {

	// @Autowired
	// private ProductService productService;
	@Autowired
	private MenuRepository menuRepo;

	public static void main(String[] args) {
		SpringApplication.run(CarlibApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		System.out.println("COMPILATION TERMINEE");
		Menu menu1 = new Menu(TypeMenu.PETIT_DEJEUNER),
			menu2 = new Menu(TypeMenu.DEJEUNER),
			menu3 = new Menu(TypeMenu.SOUPER);

			menuRepo.save(menu1); menuRepo.save(menu2); menuRepo.save(menu3);
	}

}















/*
 * ANCIEN MAIN
 
 public void run(String... args) throws Exception {
		System.out.println("\n##################\n\nBonjour à tous. On peut commencer\n\n");

		Product existingProduct = productService.getProductById(2).get();
		System.out.println("Prix du produit avant: " + existingProduct.getCost());
		
		existingProduct.setCost(817);
		productService.saveProduct(existingProduct); //Mise à jour du produit
		
		existingProduct = productService.getProductById(2).get();
		System.out.println("Prix du produit après: " + existingProduct.getCost());

		// Product prod = productService.getProductById(9).get(); //on récupère un produit
		// Comment c = new Comment("Bonne assurance. Mais elle est trop chère."); //On crée un commentaire
		// prod.saveComment(c); //On lie le commentaire et le produit

		// c = commentService.saveComment(c); //On enregistre le commentaire en BD

		// Iterable<Product> productList = productService.getProducts();

		// System.out.println("\n************Les produits et leurs commentaires************\n");
		// for(Product p: productList){
		// 	System.out.println("Produit " + p.getProductId() + ": " + p.getName() + " (" + p.getCost() + " euros)");
		// 	List<Comment> comments = p.getComments();
		// 	System.out.println("              Commentaires" + "(" + comments.size() + "):");
			
		// 	for(Comment com: comments){
		// 		System.out.println("                " + com.getContent());
		// 	}
		// }

		// System.out.println("\n************Les produits et leurs catégories************\n");
		// for(Product p: productList){
		// 	System.out.println("Produit " + p.getProductId() + ": " + p.getName() + " (" + p.getCost() + " euros)");
		// 	List<Category> categories = p.getCategories();
		// 	System.out.println("              Catégories" + "(" + categories.size() + "):");
			
		// 	for(Category cat: categories){
		// 		System.out.println("                " + cat.getName());
		// 	}
		// }

		//Suppression d'un commentaire
		// commentService.deleteCommentById(3);

		//Suppression d'un produit
		//productService.deleteProductById(5);

		//Get products by name
		// Iterable<Product> searchResults = productService.getProductsByNameLike("%AssuranceTousRisques%");
		// searchResults.forEach(product -> System.out.println(product.getProductId() + ": " + product.getName()));

		//Obtenir les produits qui ont une certaine catégorie
		// Iterable<Product> searchResults = productService.getProductsByCategoriesNameLike("%Special%");
		// searchResults.forEach(product -> System.out.println(product.getProductId() + ": " + product.getName()));

		//Obtenir les produits qui ont un prix supérieur à une certaine valeur
		// Iterable<Product> searchResults = productService.getProductsByMinCost(1500);
		// searchResults.forEach(product -> System.out.println(product.getProductId() + ": " + product.getName()));

		//Categories by name
		// Iterable<Category> searchResults = categoryService.getCategoriesByNameLike("%Jeunes%");
		// searchResults.forEach(category -> System.out.println(category.getCategoryId() + ": " + category.getName()));

		//Categories by product name
		// Iterable<Category> searchResults2 = categoryService.getCategoriesByProductsNameLike("%risque%");
		// searchResults2.forEach(category -> System.out.println(category.getCategoryId() + ": " + category.getName()));

		//Get comment by content
		Iterable<Comment> searchResults3 = commentService.getCommentsByContentLike("%minimum%");
		searchResults3.forEach(comment -> System.out.println(comment.getCommentId() + ": " + comment.getContent()));

		// System.out.println("\n************Les commentaires et leur produit************\n");
		// Iterable<Comment> commentList = commentService.getComments();
		// for(Comment com: commentList){
		// 	System.out.println("_" + com.getProduct().getName()+ "_: " + com.getContent());
		// }

		// Category newCategory = categoryService.saveCategory(new Category("Royal"));

		// Product newProduct = new Product("Assurance totale adultes", "Pour couvrir toutes les charges possibles", 5000);
		// newCategory.saveProduct(newProduct); //On lie le produit et la catégorie

		// newProduct = productService.saveProduct(newProduct); //On ajoute le produit en BD

		// System.out.println("\n************Les catégories et leurs produits************\n");
		// Iterable<Category> categoryList = categoryService.getCategories();
		// for(Category cat: categoryList){
		// 	System.out.println("     Liste des produits de la catégorie " + cat.getCategoryId() + " (" + cat.getName() + ")");
		// 	cat.getProducts().forEach(product -> System.out.println("              " + product.getName()));
		// }

		// int _id = 3;
		// Optional<Product> product = productService.getProductById(_id);
		// if(product.isPresent()) System.out.println("\nProduit numéro " + _id + ": "+ product.get());
		// else System.out.println("\nLe produit " + _id + " n'existe pas.");

		System.out.println("\n\n##################");
	}


 *
 */