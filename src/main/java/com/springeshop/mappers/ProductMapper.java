package com.springeshop.mappers;

import com.springeshop.data.domain.Category;
import com.springeshop.data.domain.Manufacturer;
import com.springeshop.data.domain.Product;
import com.springeshop.data.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "manufacturer", source = "manufacturer", qualifiedByName = "mapStringToManufacturer")
    @Mapping(target = "category", source = "category", qualifiedByName = "mapStringToCategory")
    Product toProduct(ProductDTO dto);


    @Mapping(target = "id", ignore = true)
    @Named("mapStringToManufacturer")
    default Manufacturer mapManufacturer(String value) {
        return Manufacturer.builder().name(value).build();
    }

    @Named("mapManufacturerToString")
    default String mapManufacturer(Manufacturer value) {
        return value.getName();
    }

    @Named("mapStringToCategory")
    default Category mapCategory(String value) {
        return Category.builder().name(value).build();
    }

    @Named("mapCategoryToString")
    default String mapCategory(Category value) {
        return value.getName();
    }

    @Mapping(target = "manufacturer", source = "manufacturer", qualifiedByName = "mapManufacturerToString")
    @Mapping(target = "category", source = "category", qualifiedByName = "mapCategoryToString")
    ProductDTO toProductDTO(Product product);

    List<Product> toProductList(List<ProductDTO> dtoList);

    List<ProductDTO> toProductDTOList(List<Product> productList);
}
