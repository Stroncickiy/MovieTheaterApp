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
import com.itextpdf.text.pdf.PdfWriter;

public class TicketsPdfView extends AbstractPdfView {

	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest req, HttpServletResponse resp) throws Exception {

		// Get data "articles" from model
		@SuppressWarnings("unchecked")
		List<Ticket> tickets = (ArrayList<Ticket>) model.get("tickets");

		// Fonts
		Font fontTitle = new Font(FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.BLACK);
		Font fontTag = new Font(FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.WHITE);

	}

}