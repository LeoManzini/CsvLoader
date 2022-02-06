package br.com.leomanzini.products.store.servlet;

import java.io.IOException;

import br.com.leomanzini.products.store.utils.SystemMessages;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;


@WebServlet("/upload_file")
@MultipartConfig(
		location = "D:\\git\\CsvLoader\\documents",
		fileSizeThreshold = 1024 * 1024, // 1Mb
		maxFileSize = 1024 * 1024 * 10,  // 10Mb
		maxRequestSize = 1024 * 1024 * 11 // 11Mb
		)
public class FileUploadServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public FileUploadServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Part part = request.getPart("file");
			part.write(getFileName(part));
			request.setAttribute("message", SystemMessages.FILE_UPLOAD_SUCCESS.getMessage());
		} catch (Exception e) {
			request.setAttribute("message", SystemMessages.FILE_UPLOAD_ERROR.getMessage());
		}	
		request.getRequestDispatcher("message.jsp").forward(request, response);
	}
	
	private String getFileName(Part filePart) {
		String contentDisposition = filePart.getHeader("content-disposition");
		
		if(!contentDisposition.contains("filename=")) {
			return null;
		}
		
		int beginIndex = contentDisposition.indexOf("filename=") + 10;
		int endIndex = contentDisposition.length() - 1;
		
		return contentDisposition.substring(beginIndex, endIndex);
	}
}
