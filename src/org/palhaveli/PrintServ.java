package org.palhaveli;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.palhaveli.db.DbManager;
import org.palhaveli.db.MemberDbHelper;
import org.palhaveli.model.Member;

import com.princexml.Prince;

/**
 * Servlet implementation class PrintServ
 */
@WebServlet("/PrintServ")
public class PrintServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_BUFFER_SIZE=4098;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrintServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("home.jsp");
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idsToPrint=request.getParameter("idsToPrint");
		
		if(idsToPrint==null || idsToPrint.length()==0){
			response.sendRedirect("home.jsp");
			return;
		}
		
		Connection connection=DbManager.getConnection();
		MemberDbHelper memberDb=new MemberDbHelper(connection);
		
		List<Member> addresses = memberDb.getPrintDetails(idsToPrint);
		
		File htmlFile=new File("printfile.html");
		
		htmlFile.delete();
		FileWriter fileWriter=new FileWriter(htmlFile);
		fileWriter.append("<html><body><table width=\"100%\" border=\"1\">");
		
		int rowAddressCount=0;
		
		for(Member address : addresses){
			if(rowAddressCount == 0)
				fileWriter.append("<tr>");			
			
			rowAddressCount++;
			if(rowAddressCount==4)
				rowAddressCount=0;
			
			fileWriter.append("<td width=\"25%\">");
			fileWriter.append("<table>");
			
			fileWriter.append("<tr><td>");
			fileWriter.append(address.getFirstName());
			fileWriter.append("</td></tr>");
			
			fileWriter.append("<tr><td>");
			fileWriter.append(address.getFlatApt()+address.getApartment());
			fileWriter.append("</td></tr>");
			
			fileWriter.append("<tr><td>");
			fileWriter.append(address.getLandmark());
			fileWriter.append("</td></tr>");
			
			fileWriter.append("<tr><td>");
			fileWriter.append(address.getRoad()+" "+address.getRegion());
			fileWriter.append("</td></tr>");			
			
			fileWriter.append("<tr><td>");
			fileWriter.append(address.getCity()+address.getPincode());
			fileWriter.append("</td></tr>");
			
			fileWriter.append("</table>");
			fileWriter.append("</td>");
			
			if(rowAddressCount == 0)
				fileWriter.append("</tr>");									
		}
		
		fileWriter.append("</table></body></html>");
		
		fileWriter.close();
		
		Prince prince=new Prince("C:\\Program Files\\Prince\\Engine\\bin\\prince.exe");
		prince.setHTML(true);
		prince.addStyleSheet("C:\\pdfstyle.css");
		
		boolean isConvert=prince.convert("printfile.html","Print.pdf");
		if(!isConvert){
			response.sendRedirect("home.jsp?errorMsg=2");
		}
		
		response.setContentType("application/pdf");
		File file=new File("Print.pdf");
		
		String contentType = getServletContext().getMimeType(file.getName());

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        response.reset();
        response.setBufferSize(DEFAULT_BUFFER_SIZE);
        response.setContentType(contentType);
        response.setHeader("Content-Length", String.valueOf(file.length()));
        response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
        
        BufferedInputStream input = null;
        BufferedOutputStream output = null;

        try {
            // Open streams.
            input = new BufferedInputStream(new FileInputStream(file), DEFAULT_BUFFER_SIZE);
            output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);

            // Write file contents to response.
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
        } finally {
            // Gently close streams.
            close(output);
            close(input);
        }
	}
	
	private static void close(Closeable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (IOException e) {
                // Do your thing with the exception. Print it, log it or mail it.
                e.printStackTrace();
            }
        }
    }
}
