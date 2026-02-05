package dev.sakshijoshi.productcatalogservice.client;

import dev.sakshijoshi.productcatalogservice.dtos.FakestoreProductDTO;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class FakeStoreAPIClient {

    private RestTemplate restTemplate;

    public FakeStoreAPIClient(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, HttpMethod.GET, requestCallback, responseExtractor, uriVariables);
    }

    public Boolean validateResponse(ResponseEntity<FakestoreProductDTO> fakeStoreProductDtoResponseEntity) {
        if (fakeStoreProductDtoResponseEntity.hasBody() &&
                fakeStoreProductDtoResponseEntity.getStatusCode().
                        equals(HttpStatusCode.valueOf(200))) {
            return true;
        }

        return false;
    }
}
