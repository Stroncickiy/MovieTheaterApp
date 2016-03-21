package com.epam.moovies.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.moovies.model.Ticket;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class TicketsPdfView extends AbstractPdfView {

	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {

		@SuppressWarnings("unchecked")
		List<Ticket> tickets = (ArrayList<Ticket>) model.get("tickets");
		Font fontTitle = new Font(FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.BLACK);

		PdfPTable ticketsPdfTable = new PdfPTable(8);
		document.add(new Paragraph("Recommended books for Spring framework", fontTitle));

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

	}

}