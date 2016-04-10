package com.epam.movies.util;

import com.epam.movies.model.Ticket;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class TicketToPdfConverter {


    public static byte[] buildDocumentWithTickets(List<Ticket> tickets) throws DocumentException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Document document = new Document();
        // Apply preferences and build metadata.
        PdfWriter writer = PdfWriter.getInstance(document, baos);
        writer.setViewerPreferences(PdfWriter.ALLOW_PRINTING | PdfWriter.PageLayoutSinglePage);
        // Build PDF document.
        writer.setInitialLeading(16);
        document.open();
        Font fontTitle = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.BLACK);

        PdfPTable ticketsPdfTable = new PdfPTable(8);
        document.add(new Paragraph("Requested Tickets", fontTitle));

        ticketsPdfTable.setWidthPercentage(100.0f);
        ticketsPdfTable.setSpacingBefore(10);

        ticketsPdfTable.addCell("id");
        ticketsPdfTable.addCell("customer");
        ticketsPdfTable.addCell("event");
        ticketsPdfTable.addCell("seats");
        ticketsPdfTable.addCell("totalPrice");
        ticketsPdfTable.addCell("realPrice");
        ticketsPdfTable.addCell("discountStrategy");
        ticketsPdfTable.addCell("discountAmount");

        for (Ticket ticket : tickets) {
            ticketsPdfTable.addCell(String.valueOf(ticket.getId()));
            ticketsPdfTable.addCell(ticket.getCustomer().getFirstName() + " " + ticket.getCustomer().getLastName());
            ticketsPdfTable.addCell(ticket.getEvent().getName());
            ticketsPdfTable.addCell(ticket.getBookedSeats().toString());
            ticketsPdfTable.addCell(String.valueOf(ticket.getTotalPrice()));
            ticketsPdfTable.addCell(String.valueOf(ticket.getRealPrice()));
            ticketsPdfTable.addCell(ticket.getDiscountStrategy().name());
            ticketsPdfTable.addCell(String.valueOf(ticket.getDiscountAmount()));
        }

        document.add(ticketsPdfTable);
        document.close();
        return baos.toByteArray();

    }

    public static byte[] buildDocumentWithTicket(Ticket ticket) throws DocumentException {
        ArrayList<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket);
        return buildDocumentWithTickets(tickets);
    }
}
