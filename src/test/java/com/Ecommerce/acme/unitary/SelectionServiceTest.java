package com.Ecommerce.acme.unitary;

import com.Ecommerce.acme.model.Selection;
import com.Ecommerce.acme.repository.SelectionRepository;
import com.Ecommerce.acme.service.SelectionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SelectionServiceTest {

    @Mock
    SelectionRepository repository;

    @InjectMocks
    SelectionService service;

    @Test
    public void mustToSendTheCorrespondingAllSelections(){
        Selection selection = new Selection();
        List<Selection> selectionsList = new ArrayList<>();
        selectionsList.add(selection);

        when(repository.findAll()).thenReturn(selectionsList);
        assertEquals(selectionsList, service.getAllSelection());
    }
}