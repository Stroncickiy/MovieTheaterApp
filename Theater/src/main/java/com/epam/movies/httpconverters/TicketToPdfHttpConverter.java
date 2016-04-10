package com.epam.movies.httpconverters;

import com.epam.movies.model.Ticket;
import com.epam.movies.util.TicketToPdfConverter;
import com.itextpdf.text.DocumentException;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Component
public class TicketToPdfHttpConverter extends AbstractHttpMessageConverter {
    public TicketToPdfHttpConverter() {
        super(new MediaType("application", "pdf"));
    }


    @Override
    protected boolean supports(Class clazz) {
        if (clazz.equals(ArrayList.class)) {
            try {
                Field data = clazz.getField("data");
                Type genericType = data.getGenericType();
                return Ticket.class.getTypeName().equals(genericType.getTypeName());
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return clazz.equals(Ticket.class);
    }

    @Override
    protected boolean canRead(MediaType mediaType) {
        return false;
    }

    @Override
    protected Object readInternal(Class clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    protected void writeInternal(Object o, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        byte[] resultResponse = new byte[0];
        if (o instanceof Ticket) {
            Ticket ticket = (Ticket) o;
            try {
                resultResponse = TicketToPdfConverter.buildDocumentWithTicket(ticket);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        } else {
            List<Ticket> tickets = (List<Ticket>) o;
            try {
                resultResponse = TicketToPdfConverter.buildDocumentWithTickets(tickets);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
        outputMessage.getBody().write(resultResponse);
    }
}
