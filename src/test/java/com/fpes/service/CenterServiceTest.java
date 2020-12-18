package com.fpes.service;

import com.fpes.exception.EntityNotActiveException;
import com.fpes.model.Center;
import com.fpes.repository.CenterRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CenterServiceTest {

    @InjectMocks
    private CenterService service;
    @Mock
    private CenterRepository repository;

    @Test
    void whenGetActiveCenter_thenReturnOptional() {
        //given
        Center center = new Center();
        center.setIsActive(true);
        Optional<Center> expected = Optional.of(center);
        when(repository.findCenterById(anyLong())).thenReturn(expected);

        //when
        Center actual = service.findSingleCenter(1L);

        //then
        assertEquals(expected, Optional.of(actual));
    }

    @Test
    void whenGetInactiveCenter_thenReturnOptional() {
        //given
        Center center = new Center();
        center.setIsActive(false);
        Optional<Center> expected = Optional.of(center);
        when(repository.findCenterById(anyLong())).thenReturn(expected);

        //when
        Executable whenFindSingleCenter = () -> service.findSingleCenter(1L);

        //then
        assertThrows(EntityNotActiveException.class, whenFindSingleCenter);
    }
}
