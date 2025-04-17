package vn.hoidanit.laptopshop.controller.client;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.Product_;
import vn.hoidanit.laptopshop.domain.dto.product.ProductCriteriaDTO;
import vn.hoidanit.laptopshop.service.ProductService;

@Controller
public class ProductListController {

    private final ProductService productService;

    public ProductListController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String getProductsPage(Model model,
            ProductCriteriaDTO productCriteriaDTO, HttpServletRequest request) {

        int page = 1;
        try {
            if (productCriteriaDTO.getPage().isPresent()) {
                page = Integer.parseInt(productCriteriaDTO.getPage().get());
            }
        } catch (Exception e) {
            // handle exception
        }

        Pageable pageable = PageRequest.of(page - 1, 9);
        // check sort price
        if (productCriteriaDTO.getSort() != null && productCriteriaDTO.getSort().isPresent()) {
            String sort = productCriteriaDTO.getSort().get();
            if (sort.equals("desc")) {
                pageable = PageRequest.of(page - 1, 9, Sort.by(Product_.PRICE).descending());
            } else if (sort.equals("asc")) {
                pageable = PageRequest.of(page - 1, 9, Sort.by(Product_.PRICE).ascending());
            } else {
                pageable = PageRequest.of(page - 1, 9);
            }
        }

        Page<Product> productPageList = this.productService.getAllProductsWithSpecification(productCriteriaDTO,
                pageable);

        // case: Lấy ra tất cả sản phẩm có tên gần đúng
        // String name = nameOptional.isPresent() ? nameOptional.get() : "";
        // Page<Product> productPageList =
        // this.productService.getAllProductsWithSpecification(productCriteriaDTO,
        // pageable);

        // case: Lấy ra tất cả sản phẩm có giá cả tối thiểu
        // double minPrice = minPriceOptional.isPresent() ?
        // Double.parseDouble(minPriceOptional.get()) : 0;
        // Page<Product> productPageList =
        // this.productService.getAllProductsWithSpecification(minPrice, pageable);

        // case: Lấy ra tất cả sản phẩm có giá cả tối đa
        // double maxPrice = maxPriceOptional.isPresent() ?
        // Double.parseDouble(maxPriceOptional.get()) : 0;
        // Page<Product> productPageList =
        // this.productService.getAllProductsWithSpecification(maxPrice, pageable);

        // case: Lấy ra tất cả sản phẩm có hãng sản xuất = APPLE
        // String factory = factoryOptional.isPresent() ? factoryOptional.get() : "";
        // Page<Product> productPageList =
        // this.productService.getAllProductsWithSpecification(factory, pageable);

        // case: Lấy ra tất cả sản phẩm có hãng sản xuất = APPLE hoặc DELL
        // String factory = factoryOptional.isPresent() ? factoryOptional.get() : "";
        // List<String> factoryList = Arrays.asList(factory.split(","));
        // Page<Product> productPageList =
        // this.productService.getAllProductsWithSpecification(factoryList, pageable);

        // case: Lấy ra tất cả sản phẩm theo range (khoảng giá).
        // String price = priceOptional.isPresent() ? priceOptional.get() : "";
        // Page<Product> productPageList =
        // this.productService.getAllProductsWithSpecification(price, pageable);

        // case: Lấy ra tất cả sản phẩm theo range (khoảng giá).
        // String price = priceOptional.isPresent() ? priceOptional.get() : "";
        // List<String> priceList = Arrays.asList(price.split(","));
        // Page<Product> productPageList =
        // this.productService.getAllProductsWithSpecification(priceList, pageable);

        List<Product> productList = productPageList.getContent();

        String queryString = request.getQueryString();

        if (queryString != null && !queryString.isBlank()) {
            queryString = queryString.replace("page=" + page, "");
        }

        model.addAttribute("productList", productList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPageList.getTotalPages());
        model.addAttribute("queryString", queryString);
        return "client/product/show";
    }

}
