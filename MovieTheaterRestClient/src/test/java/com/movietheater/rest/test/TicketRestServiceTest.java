package com.movietheater.rest.test;

import com.epam.movies.model.Ticket;
import com.epam.movietheater.configuration.AppConfig;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {AppConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TicketRestServiceTest {


    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void getTicketsJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<List<Ticket>> listResponseEntity = restTemplate.exchange("http://localhost:8080/rest/ticket", HttpMethod.GET, entity, new ParameterizedTypeReference<List<Ticket>>() {
        });

        System.out.println(" TESTING Tickets REST controller TEST 1");
        System.out.println(" Testing Accept JSON");
        List<Ticket> tickets = listResponseEntity.getBody();
        System.out.println("Found " + tickets.size() + " tikets from REST call ");
        for (Ticket ticket : tickets) {
            System.out.println(ticket);
        }

    }


    @Test
    public void getTicketsPDF() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType("application", "pdf")));
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<Void> forEntity = restTemplate.exchange("http://localhost:8080/rest/ticket", HttpMethod.GET, entity, Void.class);
        MediaType contentType = forEntity.getHeaders().getContentType();
        System.out.println(" TESTING Tickets REST controller TEST 2");
        System.out.println(" Testing Accept PDF");
        System.out.println(" Content type of retrieved data is " + contentType);
    }


}
