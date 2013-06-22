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
 * Servlet implementation class PrintTableServ
 */
@WebServlet("/PrintTableServ")
public class PrintTableServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_BUFFER_SIZE=4098;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrintTableServ() {
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
		String idsToPrint=request.getParameter("idsToPrintTable");
		
		if(idsToPrint==null || idsToPrint.length()==0){
			response.sendRedirect("home.jsp");
			return;
		}
		
		Connection connection=DbManager.getConnection();
		MemberDbHelper memberDb=new MemberDbHelper(connection);
		
		List<Member> addresses = memberDb.getPrintTableDetails(idsToPrint);
		
		//File htmlFile=new File(".\\palhaveli\\printtable.html");
		File htmlFile=new File("./palhaveli/printtable.html");
		
		htmlFile.delete();
		FileWriter fileWriter=new FileWriter(htmlFile);
		fileWriter.append("<html>");
		/*fileWriter.append("<head>");
		fileWriter.append("<style type=\"text/css\" title=\"currentStyle\">\n");				
		fileWriter.append("@import \"css/jquery.dataTables_themeroller.css\";\n");			
		fileWriter.append("@import \"css/demo_table.css\";\n");
		fileWriter.append("@import \"css/TableTools.css\";\n");
		fileWriter.append("</style>\n");
		fileWriter.append("<script type=\"text/javascript\" charset=\"utf-8\" src=\"js/jquery.js\"></script>\n");
		fileWriter.append("<script type=\"text/javascript\" charset=\"utf-8\" src=\"js/jquery.dataTables.js\"></script>\n");

		fileWriter.append("<script type=\"text/javascript\" charset=\"utf-8\" src=\"js/ZeroClipboard.js\"></script>\n");
		fileWriter.append("<script type=\"text/javascript\" charset=\"utf-8\" src=\"js/TableTools.min.js\"></script>\n");

		fileWriter.append("<link href=\"css/google.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
		fileWriter.append("<link href=\"css/style.css\" rel=\"stylesheet\" type=\"text/css\" media=\"screen\" />\n");

		fileWriter.append("<script type=\"text/javascript\">\n");
		
		fileWriter.append("$(document).ready(function() {\n");
		fileWriter.append("var oTable = $('#data').dataTable({\"bFilter\": false});\n");			
		fileWriter.append("} );\n");
		fileWriter.append("</script></head>\n");*/
		fileWriter.append("<body><table width=\"100%\" border=\"1\" id=\"data\" style=\"border: 1px solid black; border-collapse: collapse;\">");		
		
		fileWriter.append("<thead>\n");
		fileWriter.append("<tr>\n");
		fileWriter.append("    <th width=\"25%\">Name</th>\n");            
		fileWriter.append("    <th width=\"20%\">Mobile</th>\n");
		fileWriter.append("    <th width=\"55%\">Address</th>    \n");        						            
		fileWriter.append("</tr>\n");
		fileWriter.append("</thead>  \n");      
		fileWriter.append("<tbody>\n");
		
		for(Member address : addresses){			
			fileWriter.append("<tr>");
						
			fileWriter.append("<td width=\"25%\" style=\"padding-top: .5em; padding-bottom: .5em; padding-left: 0.4cm;\">");
			fileWriter.append(address.getFirstName());
			fileWriter.append("</td>");
			
			fileWriter.append("<td width=\"20%\" style=\"padding-top: .5em; padding-bottom: .5em; text-align: center;\">");
			fileWriter.append(address.getMobile());
			fileWriter.append("</td>");
			
			fileWriter.append("<td width=\"55%\" style=\"padding-top: .5em; padding-bottom: .5em; padding-left: 0.4cm;\">");
			fileWriter.append("<table width=\"100%\">");
			
			fileWriter.append("<tr><td>");
			fileWriter.append(address.getFlatApt()+address.getApartment());
			fileWriter.append("</td></tr>");
			
			fileWriter.append("<tr><td>");
			fileWriter.append(address.getLandmark());
			fileWriter.append("</td></tr>");
			
			fileWriter.append("<tr><td>");
			fileWriter.append(address.getRoad()+" "+address.getRegion()+" "+address.getCity()+address.getPincode());
			fileWriter.append("</td></tr>");			
			
			/*fileWriter.append("<tr><td>");
			fileWriter.append(address.getCity()+address.getPincode());
			fileWriter.append("</td></tr>");*/
			
			fileWriter.append("</table>");
			fileWriter.append("</td>");	
			
			fileWriter.append("</tr>");
		}
		
		fileWriter.append("</tbody></table></body></html>");
		
		fileWriter.close();
		
		//Prince prince=new Prince("C:\\Program Files\\Prince\\Engine\\bin\\prince.exe");
		Prince prince=new Prince("/usr/bin/prince");
		prince.setHTML(true);
		//prince.addStyleSheet("C:\\pdftablestyle.css");
		prince.addStyleSheet("/home/raseshmori/pdftablestyle.css");
		
		//boolean isConvert=prince.convert(".\\palhaveli\\printtable.html","PrintTable.pdf");
		boolean isConvert=prince.convert("./palhaveli/printtable.html","PrintTable.pdf");
		if(!isConvert){
			response.sendRedirect("home.jsp?errorMsg=2");
		}
		
		response.setContentType("application/pdf");
		File file=new File("PrintTable.pdf");
		
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
