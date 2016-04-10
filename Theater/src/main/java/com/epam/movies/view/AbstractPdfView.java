package com.epam.movies.view;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.Map;

public abstract class AbstractPdfView extends AbstractView {

	
	public AbstractPdfView() {
		setContentType("application/pdf");
	}
	@Override
	protected boolean generatesDownloadContent() {
		return true;
	}


	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception  {
		// IE workaround: write into byte array first.
				ByteArrayOutputStream baos = createTemporaryOutputStream();

				// Apply preferences and build metadata.
				Document document = new Document();
				PdfWriter writer = PdfWriter.getInstance(document, baos);
		prepareWriter(writer);
				buildPdfMetadata(model, document, request);

				// Build PDF document.
				writer.setInitialLeading(16);
				document.open();
		buildPdfDocument(model, document, request, response);
				document.close();

				// Flush to HTTP response.
				writeToResponse(response, baos);

		
	}

	protected void prepareWriter(PdfWriter writer) {
		writer.setViewerPreferences(getViewerPreferences());
	}
	protected int getViewerPreferences() {
		return PdfWriter.ALLOW_PRINTING | PdfWriter.PageLayoutSinglePage;
	}
	protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {
	}

	protected abstract void buildPdfDocument(Map<String, Object> model, Document document,
											 HttpServletRequest request, HttpServletResponse response) throws Exception;




}
