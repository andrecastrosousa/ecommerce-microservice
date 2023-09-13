package academy.mindswap.catalogservice.service;

import academy.mindswap.catalogservice.repository.CatalogRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class CatalogServiceUnitTest {
    @InjectMocks
    CatalogService catalogService;

    @Mock
    CatalogRepository catalogRepository;

}
