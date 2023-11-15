package rs.beograd;

import org.junit.jupiter.api.Test;
import rs.beograd.dto.FlatInfo;
import rs.beograd.parsers.CityExpertFlatInfoParser;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CityExpertFlatInfoParserTest {
    @Test
    void cityExpertParser() {
        Set<FlatInfo> flats = new CityExpertFlatInfoParser().getFlats();
        assertNotNull(flats);
    }
}