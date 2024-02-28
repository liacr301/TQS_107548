package integration;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import connection.ISimpleHttpClient;
import connection.TqsBasicHttpClient;
import geocoding.AddressResolver;

public class AddressResolverIT {

    private AddressResolver addressResolver;

    private TqsBasicHttpClient client;

    @BeforeEach
    public void init(){
        addressResolver = new AddressResolver();
        client = new TqsBasicHttpClient;
    }


    @Test
    public void whenGoodCoordidates_returnAddress() throws IOException, URISyntaxException, ParseException {

        //todo

        // repeat the same tests conditions from AddressResolverTest, without mocks

        Optional<Address> result = resolver.findAddressForLocation(40.63436, -8.65616);
        Address expected = new Address( "Avenida da Universidade", "Aveiro","3810-489", "");

        assertTrue( result.isPresent());
        assertEquals( expected, result.get());

    }

    @Test
    public void whenBadCoordidates_thenReturnNoValidAddrress() throws IOException, URISyntaxException, ParseException {

        //todo
        // repeat the same tests conditions from AddressResolverTest, without mocks
        Optional<Address> result = resolver.findAddressForLocation( -361,-361);
        assertThrows(IllegalArgumentException.class, ()-> result, "No valid address");

    }

}
