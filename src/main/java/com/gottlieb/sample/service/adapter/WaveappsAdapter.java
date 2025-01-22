package com.gottlieb.sample.service.adapter;

import com.gottlieb.sample.service.dto.InvoiceRequestDTO;
import com.gottlieb.sample.service.dto.InvoiceResponseDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class WaveappsAdapter implements InvoiceAdapter {

    private static final Logger logger = LoggerFactory.getLogger(WaveappsAdapter.class);

    @Value("${waveapps.api.url}")
    private String waveappsUrl;

    @Value("${waveapps.api.token}")
    private String apiToken;

    @Value("${waveapps.api.business-id}")
    private String businessId;

    @Override
    public Mono<InvoiceResponseDTO> createInvoice(InvoiceRequestDTO request) {
        logger.info("Creating invoice for request: {}", request);
      
        // String customerId = "QnVzaW5lc3M6MDE3NzQ3YTctM2E4Zi00OWU0LTg3MTMtMjVjMjcyOTQ5MjBlO0N1c3RvbWVyOjg0NjkxODUx"; 
        String customerId = request.getAccount().getGeneralLedgerId();

        // GraphQL.
        String query = """
            mutation ($input: InvoiceCreateInput!) {
              invoiceCreate(input: $input) {
                didSucceed
                inputErrors {
                  message
                  code
                  path
                }
                invoice {
                  id
                  pdfUrl
                }
              }
            }
        """;

        List<WaveappsItem> items = request.getItems().stream()
                .map(v2Item -> new WaveappsItem(
                        v2Item.getProduct().getGeneralLedgerId(),
                        v2Item.getQuantity(),
                        v2Item.getUnitPrice().doubleValue()
                ))
                .collect(Collectors.toList());

      // Right here is where we map our definition of an INvocice Request into what is expected by WaveApps. This is the whole purpose of the adapter (which is to âdapt^)
      Map<String, Object> input = Map.of(
        "businessId", businessId,
        "customerId", customerId,
        "items", items
         );
    

        // Payload final
        Map<String, Object> payload = Map.of("query", query, "variables", Map.of("input", input));
        logger.info("Payload enviado para WaveApps: {}", payload);

      //Initializes the response dto
        InvoiceResponseDTO businessResponse = new InvoiceResponseDTO();

      // Make the call to wave apps using regular http call (does not need to be reactor -use the non-reactive web client.
      // WebClient.ResponseSpec waveAppsRawResponse = createWebClient().post().bodyValue(payload).retrieve();

      return createWebClient().post().bodyValue(payload)
      .retrieve().bodyToMono(WaveappsCreateInvoiceResponse.class)
      .map(rawResponse -> {businessResponse.setInvoiceId("123456"); return businessResponse;});    



    }

    /**
     * Dynamically creates a WebClient instance.
     *
     * @return a new instance of WebClient
     */
    private WebClient createWebClient() {
        return WebClient.builder()
                .baseUrl(waveappsUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiToken)
                .build();
    }

    private class WaveappsItem{
        private String productID;
        private Integer quantity;
        private Double unitPrice;  

        private WaveappsItem(String productID, Integer quantity, Double unitPrice) {  
            this.productID = productID;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
        }

        public String getProductID() {
            return productID;
        }
        public void setProductID(String productID) {
            this.productID = productID;
        }
        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public Double getUnitPrice() {
            return unitPrice;
        }
        public void setUnitPrice(Double unitPrice) {
            this.unitPrice = unitPrice;
        }
    }
}
