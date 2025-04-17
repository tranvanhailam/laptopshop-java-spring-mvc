package vn.hoidanit.laptopshop.service.specification;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;


import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.Product_;

public class ProductSpecifications {

    public static Specification<Product> nameLike(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Product_.NAME), "%" + name + "%");
        // return new Specification<Product>() {
        // @Override
        // public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query,
        // CriteriaBuilder criteriaBuilder) {
        // return criteriaBuilder.like(root.get(Product_.NAME), "%" + name + "%");
        // }
        // };
    }

    // public static Specification<Product> priceGreaterThanOrEqualTo(double minPrice) {
    //     return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get(Product_.PRICE),
    //             minPrice);
    // }

    // public static Specification<Product> priceLessThanOrEqualTo(double maxPrice) {
    //     return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get(Product_.PRICE),
    //             maxPrice);
    // }

    // public static Specification<Product> factoryEqual(String factory) {
    //     return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Product_.FACTORY),
    //             factory);
    // }

    public static Specification<Product> factoryListEqual(List<String> factoryList) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.in(root.get(Product_.FACTORY)).value(factoryList);
    }

    public static Specification<Product> targetListEqual(List<String> targetList) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.in(root.get(Product_.TARGET)).value(targetList);
    }

    // public static Specification<Product> priceGreaterThanOrEqualAndLessThanOrEqualTo(double minPrice, double maxPrice) {
    //     return (root, query, criteriaBuilder) -> criteriaBuilder.and(
    //             criteriaBuilder.greaterThanOrEqualTo(root.get(Product_.PRICE), minPrice),
    //             criteriaBuilder.lessThanOrEqualTo(root.get(Product_.PRICE), maxPrice));
    // }

    public static Specification<Product> priceMultipleGreaterThanOrEqualAndLessThanOrEqualTo(double minPrice,
            double maxPrice) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get(Product_.PRICE), minPrice, maxPrice);
    }
}
