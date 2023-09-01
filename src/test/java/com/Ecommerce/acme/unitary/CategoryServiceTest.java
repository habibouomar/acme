package com.Ecommerce.acme.unitary;

import com.Ecommerce.acme.model.Category;
import com.Ecommerce.acme.repository.CategoryRepository;
import com.Ecommerce.acme.service.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest{

        @Mock
        CategoryRepository repository;

        @InjectMocks
        CategoryService service;

        @Captor
        private ArgumentCaptor<Category> categoryArgumentCaptor;

        @Test
        public void testGetCategoryById(){
            Category category = new Category();
            category.setId_category(1);

            Optional result = Optional.of(category);

            when(repository.findById(1)).thenReturn(result);
            Assertions.assertEquals(result, service.getCategory(1));
        }

         @Test
         public void testGetAllCategories(){
            Category category = new Category();
            List<Category> categoriesList = new ArrayList<>();
            categoriesList.add(category);

            when(repository.findAll()).thenReturn(categoriesList);
            assertEquals(categoriesList, service.getAllCategory());
         }

        @Test
        public void testDeleteCategory() {
            int categoryIdToDelete = 1;

            service.deleteCategory(categoryIdToDelete);
            verify(repository).deleteById(categoryIdToDelete);
        }

        @Test
        public void testInsertCategory() {
            Category category = new Category();
            category.setId_category(1);
            category.setName("Sample Category");

            service.insertCategory(category);

            verify(repository).save(categoryArgumentCaptor.capture());

            Category capturedCategory = categoryArgumentCaptor.getValue();

            assertEquals(category, capturedCategory);
         }
}
