import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SmartDoorbellsList")

public class SmartDoorbellsList extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
		String CategoryName = request.getParameter("maker");
        


		HashMap<String, SmartDoorbells> hm = new HashMap<String, SmartDoorbells>();
		if(CategoryName==null){
			hm.putAll(SaxParserDataStore.wts);
			name = "";
		}
		else
		{
		   if(CategoryName.equals("Andoe"))
		   {
			 for(Map.Entry<String,SmartDoorbells> entry : SaxParserDataStore.wts.entrySet())
			 {
				if(entry.getValue().getRetailer().equals("Andoe"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
			 }
				name = "Andoe";
		   }
		   if(CategoryName.equals("Anker"))
		   {
			 for(Map.Entry<String,SmartDoorbells> entry : SaxParserDataStore.wts.entrySet())
			 {
				if(entry.getValue().getRetailer().equals("Anker"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
			 }
				name = "Anker";
		   }
		   if(CategoryName.equals("Kiplyki"))
		   {
			 for(Map.Entry<String,SmartDoorbells> entry : SaxParserDataStore.wts.entrySet())
			 {
				if(entry.getValue().getRetailer().equals("Kiplyki"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
			 }
				name = "Kiplyki";
		   }
		
		}


		Utilities utility = new Utilities(request,pw);
		utility.printHtml("header.html");
		utility.printHtml("LeftNavBar.html");
		pw.print("<div id='container'>");
		pw.print("<a style='font-size: 24px;'>"+name+" Smart Doorbells</a>");
		pw.print("</h2><div class='entry d-flex'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, SmartDoorbells> entry : hm.entrySet())
		{
			SmartDoorbells wt = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+wt.getName()+"</h3>");
			pw.print("<strong>$"+wt.getPrice()+"</strong><ul>");
			pw.print("<li id='item'><img class='object-fit-contain' src='images/Smart Doorbell/"+wt.getImage()+"' width='180px' height='180px' alt='' /></li>");
			
			pw.print("<form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='wts'>"+
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
