package ar.com.ada.sb.unittest.model.repository;

import ar.com.ada.sb.unittest.model.entity.Product;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductRepositoryTest {

    @Autowired @Qualifier("productRepository")
    private ProductRepository productRepository;

    @Test @Order(0)
    public void whenSaveThenReturnAnProductWithId() {
        //GIVEN
        Product product = new Product().builder()
                .name("p1")
                .description("DESCRIPTION 1")
                .price(new BigInteger("10"))
                .build();
        //WHEN
        Product saved = productRepository.save(product);
        //THEN
        assertNotNull(saved.getId());
    }

    @Test @Order(1)
    public void whenFindByNameReturnAnProduct() {
        //GIVEN
        String productName = "p1";

        //WHEN
        Optional<Product> byName = productRepository.findByName(productName);
        Product product = byName.get();
        //THEN
        assertEquals(true, byName.isPresent());
        assertEquals(productName, product.getName());

    }

    @Test @Order(2)
    public void whenFindAllReturnAllProducts() {
        //GIVEN

        //WHEN
        List<Product> productList = productRepository.findAll();
        //THEN
        assertThat(productList).hasSize(1);

    }

}