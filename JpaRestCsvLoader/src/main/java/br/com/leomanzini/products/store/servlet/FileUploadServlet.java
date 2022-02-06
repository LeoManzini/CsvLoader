package br.com.leomanzini.products.store.servlet;

import java.io.IOException;

import br.com.leomanzini.products.store.exceptions.DatabaseRoutineException;
import br.com.leomanzini.products.store.services.FileUploadService;
import br.com.leomanzini.products.store.utils.SystemMessages;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/upload_file")
@MultipartConfig(location = "D:\\git\\CsvLoader\\documents", fileSizeThreshold = 1024 * 1024, // 1Mb
		maxFileSize = 1024 * 1024 * 10, // 10Mb
		maxRequestSize = 1024 * 1024 * 11 // 11Mb
)
public class FileUploadServlet extends HttpServlet {

	private static final long serialVersionUID = 8107752870322547158L;

	private final FileUploadService fileService = FileUploadService.getFileUploadServiceImplementation();

	public FileUploadServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Part part = request.getPart("file");
			if (!fileService.executeDatabaseRoutine(part.getInputStream())) {
				throw new DatabaseRoutineException(SystemMessages.DATABASE_ROUTINE_ERROR);
			}
			request.setAttribute("message", SystemMessages.FILE_UPLOAD_SUCCESS.getMessage());
		} catch (Exception e) {
			request.setAttribute("message", SystemMessages.FILE_UPLOAD_ERROR.getMessage());
		}
		request.getRequestDispatcher("message.jsp").forward(request, response);
	}
}
