import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SmartLightingsList")

public class SmartLightingsList extends HttpServlet {

	/* Console Page Displays all the Consoles and their Information in Game Speed */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
		String CategoryName = request.getParameter("maker");
        

		/* Checks the Tablets type whether it is microsft or sony or nintendo */

		HashMap<String, SmartLightings> hm = new HashMap<String, SmartLightings>();
		if(CategoryName==null){
			hm.putAll(SaxParserDataStore.sls);
			name = "";
		}
		else
		{
		   if(CategoryName.equals("Feit"))
		   {
			 for(Map.Entry<String,SmartLightings> entry : SaxParserDataStore.sls.entrySet())
			 {
				if(entry.getValue().getRetailer().equals("Feit"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
			 }
				name = "Feit";
		   }

			else if(CategoryName.equals("FLSNT"))
			{
				for(Map.Entry<String,SmartLightings> entry : SaxParserDataStore.sls.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("FLSNT"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "FLSNT";
			}
			else if(CategoryName.equals("GE"))
			{
				for(Map.Entry<String,SmartLightings> entry : SaxParserDataStore.sls.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("GE"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "GE";
			}
		}


		Utilities utility = new Utilities(request,pw);
		utility.printHtml("header.html");
		utility.printHtml("LeftNavBar.html");
		pw.print("<div id='container'>");
		pw.print("<a style='font-size: 24px;'>"+name+" SmartLightings</a>");
		pw.print("</h2><div class='entry d-flex'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, SmartLightings> entry : hm.entrySet())
		{
			SmartLightings SmartLightings = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+SmartLightings.getName()+"</h3>");
			pw.print("<strong>$"+SmartLightings.getPrice()+"</strong><ul>");
			pw.print("<li id='item'><img class='object-fit-contain' src='images/Smart Lighting/"+SmartLightings.getImage()+"' width='180px' height='180px' alt='' /></li>");
			
			pw.print("<form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='SmartLightings'>"+
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
