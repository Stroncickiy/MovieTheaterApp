package com.epam.movies.view;

import com.epam.movies.model.Ticket;
import com.epam.movies.util.TicketToPdfConverter;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

public class TicketsPdfView extends AbstractView {


    public TicketsPdfView() {
        setContentType("application/pdf");
    }

    @Override
    protected boolean generatesDownloadContent() {
        return true;
    }


    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // IE workaround: write into byte array first.
        ByteArrayOutputStream temporaryOutputStream = createTemporaryOutputStream();
        List<Ticket> tickets = (List<Ticket>) model.get("tickets");
        byte[] bytes = TicketToPdfConverter.buildDocumentWithTickets(tickets);
        temporaryOutputStream.write(bytes);
        // Flush to HTTP response.
        writeToResponse(response, temporaryOutputStream);
    }


}
