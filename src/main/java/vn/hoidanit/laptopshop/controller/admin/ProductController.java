package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UploadService;

@Controller
public class ProductController {

    private final ProductService productService;
    private final UploadService uploadService;

    public ProductController(ProductService productService, UploadService uploadService) {
        this.productService = productService;
        this.uploadService = uploadService;
    }

    @RequestMapping(value = "/admin/product", method = RequestMethod.GET)
    public String getTableProductPage(Model model) {
        List<Product> productList = this.productService.getAllProducts();
        model.addAttribute("productList", productList);
        return "admin/product/show";
    }

    // Before delete
    @RequestMapping(value = "/admin/product/delete/{id}", method = RequestMethod.GET)
    public String getDeleteProductPage(Model model, @PathVariable long id) {
        Product product = this.productService.getProductById(id);
        model.addAttribute("product", product);
        return "admin/product/delete";
    }

    // After delete
    @RequestMapping(value = "/admin/product/delete", method = RequestMethod.POST)
    public String getTableProductPageAfterDelete(Model model, @ModelAttribute("product") Product product) {
        this.productService.deleteProductByID(product.getId());
        return "redirect:/admin/product";
    }

    // Before create
    @RequestMapping(value = "/admin/product/create", method = RequestMethod.GET)
    public String getCreateProductPage(Model model) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }

    // After create
    @RequestMapping(value = "/admin/product/create", method = RequestMethod.POST)
    public String getTableProductPageAfterCreate(Model model, @ModelAttribute("newProduct") Product product,
            @RequestParam("productFile") MultipartFile productFile) {
        String productFileName = this.uploadService.handleSaveUploadFile(productFile, "product");
        product.setImage(productFileName);
        this.productService.handleSaveProduct(product);
        return "redirect:/admin/product";
    }
}
