import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SmartDoorlocksList")

public class SmartDoorlocksList extends HttpServlet {

	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
		String CategoryName = request.getParameter("maker");
        

		/* Checks the Tablets type whether it is microsft or sony or nintendo */

		HashMap<String, SmartDoorlocks> hm = new HashMap<String, SmartDoorlocks>();
		if(CategoryName==null){
			hm.putAll(SaxParserDataStore.sdls);
			name = "";
		}
		else
		{
		   if(CategoryName.equals("Eufy"))
		   {
			 for(Map.Entry<String,SmartDoorlocks> entry : SaxParserDataStore.sdls.entrySet())
			 {
				if(entry.getValue().getRetailer().equals("Eufy"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
			 }
				name = "Eufy";
		   }
		   else if(CategoryName.equals("Miumaelv"))
		    {
			for(Map.Entry<String,SmartDoorlocks> entry : SaxParserDataStore.sdls.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("Miumaelv"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
				 name = "Miumaelv";
			}
			else if(CategoryName.equals("TEEHO"))
		    {
			for(Map.Entry<String,SmartDoorlocks> entry : SaxParserDataStore.sdls.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("TEEHO"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
				 name = "TEEHO";
			}
			 

	
		}


		Utilities utility = new Utilities(request,pw);
		utility.printHtml("header.html");
		utility.printHtml("LeftNavBar.html");
		pw.print("<div id='container'>");
		pw.print("<a style='font-size: 24px;'>"+name+" SmartDoorlocks</a>");
		pw.print("</h2><div class='entry d-flex'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, SmartDoorlocks> entry : hm.entrySet())
		{
			SmartDoorlocks sdl = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+sdl.getName()+"</h3>");
			pw.print("<strong>$"+sdl.getPrice()+"</strong><ul>");
			pw.print("<li id='item'><img class='object-fit-contain' src='images/Smart Doorlock/"+sdl.getImage()+"' width='180px' height='180px' alt='' /></li>");
			
			pw.print("<form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='sdls'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form>");
			
			pw.print("</ul></div></td></tr>");
			// if(i%3==0 || i == size) pw.print("</tr>");
			i++;
		}	
		pw.print("</table></div></div></div>");
   
		utility.printHtml("footer.html");
		
	}
}
