package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UploadService;

@Controller
public class ProductController {

    private final PasswordEncoder encoder;

    private final DashboardController dashboardController;

    private final ProductService productService;
    private final UploadService uploadService;

    public ProductController(ProductService productService, UploadService uploadService,
            DashboardController dashboardController, PasswordEncoder encoder) {
        this.productService = productService;
        this.uploadService = uploadService;
        this.dashboardController = dashboardController;
        this.encoder = encoder;
    }

    // Table Product
    @RequestMapping(value = "/admin/product", method = RequestMethod.GET)
    public String getTableProductPage(Model model) {
        List<Product> productList = this.productService.getAllProducts();
        model.addAttribute("productList", productList);
        return "admin/product/show";
    }

    // Detail Product
    @RequestMapping(value = "/admin/product/detail/{id}", method = RequestMethod.GET)
    public String getDetailProductPage(Model model, @PathVariable long id) {
        Product product = this.productService.getProductById(id);
        model.addAttribute("product", product);
        return "admin/product/detail";
    }

    // Before update
    @RequestMapping(value = "/admin/product/update/{id}", method = RequestMethod.GET)
    public String getUpdateProductPage(Model model, @PathVariable long id) {
        Product product = this.productService.getProductById(id);
        model.addAttribute("product", product);
        return "admin/product/update";
    }

    // After Update
    @RequestMapping(value = "admin/product/update", method = RequestMethod.POST)
    public String getTableProductAfterUpdate(Model model, @ModelAttribute("product") @Valid Product product,
            BindingResult productUpdatedBindingResult, @RequestParam("productFile") MultipartFile productFile) {

        if (productUpdatedBindingResult.hasErrors())
            return "admin/product/update";

        Product productUpdated = this.productService.getProductById(product.getId());
        if (productUpdated != null) {
            productUpdated.setName(product.getName());
            productUpdated.setPrice(product.getPrice());
            productUpdated.setDetailDesc(product.getDetailDesc());
            productUpdated.setShortDesc(product.getShortDesc());
            productUpdated.setQuantity(product.getQuantity());
            productUpdated.setQuantity(product.getQuantity());
            productUpdated.setFactory(product.getFactory());
            productUpdated.setTarget(product.getTarget());

            if (!productFile.isEmpty()) {
                this.uploadService.handleDeleteFile(productUpdated.getImage(), "product");
                String productFileName = this.uploadService.handleSaveUploadFile(productFile, "product");
                productUpdated.setImage(productFileName);
            }

            this.productService.handleSaveProduct(productUpdated);
        }
        return "redirect:/admin/product";
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
        Product productDeleted = this.productService.getProductById(product.getId());
        this.uploadService.handleDeleteFile(productDeleted.getImage(), "product");
        this.productService.deleteProductByID(productDeleted.getId());
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
    public String getTableProductPageAfterCreate(Model model, @ModelAttribute("newProduct") @Valid Product product,
            BindingResult newProductBindingResult,
            @RequestParam("productFile") MultipartFile productFile) {

        // List<FieldError> errors = newProductBindingResult.getFieldErrors();
        // for (FieldError error : errors) {
        // System.out.println("------------------" + error.getField() + " - " +
        // error.getDefaultMessage());
        // }

        if (newProductBindingResult.hasErrors())
            return "admin/product/create";

        String productFileName = this.uploadService.handleSaveUploadFile(productFile, "product");
        product.setImage(productFileName);
        this.productService.handleSaveProduct(product);
        return "redirect:/admin/product";
    }
}
