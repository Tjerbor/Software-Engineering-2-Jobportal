package de.hbrs.se2.womm.junit.services;

import de.hbrs.se2.womm.dtos.UnternehmenDTO;
import de.hbrs.se2.womm.entities.Nutzer;
import de.hbrs.se2.womm.entities.Unternehmen;
import de.hbrs.se2.womm.mapper.NutzerMapper;
import de.hbrs.se2.womm.mapper.UnternehmenMapper;
import de.hbrs.se2.womm.repositories.NutzerRepository;
import de.hbrs.se2.womm.repositories.UnternehmenRepository;
import de.hbrs.se2.womm.services.UnternehmenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class UnternehmenServiceTest {

    private UnternehmenService unternehmenService;
    private UnternehmenRepository unternehmenRepositoryMock;
    private NutzerRepository nutzerRepositoryMock;
    private UnternehmenMapper unternehmenMapper;
    private NutzerMapper nutzerMapper;

    private UnternehmenDTO unternehmenDTO;
    private Unternehmen unternehmen;

    @BeforeEach
    void setUp() {
        unternehmenRepositoryMock = mock(UnternehmenRepository.class);
        nutzerRepositoryMock = mock(NutzerRepository.class);
        unternehmenMapper = UnternehmenMapper.INSTANCE;
        nutzerMapper = NutzerMapper.INSTANCE;

        unternehmenService = new UnternehmenService(unternehmenRepositoryMock, nutzerRepositoryMock);

        unternehmenDTO = UnternehmenDTO.builder()
                .unternehmenId(1L).build();

        unternehmen = Unternehmen.builder()
                .unternehmenId(1).build();
    }

    @Test
    void testGetUnternehmenPerID() {
        long id = 1L;
        when(unternehmenRepositoryMock.findUnternehmenByUnternehmenId(id)).thenReturn(Optional.of(unternehmen));
        when(unternehmenMapper.unternehmenZuDTO(unternehmen)).thenReturn(unternehmenDTO);

        UnternehmenDTO result = unternehmenService.getUnternehmenPerID(id);
        assertNotNull(result);
        assertEquals(unternehmenDTO.getUnternehmenId(), result.getUnternehmenId());
    }

    @Test
    void testGetByNutzerId() {
        long nutzerId = 1L;
        when(unternehmenRepositoryMock.findUnternehmenByNutzer_NutzerId(nutzerId)).thenReturn(Optional.of(unternehmen));
        when(unternehmenMapper.unternehmenZuDTO(unternehmen)).thenReturn(unternehmenDTO);

        UnternehmenDTO result = unternehmenService.getByNutzerId(nutzerId);
        assertNotNull(result);
        assertEquals(1L, result.getUnternehmenId());
    }

    @Test
    void testGetUnternehmenDTOPerName() {
        String unternehmenName = "CompanyA";
        Unternehmen unternehmen1 = Unternehmen.builder()
                .unternehmenId(1).build();

        Unternehmen unternehmen2 = Unternehmen.builder()
                .unternehmenId(2).build();

        when(unternehmenRepositoryMock.findUnternehmenByNameIgnoreCaseContaining(unternehmenName))
                .thenReturn(Arrays.asList(unternehmen1, unternehmen2));

        when(unternehmenMapper.unternehmenZuDTO(unternehmen1)).thenReturn(unternehmenDTO);
        when(unternehmenMapper.unternehmenZuDTO(unternehmen2)).thenReturn(unternehmenDTO);

        List<UnternehmenDTO> result = unternehmenService.getUnternehmenDTOPerName(unternehmenName);
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testGetAll() {
        Unternehmen unternehmen1 = Unternehmen.builder()
                .unternehmenId(1).build();

        Unternehmen unternehmen2 = Unternehmen.builder()
                .unternehmenId(2).build();

        when(unternehmenRepositoryMock.findAll()).thenReturn(Arrays.asList(unternehmen1, unternehmen2));
        when(unternehmenMapper.unternehmenZuDTO(unternehmen1)).thenReturn(unternehmenDTO);
        when(unternehmenMapper.unternehmenZuDTO(unternehmen2)).thenReturn(unternehmenDTO);

        List<UnternehmenDTO> result = unternehmenService.getAll();
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testSaveUnternehmen() {
        when(nutzerMapper.nutzerDTOToNutzer(unternehmenDTO.getNutzer())).thenReturn(new Nutzer());
        when(unternehmenMapper.dtoZuUnternehmen(unternehmenDTO)).thenReturn(unternehmen);


        UnternehmenDTO unternehmenDTO = UnternehmenDTO.builder()
                .unternehmenId(1L).build();

        Nutzer nutzer = nutzerMapper.nutzerDTOToNutzer(unternehmenDTO.getNutzer());
        Unternehmen unternehmen = unternehmenMapper.dtoZuUnternehmen(unternehmenDTO);

        when(nutzerRepositoryMock.save(nutzer)).thenReturn(nutzer);
        when(unternehmenRepositoryMock.save(unternehmen)).thenReturn(unternehmen);

        unternehmenService.saveUnternehmen(unternehmenDTO);

        verify(nutzerRepositoryMock, times(1)).save(nutzer);
        verify(unternehmenRepositoryMock, times(1)).save(unternehmen);
    }
}
