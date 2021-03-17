package com.sassacakes.sales.product.service;

import com.sassacakes.sales.core.dto.UploadFileRequest;
import com.sassacakes.sales.core.error.SassaCakesError;
import com.sassacakes.sales.core.service.S3Service;
import com.sassacakes.sales.product.converter.ProductConverter;
import com.sassacakes.sales.product.dto.CreateProductRequest;
import com.sassacakes.sales.product.dto.ProductDto;
import com.sassacakes.sales.product.model.Category;
import com.sassacakes.sales.product.model.Product;
import com.sassacakes.sales.product.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private MessageSourceAccessor message;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductConverter productConverter;
    @Autowired
    private S3Service s3Service;

    @Value("${s3.bucket}")
    private String bucketName;
    @Value("${s3.product.folder}")
    private String bucketFolder;

    @Value("${img.prefix.product}")
    private String prefix;


    public Product findById(Integer id) {
        return productRepository.findById(id).orElseThrow(() ->
                SassaCakesError.PRODUCT_NOT_FOUND.asNotFoundException(message,id));
    }
    public Product save(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product createProduct(CreateProductRequest request) {
        LOGGER.info("Verificando categoria. Categoria: [{}]", request.getDescription());


        Category category = categoryService.findByName(request.getCategory())
                .orElse(new Category(request.getCategory()));

        Product product = productConverter.convert(request, category);
        category.getProducts().add(product);
        categoryService.save(category);
        return save(product);
    }

    public Product uploadImage(Integer productId, MultipartFile file) throws IOException {
        Product product = this.findById(productId);
        return this.uploadImageProduct(product, file);
    }

    private Product uploadImageProduct(Product product, MultipartFile multipartFile) throws IOException {

        UploadFileRequest request = new UploadFileRequest(multipartFile, this.bucketName);
        request.setFolder(this.bucketFolder);
        request.setFileName(this.prefix + product.getId());

        URI uri = s3Service.uploadFile(request);

        product.setImageUrl(uri.toString());
        return this.save(product);

    }

}
