package ru.nicoolas.warehouse.service.impl;

import org.junit.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.*;
import ru.nicoolas.warehouse.model.Socks;
import ru.nicoolas.warehouse.repository.SocksRepository;
import ru.nicoolas.warehouse.Exceptions.NotEnoughGoodsOnWarehouseException;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static ru.nicoolas.warehouse.TestConstants.Constants.*;

public class SocksServiceImplUnitTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    @Mock
    private SocksRepository socksRepository;

    @InjectMocks
    private SocksServiceImpl socksService;

    private Socks socks;

    @Before
    public void setUp() {
        socks = new Socks();
        socks.setColor(TEST_SOCKS_COLOR);
        socks.setCottonPart(TEST_SOCKS_COTTON_PART);
        socks.setQuantity(TEST_SOCKS_QUANTITY);
    }

    @Test
    public void addSocks_validInput_success() {
        when(socksRepository.getTotalQuantityByColorAndCottonPartEquals(anyString(), anyInt())).thenReturn(Optional.of(0));
        socksService.addSocks(socks);
        verify(socksRepository, times(TEST_RUN_COUNT)).save(socks);
        assertEquals(TEST_SOCKS_QUANTITY, (int) socks.getQuantity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addSocks_negativeQuantity_exception() {
        socks.setQuantity(ILLEGAL_QUANTITY);
        socksService.addSocks(socks);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addSocks_blankColor_exception() {
        socks.setColor(BLANK_COLOR);
        socksService.addSocks(socks);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addSocks_invalidCottonPart_exception() {
        socks.setCottonPart(ILLEGAL_COTTON_PART);
        socksService.addSocks(socks);
    }

    @Test
    public void deleteSocks_validInput_success() {
        when(socksRepository.getTotalQuantityByColorAndCottonPartEquals(anyString(), anyInt())).thenReturn(Optional.of(TEST_TWENTY));
        socksService.deleteSocks(socks);
        verify(socksRepository, times(TEST_RUN_COUNT)).save(socks);
        assertEquals(TEST_SOCKS_QUANTITY, (int) socks.getQuantity());
    }

    @Test(expected = NotEnoughGoodsOnWarehouseException.class)
    public void deleteSocks_notEnoughGoods_exception() {
        when(socksRepository.getTotalQuantityByColorAndCottonPartEquals(anyString(), anyInt())).thenReturn(Optional.of(TEST_FIVE));
        socksService.deleteSocks(socks);
    }

    @Test
    public void getTotalQuantity_moreThan_success() {
        when(socksRepository.getTotalQuantityByColorAndCottonPartGreaterThan(anyString(), anyInt())).thenReturn(Optional.of(TEST_TWENTY));
        int result = socksService.getTotalQuantity(TEST_SOCKS_COLOR, OPERATION_MORE, TEST_FORTY);
        assertEquals(TEST_TWENTY, result);
    }

    @Test
    public void getTotalQuantity_lessThan_success() {
        when(socksRepository.getTotalQuantityByColorAndCottonPartLessThan(anyString(), anyInt())).thenReturn(Optional.of(TEST_FIVE));
        int result = socksService.getTotalQuantity(TEST_SOCKS_COLOR, OPERATION_LESS, TEST_SIXTY);
        assertEquals(TEST_FIVE, result);
    }

    @Test
    public void getTotalQuantity_equal_success() {
        when(socksRepository.getTotalQuantityByColorAndCottonPartEquals(anyString(), anyInt())).thenReturn(Optional.of(TEST_TEN));
        int result = socksService.getTotalQuantity(TEST_SOCKS_COLOR, OPERATION_EQUAL, TEST_SOCKS_COTTON_PART);
        assertEquals(TEST_TEN, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getTotalQuantity_invalidOperation_exception() {
        socksService.getTotalQuantity("black", "invalidOperation", TEST_SOCKS_COTTON_PART);
    }
}